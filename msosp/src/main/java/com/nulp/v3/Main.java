package com.nulp.v3;

import com.nulp.v1.*;

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

//        for machine
        int resultAdditive = additive.getAdditive(normalArray);
        int resultMultiplicative = multiplicative.getMultiplicative(normalArray);
        int resultMaxMin = maxMin.getMaxMin(normalArray);
        int resultMinMax = minMax.getMinMax(normalArray);
        System.out.println("resultAdditive = machine: " + resultAdditive);
        System.out.println("resultMultiplicative = machine: " + resultMultiplicative);
        System.out.println("resultMaxMin = machine: " + resultMaxMin);
        System.out.println("resultMinMax = machine: " + resultMinMax);

//        for numbers
//        double resultAdditive = additive.getAdditive(normalArray);
//        double resultMultiplicative = multiplicative.getMultiplicative(normalArray);
//        double resultMaxMin = maxMin.getMaxMin(normalArray);
//        double resultMinMax = minMax.getMinMax(normalArray);
//        System.out.println("resultAdditive = " + resultAdditive);
//        System.out.println("resultMultiplicative = " + resultMultiplicative);
//        System.out.println("resultMaxMin = " + resultMaxMin);
//        System.out.println("resultMinMax = " + resultMinMax);
    }
}
