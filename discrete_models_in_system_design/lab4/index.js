const { readGraph } = require('../common/utils');
const bfTravellingSalesman = require('./bfTravellingSalesman');

function run() {
  const graph = readGraph();

  const salesmanPath = bfTravellingSalesman(graph);

  printResults(salesmanPath);
}

function printResults(vertexes) {
  console.log(vertexes.map(vertex => vertex.value).join(' => '));
}

run();
