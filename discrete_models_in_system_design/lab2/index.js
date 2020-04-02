const { readGraph } = require('../common/utils');
const eulerianPath = require('./eulerian-path');

function run() {
  const graph = readGraph();
  const eulerianPathSet = eulerianPath(graph);
  printResult(eulerianPathSet);
}

function printResult(vertexes) {
  console.log(vertexes.map(vertex => vertex.value).join(' -> '));
}

run();

