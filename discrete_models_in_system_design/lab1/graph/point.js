class Point {
  constructor(id) {
    // point identifier
    this.id = id;
    // points connected with current point by edges
    this.adjacentPoints = new Set();
    // edges connecting current point with adjacentPoints
    this.edges = [];
  }

  isAdjacent(pointName) {
    return this.adjacentPoints.has(pointName);
  }

  addEdge(edge) {
    this.edges.push(edge);
    this.edges.forEach(edge => {
      edge.points.forEach(edgePoint => this.adjacentPoints.add(edgePoint.id));
    });
    this.adjacentPoints.delete(this.id);
  }

  toString() {
    return this.id;
  }
}

module.exports = Point;
