package abstract_factory;

public class ElfKing implements King {
	private static final String DESCRIPTION = "this is elven king";

	@Override
	public String getDiscription() {
		return DESCRIPTION;
	}
}
