package com.condominio.acesso.entities;

public enum Role {
    ADMIN, RESIDENT, DOORMAN;

    public String toString(){
        switch (this){
            case ADMIN:
                return "ADMIN";
            case DOORMAN:
                return "DOORMAN";
            case RESIDENT:
                return "RESIDENT";
            default:
                return "";
        }
    }

    public static Role fromString(String role){
        switch (role){
            case "ADMIN":
                return Role.ADMIN;
            case "DOORMAN":
                return Role.DOORMAN;
            case "RESIDENT":
                return Role.RESIDENT;
            default:
                throw new IllegalArgumentException();
        }
    }

}
