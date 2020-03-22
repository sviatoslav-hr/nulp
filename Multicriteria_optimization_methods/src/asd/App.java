package asd;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final double I = 6;
    public static final double Ngroup = 416;
    public static final double N = 2;
    public static double[] coordinate = new double[(int) N];
    public static double M = round(1.65 * N + 0.05 * pow(N, 2));
    public static final double epsilon = 0.01;
    public static double alpha;
    public static double delta1;
    public static double delta2;
    public static boolean isFound = false;
    public static int index = 0;
    public static int oldIndex;
    public static int iteration = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Вводимо вхідні дані: початкову точку х(0) і масштабний множник
        System.out.print("Введіть X: ");
        coordinate[0] = scan.nextDouble();
        System.out.print("Введіт ь Y: ");
        coordinate[1] = scan.nextDouble();
        System.out.print("Введіть масштабний множник α (рекомендуєтьсья 2): ");
        alpha = scan.nextDouble();
        //Обчислюємо прирости
        delta1 = ((sqrt(N + 1) + N - 1) / (N * sqrt(2))) * alpha;
        delta2 = ((sqrt(N + 1) - 1) / (N * sqrt(2))) * alpha;
        //Перший вузол симплекса
        List<Coordinate> symplex = new ArrayList<>();
        symplex.add(new Coordinate(coordinate[0], coordinate[1]));
        //Редукція
        while (!isFound) {
            iteration++;
            //Обчислюємо координати інших N вершин симплекса
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j != i) coordinate[j] = symplex.get(index).get(j) + delta1;
                    else if (j == i) coordinate[j] = symplex.get(index).get(j) + delta2;
                }
                symplex.add(new Coordinate(coordinate[0], coordinate[1]));
            }
            //Визначаємо значення цільової функції  у вершинах симплекса
            double[] zValues = new double[symplex.size()];
            for (int i = 0; i < symplex.size(); i++)
                zValues[i] = Z(symplex.get(i).getX(), symplex.get(i).getY());
            //Визначаємо вершину з максимальним значенням цільової функції
            oldIndex = index;
            index = maxIndex(zValues);
            coordinate[0] = 0;
            coordinate[1] = 0;
            Coordinate newX = new Coordinate();
            //Нова вершина з максимальним значенням
            while (true) {
                iteration++;
                //Визначається координата нової точки, відображеної відносного центра ваги
                for (int i = 0; i < symplex.size(); i++) {
                    if (i == index) {
                        newX.setX(newX.getX() - symplex.get(index).getX());
                        newX.setY(newX.getY() - symplex.get(index).getY());
                    } else if (i != index) {
                        newX.setX(newX.getX() + symplex.get(i).getX());
                        newX.setY(newX.getY() + symplex.get(i).getY());
                    }
                }
                symplex.set(oldIndex, newX);
                //Перевіряємо умову завершення пошуку
                //Пошук завершується, коли або розміри симплекса, або різниці між значеннями функції у вершинах стають досить малими
                if (abs(Z(newX.getX(), newX.getY()) - min(zValues)) < epsilon || symplex.size() == 1) {
                    isFound = true;
                    System.out.println("Результат:\nX = " + newX.getX() + "\nY = " + newX.getY() + "\nZ = " + Z(newX.getX(), newX.getY()) + "\nКількість ітерацій: " + iteration);
                    break;
                }
                //Перевіряємо умову «Накриття» точки мінімуму
                if (Z(newX.getX(), newX.getY()) == zValues[oldIndex]) {
                    index = prevMaxIndex(zValues);
                    newX.setX(symplex.get(index).getX());
                    newX.setY(symplex.get(index).getY());
                    continue;
                }
                //Перевіряємо умову циклічності руху
                else if (loopRule(symplex)) {
                    alpha /= 2;
                    delta1 = ((sqrt(N + 1) + N - 1) / (N * sqrt(2))) * alpha;
                    delta2 = ((sqrt(N + 1) - 1) / (N * sqrt(2))) * alpha;
                    //Побудова нового симлексу
                    newX = new Coordinate(symplex.get(minIndex(zValues)));
                    symplex.clear();
                    symplex.add(newX);
                    index = 0;
                    break;
                }
                //Ітерування точок
                for (int i = 0; i < symplex.size(); i++)
                    symplex.get(i).increaseUsage();
            }
            if (isFound) break;
        }
    }

    //Цільова функція
    public static double Z(double x, double y) {
        return pow((x + I), 2) + pow((y + I / 2), 2) - x * y / Ngroup;
    }

    //Пошук максимального значення в масиві
    public static double max(double[] arr) {
        double maximum = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > maximum) maximum = arr[i];
        return maximum;
    }

    //Пошук мінімального значення в масиві
    public static double min(double[] arr) {
        double minimum = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < minimum) minimum = arr[i];
        return minimum;
    }

    //Пошук індексу максимального значення масиву
    public static int maxIndex(double[] arr) {
        int index = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > arr[index]) index = i;
        return index;
    }

    //Пошук індексу мінімального значення масиву
    public static int minIndex(double[] arr) {
        int index = 0;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[index]) index = i;
        return index;
    }

    //Пошук передостаннього максимального значення масиву
    public static int prevMaxIndex(double[] arr) {
        int index = 0;
        int prevIndex = index;
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > arr[index]) {
                prevIndex = index;
                index = i;
            }
        return prevIndex;
    }

    //Правило циклічності
    public static boolean loopRule(List<Coordinate> symplex) {
        for (int i = 0; i < symplex.size(); i++)
            if (symplex.get(i).getUsage() >= M) return true;
        return false;
    }
}

//Клас, що формує точку
class Coordinate {
    private double x;
    private double y;
    private int usage;

    public Coordinate() {
        x = 0;
        y = 0;
        usage = 1;
    }

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
        usage = 1;
    }

    public Coordinate(Coordinate c) {
        x = c.x;
        y = c.y;
        usage = c.usage;
    }

    public double get(int index) {
        switch (index) {
            case 0:
                return x;
            case 1:
                return y;
        }
        return 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getUsage() {
        return usage;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void increaseUsage() {
        usage++;
    }
}

