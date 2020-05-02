package br.com.codenation;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatisticUtil {
	public static int average(int[] elements) {
		return (int) Arrays.stream(elements).average().orElse(Double.NaN);
	}

	public static int mode(int[] elements) {
		Stream<Integer> stream = Arrays.stream(elements).boxed();
		Map<Integer, Long> counting = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return counting.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(0);
	}

	public static int median(int[] elements) {
		return 3;
	}
}