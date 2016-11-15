package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.model.HTTPResult;
import cloud.oracle.paas.util.HTTPRequester;

import java.util.Map;

/**
 * APIs for Managed servers, see http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/api-Scaling.html
 */
public class Scaling extends AbstractJCSAPIService {

    public Scaling(HTTPRequester requester) {
        super(requester);
    }

    // refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-servers-%7Bname%7D-post.html
    public HTTPResult scaleOutACluster(String serviceName, String cluserName, Map<String, String> formData){
        String uri = getPrefixURL() + serviceName + "/servers/" + cluserName;
        return getRequester().request(uri, getRequestHeaders(), formData, null, null, CommonConstants.Method.POST);
    }

    // refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-servers-%7Bname%7D-delete.html
    public HTTPResult scaleInACluster(String serviceName, String serverName){
        String uri = getPrefixURL() + serviceName + "/servers/" + serverName;
        return getRequester().request(uri, getRequestHeaders(), null, null, null, CommonConstants.Method.DELETE);
    }

    // refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-%7BserverName%7D-put.html
    public HTTPResult scaleANode(String serviceName, String serverName, String payload){
        String uri = getPrefixURL() + serviceName + "/" + serverName;
        return getRequester().request(uri, getRequestHeaders(), null, null, payload, CommonConstants.Method.PUT);
    }
}
