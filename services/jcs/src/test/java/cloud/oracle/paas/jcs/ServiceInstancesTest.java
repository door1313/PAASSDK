/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloud.oracle.paas.jcs;
import cloud.oracle.paas.jcs.apis.ManagedServers;
import cloud.oracle.paas.jcs.apis.ServiceInstances;
import cloud.oracle.paas.model.HTTPResult;
import org.junit.Before;
import org.junit.Test;
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

    @Before
    public void setUp(){
        si = new ServiceInstances(true);
        BAD_REQUEST_STATUS = 400;
        NOT_FOUND_STATUS = 404;
        SUCCESS_STATUS = 200;
        WRONG_SERVICE_NAME = "wrong_service_name";
        CORRECT_SERVICE_NAME = "jiawgaoJCS";
        WRONG_SERVER_NAME = "wrong_server_name";
        CORRECT_SERVER_NAME = "jiawgaoJ_server_6";
    }
    
        @Test
    public void testGetAllInstances() throws Exception {
        HTTPResult result = si.viewAllInstances();
        assertEquals("Status should be success", SUCCESS_STATUS, result.getStatus());
    }
}
