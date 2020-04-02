const INIT_DATA = require('./data');
const { Graph, Edge, Vertex } = require('./graph');

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

module.exports = { readGraph };
