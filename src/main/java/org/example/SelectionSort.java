package org.example;

import java.util.Arrays;

public class SelectionSort {



    public static void main(String[] args) {

        int[] array = new int[25];
        for (int i = 0; i < array.length; i++){
            array[i] = (int) (Math.random() * 100);
        }

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(selectionSort(array)));
    }

    public static int[] selectionSort(int[] input){
        int smallest;
        for (int i = 0; i < input.length; i++) {
            smallest = i;
            for (int j = i; j < input.length; j++) {
                if (input[j] < input[smallest]) {
                    smallest = j;
                }
            }
            int buffer = input[i];
            input[i] = input[smallest];
            input[smallest] = buffer;

        }
        return input;
    }
}
