package intercorp.retail.kpi.intercorpretail.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.atomic.DoubleAdder;

public class Util {

	private static final SecureRandom secRandom = new SecureRandom();

	public static Long generaId() {
		return Long.valueOf(secRandom.nextInt(99999999));
	}

	public static double acumuladorNumber(List<Integer> numbers) {
		DoubleAdder intanceAcumulacion = new DoubleAdder();
		numbers.parallelStream().forEach(n -> {
			intanceAcumulacion.add(n);
		});
		return intanceAcumulacion.doubleValue();
	}

	public static double promedio(List<Integer> numbers) {
		return acumuladorNumber(numbers) / numbers.size();
	}

	public static double calculateSD(List<Integer> numbers) {
		double mean = promedio(numbers);
		DoubleAdder desAcum = new DoubleAdder();
		numbers.parallelStream().forEach(n -> {
			desAcum.add(Math.pow(n - mean, 2));
		});
		double standardDeviation = acumuladorNumber(numbers);
		return Math.sqrt(standardDeviation / numbers.size());
	}

}
