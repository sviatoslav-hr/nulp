package ua.nulp.lab3;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.DoubleStream;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

class NelderMead {
    private static final double ALPHA = 1.0;
    private static final double BETA = 0.5;
    private static final double GAMMA = 2.0;
    private static final int T = 1;
    private static final int DIMENSIONS_NUMBER = 2;
    private double eps = 0.001;
    private DoubleBinaryOperator function;
    private double[] massCenters;
    private double[] contractionValues;
    private double[] reflectionValues;
    private double[] expansionValues;
    private double[] functionValues;
    private double[][] polyhedron;

    NelderMead(double eps) {
        this.eps = eps;
    }

    private double getFunction(double[] p) {
        if (p.length == 2) {
            return function.applyAsDouble(p[0], p[1]);
        }
        return DoubleStream.of(p)
                .average()
                .orElse(0.5);
    }

    void start(DoubleBinaryOperator function) {
        this.function = function;
        initArrays();
        int iterations = 0;
        while (true) {
            setFunctionValues(polyhedron);
            printPolyhedron();
            int maxFunctionIndex = getMaxValueIndex(functionValues);
            int minFunctionIndex = getMinValueIndex(functionValues);
            setMassCenters(maxFunctionIndex);
            double massCenterFunctionValue = getFunction(massCenters);
            System.out.println("massCenterFunctionValue = " + massCenterFunctionValue);
            if (isCompleted(massCenterFunctionValue)) {
                break;
            } else {
                reflect(maxFunctionIndex, minFunctionIndex);
            }
            iterations++;
        }
        System.out.println("iterations: " + iterations);
        System.out.println("x = " + massCenters[0]);
        System.out.println("y = " + massCenters[1]);
    }

    // Vertex reflection
    private void reflect(int maxFunctionIndex, int minFunctionIndex) {
        for (int i = 0; i <= DIMENSIONS_NUMBER - 1; i++) {
            reflectionValues[i] = massCenters[i] + ALPHA * (massCenters[i] - polyhedron[maxFunctionIndex][i]);
        }
        double reflectedFunctionValue = getFunction(reflectionValues);
        if (reflectedFunctionValue < functionValues[minFunctionIndex]) {
            expand(maxFunctionIndex);
        } else {
            System.out.println("The reflection was ineffective");
            if (!checkReflection(maxFunctionIndex, reflectedFunctionValue)) {
                System.out.println("Proceed to contraction");
                if (reflectedFunctionValue < functionValues[maxFunctionIndex]) {
                    System.out.println("The compression will go in the direction from xN. Vertex replacement");
                    polyhedron[maxFunctionIndex] = Arrays.copyOfRange(reflectionValues, 0, DIMENSIONS_NUMBER);
                } else {
                    System.out.println("Skip vertex replacement");
                }
                contract(maxFunctionIndex);
                if (getFunction(contractionValues) < functionValues[maxFunctionIndex]) {
                    System.out.println("Contraction was effective, using it. Vertex replacement");
                    polyhedron[maxFunctionIndex] = Arrays.copyOfRange(contractionValues, 0, DIMENSIONS_NUMBER);
                } else {
                    System.out.println("Contraction was ineffective, proceed to reduction. Recalculation of vertices");
                    reduce(minFunctionIndex);
                }
            } else {
                System.out.println("Can not proceed to contraction");
                polyhedron[maxFunctionIndex] = Arrays.copyOfRange(reflectionValues, 0, DIMENSIONS_NUMBER);
            }
        }

    }

    // Reduction
    private void reduce(int minFunctionIndex) {
        for (int i = 0; i < DIMENSIONS_NUMBER + 1; i++) {
            for (int k = 0; k <= DIMENSIONS_NUMBER - 1; k++) {
                if (i != minFunctionIndex) {
                    polyhedron[i][k] = polyhedron[minFunctionIndex][k]
                            + 0.5 * (polyhedron[i][k] - polyhedron[minFunctionIndex][k]);
                }
            }
        }
    }

    // Contraction
    private void contract(int maxFunctionIndex) {
        for (int k = 0; k <= DIMENSIONS_NUMBER - 1; k++) {
            contractionValues[k] = massCenters[k] + BETA * (polyhedron[maxFunctionIndex][k] - massCenters[k]);
        }
    }

    private void expand(int maxFunctionIndex) {
        System.out.println("The reflection was effective, proceed to expansion");
        for (int i = 0; i <= DIMENSIONS_NUMBER - 1; i++) {
            expansionValues[i] = massCenters[i] + GAMMA * (reflectionValues[i] - massCenters[i]);
        }
        // Expansion check
        if (getFunction(expansionValues) < getFunction(reflectionValues)) {
            System.out.println("The expansion was effective, using it");
            polyhedron[maxFunctionIndex] = Arrays.copyOfRange(expansionValues, 0, DIMENSIONS_NUMBER);
        } else {
            System.out.println("The expansion was ineffective, using reflection");
            polyhedron[maxFunctionIndex] = Arrays.copyOfRange(reflectionValues, 0, DIMENSIONS_NUMBER);
        }
    }

    // Check the completion
    private boolean isCompleted(double massCenterFunctionValue) {
        double functionValuesSum = 0;
        for (int i = 0; i < DIMENSIONS_NUMBER + 1; i++) {
            functionValuesSum = functionValuesSum + pow(functionValues[i] - massCenterFunctionValue, 2);
        }
        double z = sqrt((1.0 / (DIMENSIONS_NUMBER + 1)) * functionValuesSum);
        System.out.println("z = " + z);
        System.out.println('\n');
        return z <= eps;
    }

    // Searching mass centers of all vertexes except Ph
    private void setMassCenters(int maxFunctionIndex) {
        for (int i = 0; i <= DIMENSIONS_NUMBER - 1; i++) {
            double polyhedronSum = 0.0;
            for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
                polyhedronSum += polyhedron[j][i];
            }
            massCenters[i] = (1.0 / DIMENSIONS_NUMBER) * (polyhedronSum - polyhedron[maxFunctionIndex][i]);
            System.out.println("massCenters[i = " + i + "] = " + massCenters[i]);
        }
    }

    private void initArrays() {
        massCenters = new double[DIMENSIONS_NUMBER];
        contractionValues = new double[DIMENSIONS_NUMBER];
        reflectionValues = new double[DIMENSIONS_NUMBER];
        expansionValues = new double[DIMENSIONS_NUMBER];
        initPolyhedron();
    }

    private boolean checkReflection(int maxFunctionIndex, double reflectedFunctionValue) {
        for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
            if (j != maxFunctionIndex && reflectedFunctionValue < functionValues[j]) {
                return true;
            }
        }
        return false;
    }

    private int getMaxValueIndex(double[] functionValues) {
        int h = 0;
        for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
            if (functionValues[j] > functionValues[h]) {
                h = j;
            }
        }
        System.out.println("max function value index: h = " + h);
        return h;
    }

    private int getMinValueIndex(double[] functionValues) {
        int l = 0;
        for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
            if (functionValues[j] < functionValues[l]) {
                l = j;
            }
        }
        System.out.println("min function value index: l = " + l);
        return l;
    }

    // Calculating function values inside polyhedron vertexes
    private void setFunctionValues(double[][] polyhedron) {
        functionValues = new double[DIMENSIONS_NUMBER + 1];
        for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
            functionValues[j] = getFunction(polyhedron[j]);
        }
    }

    private void initPolyhedron() {
        polyhedron = new double[DIMENSIONS_NUMBER + 1][DIMENSIONS_NUMBER];
        for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
            for (int k = 0; k < DIMENSIONS_NUMBER; k++) {
                if (j == 0) {
                    polyhedron[j][k] = 0;
                } else {
                    if (j == k + 1) {
                        polyhedron[j][k] = T / (DIMENSIONS_NUMBER * sqrt(2)) * (sqrt(DIMENSIONS_NUMBER + 1.0) - 1 + DIMENSIONS_NUMBER);
                    } else {
                        polyhedron[j][k] = T / (DIMENSIONS_NUMBER * sqrt(2)) * (sqrt(DIMENSIONS_NUMBER + 1.0) - 1);
                    }
                }
            }
        }
    }

    private void printPolyhedron() {
        System.out.println("Polyhedron:");
        for (int j = 0; j < DIMENSIONS_NUMBER + 1; j++) {
            for (int k = 0; k < DIMENSIONS_NUMBER; k++) {
                System.out.print(polyhedron[j][k] + "\t");
            }
            System.out.println("f[j=" + j + "]=" + functionValues[j]);
        }
    }


}
