package ua.nulp.lab4;

class Coordinate {
    private double x;
    private double y;

    Coordinate() {
        this.x = 0.0D;
        this.y = 0.0D;
    }

    Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate c) {
        this.x = c.x;
        this.y = c.y;
    }

    double getX() {
        return this.x;
    }

    double getY() {
        return this.y;
    }
}