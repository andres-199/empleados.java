import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
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

	public static <T> T filter(Map<Integer, T> array ) {
		return array.values().stream().filter();
	}

}