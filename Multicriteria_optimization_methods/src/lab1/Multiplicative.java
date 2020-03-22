package lab1;

class Multiplicative {
    int getMultiplicative(double[][] normal) {
        double[] multiplicative = new double[normal[0].length - 1];
        for (int i = 1; i < normal[0].length; i++)
            for (int j = 0; j < normal.length; j++)
                if (j == 0) multiplicative[i - 1] = normal[j][0] * normal[j][i];
                else multiplicative[i - 1] *= normal[j][0] * normal[j][i];
        double multiplicativeMax = multiplicative[0];
        int machine = 0;
        for (int i = 0; i < multiplicative.length; i++)
            if (multiplicativeMax < multiplicative[i]) {
                multiplicativeMax = multiplicative[i];
                machine = i + 1;
            }
        return machine;
    }
}
