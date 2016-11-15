package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.config.PropertyLoader;
import cloud.oracle.paas.model.HTTPResult;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

/**
 * Created by thguo
 */
public class ServiceInstances extends AbstractAPIService {

    private final String uriPrefix = getJcsHost() + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";

    public ServiceInstances(Boolean enableLogger) {
        super(enableLogger);
    }

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-post.html
    public HTTPResult createInstance(String serviceId, String payload) {
        String uri = uriPrefix + serviceId;
        return getRequester().request(uri, getRequestHeadersWithJson(), null, payload, CommonConstants.Method.POST);
    }

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-put.html
    public HTTPResult deleteInstance(String serviceId, String payload) {
        String uri = uriPrefix + serviceId;
        return getRequester().request(uri, getRequestHeadersWithJson(), null, payload, CommonConstants.Method.PUT);
    }

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-post.html
    public HTTPResult manageInstance(String serviceId, String payload) {
        String uri = uriPrefix + serviceId;
        return getRequester().request(uri, getRequestHeadersWithJson(), null, payload, CommonConstants.Method.POST);
    }
    
    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-get.html
    public HTTPResult viewAllInstances() {
        String uri = uriPrefix;
        return getRequester().request(uri, getRequestHeaders(), null, null, CommonConstants.Method.GET);
    }

    //Refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-get.html
    public HTTPResult viewInstance(String serviceId) {
        String uri = uriPrefix + serviceId;
        return getRequester().request(uri, getRequestHeaders(), null, null, CommonConstants.Method.GET);
    }
    
    private MultivaluedStringMap getRequestHeadersWithJson(){
        MultivaluedStringMap requestHeaders = getRequestHeaders();
        requestHeaders.putSingle(CommonConstants.CONTENTTYPE, CommonConstants.MEDIATYPE);
        return requestHeaders;
    }
}
