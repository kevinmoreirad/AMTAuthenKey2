/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthenkey.model;

import java.util.Date;

/**
 *
 * @author kevin moreira
 */
public class AuthKey {

    private String authKey;
    private String owner;
    private String startDate;
    private String endDate;


    public AuthKey(String authKey, String owner, String startDate, String endDate) {
        this.authKey = authKey;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
    }
   
    
    public String getAuthKey() {
        return authKey;
    }
        
    public String getOwner(){
        return owner;
    }
    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
    
    
}
