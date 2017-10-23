/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amtauthkey.services.dao;

import com.mycompany.amtauthenkey.model.AuthKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author kevin moreira
 */
@Stateless
public class DataBaseManager {

    
    @Resource(lookup = "java:/jdbc/db_amt")
    private DataSource dataSource;
    
    /**
     * 
     * @param username
     * @param password
     * @return boolean true if username-password is on db, false otherwise
     */
    public boolean isLoginInDB(String username, String password)
    {
        try {
            Connection connection = dataSource.getConnection();
          
            PreparedStatement pstmt = connection.prepareStatement("SELECT nom FROM "
                    + "usr WHERE nom = ? AND mdp= ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            boolean temp = rs.first();
            connection.close();
            return temp;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    /**
     * 
     * @param username
     * @return true if username is on db false otherwise
     */
    public boolean isUsernameInDB(String username)
    {
         try {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT nom FROM "
                    + "usr WHERE nom = ?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            boolean temp = rs.first();
            connection.close();
            return temp;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
    /**
     * adding user unsername-password to the db
     * @param username
     * @param password 
     */
    public void addUserInDB(String username, String password) 
    {
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO usr (nom, mdp) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            connection.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addAuthKeyInDB(AuthKey authKey )
    {
        try
        {
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO auth_key (auth_key, init_date, end_date, usr_nom) "
                    + "VALUES ( ?, ?, ?, ?)");
            pstmt.setString(1, authKey.getAuthKey());
            pstmt.setString(2, authKey.getStartDate());
            pstmt.setString(3, authKey.getEndDate());
            pstmt.setString(4, authKey.getOwner());
            pstmt.executeUpdate();
            connection.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public List<AuthKey> getAuthKeyList(int numRows, int page, String orderBy)
    {
        List<AuthKey> authKeys = new ArrayList<>();
        try
        {
           Connection connection = dataSource.getConnection();
           
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM "
                    + "auth_key ORDER BY "+orderBy+" LIMIT "+numRows+" OFFSET "+numRows*(page-1));
            ResultSet rs = pstmt.executeQuery();
       
            while(rs.next())
            {     
                String auth = rs.getString("auth_key");
                String initD = rs.getString("init_date");
                String endD = rs.getString("end_date");
                String user = rs.getString("usr_nom");
              
                authKeys.add(new AuthKey(auth, user, initD, endD));
            }
            connection.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authKeys;
    }

    public int getNbAuthKeys() {
        try
        {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            String selectquery = "select count(*) from auth_key";
            ResultSet rs = stmt.executeQuery(selectquery);
            rs.next();          
            int temp = rs.getInt(1);

            connection.close();
            return temp;
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void modifyKeyInDb(String modifyKeyId, String startDate, String endDate) {
       try
        {
            Connection connection = dataSource.getConnection();
            Statement stmt = connection.createStatement();
            String selectquery = "UPDATE auth_key SET init_date = '"+startDate+"', end_date = '"
                                +endDate+"' WHERE auth_key = '"+modifyKeyId+"'";
            stmt.executeUpdate(selectquery);
            
            connection.close();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
