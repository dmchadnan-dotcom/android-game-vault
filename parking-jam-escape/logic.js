/* Parking Jam Escape — original deterministic puzzle engine. No dependencies. */
(function (root, factory) { const api=factory(); if(typeof module==='object' && module.exports)module.exports=api; else root.ParkingEngine=api; })(typeof globalThis!=='undefined'?globalThis:this,function(){
'use strict';
const N=8, DIRS=['R','L','D','U'];
function rng(seed){ let s=seed>>>0||1; return function(){s^=s<<13;s^=s>>>17;s^=s<<5;return (s>>>0)/4294967296}; }
function hash(text){let h=2166136261;for(let i=0;i<text.length;i++){h^=text.charCodeAt(i);h=Math.imul(h,16777619)}return h>>>0}
function shuffle(a,r){for(let i=a.length-1;i>0;i--){const j=Math.floor(r()*(i+1));[a[i],a[j]]=[a[j],a[i]];}return a}
function coords(c){let o=[];for(let i=0;i<c.len;i++)o.push([c.x+(c.dir==='R'||c.dir==='L'?i:0),c.y+(c.dir==='U'||c.dir==='D'?i:0)]);return o}
function occupancy(cars){let occ=Array.from({length:N},()=>Array(N).fill(-1));for(let i=0;i<cars.length;i++)for(const [x,y] of coords(cars[i])){if(x<0||y<0||x>=N||y>=N||occ[y][x]!==-1)return null;occ[y][x]=i}return occ}
function blockers(cars,index,occ){const c=cars[index];if(!occ)occ=occupancy(cars);if(!occ)return[-2];let {x,y,len,dir}=c,dx=0,dy=0;
 if(dir==='R'){x+=len-1;dx=1}else if(dir==='L'){dx=-1}else if(dir==='D'){y+=len-1;dy=1}else{dy=-1}
 let bs=new Set();x+=dx;y+=dy;while(x>=0&&x<N&&y>=0&&y<N){const id=occ[y][x];if(id!==-1&&id!==index)bs.add(id);x+=dx;y+=dy}return [...bs];}
function analysis(cars){const occ=occupancy(cars);if(!occ)return{solvable:false,reason:'overlap'};const deps=cars.map((c,i)=>blockers(cars,i,occ));const indeg=deps.map(d=>d.length),ready=indeg.map((n,i)=>n===0?i:-1).filter(i=>i>=0),order=[];let maxdepth=0;const depth=Array(cars.length).fill(1);
 while(ready.length){const id=ready.shift();order.push(id);maxdepth=Math.max(maxdepth,depth[id]);for(let j=0;j<cars.length;j++)if(deps[j].includes(id)){indeg[j]--;depth[j]=Math.max(depth[j],depth[id]+1);if(indeg[j]===0)ready.push(j)}}
 return{solvable:order.length===cars.length,order,deps,maxdepth,blocked:deps.filter(d=>d.length).length,free:deps.filter(d=>!d.length).length,edges:deps.reduce((s,d)=>s+d.length,0),occ};}
function candidate(r,id){const len=r()<(id>11?.25:.13)?3:2,dir=DIRS[Math.floor(r()*4)],isH=dir==='R'||dir==='L';return{id,x:Math.floor(r()*(N-(isH?len:1)+1)),y:Math.floor(r()*(N-(isH?1:len)+1)),len,dir,color:Math.floor(r()*8)};}
function build(seed,level=1,opts={}){
 const r=rng(seed),tier=Math.min(5,Math.floor((level-1)/20)),target=opts.target||Math.min(19,5+tier*2+Math.floor(r()*4)),requiredDepth=Math.min(5,2+Math.floor(tier/2));
 let best=null,bestScore=-Infinity;
 for(let attempt=0;attempt<34;attempt++){
  const cars=[],loops=target*115;
  for(let k=0;k<loops&&cars.length<target;k++){
   const c=candidate(r,cars.length);if(!occupancy([...cars,c]))continue;
   const a=analysis([...cars,c]);if(!a.solvable)continue;
   // prohibit placements that make the whole position static at low levels
   cars.push(c);
  }
  const a=analysis(cars);
  const score=cars.length*10+Math.min(a.maxdepth,9)*4+Math.min(a.edges,35)*.6-(a.free===cars.length?30:0);
  if(score>bestScore){bestScore=score;best={cars,a};}
  if(cars.length>=target&&a.maxdepth>=requiredDepth&&a.blocked>=Math.min(3,target-2)){best={cars,a};break}
 }
 if(!best||best.cars.length<4)throw Error('Level generator failed');
 const cars=best.cars.map((c,i)=>({...c,id:i}));
 return {seed,level,grid:N,cars,goal:cars.length,depth:analysis(cars).maxdepth,tier,verified:true};
}
function journey(level){level=Math.max(1,Math.min(120,Math.floor(+level)||1));return build(hash('PARK-ROYAL-2026-'+level),level)}
function daily(date){return build(hash('PARK-DAILY-'+date),105,{target:17})}
function freeCars(cars){const o=occupancy(cars);return cars.filter((c,i)=>blockers(cars,i,o).length===0).map(c=>c.id)}
function exitCar(cars,id){const index=cars.findIndex(c=>c.id===id);if(index<0)return{ok:false,reason:'missing'};const occ=occupancy(cars);const b=blockers(cars,index,occ);if(b.length)return{ok:false,reason:'blocked',blockers:b.map(i=>cars[i].id)};return{ok:true,cars:cars.filter(c=>c.id!==id),car:cars[index]};}
function checkSolution(level){const cars=level.cars.map(x=>({...x})),order=[],a=analysis(cars);if(!a.solvable)return null;for(const originalIdx of a.order){const id=cars.find(c=>c.id===originalIdx)?.id; if(id===undefined)continue;order.push(id)}return a.order.slice();}
return {N,DIRS,rng,hash,coords,occupancy,blockers,analysis,build,journey,daily,freeCars,exitCar,checkSolution};
});