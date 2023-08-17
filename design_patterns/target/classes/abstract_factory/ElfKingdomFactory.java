package abstract_factory;

public class ElfKingdomFactory implements KingdomFactory {

	@Override
	public Army createArmy() {
		return new ElfArmy();
	}

	@Override
	public King createKing() {
		return new ElfKing();
	}

	@Override
	public Castle createCastle() {
		return new ElfCastle();
	}

}
