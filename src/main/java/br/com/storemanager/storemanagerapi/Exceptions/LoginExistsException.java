package br.com.storemanager.storemanagerapi.Exceptions;

public class LoginExistsException extends RuntimeException {
    public LoginExistsException(String message) {
        super(message);
    }
}
