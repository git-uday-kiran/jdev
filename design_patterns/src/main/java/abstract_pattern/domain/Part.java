package abstract_pattern.domain;

import java.util.Map;

import abstract_pattern.AbstractDocument;

public class Part extends AbstractDocument implements HasPrice, HasModel, HasType {
	protected Part(Map<String, Object> properties) {
		super(properties);
	}
}
