package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.model.HTTPResult;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;

/**
 * APIs for Service Instance, see http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/api-Service%20Instances.html
 */
public class ServiceInstances extends AbstractJCSAPIService {

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-post.html
    public HTTPResult createInstance(String serviceName, String payload) {
        String uri = getPrefixURL() + serviceName;
        return getRequester().request(uri, buildHeaderWithContentType(), null, null, payload, CommonConstants.Method.POST);
    }

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-put.html
    public HTTPResult deleteInstance(String serviceName, String payload) {
        String uri = getPrefixURL() + serviceName;
        return getRequester().request(uri, buildHeaderWithContentType(), null, null, payload, CommonConstants.Method.PUT);
    }

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-post.html
    public HTTPResult stopAndStartInstance(String serviceName, String payload) {
        String uri = getPrefixURL() + serviceName;
        return getRequester().request(uri, buildHeaderWithContentType(), null, null, payload, CommonConstants.Method.POST);
    }

    //Refer to https://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-get.html
    public HTTPResult viewAllInstances() {
        String uri = getPrefixURL();
        return getRequester().request(uri, getRequestHeaders(), null, null, null, CommonConstants.Method.GET);
    }

    //Refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-get.html
    public HTTPResult viewInstance(String serviceName) {
        String uri = getPrefixURL() + serviceName;
        return getRequester().request(uri, getRequestHeaders(), null, null, null, CommonConstants.Method.GET);
    }
    private MultivaluedStringMap buildHeaderWithContentType(){
        MultivaluedStringMap requestHeaders = getRequestHeaders();
        requestHeaders.putSingle(CommonConstants.CONTENTTYPE, CommonConstants.ORACLEJSONMEDIATYPE);
        return requestHeaders;
    }
}
