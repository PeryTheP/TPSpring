package com.example.tpspring;

public class CreateUserRequest {
    private String username;
    private String mdp;
    private String role;

    public CreateUserRequest(String username, String mdp, String role) {
        this.username = username;
        this.mdp = mdp;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
