package ua.nulp.lab4;

public class Lab4App{
    public static final double I = 12.;
    public static final double N_GROUP = 416.;
    private static double x = 0.;
    private static double y = 0.;
    private static double alpha = 1.;
    private static double epsilon = 0.001;
    private static int M = 500;

    public Lab4App() {
    }

    public static void main(String[] args) {
        Coordinate point = new Coordinate(x, y);
        Coordinate newPoint = new Coordinate();

        for(int k = 0; k <= M; ++k) {
            System.out.println("k = " + k + ":\tZ(x) = " + Z(point.getX(), point.getY()) + ",\tХ(" + point.getX() + ", " + point.getY() + ").");
            Coordinate S = resS(point);
            x = point.getX() - alpha * S.getX();
            y = point.getY() - alpha * S.getY();
            newPoint = new Coordinate(x, y);
            if (stopRule(point, newPoint)) {
                break;
            }

            alpha = goldSlice(point, alpha);
            point = newPoint;
        }

        System.out.println("Z(x) = " + Z(newPoint.getX(), newPoint.getY()) + ", Х(" + newPoint.getX() + ", " + newPoint.getY() + ").");
    }

    public static double Z(double x, double y) {
        return Math.pow(x + 6., 2.) + Math.pow(y + 3., 2.) - x * y / 416.;
    }

    public static double dudx(double x, double y) {
        return 2. * x + y / 416. + 12.;
    }

    public static double dudy(double x, double y) {
        return 2. * y + x / 416. + 6.;
    }

    public static Coordinate gradZ(double x, double y) {
        return new Coordinate(dudx(x, y), dudy(x, y));
    }

    public static double normZ(Coordinate c) {
        return Math.sqrt(Math.pow(c.getX(), 2.) + Math.pow(c.getY(), 2.));
    }

    public static Coordinate resS(Coordinate c) {
        Coordinate g = gradZ(c.getX(), c.getY());
        double m = normZ(g);
        return new Coordinate(g.getX() / m, g.getY() / m);
    }

    public static boolean stopRule(Coordinate c1, Coordinate c2) {
        return c1.getX() - c2.getX() < epsilon && c1.getY() - c2.getY() < epsilon || normZ(gradZ(c1.getX() - c2.getX(), c1.getY() - c2.getY())) < epsilon;
    }

    public static double F(Coordinate point, double alpha) {
        Coordinate grad = gradZ(point.getX(), point.getY());
        double x = point.getX() - alpha * grad.getX();
        double y = point.getY() - alpha * grad.getY();
        return Z(x, y);
    }

    public static double goldSlice(Coordinate point, double b) {
        double a = 0.;
        double k = (Math.sqrt(5.) - 1.) / 2.;
        double x1 = a + (1. - k) * (b - a);
        double x2 = a + k * (b - a);
        double xm = 0.;
        double F1 = F(point, x1);
        double F2 = F(point, x2);
        boolean loop = true;

        while(loop) {
            if (Math.abs(x2 - x1) < epsilon) {
                xm = (x1 + x2) / 2.;
                F1 = F(point, xm);
                loop = false;
            } else if (F1 >= F2) {
                a = x1;
                x1 = x2;
                F1 = F2;
                x2 = a + k * (b - a);
                F2 = F(point, x2);
            } else if (F1 < F2) {
                b = x2;
                x2 = x1;
                F2 = F1;
                x1 = a + (1. - k) * (b - a);
                F1 = F(point, x1);
            }
        }

        return xm;
    }
}
