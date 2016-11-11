package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.config.PropertyLoader;
import cloud.oracle.paas.util.HTTPRequester;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

import java.util.logging.Logger;

/**
 * Abstract class for API service. Each api class needs to extend it.
 */
abstract class AbstractAPIService {

    private HTTPRequester requester;
    private String jcsHost;
    private MultivaluedStringMap requestHeaders;

    AbstractAPIService(Boolean enableLogger) {
        requester = new HTTPRequester(enableLogger);
        jcsHost = CommonConstants.USHOST;
        if (PropertyLoader.getRegion() != null){
            String host = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion());
            if(host != null){
                jcsHost = host;
            }
        }
        requestHeaders = new MultivaluedStringMap();
        requestHeaders.putSingle(CommonConstants.TENENTHEADER,PropertyLoader.getIdentityDomain());

    }

    public HTTPRequester getRequester() {
        return requester;
    }

    public String getJcsHost() {
        return jcsHost;
    }

    public MultivaluedStringMap getRequestHeaders() {
        return requestHeaders;
    }
}
