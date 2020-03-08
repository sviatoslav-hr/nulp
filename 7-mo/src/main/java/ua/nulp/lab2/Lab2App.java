package ua.nulp.lab2;

public class Lab2App {
    private static final int[] A_VALUES = new int[]{2, 1, 7};
    private static final int[] B_VALUES = new int[]{4, 8, 4};
    private static final int A_INCOME = 5;
    private static final int B_INCOME = 3;
    private static final int[] MAX_VALUES = new int[]{240, 720, 260};

    public static void main(String[] args) {
        solve();
    }

    private static void solve() {
        int x1 = 0;
        int x2 = 0;
        int tempX1 = x1;
        int tempX2 = x2;

        int max;
        for (max = f(tempX1, tempX2);
             isLessThanMax(tempX1, tempX2);
             tempX1++, tempX2 = 0) {
            for (; isLessThanMax(tempX1, tempX2); tempX2++) {
                int f = f(tempX1, tempX2);
                if (f > max) {
                    max = f;
                    x1 = tempX1;
                    x2 = tempX2;
                }
            }
        }
        System.out.printf("max\t%d"
                + "\nx1\t%d"
                + "\nx2\t%d",
                max, x1, x2);
    }

    private static int f(int var1, int var2) {
        return var1 * A_INCOME + var2 * B_INCOME;
    }

    private static boolean isLessThanMax(int var1, int var2) {
        for (int i = 0; i < A_VALUES.length; i++) {
            if (var1 * A_VALUES[i] + var2 * B_VALUES[i] > MAX_VALUES[i]) {
                return false;
            }
        }
        return true;
    }
}
