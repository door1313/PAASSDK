package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.model.HTTPResult;
import org.junit.Before;
import org.junit.Test;

import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

/**
 * Test for ManagedServers
 */
public class ManagedServersTest {
    private static int BAD_REQUEST_STATUS ;
    private static int NOT_FOUND_STATUS;
    private static int SUCCESS_STATUS;
    private static String WRONG_SERVICE_NAME;
    private static String CORRECT_SERVICE_NAME;
    private static String WRONG_SERVER_NAME;
    private static String CORRECT_SERVER_NAME;
    private static ManagedServers ms;

    @Before
    public void setUp(){
        ms = new ManagedServers();
        ms.getRequester().enableLogger(Level.INFO);
        BAD_REQUEST_STATUS = 400;
        NOT_FOUND_STATUS = 404;
        SUCCESS_STATUS = 200;
        WRONG_SERVICE_NAME = "wrong_service_name";
        CORRECT_SERVICE_NAME = "jiawgaoJCS";
        WRONG_SERVER_NAME = "wrong_server_name";
        CORRECT_SERVER_NAME = "jiawgaoJ_server_6";
    }

    @Test
    public void testGetAllServers() throws Exception {
        HTTPResult result = ms.viewAllServers(WRONG_SERVICE_NAME);
        assertEquals("Status should be Bad Request", BAD_REQUEST_STATUS, result.getStatus());

        result = ms.viewAllServers(CORRECT_SERVICE_NAME);
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());

    }

    @Test
    public void testGetServer() throws Exception {
        HTTPResult result = ms.viewServer(CORRECT_SERVICE_NAME,WRONG_SERVER_NAME);
        assertEquals("Status should be Not Found", NOT_FOUND_STATUS, result.getStatus());

        result = ms.viewServer(CORRECT_SERVICE_NAME,CORRECT_SERVER_NAME);
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());

    }
}
