package cloud.oracle.paas.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.model.HTTPResult;
import cloud.oracle.paas.util.HTTPRequester;

/**
 * APIs for Managed servers, see http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/api-Managed%20Servers.html
 */
public class ManagedServers extends AbstractJCSAPIService {

    public ManagedServers(HTTPRequester requester) {
        super(requester);
    }

    // refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-servers-get.html
    public HTTPResult viewAllServers(String serviceName){
        String uri = getPrefixURL() + serviceName + "/servers";
        return getRequester().request(uri, getRequestHeaders(), null, null, null, CommonConstants.Method.GET);
    }

    //  refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-servers-%7Bname%7D-get.html
    public HTTPResult viewServer(String serviceName, String serverName){
        String uri = getPrefixURL() +  serviceName + "/servers/" + serverName;
        return getRequester().request(uri, getRequestHeaders(), null, null, null, CommonConstants.Method.GET);
    }
}
