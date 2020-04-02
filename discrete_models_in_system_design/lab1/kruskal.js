class KruskalMST {

  constructor(graph) {
    this.graph = graph;
    this.queue = [];
    this.includedEdges = [];
  }

  find() {
    this.queue.push(...this.graph.edges);
    this.queue.sort((a, b) => a.weight - b.weight);

    while (this.queue.length) {
      let edge = this.queue.shift();

      if (!this._isLooped(edge)) {
        this.includedEdges.push(edge);
      }
    }
    return this.includedEdges.length === this.graph.pointsSize - 1
      ? this.includedEdges.map(value => value.toString()).join(' => ')
      : [];
  }

  _isLooped(edge, targetEdge = edge, visited = [], targetPoint = undefined,) {
    const includedUnvisitedAdjacentEdges = this.includedEdges
      .filter(value => edge.isAdjacent(value) && !visited.includes(value));
    if (targetPoint && edge.includesPoint(targetPoint.id)) {
      return true;
    }
    visited.push(edge);
    while (includedUnvisitedAdjacentEdges.length) {
      let poppedEdge = includedUnvisitedAdjacentEdges.pop();
      if (edge === targetEdge) {
        targetPoint = edge.getUncommonPoint(poppedEdge);
      } else if (poppedEdge.includesPoint(targetPoint.id)) {
        return true;
      }
      visited.push(poppedEdge);
      const nextPoint = poppedEdge.getUncommonPoint(edge);
      let filtered = poppedEdge.adjacentEdges
        .filter(value => this.includedEdges.includes(value) && value.includesPoint(nextPoint.id));
      for (const adjacentEdge of filtered) {
        if (this._isLooped(adjacentEdge, targetEdge, [...visited], targetPoint)) {
          return true;
        }
      }
    }
    return false;
  }
}

module.exports = KruskalMST;
