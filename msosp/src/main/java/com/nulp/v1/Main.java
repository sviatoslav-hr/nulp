package com.nulp.v1;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Normal normal = new Normal();
        Additive additive = new Additive();
        Multiplicative multiplicative = new Multiplicative();
        MaxMin maxMin = new MaxMin();
        MinMax minMax = new MinMax();
        double[][] array = data.getArray();
        double[][] normalArray = normal.getNormal(array);
        double resultAdditive = additive.getAdditive(normalArray);
        double resultMultiplicative = multiplicative.getMultiplicative(normalArray);
        double resultMaxMin = maxMin.getMaxMin(normalArray);
        double resultMinMax = minMax.getMinMax(normalArray);
        System.out.println("resultAdditive = " + resultAdditive);
        System.out.println("resultMultiplicative = " + resultMultiplicative);
        System.out.println("resultMaxMin = " + resultMaxMin);
        System.out.println("resultMinMax = " + resultMinMax);
    }
}
