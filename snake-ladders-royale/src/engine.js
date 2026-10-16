/* Royal Ascent game rules, original code. Runs in Node.js tests and offline browser. */
(function(root,factory){const api=factory();if(typeof module==='object'&&module.exports)module.exports=api;root.RoyaleEngine=api;})(typeof globalThis!=='undefined'?globalThis:this,function(){
'use strict';
const CLASSIC_LADDERS=[[3,22],[8,30],[21,42],[28,54],[36,57],[51,72],[71,92],[80,99]];
const CLASSIC_SNAKES=[[17,6],[34,13],[48,26],[62,41],[69,50],[87,66],[95,75],[98,79]];
const PALETTES=['#53e8d1','#ffbd67','#ec86df','#90b4ff'];
const CHAPTERS=[
 ['Garden Gate','Gentle start',4,4],['Moonlit Meadow','More climbs',5,5],['Coral Coast','Tricky trails',6,6],
 ['Amber Jungle','Watch your step',7,7],['Cloud Kingdom','Higher stakes',7,8],['Cobra Canyon','Risky shortcuts',7,9],
 ['Starlight Ridge','Sharp turns',8,9],['Golden Dunes','Hidden dangers',8,10],['Crystal Cove','No easy path',8,11],
 ['Dragon Valley','Brave the descent',8,12],['Crown Citadel','Royal challenge',8,13],['Celestial Peak','Final ascent',8,14]
];
function hashSeed(str){let h=2166136261;for(let i=0;i<str.length;i++){h^=str.charCodeAt(i);h=Math.imul(h,16777619);}return h>>>0;}
function rng(seed){let x=(seed>>>0)||1;return()=>{x^=x<<13;x^=x>>>17;x^=x<<5;return(x>>>0)/4294967296;};}
function generateJumps(size,seed,lc,sc){const random=rng(hashSeed('royal-v4-'+seed));const occupied=new Set([1,size]);const snakes=[],ladders=[];
  function take(kind,count){let loops=0;while(count>0&&loops++<9000){const from=2+Math.floor(random()*(size-3));const minSpan=Math.max(6,Math.floor(size*.12));const maxSpan=Math.max(minSpan+3,Math.floor(size*.38));const d=minSpan+Math.floor(random()*(maxSpan-minSpan+1));const to=kind==='ladder'?from+d:from-d;
      if(to<2||to>=size||occupied.has(from)||occupied.has(to))continue;
      if(kind==='ladder'&&from>size-8)continue;
      occupied.add(from);occupied.add(to);(kind==='ladder'?ladders:snakes).push([from,to]);count--;
  }}
  take('ladder',lc);take('snake',sc);
  return {ladders, snakes};
}
function board(mode='classic',stage=1,date){let size=100,jumps,name,subtitle;
 if(mode==='sprint'){size=64;jumps=generateJumps(size,81818,5,5);name='Lightning Sprint';subtitle='Reach square 64 to win';}
 else if(mode==='adventure'){const id=Math.max(1,Math.min(12,Number(stage)||1));const c=CHAPTERS[id-1];jumps=generateJumps(100,1961+id*7927,c[2],c[3]);name=c[0];subtitle='Chapter '+id+' · '+c[1];}
 else if(mode==='daily'){const d=date||today();jumps=generateJumps(100,'daily-'+d,7,8);name='Daily Quest';subtitle=d+' · same board for everyone on this device';}
 else{jumps={ladders:CLASSIC_LADDERS.map(x=>[...x]),snakes:CLASSIC_SNAKES.map(x=>[...x])};name='Royal Classic';subtitle='A race to the crown';mode='classic';}
 const seen=new Set();for(const [a,b] of [...jumps.snakes,...jumps.ladders]){if(a<=1||b<=1||a>=size||b>=size||seen.has(a))throw Error('Invalid jumps');seen.add(a);}
 return {size,mode,stage:Math.max(1,Math.min(12,Number(stage)||1)),date:date||today(),name,subtitle,ladders:jumps.ladders,snakes:jumps.snakes};
}
function today(dt=new Date()){const y=dt.getFullYear(),m=String(dt.getMonth()+1).padStart(2,'0'),d=String(dt.getDate()).padStart(2,'0');return `${y}-${m}-${d}`;}
function createGame(config={}){
 const mode=['classic','sprint','adventure','daily'].includes(config.mode)?config.mode:'classic';
 const count=Math.max(2,Math.min(4,Number(config.count)||2));const humans=Math.max(1,Math.min(count,Number(config.humans)||1));
 const players=Array.from({length:count},(_,i)=>({id:i,name:(config.names&&config.names[i])|| (i<humans?'Player '+(i+1):'Royal Bot '+(i+1-humans)),type:i<humans?'human':'bot',color:PALETTES[i],pos:0,snakes:0,ladders:0,rolls:0,sixes:0}));
 return {version:1,created:Date.now(),board:board(mode,config.stage||1,config.date),players,turn:0,consecutiveSixes:0,moveCount:0,status:'playing',winner:null,history:[],lastRoll:null,sound:config.sound!==false};
}
function dice(random=Math.random){const v=random();return Math.min(6,Math.max(1,Math.floor(v*6)+1));}
function roll(state,value){
 if(!state||state.status!=='playing')throw Error('Game is not active');
 if(!Number.isInteger(value)||value<1||value>6)throw Error('Dice must be between one and six');
 const p=state.players[state.turn],from=p.pos;const entry={id:state.moveCount+1,player:state.turn,value,from,to:from,landing:from,jump:null,forfeit:false,extra:false,win:false};
 p.rolls++;p.sixes+=(value===6?1:0);state.moveCount++;
 state.consecutiveSixes=value===6?state.consecutiveSixes+1:0;
 if(state.consecutiveSixes===3){entry.forfeit=true;state.consecutiveSixes=0;state.turn=(state.turn+1)%state.players.length;}
 else{
  let landing=from+value;if(landing>state.board.size)landing=from;
  entry.landing=landing;
  const jump=landing!==from?(state.board.ladders.find(([a])=>a===landing) || state.board.snakes.find(([a])=>a===landing)):null;
  if(jump){entry.jump={kind:jump[1]>jump[0]?'ladder':'snake',from:landing,to:jump[1]};landing=jump[1];if(entry.jump.kind==='ladder')p.ladders++;else p.snakes++;}
  p.pos=landing;entry.to=landing;
  if(landing===state.board.size){state.status='finished';state.winner=state.turn;entry.win=true;state.consecutiveSixes=0;}
  else if(value===6){entry.extra=true;}
  else{state.consecutiveSixes=0;state.turn=(state.turn+1)%state.players.length;}
 }
 state.lastRoll=value;state.history.push(entry);if(state.history.length>300)state.history.shift();return entry;
}
function snakeOrLadder(state,pos){const l=state.board.ladders.find(([a])=>a===pos);if(l)return {kind:'ladder',to:l[1]};const s=state.board.snakes.find(([a])=>a===pos);if(s)return {kind:'snake',to:s[1]};return null;}
function load(json){const val=typeof json==='string'?JSON.parse(json):json;if(!val||val.version!==1||!val.board||!Array.isArray(val.players)||val.players.length<2||val.players.length>4||!['playing','finished'].includes(val.status))throw Error('Unsupported saved match');
 const size=Number(val.board.size);if(![64,100].includes(size)||!Number.isInteger(val.turn)||val.turn<0||val.turn>=val.players.length||!val.players.every(p=>Number.isInteger(p.pos)&&p.pos>=0&&p.pos<=size&&['human','bot'].includes(p.type)))throw Error('Corrupted saved match');
 if(!Array.isArray(val.board.snakes)||!Array.isArray(val.board.ladders)||[...val.board.snakes,...val.board.ladders].some(x=>!Array.isArray(x)||x.length!==2||x.some(n=>!Number.isInteger(n)||n<2||n>=size)))throw Error('Corrupted board');
 return val;
}
return {CHAPTERS,PALETTES,board,createGame,roll,dice,rng,hashSeed,generateJumps,today,snakeOrLadder,load};
});
