package com.hunglp.spring_rest_control_advice_exception.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
