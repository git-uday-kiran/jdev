package abstract_pattern.domain;

import java.util.Optional;

import abstract_pattern.Document;
import abstract_pattern.domain.enums.Property;

public interface HasPrice extends Document {

	default Void putPrice(Number number) {
		put(Property.PRICE.toString(), number);
		return null;
	}

	default Optional<Number> getPrice() {
		return Optional.ofNullable((Number) get(Property.PRICE.toString()));
	}
}
