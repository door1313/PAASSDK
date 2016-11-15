package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.model.HTTPResult;
import cloud.oracle.paas.util.HTTPRequester;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
/**
 *
 * @author pengw
 */
public class ServiceInstancesTest {
    private static int BAD_REQUEST_STATUS ;
    private static int NOT_FOUND_STATUS;
    private static int SUCCESS_STATUS;
    private static String WRONG_SERVICE_NAME;
    private static String CORRECT_SERVICE_NAME;
    private static String WRONG_SERVER_NAME;
    private static String CORRECT_SERVER_NAME;
    private static ServiceInstances si;
    private static final HTTPRequester requester = new HTTPRequester();

    @Before
    public void setUp(){
        requester.enableLogger(Level.INFO);
        si = new ServiceInstances(requester);
        BAD_REQUEST_STATUS = 400;
        NOT_FOUND_STATUS = 404;
        SUCCESS_STATUS = 200;
        WRONG_SERVICE_NAME = "wrong_service_name";
        CORRECT_SERVICE_NAME = "jiawgaoJCS";
        WRONG_SERVER_NAME = "wrong_server_name";
        CORRECT_SERVER_NAME = "jiawgaoJ_server_6";
    }

    @Test
    public void testViewAllInstances() throws Exception {
        HTTPResult result = si.viewAllInstances();
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());
    }
}
