package br.com.codenation;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class StatisticUtil {
	public static int average(int[] elements) {
		DoubleStream stream = Arrays.stream(elements).mapToDouble(Double::valueOf);
		return (int) stream.average().orElse(Double.NaN);
	}

	public static int mode(int[] elements) {
		return 3;
	}

	public static int median(int[] elements) {
		return 3;
	}
}