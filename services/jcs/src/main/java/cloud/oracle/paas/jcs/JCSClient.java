package cloud.oracle.paas.jcs;

import cloud.oracle.paas.jcs.apis.ServiceInstances;
import cloud.oracle.paas.model.HTTPResult;
import cloud.oracle.paas.util.HTTPRequester;

import java.util.logging.Level;

/**
 * Client for JCS
 */
public class JCSClient {

    private static final HTTPRequester requester = new HTTPRequester();
    private ServiceInstances si;

    public JCSClient() {
    }

    public  void enableLog(Level level) {
        requester.enableLogger(level);
    }

    public HTTPResult createInstance(String serviceName, String payload){
        return new ServiceInstances(requester).createInstance(serviceName,payload);
    }

    public HTTPResult viewAllInstancse(){
        return new ServiceInstances(requester).viewAllInstances();
    }

    public class Instance{
        public Instance() {
            si = new ServiceInstances(requester);
        }

        public HTTPResult create(String serviceName, String payload){
            return si.createInstance(serviceName,payload);
        }

        public HTTPResult viewAll(){
            return si.viewAllInstances();
        }

        public HTTPResult view(String sericeName){
            return si.viewInstance(sericeName);
        }

        public HTTPResult control(String sericeName, String payload){
            return si.stopAndStartInstance(sericeName, payload);
        }

        public HTTPResult delete(String sericeName, String payload){
            return si.deleteInstance(sericeName, payload);
        }

    }



}
