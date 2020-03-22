const GraphVertex = require('../lab2/graph/GraphVertex');
const GraphEdge = require('../lab2/graph/GraphEdge');
const Graph = require('../lab2/graph/Graph');
const bfTravellingSalesman = require('./bfTravellingSalesman');

function test() {

    const vertexA = new GraphVertex('A');
    const vertexB = new GraphVertex('B');
    const vertexC = new GraphVertex('C');
    const vertexD = new GraphVertex('D');

    const edgeAB = new GraphEdge(vertexA, vertexB, 10);
    const edgeAC = new GraphEdge(vertexA, vertexC, 35);
    const edgeAD = new GraphEdge(vertexA, vertexD, 30);
    const edgeBC = new GraphEdge(vertexB, vertexC, 30);
    const edgeBD = new GraphEdge(vertexB, vertexD, 15);
    const edgeCD = new GraphEdge(vertexC, vertexD, 30);

    const graph = new Graph(false);
    graph
        .addEdge(edgeAB)
        .addEdge(edgeBD)
        .addEdge(edgeCD)
        .addEdge(edgeBC)
        .addEdge(edgeAC)
        .addEdge(edgeAD);

    const salesmanPath = bfTravellingSalesman(graph);

    console.log(salesmanPath);
}
test();
