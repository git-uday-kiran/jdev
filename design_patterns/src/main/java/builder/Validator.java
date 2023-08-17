package builder;

import java.util.Objects;

public class Validator {

	private boolean validateState;

	public Validator(boolean validateState) {
		this.validateState = validateState;
	}

	public <T> Validator equals(T source, T target) {
		setValidateState(Objects.deepEquals(target, target));
		return this;
	}

	public <T> Validator notEquals(T source, T target) {
		setValidateState(!Objects.deepEquals(source, target));
		return this;
	}

	public boolean getValidateState() {
		return validateState;
	}

	private void setValidateState(boolean validateState) {
		this.validateState = this.validateState ? validateState : this.validateState;
	}

	public static Validator newInstance() {
		return new Validator(true);
	}
}