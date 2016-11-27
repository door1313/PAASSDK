/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.action;

import cloud.oracle.paas.jcs.apis.ManagedServers;
import cloud.oracle.paas.jcs.apis.ServiceInstances;
import cloud.oracle.paas.model.HTTPResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author pengw
 */
public class SDKAction extends Action {
        private static ServiceInstances si;
    private static ManagedServers ms;
     public ActionForward execute(ActionMapping mapping, ActionForm form,
              HttpServletRequest request, HttpServletResponse response){
        si = new ServiceInstances();
        ms = new ManagedServers();
        HTTPResult result = si.viewAllInstances();
        System.out.println("Testing get all instances....");
        System.out.println(result.getStatus());
        System.out.println(result.getContent());
        request.setAttribute("InstanceStatus", result.getStatus());
        request.setAttribute("InstanceContent", result.getContent());
        
        result = ms.viewAllServers("GenPactDemoJCS");
        System.out.println("Testing get all servers....");
        System.out.println(result.getStatus());
        System.out.println(result.getContent());
        request.setAttribute("ManagedServerStatus", result.getStatus());
        request.setAttribute("ManagedServerContent", result.getContent());
         return mapping.findForward("done");
     }
}
