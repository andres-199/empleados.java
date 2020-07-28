import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter {

	public static <T> T max(Map<Integer, T> array, Comparator<T> comparator) {
		return array.values().stream().max(comparator).orElseThrow(NoSuchElementException::new);
	}

	public static <T> T min(Map<Integer, T> array, Comparator<T> comparator) {
		return array.values().stream().min(comparator).orElseThrow(NoSuchElementException::new);
	}

	public static <T> List<T> sort(Map<Integer, T> array, Comparator<T> comparator) {
		return array.values().stream().sorted(comparator).collect(Collectors.toList());
	}

	public static <T, R> R propertySummary(Map<Integer, T> array, Predicate<T> predicate, Function<T, R> mapper,
			R initialValue, BinaryOperator<R> reducer) {
		return array.values().stream().filter(predicate).map(mapper).reduce(initialValue, reducer);
	}

	public static <T> Long countElements(Map<Integer, T> array, Predicate<T> predicate) {
		return array.values().stream().filter(predicate).count();
	}

	public static <T> List<T> sortAndLimit(Map<Integer, T> array, Comparator<T> comparator, Long limit) {
		return array.values().stream().sorted(comparator).limit(limit).collect(Collectors.toList());
	}

}