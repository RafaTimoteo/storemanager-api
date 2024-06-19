package br.com.storemanager.storemanagerapi.models.enums;

import java.util.Objects;

public enum ProfileEnum {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private Integer code;
    private String description;

    public static ProfileEnum toEnum(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        
        for (ProfileEnum i : ProfileEnum.values()) {
            if (code.equals(i.getCode())) {
                return i;
            }
        }

        throw new IllegalArgumentException("invalid code: " + code);
    }

    // Constructor
    private ProfileEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    // Getters
    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
