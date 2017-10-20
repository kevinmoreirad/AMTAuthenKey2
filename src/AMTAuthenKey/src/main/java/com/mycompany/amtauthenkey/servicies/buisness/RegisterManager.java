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
public class RegisterManager {

    @EJB
    private DataBaseManager dataBaseManager;  
    
    public boolean isUserAlreadyOnDataBase(String username)
    {
        return dataBaseManager.isUsernameInDB(username);
    }

    public void addUser(String username, String password) 
    {
       dataBaseManager.addUserInDB(username, password);
    }
}
