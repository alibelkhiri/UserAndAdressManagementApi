package com.abscript.brightcodingspringv2.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class Utils {
    private final Random random=new SecureRandom();
    private final String alphabet="AZERTYUIOPQSDFGHJKLMWXCVBNazertyuiopqsdfghjklmwxcvbn1234567890";

   
    public String generateUserId(int lenght){
        StringBuilder returnValue=new StringBuilder(lenght);
        for(int i=0;i<lenght;i++){
            returnValue.append(alphabet.charAt(random.nextInt(alphabet.length())));

        }
        return new String(returnValue);
    }
    
}
