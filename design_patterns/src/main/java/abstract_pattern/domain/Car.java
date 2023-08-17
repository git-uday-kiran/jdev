package abstract_pattern.domain;

import java.util.Map;

import abstract_pattern.AbstractDocument;

public class Car extends AbstractDocument implements HasModel, HasType, HasParts, HasPrice {

	public Car(Map<String, Object> properties) {
		super(properties);
	}

}
