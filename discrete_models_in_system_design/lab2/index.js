const { Graph, Edge, Vertex } = require('./graph');
const eulerianPath = require('./eulerian-path');


function test() {
  const vertexA = new Vertex('A');
  const vertexB = new Vertex('B');
  const vertexC = new Vertex('C');
  const vertexD = new Vertex('D');
  const vertexE = new Vertex('E');
  const vertexF = new Vertex('F');
  const vertexG = new Vertex('G');

  const edgeAB = new Edge(vertexA, vertexB);
  const edgeAE = new Edge(vertexA, vertexE);
  const edgeAF = new Edge(vertexA, vertexF);
  const edgeAG = new Edge(vertexA, vertexG);
  const edgeGF = new Edge(vertexG, vertexF);
  const edgeBE = new Edge(vertexB, vertexE);
  const edgeEB = new Edge(vertexE, vertexB);
  const edgeBC = new Edge(vertexB, vertexC);
  const edgeED = new Edge(vertexE, vertexD);
  const edgeCD = new Edge(vertexC, vertexD);

  const graph = new Graph();

  graph
    .addEdge(edgeAB)
    .addEdge(edgeAE)
    .addEdge(edgeAF)
    .addEdge(edgeAG)
    .addEdge(edgeGF)
    .addEdge(edgeBE)
    .addEdge(edgeEB)
    .addEdge(edgeBC)
    .addEdge(edgeED)
    .addEdge(edgeCD);

  const graphEdgesCount = graph.getAllEdges().length;

  const eulerianPathSet = eulerianPath(graph);
  printResult(eulerianPathSet);
}

function methodExample() {
  const vertex0 = new Vertex('0');
  const vertex1 = new Vertex('1');
  const vertex2 = new Vertex('2');
  const vertex3 = new Vertex('3');
  const vertex4 = new Vertex('4');

  const edge01 = new Edge(vertex0, vertex1);
  const edge02 = new Edge(vertex0, vertex2);
  const edge03 = new Edge(vertex0, vertex3);
  const edge04 = new Edge(vertex0, vertex4);
  const edge12 = new Edge(vertex1, vertex2);
  const edge34 = new Edge(vertex3, vertex4);

  const graph = new Graph();

  graph
    .addEdge(edge01)
    .addEdge(edge02)
    .addEdge(edge03)
    .addEdge(edge04)
    .addEdge(edge12)
    .addEdge(edge34);

  const eulerianPathSet = eulerianPath(graph);
  printResult(eulerianPathSet);
}

function printResult(vertexes) {
  console.log(vertexes.map(vertex => vertex.value).join(' -> '));
}

test();

