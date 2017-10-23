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
    
    /**
     * 
     * @param username
     * @return true if the username is already on database false otherwise
     */
    public boolean isUserAlreadyOnDataBase(String username)
    {
        return dataBaseManager.isUsernameInDB(username);
    }

    /**
     * add user on dataBase
     * @param username username to add  
     * @param password password of username
     */
    public void addUser(String username, String password) 
    {
       dataBaseManager.addUserInDB(username, password);
    }
}
