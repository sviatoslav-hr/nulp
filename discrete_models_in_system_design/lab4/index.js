const { Vertex, Edge, Graph } = require('../lab2/graph');
const bfTravellingSalesman = require('./bfTravellingSalesman');
const INIT_DATA = require('./data');

function run() {
  const graph = readGraph();

  const salesmanPath = bfTravellingSalesman(graph);

  printResults(salesmanPath);
}

function printResults(vertexes) {
  console.log(vertexes.map(vertex => vertex.value).join(' => '));
}

function readGraph(data = INIT_DATA) {
  const vertexes = new Map();
  const graph = new Graph(false);
  data.forEach(([vertex1, vertex2, weight]) => {
    if (!vertexes.has(vertex1)) {
      vertexes.set(vertex1, new Vertex(vertex1));
    }
    if (!vertexes.has(vertex2)) {
      vertexes.set(vertex2, new Vertex(vertex2));
    }
    const edge = new Edge(vertexes.get(vertex1), vertexes.get(vertex2), weight);
    graph.addEdge(edge);
  });
  return graph;
}

run();
