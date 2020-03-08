package ua.nulp;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Test {

    /**
     * Main method that runs when the program is started.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        List<Integer> integers = Arrays.asList(1, 435, 324, 21, 0, -11);
        List<String> strings = Arrays.asList("gf", "dsf", "asd", "yls", "sdf", "osd");
        System.out.println("integers = " + integers);
        System.out.println("strings = " + strings);
        priorityQueue.addAll(integers);
        System.out.println("priorityQueue = " + priorityQueue);
        while (!priorityQueue.isEmpty()) {
            System.out.println("priorityQueue = " + priorityQueue.remove());
        }
    }
}