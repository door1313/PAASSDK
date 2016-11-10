package cloud.oracle.paas.util;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.config.PropertyLoader;
import cloud.oracle.paas.model.HTTPResult;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Test for HTTPRequester
 */
public class HTTPRequesterTest {
    private static int STATUS;
    private static String CONTENT;
    private static Map<String,Object> HEADERS;

    @Before
    public void setUp(){
        STATUS = 404;
        CONTENT = "{\"status\":\"Can not Complete\",\"details\":{\"message\":\"JAAS-SCALING-027: The provided server name, [aaa], is not a valid managed server name in the service [jiawgaoJCS]\"}}";
        HEADERS = new HashMap<>();
        HEADERS.put("X-Frame-Options","[DENY]");
    }

    @Test
    public void testBuild() throws Exception {

        String uri = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion()) + "/paas/service/jcs/api/v1.1/instances/" + PropertyLoader.getIdentityDomain() + "/jiawgaoJCS/servers/aaa";
        MultivaluedStringMap requestHeaders = new MultivaluedStringMap();
        requestHeaders.putSingle(CommonConstants.TENENTHEADER, PropertyLoader.getIdentityDomain());
        HTTPRequester requester = new HTTPRequester(true);
        HTTPResult result = requester.request(uri, requestHeaders, null, null, CommonConstants.Method.GET);

        assertEquals("Http status should equal", STATUS, result.getStatus());
        assertEquals("Http headers should equal", HEADERS.get("X-Frame-Option"), result.getHeaders().get("X-Frame-Option"));
        assertEquals("Http body should equal", CONTENT, Json.createReader(new StringReader(result.getContent())).readObject().toString());

    }
}
