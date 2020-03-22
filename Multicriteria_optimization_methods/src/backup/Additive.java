package backup;

class Additive {
    int getAdditive(double[][] normal) {
        double[] additive = new double[normal[0].length - 1];
        for (int i = 1; i < normal[0].length; i++)
            for (double[] doubles : normal) additive[i - 1] += doubles[0] * doubles[i];
        double additiveMax = additive[0];
        int machine = 0;
        for (int i = 0; i < additive.length; i++)
            if (additiveMax < additive[i]) {
                additiveMax = additive[i];
                machine = i + 1;
            }
//        return additiveMax;
        return machine;
    }
}
