package br.com.storemanager.storemanagerapi.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.storemanager.storemanagerapi.security.UserSpringSecurity;

public class SecurityUtil {

    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }

    }
}

