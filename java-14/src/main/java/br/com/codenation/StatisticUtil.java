package br.com.codenation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatisticUtil {

    public static int average(int[] elements) {
        return (int) IntStream.of(elements).average().getAsDouble();
    }

    public static int mode(int[] elements) {
        return IntStream.of(elements)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .get();
    }

    public static int median(int[] elements) {
        Arrays.sort(elements);
        int middle = elements.length / 2;
        return elements.length % 2 == 1
                ? elements[middle]
                : Double.valueOf((elements[middle - 1] + elements[middle]) / 2.0).intValue();
    }
}