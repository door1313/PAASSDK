/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import cloud.oracle.paas.CommonConstants;
import cloud.oracle.paas.jcs.apis.ManagedServers;
import cloud.oracle.paas.jcs.apis.ServiceInstances;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import cloud.oracle.paas.jcs.apis.*;
import cloud.oracle.paas.config.PropertyLoader;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
/**
 *
 * @author pengw
 */
public class JerseyAction  extends Action{
    private static Client httpClient;
    private static String prefixURL;
    private static MultivaluedStringMap requestHeaders;
    private static ServiceInstances si;
    private static ManagedServers ms;
     public ActionForward execute(ActionMapping mapping, ActionForm form,
              HttpServletRequest request, HttpServletResponse response){
         
         ClientConfig configuration = new ClientConfig();
        int timeout = PropertyLoader.getTimeout();
        System.out.println("Testing get all instances....");
        if (timeout != 1) {
            configuration.property(ClientProperties.CONNECT_TIMEOUT, timeout);
            configuration.property(ClientProperties.READ_TIMEOUT, timeout);
        }
        if (!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())) {
            System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());
            System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());
        }
        if (!"".equals(PropertyLoader.getProxyUser()) && !"".equals(PropertyLoader.getProxyPassword())) {
            configuration.property(ClientProperties.PROXY_USERNAME, PropertyLoader.getProxyUser())
                    .property(ClientProperties.PROXY_PASSWORD, PropertyLoader.getProxyPassword());
        }

        httpClient = ClientBuilder.newClient(configuration);
        //  System.out.println("pwd + " +PropertyLoader.getPassword() + " " +PropertyLoader.getUser());
        if (!"".equals(PropertyLoader.getUser()) && !"".equals(PropertyLoader.getPassword())) {
            httpClient.register(HttpAuthenticationFeature.basic(PropertyLoader.getUser(), PropertyLoader.getPassword()));
        }

        prefixURL = CommonConstants.USHOST + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";
        if (!"".equals(PropertyLoader.getRegion())) {
            String jcsHost = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion());
            if (jcsHost != null) {
                prefixURL = jcsHost + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";
            }
        }
        //  System.out.println("url : "+ prefixURL);
        WebTarget target = httpClient.target(prefixURL);

        Invocation.Builder builder = target.request();

        requestHeaders = new MultivaluedStringMap();
        requestHeaders.putSingle(CommonConstants.TENENTHEADER, PropertyLoader.getIdentityDomain());

        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();

                for (String value : values) {
                    builder.header(key, value);
                }
            }
        }

        Response restResponse;
        restResponse = builder.get();
        int status = response.getStatus();
        Map respHeaders = restResponse.getHeaders();
        String content = (restResponse.hasEntity() ? restResponse.readEntity(String.class) : "");
        System.out.println("status is : " + status);
        System.out.println("content is : " + content);
        request.setAttribute("InstanceStatus", status);
        request.setAttribute("InstanceContent", content);
        
        
        System.out.println("Testing get all servers....");
        target = httpClient.target(prefixURL + "GenPactDemoJCS" + "/servers");
        builder = target.request();
        requestHeaders = new MultivaluedStringMap();
        requestHeaders.putSingle(CommonConstants.TENENTHEADER, PropertyLoader.getIdentityDomain());

        if (requestHeaders != null && !requestHeaders.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : requestHeaders.entrySet()) {
                String key = entry.getKey();
                List<String> values = entry.getValue();

                for (String value : values) {
                    builder.header(key, value);
                }
            }
        }
        restResponse = builder.get();
        status = restResponse.getStatus();
        respHeaders = restResponse.getHeaders();
        content = (restResponse.hasEntity() ? restResponse.readEntity(String.class) : "");
        System.out.println("status is : " + status);
        System.out.println("content is : " + content);
            request.setAttribute("ManagedServerStatus", status);
        request.setAttribute("ManagedServerContent", content);
         return mapping.findForward("done");
     }
}
