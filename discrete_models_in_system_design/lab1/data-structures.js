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

module.exports = {
    Point, Edge, Graph
};
