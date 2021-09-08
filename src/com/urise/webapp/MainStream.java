package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        System.out.println(minValue(new int[]{9, 8}));
        System.out.println(oddOrEven(Arrays.asList(2, 8, 9, 2, 5, 7)));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (a, b) -> 10 * a + b);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> map = integers.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0, Collectors.toList()));
        return map.get(map.get(false).size() % 2 != 0);
    }
}