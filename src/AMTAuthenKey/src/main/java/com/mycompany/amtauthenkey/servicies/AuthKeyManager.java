
package com.mycompany.amtauthenkey.servicies;

import com.mycompany.amtauthenkey.model.AuthKey;
import java.util.Date;
import java.util.Random;
import javax.ejb.Stateless;
/**
 *
 * @author kevin moreira
 */
@Stateless
public class AuthKeyManager {
    private final String CHARFORKEY = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public AuthKey getRandomAuthKey(String owner)
    {
        return new AuthKey(getRandomKey(), owner , new Date(12, 10, 2010), new Date(11,2,2030));
    }
    public AuthKey getRandomAuthKey(String owner, Date startDate, Date endDate)
    {
        return new AuthKey(getRandomKey(), owner, startDate, endDate);
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
            int randNumb = rand.nextInt(CHARFORKEY.length() - 0 + 1) + 0;
            key = key + CHARFORKEY.charAt(randNumb);
        }
        return key;
    }
    
}
