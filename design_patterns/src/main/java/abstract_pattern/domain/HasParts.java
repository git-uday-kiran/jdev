package abstract_pattern.domain;

import java.util.stream.Stream;

import abstract_pattern.Document;
import abstract_pattern.domain.enums.Property;

public interface HasParts extends Document {

	default Stream<Part> getParts() {
		return children(Property.PARTS.toString(), Part::new);
	}

}
