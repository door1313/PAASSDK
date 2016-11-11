package cloud.oracle.paas.jcs;

import cloud.oracle.paas.jcs.apis.ManagedServers;
import cloud.oracle.paas.model.HTTPResult;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for ManagedServers
 */
public class ManagedServersTest {
    private static int ERROR_STATUS ;
    private static int SUCCESS_STATUS;
    private static String WRONG_SERVICE_NAME;
    private static String CORRECT_SERVICE_NAME;
    private static String WRONG_SERVER_NAME;
    private static String CORRECT_SERVER_NAME;
    private static ManagedServers ms;

    @Before
    public void setUp(){
        ms = new ManagedServers(true);
        ERROR_STATUS = 400;
        SUCCESS_STATUS = 200;
        WRONG_SERVICE_NAME = "wrong_service_name";
        CORRECT_SERVICE_NAME = "jiawgaoJCS";
        WRONG_SERVER_NAME = "wrong_server_name";
        CORRECT_SERVER_NAME = "jiawgaoJ_server_6";
    }

    @Test
    public void testGetAllServers() throws Exception {
        HTTPResult result = ms.getAllServers(WRONG_SERVICE_NAME);
        assertEquals("Status should be error", ERROR_STATUS, result.getStatus());

        result = ms.getAllServers(CORRECT_SERVICE_NAME);
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());

    }
}
