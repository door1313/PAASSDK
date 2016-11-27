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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
/**
/**
 *
 * @author pengw
 */
public class JavaAction extends Action{
     public ActionForward execute(ActionMapping mapping, ActionForm form,
              HttpServletRequest request, HttpServletResponse response){
         
         System.out.println("Testing get all instances....");
        String prefixURL = CommonConstants.USHOST + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";
        if (!"".equals(PropertyLoader.getRegion())) {
            String jcsHost = CommonConstants.RegionHost.findHostByRegion(PropertyLoader.getRegion());
            if (jcsHost != null) {
                prefixURL = jcsHost + CommonConstants.ENDPOINTPREFIX + "/" + PropertyLoader.getIdentityDomain() + "/";
            }
        }
        // System.out.println(prefixURL);
        try {

            URL restServiceURL = new URL(prefixURL);
            if (!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())) {
                System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());
                System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());
            }
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");

            String auth = PropertyLoader.getUser() + ":" + PropertyLoader.getPassword();
            // httpConnection.setRequestProperty("Authorization",PropertyLoader.getUser()+":"+PropertyLoader.getPassword());
            httpConnection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));
            // System.out.println("base " + Base64.getEncoder().encodeToString(auth.getBytes()));
            // System.out.println("base " +PropertyLoader.getUser()+":"+PropertyLoader.getPassword());

            // httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("X-ID-TENANT-NAME", PropertyLoader.getIdentityDomain());
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:  \n");
            String instancesResults = "";
            while ((output = responseBuffer.readLine()) != null) {
                instancesResults = instancesResults.concat(output);
                System.out.println(output);
            }
            request.setAttribute("AllInstanceResults", instancesResults);
            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        System.out.println("Testing get all servers....");

        try {

            URL restServiceURL = new URL(prefixURL + "GenPactDemoJCS" + "/servers");
            if (!"".equals(PropertyLoader.getProxyHost()) && !"".equals(PropertyLoader.getProxyPort())) {
                System.setProperty("https.proxyHost", PropertyLoader.getProxyHost());
                System.setProperty("https.proxyPort", PropertyLoader.getProxyPort());
            }
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");

            String auth = PropertyLoader.getUser() + ":" + PropertyLoader.getPassword();
            // httpConnection.setRequestProperty("Authorization",PropertyLoader.getUser()+":"+PropertyLoader.getPassword());
            httpConnection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));
            // System.out.println("base " + Base64.getEncoder().encodeToString(auth.getBytes()));
            // System.out.println("base " +PropertyLoader.getUser()+":"+PropertyLoader.getPassword());

            // httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setRequestProperty("X-ID-TENANT-NAME", PropertyLoader.getIdentityDomain());
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String output;
            System.out.println("Output from Server:  \n");
            String allManagedServerResults = "";
            while ((output = responseBuffer.readLine()) != null) {
                allManagedServerResults = allManagedServerResults.concat(output);
                System.out.println(output);
            }
            request.setAttribute("AllManagedServerResults", allManagedServerResults);
            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
         return mapping.findForward("done");
     }
}
