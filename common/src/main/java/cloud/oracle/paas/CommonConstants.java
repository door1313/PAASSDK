package cloud.oracle.paas;

/**
 * Common constants would be used by other
 */
public class CommonConstants {
    public enum Method{
        GET,
        POST,
        PUT,
        DELETE
    }

    public static final String EMEAHOST = "https://jcs.emea.oraclecloud.com";
    public static final String USHOST = "https://jaas.oraclecloud.com";
    public static final String TENENTHEADER = "X-ID-TENANT-NAME";
    public static final String CONTENTTYPE = "Content-Type";
    public static final String ORACLEJSONMEDIATYPE = "application/vnd.com.oracle.oracloud.provisioning.Service+json";
    public static final String ENDPOINTPREFIX = "/paas/service/jcs/api/v1.1/instances";

    public enum RegionHost{
        EMEA("emea", EMEAHOST),
        US("us", USHOST);

        private final String region;
        private final String host;

        RegionHost(String region, String host) {
            this.region = region;
            this.host = host;
        }
        public static String findHostByRegion( String regionName){
            for(RegionHost region : values()){
                if (region.region.equalsIgnoreCase(regionName)){
                    return region.host;
                }
            }
            return null;
        }
    }
}
