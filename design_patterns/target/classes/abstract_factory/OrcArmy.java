package abstract_factory;

public class OrcArmy implements Army {

	private static final String DESCRIPTION = "this is orc army";

	@Override
	public String getDiscription() {
		return DESCRIPTION;
	}

}
