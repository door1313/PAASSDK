package cloud.oracle.pass.jcs.apis;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.config.PropertyLoader;
import cloud.oracle.paas.model.HTTPResult;

/**
 * APIs for Managed servers, see http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/api-Managed%20Servers.html
 */
public class ManagedServers extends AbstractAPIService {

    private static final String uriPrefix = getJcsHost() + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";

    public ManagedServers(Boolean enableLogger) {
        super(enableLogger);
    }

    // refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-servers-get.html
    public HTTPResult getAllServers(String serviceName){
        String uri = uriPrefix + serviceName + "/servers";
        return getRequester().request(uri, getRequestHeaders(), null, null, CommonConstants.Method.GET);
    }

    //  refer to http://docs.oracle.com/cloud/latest/jcs_gs/JSRMR/op-paas-service-jcs-api-v1.1-instances-%7BidentityDomainId%7D-%7BserviceId%7D-servers-%7Bname%7D-get.html
    public HTTPResult getServer(String serviceName, String serverName){
        String uri = uriPrefix +  serviceName + "/servers/" + serverName;
        return getRequester().request(uri, getRequestHeaders(), null, null, CommonConstants.Method.GET);
    }
}
