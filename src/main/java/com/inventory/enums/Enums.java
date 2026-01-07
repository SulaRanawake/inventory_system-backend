package com.inventory.enums;

public class Enums {
    public enum Role {
        ADMIN,
        USER
    }

    public enum AuditAction {
        CREATE,
        UPDATE,
        DELETE,
        LOGIN,
        LOGOUT
    }

    public enum ProductStatus {
        ACTIVE,
        INACTIVE,
        DISCONTINUED,
        OUT_OF_STOCK
    }

}
