package br.com.storemanager.storemanagerapi.Exceptions;

public class DeleteExistsException extends RuntimeException {
    
    public DeleteExistsException(String message) {
        super(message);
    }

}
