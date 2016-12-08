package com.dhl.serv.web.rest;

/**
 * Created by Gustavo on 5/12/16.
 */
public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
//
//public class StorageException extends RuntimeException {
//
//    public StorageException(String message) {
//        super(message);
//    }
//
//    public StorageException(String message, Throwable cause) {
//        super(message, cause);
//    }
//}
