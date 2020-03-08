package com.nulp.v1;

public class Additive {
    double getAdditive(double[][] normal) {
        double[] additive = new double[normal[0].length - 1];
        for (int i = 1; i < normal[0].length; i++)
            for (double[] doubles : normal) additive[i - 1] += doubles[0] * doubles[i];
        double additiveMax = additive[0];
        for (double temp : additive) if (additiveMax < temp) additiveMax = temp;
        return additiveMax;
    }
}
