package com.nulp.v1;

class MaxMin {
    double getMaxMin(double[][] normal) {
        double[][] maxMinArray = new double[normal.length][normal[0].length - 1];
        for (int i = 0; i < normal.length; i++)
            for (int j = 0; j < normal.length + 1; j++) maxMinArray[i][j] = normal[i][j + 1] / normal[i][0];
        double maxNumber;
        double[] minNumbersOfColumns = new double[maxMinArray.length + 1];
        for (int i = 0; i < maxMinArray[0].length; i++) {
            if (i > 2) maxNumber = maxMinArray[i - 1][i - 1];
            else maxNumber = maxMinArray[i][i];
            for (double[] doubles : maxMinArray) if (maxNumber > doubles[i]) maxNumber = doubles[i];
            minNumbersOfColumns[i] = maxNumber;
        }
        maxNumber = minNumbersOfColumns[0];
        for (double temp : minNumbersOfColumns) if (temp > maxNumber) maxNumber = temp;
        return maxNumber;
    }
}
