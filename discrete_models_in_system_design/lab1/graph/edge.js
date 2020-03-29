class Edge {
  constructor(source, destination, weight) {
    this.points = [source, destination];
    this.weight = weight;
    this.adjacentEdges = [];
    this.points.forEach(point => point.addEdge(this));
  }

  isAdjacent(edge) {
    if (edge === this) {
      return false;
    }
    for (const edgePoint of edge.points) {
      if (this.includesPoint(edgePoint.id)) {
        return true;
      }
    }
    return false;
  }

  includesPoint(pointId) {
    return pointId && this.points.some(point => point.id === pointId);
  }

  getUncommonPoint(edge) {
    if (!edge) {
      return null;
    }
    for (const point of this.points) {
      if (edge.includesPoint(point.id)) {
        for (const otherPoint of this.points) {
          if (!edge.includesPoint(otherPoint.id)) {
            return otherPoint;
          }
        }
      }
    }
  }

  toString() {
    return `(${this.points[0]}-[${this.weight}]-${this.points[1]})`;
  }
}

module.exports = Edge;
