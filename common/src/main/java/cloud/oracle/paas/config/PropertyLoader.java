package cloud.oracle.paas.config;

import cloud.oracle.paas.resource.CommonLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Loader to load properties from config.properties file
 */
public class PropertyLoader {

    private static String identity_domain;
    private static String region;
    private static String user;
    private static String password;
    private static String proxyHost;
    private static String proxyPort;
    private static String proxyUser;
    private static String proxyPassword;
    private static int timeout;
    private static final Logger LOGGER = CommonLogger.getLOGGER();


    static {
        Properties prop = new Properties();
        FileInputStream in;

        try {
            in = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
            prop.load(in);
            identity_domain = prop.getProperty("identity_domain") == null ? "" : prop.getProperty("identity_domain").trim();
            region = prop.getProperty("region") == null ? "" : prop.getProperty("region").trim();
            user = prop.getProperty("user") == null ? "" : prop.getProperty("user").trim();
            password = prop.getProperty("password") == null ? "" : prop.getProperty("password").trim();
            proxyHost = prop.getProperty("proxyHost") == null ? "" : prop.getProperty("proxyHost").trim();
            proxyPort = prop.getProperty("proxyPort") == null ? "" : prop.getProperty("proxyPort").trim();
            proxyUser = prop.getProperty("proxyUser") == null ? "" : prop.getProperty("proxyUser").trim();
            proxyPassword = prop.getProperty("proxyPassword") == null ? "" : prop.getProperty("proxyPassword").trim();
            timeout = getNumber(prop.getProperty("timeout"));

        } 
          catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static int getNumber(String input){
        int result = 0;
        if("".equals(input)||input == null){
            return result;
        }else{
            try {
               result = Integer.parseInt(input.trim());
            }catch (java.lang.NumberFormatException e){
                LOGGER.log(Level.WARNING, "error occurred when converting string to number, exception:  " + e.toString());
            }
        }
        return result;
    }
    public static String getIdentityDomain() {
        return identity_domain;
    }

    public static String getRegion() {
        return region;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getProxyHost() {
        return proxyHost;
    }

    public static String getProxyPort() {
        return proxyPort;
    }

    public static String getProxyUser() {
        return proxyUser;
    }
    
    public static String getProxyPassword() {
        return proxyPassword;
    }

    public static int getTimeout() {
        return timeout;
    }


//    public static JSONObject readJSONObject() {
//        return readJSONObject(jsonFilePath);
//    }
//
//    public static JSONObject readJSONObject(String filePath) {
//        System.out.println("testing read json");
//        JSONObject dataJson = null;
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(
//                    filePath));
//            String s = null;
//
//            while ((s = br.readLine()) != null) {
//                try {
//                    System.out.println("testing read json step2 ===" + s);
//                    dataJson = new JSONObject(s);
//
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//
//                }
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(PropertyLoader.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dataJson;
//    }

//    public static String getTenantID() {
//        return getTenantID(jsonFilePath);
//    }
//
//    public static String getTenantID(String filePath) {
//        String tenantID = "";
//        JSONObject dataJson = readJSONObject(filePath);
//        JSONObject properties = dataJson.getJSONObject("properties");
//        tenantID = properties.getString("tenantId");
//        System.out.println(tenantID);
//        return tenantID;
//    }
//
//    public static String getAttribute(String attributeName) {
//        return getAttrbiute(attributeName, jsonFilePath);
//    }
//
//    public static String getAttrbiute(String attributeName, String filePath) {
//        String attribute = "";
//        JSONObject dataJson = readJSONObject(filePath);
//        JSONObject properties = dataJson.getJSONObject("properties");
//        attribute = properties.getString(attributeName);
//        System.out.println(attribute);
//        return attribute;
//    }

}
