package ua.nulp.lab1;

import java.util.Scanner;

public class Lab1App {
    private static final double TASK_X0 = -1.5;
    private static final int TASK_I = 26;
    private static final double TASK_EPS = 0.0001;

    /**
     * Main method that runs when the program is started.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        boolean useDefaults = askToUseDefaults();
        double x0 = getX0(useDefaults);
        double eps = getEps(useDefaults);
        double i = getI(useDefaults);
        double F;
        double f;
        double f1;
        double f2;
        int a = 1;
        do {
            f = (Math.pow(x0, 4)) + (Math.pow((x0 - (i / 100.0)), 3))
                    - ((Math.pow(-1, i)) * (Math.cos(i * x0)) / 100);
            f1 = 4.0 * (Math.pow(x0 + 1.0, 3.0));
//                    + 3 * (Math.pow((x0 - (i / 100.0)), 2))
//                    - ((i * Math.pow(-1, i)) * (Math.sin(i * x0) / 100));
            F = Math.abs(f1);
            f2 = 12.0 * (Math.pow(x0 + 1.0, 2.0));
//                    + 6 * (x0 - (i / 100.0))
//                    - ((Math.pow(i, 2) * Math.pow(-1, i)) * (Math.cos(i * x0) / 100));
            System.out.printf("i = %d\t" +
                    "x0 = %f\t" +
                    "f = %f\t" +
                    "f1 = %f\t" +
                    "f2 = %f\t\n", a, x0, f, f1, f2);
            if (F < eps) {
                System.out.printf("Result:\n" +
                        "i = %d\t" +
                        "x0 = %f\t" +
                        "f = %f\t" +
                        "f1 = %f\t" +
                        "f2 = %f\t\n", a, x0, f, f1, f2);
                break;
            }
            x0 = x0 - (f1 / f2);
            a++;
        }
        while (true);
    }

    private static int getI(boolean useDefaults) {
        if (useDefaults) {
            return TASK_I;
        } else {
            System.out.print("Enter i: ");
            return nextInt();
        }
    }

    private static double getX0(boolean useDefaults) {
        if (useDefaults) {
            return TASK_X0;
        } else {
            System.out.print("Enter x0: ");
            return nextDouble();
        }
    }

    private static double getEps(boolean useDefaults) {
        if (useDefaults) {
            return TASK_EPS;
        } else {
            System.out.print("Enter eps: ");
            return nextDouble();
        }
    }

    private static boolean askToUseDefaults() {
        System.out.print("Use defaults? ");
        return nextInt() > 0;
    }

    private static String nextLine() {
        return new Scanner(System.in).nextLine();
    }

    private static int nextInt() {
        return new Scanner(System.in).nextInt();
    }

    private static double nextDouble() {
        return new Scanner(System.in).nextDouble();
    }
}
