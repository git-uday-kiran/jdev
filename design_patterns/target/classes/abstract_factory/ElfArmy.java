package abstract_factory;

public class ElfArmy implements Army {
	private static final String DESCRIPTION = "this is elven army";

	@Override
	public String getDiscription() {
		return DESCRIPTION;
	}
}
