package chain_of_responsibility;

import chain_of_responsibility.Logger.Level;

/**
 * Logger with console and file implementation
 *
 * @author uday.mekala
 */
interface Logger {

	/**
	 * Prints the message with level and message
	 *
	 * @param level
	 * @param message
	 */
	void log(Level level, String message);

	/**
	 * Console Logger
	 *
	 * @return {@code Logger}
	 */
	static Logger console() {
		return (level, msg) -> {
			System.out.printf("CONSOLE %s : %s %n", level, msg);
		};
	}

	/**
	 * File Logger
	 *
	 * @return {@code Logger}
	 */
	static Logger file() {
		return (level, msg) -> {
			System.out.printf("FILE %s : %s %n", level, msg);
		};
	}

	/**
	 * Gives the new {@code Logger} instance with given {@code Level}
	 *
	 * @param level
	 * @return {@code Logger}
	 */
	default Logger withLevel(final Level level) {
		return (msgLevel, msg) -> {
			if (msgLevel.compareTo(level) >= 0) {
				log(level, msg);
			}
		};
	}

	/**
	 * Gives the new combined {@code Logger} of current {@code Logger} and given
	 * {@code Logger}
	 *
	 * @param logger
	 * @return {@code Logger}
	 */
	default Logger withLogger(final Logger logger) {
		return  (level, msg) -> {
			log(level, msg);
			logger.log(level, msg);
		};
	}

	/**
	 * Level of the {@code Logger}
	 *
	 * @author uday.mekala
	 */
	enum Level {
		DEBUG, INFO, WARN, ERROR, FATAL;
	}
}

/**
 * ChainOfResponsibility Design Pattern
 * @author uday.mekala
 */
public class ChainOfResponsibility {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String... args) {
		final Logger logger = Logger.console().withLogger(Logger.file());
		logger.log(Level.INFO, "checking error message");
		logger.log(Level.DEBUG, "debug message");

		Logger.console().log(Level.INFO, "----------------------------");

		final Logger logger2 = Logger.console().withLevel(Level.INFO);
		logger2.log(Level.DEBUG, "checking degug, it should not print");
		logger2.log(Level.INFO, "it should print");
	}

	private ChainOfResponsibility() {}
}
