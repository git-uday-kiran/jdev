package abstract_factory;

import abstract_factory.Kingdom.FactoryMaker.KingdomType;

public class App implements Runnable {

	private final Kingdom kingdom = new Kingdom();

	public static void main(String... args) {
		App app = new App();
		app.createKingdom(KingdomType.ELV);

		app.run();
	}

	@Override
	public void run() {
		System.out.println(kingdom.getClass().getName());
		System.out.println(kingdom.getArmy().getDiscription());
		System.out.println(kingdom.getCastle().getDiscription());
		System.out.println(kingdom.getKing().getDiscription());
	}

	public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
		KingdomFactory factory = Kingdom.FactoryMaker.makeFactory(kingdomType);
		kingdom.setArmy(factory.createArmy());
		kingdom.setCastle(factory.createCastle());
		kingdom.setKing(factory.createKing());
	}

}
