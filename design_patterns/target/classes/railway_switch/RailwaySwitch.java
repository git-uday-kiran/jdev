package railway_switch;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * RailwaySwitch
 *
 * @author uday.mekala
 */
public class RailwaySwitch {

	/**
	 * Logger for RailwaySwitch
	 */
	private static final Logger LOGGER = LogManager.getLogger(RailwaySwitch.class);

	/**
	 * Main method
	 *
	 * @param args
	 */
	public static void main(String... args) {
		LOGGER.info("Hello, Raiway Switch Design Pattern");

		final String string = getNullString().orElse(getString()).find().orElse(null);
		final Integer integer = getNullInteger().orElse(getInteger()).find().orElse(null);

		LOGGER.info(string);
		LOGGER.info(integer);
	}

	private static StringFinder getString() {
		return () -> Optional.ofNullable("Uday Kiran");
	}

	private static IntegerFinder getInteger() {
		return () -> Optional.ofNullable(1234);
	}

	private static StringFinder getNullString() {
		return () -> Optional.ofNullable(null);
	}

	private static IntegerFinder getNullInteger() {
		return () -> Optional.ofNullable(null);
	}

	private RailwaySwitch() {}
}
