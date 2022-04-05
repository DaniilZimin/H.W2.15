package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        IntegerListImpl integerList = new IntegerListImpl();
        IntegerList integerList1 = new IntegerListImpl();
        Random random = new Random();

        while (integerList.size() < 100) {
            integerList.add(random.nextInt(100));
        }

        while (integerList1.size() < 100) {
            integerList1.add(random.nextInt(100));
        }

        System.out.println(integerList.add(4, 5));
        System.out.println(integerList.set(7, 8));
        System.out.println(integerList.remove(1));
        System.out.println(integerList.removeInd(9));
        System.out.println(integerList.contains(5));
        System.out.println(integerList.indexOf(3));
        System.out.println(integerList.lastIndexOf(7));
        System.out.println(integerList.get(4));
        System.out.println(integerList.size());
        System.out.println(integerList.isEmpty());
        System.out.println(Arrays.toString(integerList.toArray()));
        integerList.clear();
        System.out.println(Arrays.toString(integerList.toArray()));
        System.out.println(integerList.equals(integerList1));
    }
}
