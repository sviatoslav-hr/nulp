const Point = require('./point');

class Graph {
  constructor() {
    this.edges = [];
    this.pointsMap = new Map();
  }

  getPoint(pointId) {
    return this.pointsMap.get(pointId);
  }

  addPointIfAbsent(pointId) {
    if (!this.pointsMap.has(pointId)) {
      this.pointsMap.set(pointId, new Point(pointId));
    }
  }

  addEdge(edge) {
    this.edges.forEach(value => {
      if (this._isAdjacent(edge, value)) {
        value.adjacentEdges.push(edge);
        edge.adjacentEdges.push(value);
      }
    });
    this.edges.push(edge);
  }

  get points() {
    return this.pointsMap.size;
  }

  _isAdjacent(edge1, edge2) {
    if (!edge1 || !edge2 || edge1 === edge2) {
      return false;
    }
    for (const edge1Point of edge1.points) {
      if (edge2.includesPoint(edge1Point.id)) {
        return true;
      }
    }
    return false;
  }
}

module.exports = Graph;
