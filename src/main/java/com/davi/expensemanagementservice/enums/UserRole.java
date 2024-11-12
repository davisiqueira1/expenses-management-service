package com.davi.expensemanagementservice.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("USER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
