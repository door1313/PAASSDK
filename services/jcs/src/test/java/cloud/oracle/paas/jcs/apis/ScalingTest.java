package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.model.HTTPResult;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;

/**
 * Test for Scaling
 */
public class ScalingTest {
    private static int BAD_REQUEST_STATUS ;
    private static int NOT_FOUND_STATUS;
    private static int SUCCESS_STATUS;
    private static String CLUSTER_NAME;
    private static String SERVICE_NAME;
    private static String WRONG_SERVER_NAME;
    private static String CORRECT_SERVER_NAME;
    private static Scaling scaling;

    @Before
    public void setUp(){
        scaling = new Scaling();
        scaling.getRequester().enableLogger(Level.INFO);
        BAD_REQUEST_STATUS = 400;
        NOT_FOUND_STATUS = 404;
        SUCCESS_STATUS = 202;
        CLUSTER_NAME = "clustertest";
        SERVICE_NAME = "jiawgaoJCS";
        WRONG_SERVER_NAME = "wrong_server_name";
        CORRECT_SERVER_NAME = "jiawgaoJ_server_17";
    }

    @Test
    public void testSaleOutACluster() throws Exception {
        Map<String, String> formData = new HashMap<String, String>();
        formData.put("createClusterIfMissing", "false");
        HTTPResult result = scaling.scaleOutACluster(SERVICE_NAME, CLUSTER_NAME, formData);
        assertEquals("Status should be Bad Request", BAD_REQUEST_STATUS, result.getStatus());

        formData.replace("createClusterIfMissing", "true");
        result = scaling.scaleOutACluster(SERVICE_NAME, CLUSTER_NAME, formData);
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());

    }

    @Test
    public void testScaleANode() throws Exception {
        String payload = "{\"shape\": \"oc4\"}";
        HTTPResult result = scaling.scaleANode(SERVICE_NAME, WRONG_SERVER_NAME, payload);
        assertEquals("Status should be Not Found", NOT_FOUND_STATUS, result.getStatus());

        result = scaling.scaleANode(SERVICE_NAME, CORRECT_SERVER_NAME, payload);
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());

    }

    @Test
    public void testScaleInACluster() throws Exception {
        HTTPResult result = scaling.scaleInACluster(SERVICE_NAME, WRONG_SERVER_NAME);
        assertEquals("Status should be Not Found", NOT_FOUND_STATUS, result.getStatus());

        result = scaling.scaleInACluster(SERVICE_NAME, CORRECT_SERVER_NAME);
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());

    }
}
