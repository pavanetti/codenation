package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		ArrayList<Integer> sequence = new ArrayList<>(List.of(0, 1));

		int last, penultimate;
		do {
			last = sequence.get(sequence.size() - 1);
			penultimate = sequence.get(sequence.size() - 2);
			sequence.add(last + penultimate);
		} while (last + penultimate < 350);

		return sequence;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}
