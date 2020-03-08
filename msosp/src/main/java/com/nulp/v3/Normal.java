package com.nulp.v3;

class Normal {
    double[][] getNormal(double[][] array) {
        double[][] normal = new double[array.length][array[0].length];
        for (int i = 0; i < normal.length; i++) normal[i][0] = array[i][0];
        double productive = 0.0;
        double reliability = 0.0;
        double cost = array[2][1];
        for (int i = 0; i < array.length; i++)
            for (int j = 1; j < array[i].length; j++) {
                if (i == 0) if (array[i][j] > productive) productive = array[i][j];
                if (i == 1) if (array[i][j] > reliability) reliability = array[i][j];
                if (i == 2) if (array[i][j] < cost) cost = array[i][j];
            }
        for (int i = 0; i < normal.length; i++)
            for (int j = 1; j < normal[i].length; j++) {
                if (i == 0) normal[i][j] = array[i][j] / productive * 10;
                if (i == 1) normal[i][j] = array[i][j] / reliability * 10;
                if (i == 2) normal[i][j] = cost / array[i][j] * 10;
            }
        return normal;
    }
}
