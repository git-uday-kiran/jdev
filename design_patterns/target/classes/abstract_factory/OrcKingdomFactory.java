package abstract_factory;

public class OrcKingdomFactory implements KingdomFactory {

	@Override
	public Army createArmy() {
		return new OrcArmy();
	}

	@Override
	public King createKing() {
		return new OrcKing();
	}

	@Override
	public Castle createCastle() {
		return new OrcCastle();
	}

}
