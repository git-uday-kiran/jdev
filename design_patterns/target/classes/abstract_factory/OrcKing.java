package abstract_factory;

public class OrcKing implements King {

	private static final String DESCRIPTION = "this is orc king";

	@Override
	public String getDiscription() {
		return DESCRIPTION;
	}

}
