package cloud.oracle.paas.util;

import cloud.oracle.paas.CommonConstants.Method;
import cloud.oracle.paas.config.PropertyLoader;
import cloud.oracle.paas.model.HTTPResult;
import cloud.oracle.paas.resource.CommonLogger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Generate different kinds of HTTP request
 */
public class HTTPRequester {
    private static final Client httpClient;
    private static final Logger LOGGER = CommonLogger.getLOGGER();


    static {
        int timeout = PropertyLoader.getTimeout();
        ClientConfig configuration = new ClientConfig();
        if( timeout != 1){
            configuration.property(ClientProperties.CONNECT_TIMEOUT, timeout);
            configuration.property(ClientProperties.READ_TIMEOUT, timeout);
        }
        if(!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())){
            System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());
            System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());
        }
        if(!"".equals(PropertyLoader.getProxyUser()) && !"".equals(PropertyLoader.getProxyPassword())){
            configuration.property(ClientProperties.PROXY_USERNAME, PropertyLoader.getProxyUser())
                    .property(ClientProperties.PROXY_PASSWORD, PropertyLoader.getProxyPassword());
        }
        httpClient = ClientBuilder.newClient(configuration);

        if(!"".equals(PropertyLoader.getUser()) && !"".equals(PropertyLoader.getPassword())){
            httpClient.register(HttpAuthenticationFeature.basic(PropertyLoader.getUser(), PropertyLoader.getPassword()));
        }

    }

    public HTTPRequester(Boolean enableLogger) {
        if(enableLogger){
            Feature feature = new LoggingFeature(LOGGER, Level.INFO, null, null);
            httpClient.register(feature);
        }
    }

    public HTTPResult request(String uri, MultivaluedMap<String, String> headers, Map<String, String> params, String payload, Method method) throws Exception {

        WebTarget target = httpClient.target(uri);
        buildParams(target, params);
        Invocation.Builder builder = target.request();
        buildHeaders(builder, headers);
        if(method == null){
            throw new Exception("Missing method");
        }
        Response response;
        switch (method){
            case GET:
                response = builder.get();
                break;
            case POST:
                response = builder.post(Entity.json(payload));
                break;
            case PUT:
                response = builder.put(Entity.json(payload));
                break;
            case DELETE:
                response = builder.delete();
                break;
            default:
                response = builder.get();
        }
        return buildResult(response);
    }

    private void buildParams(WebTarget target, Map<String, String> params){
        if (params != null && !params.isEmpty()){
            for(Map.Entry<String, String> entry : params.entrySet()){
                target = target.queryParam(entry.getKey(),entry.getValue());
            }
        }
    }

    private void buildHeaders(Invocation.Builder builder, MultivaluedMap<String, String> headers){
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();

                for (String value : values)
                    builder = builder.header(key, value);
            }
        }
    }

    private HTTPResult buildResult(Response response){
        int status = response.getStatus();
        Map respHeaders = response.getHeaders();
        String content = (response.hasEntity() ? response.readEntity(String.class) : "");
        return new HTTPResult(status, content, respHeaders);
    }
}
