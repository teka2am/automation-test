package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;

public class Log {
    private Log() {}

    private static Logger logger = LogManager.getLogger(lookup().lookupClass());

    public static void info(String message) {
        logger.info(message);
    }

    public static void warning(String message) {
        logger.warn(message);
    }

    public static void error(String message, String errorMessage) {
        logger.error(message + System.getProperty("line.separator") + errorMessage);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
