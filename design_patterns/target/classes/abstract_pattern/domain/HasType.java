package abstract_pattern.domain;

import java.util.Optional;

import abstract_pattern.Document;
import abstract_pattern.domain.enums.Property;

public interface HasType extends Document {

	default Void putType(String type) {
		put(Property.TYPE.toString(), type);
		return null;
	}

	default Optional<String> getType() {
		return Optional.ofNullable((String) get(Property.TYPE.toString()));
	}

}
