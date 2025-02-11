package com.uz.justplan.beans;

public class AuthResponse {
    private String token;
    private String message;
    private LoggedInDetails details;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoggedInDetails getDetails() {
        return details;
    }

    public void setDetails(LoggedInDetails details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
// Getter
}
