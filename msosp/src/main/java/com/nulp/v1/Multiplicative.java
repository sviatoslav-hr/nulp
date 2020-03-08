package com.nulp.v1;

class Multiplicative {
    double getMultiplicative(double[][] normal) {
        double[] multiplicative = new double[normal[0].length - 1];
        for (int i = 1; i < normal[0].length; i++)
            for (int j = 0; j < normal.length; j++)
                if (j == 0) multiplicative[i - 1] = normal[j][0] * normal[j][i];
                else multiplicative[i - 1] *= normal[j][0] * normal[j][i];
        double multiplicativeMax = multiplicative[0];
        for (double temp : multiplicative) if (multiplicativeMax < temp) multiplicativeMax = temp;
        return multiplicativeMax;
    }
}
