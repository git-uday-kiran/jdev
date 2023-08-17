package abstract_factory;

public class Kingdom {

	private King king;
	private Army army;
	private Castle castle;

	static class FactoryMaker {
		enum KingdomType {
			ELV, ORC;
		}
		public static KingdomFactory makeFactory(KingdomType type) {
			switch (type) {
				case ELV :
					return new ElfKingdomFactory();
				case ORC :
					return new OrcKingdomFactory();
				default :
					throw new IllegalArgumentException("Unsupported kingdom type: " + type);
			}
		}
	}

	public King getKing() {
		return king;
	}

	public void setKing(King king) {
		this.king = king;
	}

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		this.army = army;
	}

	public Castle getCastle() {
		return castle;
	}

	public void setCastle(Castle castle) {
		this.castle = castle;
	}

}
