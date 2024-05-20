package br.com.storemanager.storemanagerapi.Exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}
