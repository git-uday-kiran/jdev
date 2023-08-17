package abstract_pattern.domain;

import java.util.Optional;

import abstract_pattern.Document;
import abstract_pattern.domain.enums.Property;

public interface HasModel extends Document {

	default Void putModel(String model) {
		put(Property.MODEL.toString(), model);
		return null;
	}

	default Optional<String> getModel() {
		return Optional.ofNullable((String) get(Property.MODEL.toString()));
	}

}
