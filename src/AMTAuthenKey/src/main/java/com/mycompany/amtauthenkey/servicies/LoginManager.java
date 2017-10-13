/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthenkey.servicies;

import javax.ejb.Stateless;

/**
 *
 * @author kevin moreira
 */
@Stateless
public class LoginManager {
   public boolean isOnDataBase(String name, String pass){
       if(name.equals("kevin") && pass.equals("1234"))
       {
           return true;
       }
       else
       {
           return false;
       }
   }
    
}
