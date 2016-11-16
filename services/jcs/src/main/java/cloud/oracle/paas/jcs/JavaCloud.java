package cloud.oracle.paas.jcs;

import cloud.oracle.paas.jcs.apis.ManagedServers;
import cloud.oracle.paas.jcs.apis.Scaling;
import cloud.oracle.paas.jcs.apis.ServiceInstances;
import cloud.oracle.paas.model.HTTPResult;

import java.util.Map;
import java.util.logging.Level;

/**
 * Client for JCS
 */
public class JavaCloud {

    private static ServiceInstances si;
    private static ManagedServers ms;
    private static Scaling scaling;

//    public HTTPResult createInstance(String serviceName, String payload){
//        return new ServiceInstances().createInstance(serviceName,payload);
//    }
//
//    public HTTPResult viewAllInstancse(){
//        return new ServiceInstances().viewAllInstances();
//    }

    public static class Instance{
        public Instance() {
            si = new ServiceInstances();
        }

        public void enableLog(Level level){
            si.getRequester().enableLogger(level);
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

    public static class ManagedServer {
        public ManagedServer() {
            ms = new ManagedServers();
        }

        public void enableLog(Level level){
            ms.getRequester().enableLogger(level);
        }

        public HTTPResult viewAll(String serviceName) {
            return ms.viewAllServers(serviceName);
        }

        public HTTPResult view(String sericeName, String serverName) {
            return ms.viewServer(sericeName, serverName);
        }
    }

    public static class ScaleOperation {
        public ScaleOperation() {
            scaling = new Scaling();
        }

        public void enableLog(Level level){
            scaling.getRequester().enableLogger(level);
        }

        public HTTPResult scaleOutCluster(String serviceName, String clusterName, Map<String, String> queryParams)  {
            return scaling.scaleOutACluster(serviceName, clusterName, queryParams);
        }

        public HTTPResult scaleInCluster(String serviceName, String serverName)  {
            return scaling.scaleInACluster(serviceName, serverName);
        }

        public HTTPResult scaleNode(String serviceName, String serverName, String payloade)  {
            return scaling.scaleANode(serviceName, serverName, payloade);
        }

    }

}
