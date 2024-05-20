package br.com.storemanager.storemanagerapi.Exceptions;

public class LoginExistsException extends Exception {
    public LoginExistsException(String message) {
        super(message);
    }
}
