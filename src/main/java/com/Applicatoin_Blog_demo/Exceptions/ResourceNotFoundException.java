package com.Applicatoin_Blog_demo.Exceptions;

import lombok.*;

@Data
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue)
    {
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }


}
