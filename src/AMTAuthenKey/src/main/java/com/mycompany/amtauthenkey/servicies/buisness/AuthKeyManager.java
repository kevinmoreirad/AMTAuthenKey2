
package com.mycompany.amtauthenkey.servicies.buisness;

import com.mycompany.amtauthenkey.model.AuthKey;
import com.mycompany.amtauthkey.services.dao.DataBaseManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.ejb.Stateless;
/**
 * Manager of the Authentifications keys
 * @author kevin moreira
 */
@Stateless
public class AuthKeyManager {
    @EJB
    private DataBaseManager dataBaseManager;  
    
    //char possible for creating the authentification key
    private final String CHARFORKEY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    
    /**
     * adding a random key. called when adding done number of keys
     * @param owner the owner of the key
     * @param startDate start date of the key
     * @param endDate end date of the key
     */
    public void addRandomAuthKey(String owner, String startDate, String endDate)
    {        
        AuthKey authKey = new AuthKey(getRandomKey(), owner, startDate, endDate);
        dataBaseManager.addAuthKeyInDB(authKey);
    }
    /**
     * generates a random authentification key
     * @return String length 10
     */
    private String getRandomKey(){
        
        Random rand = new Random();

        String key = new String();
        for(int i = 0; i < 10; i++)
        {
            int randNumb = rand.nextInt(CHARFORKEY.length());
            key = key + CHARFORKEY.charAt(randNumb);
        }
        
        return key;
    }
    
    /**
     * getting a list of AuthKey wanted
     * @param numRows number of authkeys 
     * @param page page we want to get
     * @param orderBy the way we want to order the list
     * @return list of authentifiacation keys
     */
    public List<AuthKey> getAuthKeyList(int numRows, int page, String orderBy)
    {       
       return dataBaseManager.getAuthKeyList(numRows, page, orderBy);
    }
   
    /**
     * 
     * @return number of authkeys
     */
   public int getNbAuthKeys()
   {
       return dataBaseManager.getNbAuthKeys();
   }

   /**
    * addding nbKeys of random keys
    * @param username the owner of the keys
    * @param nbKeys number keys
    */
    public void addRandomAuthKey(String username, int nbKeys) {
        for(int i = 0; i < nbKeys; i++)
        {
            List<String> twoDate = getTwoDate();
            addRandomAuthKey(username, twoDate.get(0), twoDate.get(1));
        }
    }
    
    /**
     * 
     * @return list of two dates between 2017 and 2100. the first date is always before the second
     */
    private List<String> getTwoDate()
    {
        int randYear1 = randBetween(2017, 2100);
        int randYear2 = randBetween(2017, 2100);
        
        int randDay1 = randBetween(1, 365);
        int randDay2 = randBetween(1, 365);
        
        //checking the date more advanced
        //if second date more advanced switch the two
        if(randYear2 < randYear1 || (randYear1 == randYear2 && randDay2 < randDay1))
        {
            int tempYear = randYear1;
            randYear1 = randYear2;
            randYear2 = tempYear;
            
            int tempDay = randDay1;
            randDay1 = randDay2;
            randDay2 = tempDay;
        }
        
        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.YEAR, randYear1);
        date1.set(Calendar.DAY_OF_YEAR, randDay1);
        
        Calendar date2 = Calendar.getInstance();
        date2.set(Calendar.YEAR, randYear2);
        date2.set(Calendar.DAY_OF_YEAR, randDay2);
        
        //Calendar has MONTH who provied number 0 to 11 (0 for januar etc)
        //so we have to transform it to 01-12 form and days from 1 to 01
        int intMonth1 = (date1.get(Calendar.MONTH)+1);
        String strMonth1 = (intMonth1<10)?"0"+intMonth1:""+intMonth1;
        
        int intMonth2 = (date2.get(Calendar.MONTH)+1);
        String strMonth2 = (intMonth2<10)?"0"+intMonth2:""+intMonth2;
        
        int intDay1 = date1.get(Calendar.DAY_OF_MONTH);
        String strDay1 = (intDay1<10)?"0"+intMonth1:""+intMonth1;
        
        
        int intDay2 = date2.get(Calendar.DAY_OF_MONTH);
        String strDay2 = (intDay2<10)?"0"+intDay2:""+intDay2;
        
        String d1 = date1.get(Calendar.YEAR)+"-"+ strMonth1+"-"+strDay1;
        String d2 = date2.get(Calendar.YEAR)+"-"+ strMonth2+"-"+strDay2;
        List<String> listDate = new ArrayList<>();
        listDate.add(d1);
        listDate.add(d2);
        
        return listDate;
    }
    
    /**
     * 
     * @param start
     * @param end
     * @return int between start and end
     */
    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    /**
     * modify the key with authkey name modifyKeyId
     * @param modifyKeyId authkey to modify
     * @param startDate new sttart date
     * @param endDate new end date
     */
    public void modifyKey(String modifyKeyId, String startDate, String endDate) {
       dataBaseManager.modifyKeyInDb(modifyKeyId, startDate, endDate);
    }

    public void deleteKey(String deleteKey) {
        dataBaseManager.deleteKeyInDb(deleteKey);
    }
    
}
