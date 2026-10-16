const assert=require('node:assert/strict');const E=require('../src/engine.js');let checks=0;const ok=(v,m)=>{assert.ok(v,m);checks++};
const eq=(a,b,m)=>{assert.deepEqual(a,b,m);checks++};
for(const mode of ['classic','sprint','adventure','daily'])for(let stage=1;stage<=12;stage++){
 const b=E.board(mode,stage,'2026-10-16'),fromSet=new Set();eq(Math.sqrt(b.size)%1,0);for(const [from,to] of [...b.ladders,...b.snakes]){ok(from>1&&from<b.size&&to>1&&to<b.size,'jump location inside board');ok(from!==to);ok(!fromSet.has(from),'unique jump starts');fromSet.add(from);}for(const [from,to] of b.ladders)ok(from<to,'ladders go up');for(const [from,to] of b.snakes)ok(from>to,'snakes go down');
}
let g=E.createGame({count:4,humans:2});eq(g.players.length,4);eq(g.players.map(p=>p.type),['human','human','bot','bot']);
for(const [v,expected] of [[0,1],[.9999999,6],[.17,2],[.5,4]])eq(E.dice(()=>v),expected);
let r=E.roll(g,6);ok(r.extra);eq(g.turn,0);r=E.roll(g,6);eq(g.turn,0);r=E.roll(g,6);ok(r.forfeit);eq(g.turn,1);eq(g.players[0].pos,12);
g=E.createGame();g.players[0].pos=98;const near=E.roll(g,5);eq(near.to,98);ok(!near.jump,'overshoot cannot retrigger jump');eq(g.turn,1);
g=E.createGame();g.players[0].pos=2;r=E.roll(g,1);eq(r.jump.kind,'ladder');eq(r.to,22);eq(g.players[0].ladders,1);
g=E.createGame();g.players[0].pos=16;r=E.roll(g,1);eq(r.jump.kind,'snake');eq(r.to,6);
g=E.createGame();g.players[0].pos=99;r=E.roll(g,1);ok(r.win);eq(g.status,'finished');eq(g.winner,0);assert.throws(()=>E.roll(g,1));checks++;
g=E.createGame();const loaded=E.load(JSON.stringify(g));eq(loaded.players.map(p=>p.pos),[0,0]);assert.throws(()=>E.load('{"foo":2}'));checks++;
for(let i=0;i<300;i++){
 const modes=['classic','sprint','adventure','daily'];g=E.createGame({mode:modes[i%4],stage:1+i%12,count:2+i%3,humans:1});let turns=0;
 while(g.status==='playing'&&turns++<70000){const val=E.dice();const r=E.roll(g,val);ok(g.players.every(p=>p.pos>=0&&p.pos<=g.board.size&&Number.isInteger(p.pos)),'token remains on board');ok(g.turn>=0&&g.turn<g.players.length,'valid player');ok(g.moveCount===turns);if(r.win)ok(g.players[g.winner].pos===g.board.size,'win at last square');}
 ok(g.status==='finished','all simulated games have a winner');ok(turns<70000,'finite game');
}
for(let i=0;i<200;i++){
 const b=E.board('adventure',i%12+1);const c=E.board('adventure',i%12+1);eq(b,c,'same chapter deterministically generates same map');
}
console.log(`PASS ${checks.toLocaleString()} rules checks across 300 complete simulated matches`);
