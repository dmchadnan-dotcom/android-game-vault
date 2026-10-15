/* All computation happens locally. Never sends the cube state over a network. */
importScripts('vendor/cube.js','vendor/solve.js');
let initialized=false;
onmessage=(event)=>{if(!event.data||event.data.type!=='solve')return;try{if(!initialized){Cube.initSolver();initialized=true;}const state=event.data.state;const cube=Cube.fromString(state);postMessage({solution:cube.solve()});}catch(error){postMessage({error:String(error.message||error)})}};
