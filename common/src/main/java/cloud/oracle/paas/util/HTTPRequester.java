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
import javax.ws.rs.core.*;
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

    public HTTPResult request(String uri, MultivaluedMap<String, String> headers, Map<String, String> params, Map<String, String> formData, String payload, Method method)  {

        WebTarget target = httpClient.target(uri);
        target = buildURIWithParams(target, params);
        Invocation.Builder builder = target.request();
        buildHeaders(builder, headers);
        if(method == null){
            LOGGER.warning("Missing method");
            return null;
        }
        Response response;
        switch (method){
            case GET:
                response = builder.get();
                break;
            case POST:
                if (formData != null && !formData.isEmpty()){
                    Form form = new Form();
                    for (Map.Entry<String,String> entry: formData.entrySet()){
                        form.param(entry.getKey(),entry.getValue());
                    }
                    response = builder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
                }else {
                    response = builder.post(Entity.json(payload));
                }
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

    private WebTarget buildURIWithParams(WebTarget wt, Map<String, String> params){

        if (params != null && !params.isEmpty()){
            for(Map.Entry<String, String> entry : params.entrySet()){
                wt = wt.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return wt;
    }

    private void buildHeaders(Invocation.Builder builder, MultivaluedMap<String, String> headers){
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();

                for (String value : values)
                    builder.header(key, value);
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
