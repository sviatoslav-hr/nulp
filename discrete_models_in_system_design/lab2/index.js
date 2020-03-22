const GraphEdge = require('./graph/GraphEdge');
const Graph = require('./graph/Graph');
const eulerianPath = require('./eulerianPath');
const GraphVertex = require('./graph/GraphVertex');


function test() {
  const vertexA = new GraphVertex('A');
  const vertexB = new GraphVertex('B');
  const vertexC = new GraphVertex('C');
  const vertexD = new GraphVertex('D');
  const vertexE = new GraphVertex('E');
  const vertexF = new GraphVertex('F');
  const vertexG = new GraphVertex('G');

  const edgeAB = new GraphEdge(vertexA, vertexB);
  const edgeAE = new GraphEdge(vertexA, vertexE);
  const edgeAF = new GraphEdge(vertexA, vertexF);
  const edgeAG = new GraphEdge(vertexA, vertexG);
  const edgeGF = new GraphEdge(vertexG, vertexF);
  const edgeBE = new GraphEdge(vertexB, vertexE);
  const edgeEB = new GraphEdge(vertexE, vertexB);
  const edgeBC = new GraphEdge(vertexB, vertexC);
  const edgeED = new GraphEdge(vertexE, vertexD);
  const edgeCD = new GraphEdge(vertexC, vertexD);

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
  console.log(eulerianPathSet);
}

function methodExample() {
  const vertex0 = new GraphVertex('0');
  const vertex1 = new GraphVertex('1');
  const vertex2 = new GraphVertex('2');
  const vertex3 = new GraphVertex('3');
  const vertex4 = new GraphVertex('4');

  const edge01 = new GraphEdge(vertex0, vertex1);
  const edge02 = new GraphEdge(vertex0, vertex2);
  const edge03 = new GraphEdge(vertex0, vertex3);
  const edge04 = new GraphEdge(vertex0, vertex4);
  const edge12 = new GraphEdge(vertex1, vertex2);
  const edge34 = new GraphEdge(vertex3, vertex4);

  const graph = new Graph();

  graph
    .addEdge(edge01)
    .addEdge(edge02)
    .addEdge(edge03)
    .addEdge(edge04)
    .addEdge(edge12)
    .addEdge(edge34);

  const eulerianPathSet = eulerianPath(graph);
  console.log(eulerianPathSet);
}

methodExample();

