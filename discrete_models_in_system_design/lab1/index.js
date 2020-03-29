const INIT_DATA = require('./data');
const { Edge, Graph } = require('./graph');
const KruskalMST = require('./kruskal');

const startKruskal = (data = INIT_DATA) => {
  const graph = new Graph();
  data.forEach(el => {
    graph.addPointIfAbsent(el[0]);
    graph.addPointIfAbsent(el[1]);
    const source = graph.getPoint(el[0]);
    const destination = graph.getPoint(el[1]);
    graph.addEdge(new Edge(source, destination, el[2]));
  });
  const kruskal = new KruskalMST(graph);
  console.log(kruskal.find());
};

startKruskal();
