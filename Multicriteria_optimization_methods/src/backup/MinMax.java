package backup;

class MinMax {
    //    double getMinMax(double[][] normal) {
    int getMinMax(double[][] normal) {
        double[][] minMaxArray = new double[normal.length][normal[0].length - 1];
        for (int i = 0; i < normal.length; i++)
            for (int j = 0; j < normal.length + 1; j++) minMaxArray[i][j] = normal[i][j + 1] / normal[i][0];
        double minNumber;
        double[] maxNumbersOfColumns = new double[minMaxArray.length + 1];
        for (int i = 0; i < minMaxArray[0].length; i++) {
            if (i > 2) minNumber = minMaxArray[i - 1][i - 1];
            else minNumber = minMaxArray[i][i];
            for (double[] doubles : minMaxArray) if (minNumber < doubles[i]) minNumber = doubles[i];
            maxNumbersOfColumns[i] = minNumber;
        }
        minNumber = maxNumbersOfColumns[0];
        int machine = 0;
        for (int i = 0; i < maxNumbersOfColumns.length; i++)
            if (maxNumbersOfColumns[i] < minNumber) {
                minNumber = maxNumbersOfColumns[i];
                machine = i + 1;

            }
//        return minNumber;
        return machine;
    }
}
