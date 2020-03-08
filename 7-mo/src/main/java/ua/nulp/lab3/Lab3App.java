package ua.nulp.lab3;


import java.util.function.DoubleBinaryOperator;

import static java.lang.Math.pow;

public class Lab3App {
    private static final int I = 12;
    private static final int N = 416;
    private static final double EPS = 0.001;

    public static void main(String[] args) {
        DoubleBinaryOperator function = (x, y) -> pow(x - I, 2) + pow(y - I / 2.0, 2) - x * y / N;
        NelderMead nelderMead = new NelderMead(EPS);
        nelderMead.start(function);
    }

}
