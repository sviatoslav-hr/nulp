//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package asd;

public class Lab2 {
    private static final int[] A = new int[]{2, 1, 7};
    private static final int[] B = new int[]{4, 8, 4};
    private static final int A_INCOME = 5;
    private static final int B_INCOME = 3;
    private static final int[] MAX = new int[]{240, 720, 260};

    private static int f(int var1, int var2) {
        return var1 * A_INCOME + var2 * B_INCOME;
    }

    private static boolean isLessThanMax(int var1, int var2) {
        for (int i = 0; i < A.length; i++) {
            if (var1 * A[i] + var2 * B[i] > MAX[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int x1 = 0;
        int x2 = 0;
        int tempX1 = x1;
        int tempX2 = x2;

        int maxF;
        for (maxF = f(tempX1, tempX2); isLessThanMax(tempX1, tempX2); ++tempX1, tempX2 = 0) {
            for (; isLessThanMax(tempX1, tempX2); ++tempX2) {
                int f = f(tempX1, tempX2);
                if (maxF < f) {
                    maxF = f;
                    x1 = tempX1;
                    x2 = tempX2;
                }
            }
        }
        System.out.println("max\t" + maxF
                + "\nx1\t" + x1
                + "\nx2\t" + x2);
    }
}
