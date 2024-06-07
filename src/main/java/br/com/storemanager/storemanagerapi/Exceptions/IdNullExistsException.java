package br.com.storemanager.storemanagerapi.Exceptions;

public class IdNullExistsException extends RuntimeException {
    
    public IdNullExistsException(String message) {
        super(message);
    }
}
