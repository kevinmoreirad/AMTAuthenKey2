/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthenkey.servicies.buisness;

import com.mycompany.amtauthkey.services.dao.DataBaseManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author kevin moreira
 */
@Stateless
public class LoginManager {
    @EJB
    private DataBaseManager dataBaseManager;  
    
    /**
     * 
     * @param name
     * @param pass
     * @return boolean true if the name-pass is on the database, false if not 
     */
   public boolean isOnDataBase(String name, String pass){
       return dataBaseManager.isLoginInDB(name, pass);
   }
    
}
