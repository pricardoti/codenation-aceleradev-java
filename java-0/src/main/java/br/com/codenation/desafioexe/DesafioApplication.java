package br.com.codenation.desafioexe;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DesafioApplication {

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

	public static Integer fibonacci(int number) {
		return (number == 0 || number == 1)
				? number
				: fibonacci(number - 1) + fibonacci(number - 2);
	}

	public static List<Integer> fibonacci() {
		return IntStream.range(0, 15).mapToObj(DesafioApplication::fibonacci).collect(Collectors.toList());
	}

}