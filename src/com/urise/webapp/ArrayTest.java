package com.urise.webapp;

public class ArrayTest {

    public static void main(String[] args) {

        int[] array = new int[8];

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) ((Math.random() * 9) - 1);
        }

        for (int i = 0; i < array.length; i++) {
            if (i % 2 != 0) {
                array[i] = 0;
            }
            System.out.println(array[i]);
        }
    }
}
