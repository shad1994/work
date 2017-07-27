package com.qainfotech.tap.training.resourceio.exceptions;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class ObjectNotFoundException extends Exception{
    
    public ObjectNotFoundException(String type, String key, String value){
        super(type + " Object with "+key+"="+value+" not found");
    }
    
}
