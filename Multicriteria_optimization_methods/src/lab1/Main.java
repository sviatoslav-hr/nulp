package lab1;

public class Main {
    public static void main(String[] args) {
        Data data = new Data();
        Normal normal = new Normal();
        Multiplicative multiplicative = new Multiplicative();
        double[][] array = data.getArray();
        double[][] normalArray = normal.getNormal(array);

        int resultMultiplicative = multiplicative.getMultiplicative(normalArray);
        System.out.println("resultMultiplicative = " + resultMultiplicative);
    }
}
