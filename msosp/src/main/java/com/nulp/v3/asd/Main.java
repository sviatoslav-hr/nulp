//package asd;
//
//public class Main {
//    public static void main(String[] args) {
//        double alpha1 = 0.3;
//        double alpha2 = 0.1;
//        double alpha3 = 0.6;
//        double[][] array = {
//                {alpha1, 1000.0, 1500.0, 2000.0, 2500.0},
//                {alpha2, 100.0, 90.0, 85.0, 80.0},
//                {alpha3, 8.0, 7.0, 6.0, 7.5}
//        };
//        double[][] normal = new double[array.length][array[0].length];
//        for (int i = 0; i < normal.length; i++) {
//            normal[i][0] = array[i][0];
//        }
//        double productive = 0.0;
//        double reliability = 0.0;
//        double cost = array[2][1];
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 1; j < array[i].length; j++) {
//                if (i == 0) {
//                    if (array[i][j] > productive) {
//                        productive = array[i][j];
//                    }
//                }
//                if (i == 1) {
//                    if (array[i][j] > reliability) {
//                        reliability = array[i][j];
//                    }
//                }
//                if (i == 2) {
//                    if (array[i][j] < cost) {
//                        cost = array[i][j];
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < normal.length; i++) {
//            for (int j = 1; j < normal[i].length; j++) {
//                if (i == 0) {
//                    normal[i][j] = array[i][j] / productive * 10;
//                }
//                if (i == 1) {
//                    normal[i][j] = array[i][j] / reliability * 10;
//                }
//                if (i == 2) {
//                    normal[i][j] = cost / array[i][j] * 10;
//                }
//            }
//        }
//        //Адитивний критерій
//        double[] additive = new double[array[0].length - 1];
//        for (int i = 0; i < normal.length; i++) {
//            additive[0] += normal[i][0] * normal[i][1];
//            additive[1] += normal[i][0] * normal[i][2];
//            additive[2] += normal[i][0] * normal[i][3];
//            additive[3] += normal[i][0] * normal[i][4];
//        }
//        double additiveMax = additive[0];
//        for (double v : additive) {
//            if (additiveMax < v) {
//                additiveMax = v;
//            }
//        }
//        //Мультиплікативний критерій
//        double[] multiplicative = new double[array[0].length - 1];
//        for (int i = 0; i < normal.length; i++) {
//            if (i == 0) {
//                multiplicative[0] = normal[i][0] * normal[i][1];
//                multiplicative[1] = normal[i][0] * normal[i][2];
//                multiplicative[2] = normal[i][0] * normal[i][3];
//                multiplicative[3] = normal[i][0] * normal[i][4];
//            } else {
//                multiplicative[0] *= normal[i][0] * normal[i][1];
//                multiplicative[1] *= normal[i][0] * normal[i][2];
//                multiplicative[2] *= normal[i][0] * normal[i][3];
//                multiplicative[3] *= normal[i][0] * normal[i][4];
//            }
//        }
//        double multiplicativeMax = multiplicative[0];
//        for (double v : multiplicative) {
//            if (multiplicativeMax < v) {
//                multiplicativeMax = v;
//            }
//        }
//        //Максімінний критерій
//        double[][] maxMinArray = new double[normal.length][normal[0].length - 1];
//        for (int i = 0; i < normal.length; i++) {
//            for (int j = 0; j < normal.length + 1; j++) {
//                maxMinArray[i][j] = normal[i][j + 1] / normal[i][0];
//            }
//        }
//        double maxNumber = maxMinArray[0][0];
//        double[] minNumbersOfColumns = new double[maxMinArray.length + 1];
//        for (int i = 0; i < maxMinArray[0].length; i++) {
//            if (i > 2) {
//                maxNumber = maxMinArray[i - 1][i - 1];
//            } else {
//                maxNumber = maxMinArray[i][i];
//            }
//            for (int j = 0; j < maxMinArray.length; j++) {
//                if (maxNumber > maxMinArray[j][i]) {
//                    maxNumber = maxMinArray[j][i];
//                }
//            }
//            minNumbersOfColumns[i] = maxNumber;
//        }
//        maxNumber = minNumbersOfColumns[0];
//        for (double x : minNumbersOfColumns) if (x > maxNumber) maxNumber = x;
//        //Мінімаксний критерій
//        double[][] minMaxArray = new double[normal.length][normal[0].length - 1];
//        for (int i = 0; i < normal.length; i++) {
//            for (int j = 0; j < normal.length + 1; j++) {
//                minMaxArray[i][j] = normal[i][j + 1] / normal[i][0];
//            }
//        }
//        double minNumber = minMaxArray[0][0];
//        double[] maxNumbersOfColumns = new double[minMaxArray.length + 1];
//        for (int i = 0; i < minMaxArray[0].length; i++) {
//            if (i > 2) {
//                minNumber = minMaxArray[i - 1][i - 1];
//            } else {
//                minNumber = minMaxArray[i][i];
//            }
//            for (int j = 0; j < minMaxArray.length; j++) {
//                if (minNumber < minMaxArray[j][i]) {
//                    minNumber = minMaxArray[j][i];
//                }
//            }
//            maxNumbersOfColumns[i] = minNumber;
//        }
//        minNumber = maxNumbersOfColumns[0];
//        for (double x : maxNumbersOfColumns) if (x < minNumber) minNumber = x;
//    }
//}
