package br.com.storemanager.storemanagerapi.Exceptions;

public class UsernameExistsException extends Exception {

    public UsernameExistsException(String message) {
        super(message);
    }
}
