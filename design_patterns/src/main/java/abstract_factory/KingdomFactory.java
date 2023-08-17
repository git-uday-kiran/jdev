package abstract_factory;

public interface KingdomFactory {
	Army createArmy();
	King createKing();
	Castle createCastle();
}
