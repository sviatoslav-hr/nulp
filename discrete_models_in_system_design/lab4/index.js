const { readGraph } = require('../common/utils');
const bfTravellingSalesman = require('./travelling-salesman');

function run() {
  const graph = readGraph();

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
