package intercorp.retail.kpi.intercorpretail.util;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.DoubleAdder;

public class Util {

	private static final Random random = new Random(99999999999L);

	public static Long generaId() {
		return Long.valueOf(random.nextInt());
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
