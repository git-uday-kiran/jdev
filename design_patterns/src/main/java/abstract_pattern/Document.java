package abstract_pattern;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Document {

	Object get(String key);

	Void put(String key, Object val);

	/**
	 * 
	 * @param <T>
	 * @param key
	 * @param constructor constructor of child class
	 * @return child documents
	 */
	<T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor);

}
