package com.kaishengit.exception;

public class ServiceException extends RuntimeException {

    /**
     * 业务层的异常
     */
    public ServiceException(){

    }

    public ServiceException(String message){
        super(message);
    }

}
