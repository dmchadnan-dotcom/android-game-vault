/* Local-only 3x3 solver. Vendored MIT cube.js, see LICENSE. */
importScripts('vendor/cube.js','vendor/solve.js');
let initialized=false;
onmessage=e=>{if(!e.data||e.data.type!=='solve')return;try{if(!initialized){Cube.initSolver();initialized=true}const cube=Cube.fromString(e.data.state);postMessage({solution:cube.solve()});}catch(error){postMessage({error:String(error.message||error)})}};