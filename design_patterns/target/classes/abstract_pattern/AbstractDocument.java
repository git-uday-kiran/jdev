package abstract_pattern;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractDocument implements Document {

	private final Map<String, Object> properties;

	protected AbstractDocument(Map<String, Object> properties) {
		Objects.requireNonNull(properties, "properties can not be null");
		this.properties = properties;
	}

	@Override
	public Object get(String key) {
		return properties.get(key);
	}

	@Override
	public Void put(String key, Object value) {
		properties.put(key, value);
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor) {
		return Stream.ofNullable(get(key)).filter(Objects::nonNull).map(e -> (List<Map<String, Object>>) e).findAny().stream().flatMap(Collection::stream).map(constructor);
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ", "[ ", " ]");
		properties.forEach((key, value) -> sj.add("[" + key + ":" + value + "]"));
		return sj.toString();
	}

}
