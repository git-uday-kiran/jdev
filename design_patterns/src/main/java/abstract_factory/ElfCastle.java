package abstract_factory;

public class ElfCastle implements Castle {
	private static final String DESCRIPTION = "this is elven castle";

	@Override
	public String getDiscription() {
		return DESCRIPTION;
	}
}
