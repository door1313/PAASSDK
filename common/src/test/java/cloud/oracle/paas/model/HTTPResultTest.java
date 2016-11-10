package cloud.oracle.paas.model;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Test for HTTPResult
 */
public class HTTPResultTest {

    private static int STATUS;
    private static String CONTENT;
    private static Map<String,Object> HEADERS;

    @Before
    public void setUp(){
        STATUS = 404;
        CONTENT = "{\"status\":\"Can not Complete\",\"details\":{\"message\":\"JAAS-SCALING-027: The provided server name, [aaa], is not a valid managed server name in the service [jiawgaoJCS]\"}}";
        HEADERS = new HashMap<>();
        HEADERS.put("X-Frame-Options","DENY");
        HEADERS.put("Access-Control-Allow-Origin","*");
        HEADERS.put("Content-Type","application/json");
    }

    @Test
    public void testHTTPResult() throws Exception {
        InputStream in = getClass().getResourceAsStream("/sample_response.json");
        JsonObject json = Json.createReader(in).readObject();
        Map<String, Object> headers = parseHeaders(json.getJsonObject("headers"));
        HTTPResult httpResult = new HTTPResult(json.getInt("status"), json.getJsonObject("body").toString(), headers);
        assertEquals("Http status should equal", STATUS, httpResult.getStatus());
        assertEquals("Http headers should equal", HEADERS, httpResult.getHeaders());
        assertEquals("Http body should equal", CONTENT, httpResult.getContent());
    }

    private Map<String, Object> parseHeaders(JsonObject jObject){
        Map<String, Object> headers = new HashMap<String, Object>();
        for (String key : jObject.keySet()) {
            headers.put(key, jObject.getString(key));
        }

        return headers;
    }
}
