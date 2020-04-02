const { readGraph, Edge } = require('../common');
const bfTravellingSalesman = require('./travelling-salesman');

function run() {
  const graph = readGraph();
  madeFullGraph(graph);

  const salesmanPath = bfTravellingSalesman(graph);

  printResults(salesmanPath);
}

function printResults(vertexes) {
  console.log(vertexes.map(vertex => vertex.value).join(' => '));

  console.log(`Total weight: ${findTotalWeight(vertexes)}`);
}

function findTotalWeight(graphVertices) {
  let totalWeight = 0;
  let firstVertex = graphVertices[0];
  let lastVertex = graphVertices[graphVertices.length - 1];
  let currentVertex = graphVertices.pop();
  while (currentVertex) {
    let nextVertex = graphVertices.pop();
    if (!nextVertex) {
      totalWeight += findCommonEdge(firstVertex, lastVertex).weight;
      return totalWeight;
    }
    let commonEdge = findCommonEdge(currentVertex, nextVertex);
    if (commonEdge) {
      currentVertex = nextVertex;
      totalWeight += commonEdge.weight;
    }
  }
}

function madeFullGraph(graph) {
  Object.keys(graph.vertices).forEach(key => {
    Object.keys(graph.vertices).forEach(otherKey => {
      if (key !== otherKey) {
        const vertex = graph.vertices[key];
        const otherVertex = graph.vertices[otherKey];
        if (!findCommonEdge(vertex, otherVertex)) {
          graph.addEdge(new Edge(vertex, otherVertex, Number.MAX_SAFE_INTEGER));
        }
      }
    })
  })
}


function findCommonEdge(vertex, otherVertex) {
  let edge = vertex.edges.head;
  while (edge) {
    if (edge.value.startVertex.value === otherVertex.value || edge.value.endVertex.value === otherVertex.value) {
      return edge.value;
    }
    edge = edge.next;
  }
}

run();
