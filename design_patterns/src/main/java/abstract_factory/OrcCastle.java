package abstract_factory;

public class OrcCastle implements Castle {

	private static final String DESCRIPTION = "this is orc castle";

	@Override
	public String getDiscription() {
		return DESCRIPTION;
	}

}
