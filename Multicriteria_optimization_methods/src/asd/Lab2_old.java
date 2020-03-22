//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package asd;

public class Lab2_old {
    public static final int[] A = new int[]{9, 8, 16};
    public static final int[] B = new int[]{5, 7, 4};
    public static final int Agrowth = 3;
    public static final int Bgrowth = 2;
    public static final int[] max = new int[]{1200, 600, 400};
    public static int x1 = 0;
    public static int x2 = 0;

    public Lab2_old() {
    }

    public static int F(int var1, int var2) {
        return var1 * 3 + var2 * 2;
    }

    public static boolean condition(int var1, int var2) {
        return var1 * A[0] + var2 * B[0] <= max[0] && var1 * A[1] + var2 * B[1] <= max[1] && var1 * A[2] + var2 * B[2] <= max[2];
    }

    public static void main(String[] args) {
        int tempX1 = x1;
        int tempX2 = x2;

        int maxF;
        for(maxF = F(tempX1, tempX2); condition(tempX1, tempX2); tempX2 = 0) {
            for(; condition(tempX1, tempX2); ++tempX2) {
                if (maxF < F(tempX1, tempX2)) {
                    maxF = F(tempX1, tempX2);
                    x1 = tempX1;
                    x2 = tempX2;
                }
            }

            ++tempX1;
        }

        System.out.println(" - Математична відповідь: Максимум цільової функції F досягає " + maxF + " одинииць коли змінні x1 = " + x1 + " та x2 = " + x2 + " одиниць.\n");
        System.out.println(" - Відповідь на задачу: Максимальний приріст ваги тварин становить " + maxF + " виробляючи " + x1 + " тонн культури А та " + x2 + " тонн культури В.\n");
    }
}
