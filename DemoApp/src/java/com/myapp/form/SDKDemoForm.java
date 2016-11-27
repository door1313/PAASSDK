/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.form;

import org.apache.struts.action.ActionForm;

/**
 *
 * @author pengw
 */
public class SDKDemoForm extends ActionForm {
      private String productID; 
       public String getProductID()
      {
          return productID;
      }
      public void setProductID(String productID)
      {
          this.productID = productID;
      }
}
