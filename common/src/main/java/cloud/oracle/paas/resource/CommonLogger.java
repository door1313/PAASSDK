package cloud.oracle.paas.resource;

import java.util.logging.Logger;

/**
 * Common Logger
 */
public class CommonLogger {
    private static Logger LOGGER = Logger.getLogger("cloud.oracle.paas.common");

    private CommonLogger(){
    }

    public static Logger getLOGGER(){
        return LOGGER;
    }
}
