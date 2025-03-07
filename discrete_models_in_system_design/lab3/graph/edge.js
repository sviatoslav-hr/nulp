class Edge {
  constructor(
    a = 'A', b = 'B', weight = 0
  ) {
    this.a = a;
    this.b = b;
    this.weight = weight;
  }

  hasPoint(point) {
    return this.a === point || this.b === point;
  }

  toString() {
    return `(${this.a}-[${this.weight}]-${this.b})`;
  }

}

module.exports = Edge;
