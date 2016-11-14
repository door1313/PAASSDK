package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.config.PropertyLoader;
import cloud.oracle.paas.util.HTTPRequester;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

import java.util.logging.Logger;

/**
 * Abstract class for API service. Each api class needs to extend it.
 */
abstract class AbstractJCSAPIService {

    private HTTPRequester requester;
    private String prefixURL;
    private MultivaluedStringMap requestHeaders;

    AbstractJCSAPIService(Boolean enableLogger) {
        requester = new HTTPRequester(enableLogger);
        prefixURL = CommonConstants.USHOST + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";
        if (!"".equals(PropertyLoader.getRegion())){
            String jcsHost = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion());
            if(jcsHost != null){
                prefixURL = jcsHost + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";
            }
        }
        requestHeaders = new MultivaluedStringMap();
        requestHeaders.putSingle(CommonConstants.TENENTHEADER,PropertyLoader.getIdentityDomain());

    }

    public HTTPRequester getRequester() {
        return requester;
    }

    public String getPrefixURL() {
        return prefixURL;
    }

    public MultivaluedStringMap getRequestHeaders() {
        return requestHeaders;
    }
}
