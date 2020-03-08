package ua.nulp.lab5;

public class Lab5App {
    public static final double I = 12.;
    public static final double N_GROUP = 416.;
    public static double r = 100;
    public static double C = 5;
    public static double epsilon = 0.0001;
    public static double x = -6.;
    public static double y = -3.;
    public static double alpha = 1.;
    public static int M = 500;

    public static void main(String[] args) {
        Coordinate point = new Coordinate(x, y);
        int k = 0;

        while(range(point)) {
            System.out.println("k = " + k++ + ":\n| F (" + point.getX() + "; " + point.getY() + ") = " + F(point));
            Coordinate newPoint = Koshi(point);
            double Pxr = -r * G(newPoint);
            if (Pxr <= epsilon) {
                break;
            }

            r /= C;
            point = newPoint;
            System.out.println("| Pxr = " + Pxr + " | r = " + r + " | X = " + newPoint.getX() + ", Y = " + newPoint.getY() + " |");
        }

        System.out.println("\nk = " + k + "\nF(x) = " + F(point) + ",\tХ(" + point.getX() + ", " + point.getY() + ").");
    }

    public static double Z(Coordinate p) {
        return Math.pow(p.getX() + 6., 2.) + Math.pow(p.getY() + 3., 2.) - p.getX() * p.getY() / N_GROUP;
    }

    public static double G(Coordinate p) {
        return p.getX() + p.getY() - 37.81818181818182;
    }

    public static double F(Coordinate p) {
        return Z(p) - r * (1. / G(p));
    }

    public static boolean range(Coordinate p) {
        return p.getX() + p.getY() <= 37.81818181818182;
    }

    public static Coordinate Koshi(Coordinate point) {
        new Coordinate();

        for(int k = 0; k <= M; ++k) {
            Coordinate gradP = gradZ(point);
            Coordinate S = resS(point);
            x = point.getX() - alpha * S.getX();
            y = point.getY() - alpha * S.getY();
            Coordinate newPoint = new Coordinate(x, y);
            Coordinate gradNP = gradZ(newPoint);
            if (stopRule(point, newPoint)) {
                break;
            }

            alpha = goldSlice(point, alpha);
            point = newPoint;
        }

        return point;
    }

    public static double dudx(Coordinate p) {
        return 2. * p.getX() - p.getY() / N_GROUP + 12. + r / Math.pow(G(p), 2.);
    }

    public static double dudy(Coordinate p) {
        return 2. * p.getY() - p.getX() / N_GROUP + 6. + r / Math.pow(G(p), 2.);
    }

    public static Coordinate gradZ(Coordinate p) {
        return new Coordinate(dudx(p), dudy(p));
    }

    public static double normZ(Coordinate c) {
        return Math.sqrt(Math.pow(c.getX(), 2.) + Math.pow(c.getY(), 2.));
    }

    public static Coordinate resS(Coordinate c) {
        Coordinate g = gradZ(c);
        double m = normZ(g);
        return new Coordinate(g.getX() / m, g.getY() / m);
    }

    public static boolean stopRule(Coordinate c1, Coordinate c2) {
        return c1.getX() - c2.getX() < epsilon && c1.getY() - c2.getY() < epsilon || normZ(gradZ(new Coordinate(c1.getX() - c2.getX(), c1.getY() - c2.getY()))) < epsilon;
    }

    public static double Faplh(Coordinate point, double alpha) {
        Coordinate grad = gradZ(point);
        double x = point.getX() - alpha * grad.getX();
        double y = point.getY() - alpha * grad.getY();
        return Z(new Coordinate(x, y));
    }

    public static double goldSlice(Coordinate point, double b) {
        double a = 0.;
        double k = (Math.sqrt(5.) - 1.) / 2.;
        double x1 = a + (1. - k) * (b - a);
        double x2 = a + k * (b - a);
        double xm = 0.;
        double f1 = Faplh(point, x1);
        double f2 = Faplh(point, x2);
        boolean loop = true;

        while(loop) {
            if (Math.abs(x2 - x1) < epsilon) {
                xm = (x1 + x2) / 2.;
                f1 = Faplh(point, xm);
                loop = false;
            } else if (f1 >= f2) {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = a + k * (b - a);
                f2 = Faplh(point, x2);
            } else if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + (1. - k) * (b - a);
                f1 = Faplh(point, x1);
            }
        }

        return xm;
    }
}