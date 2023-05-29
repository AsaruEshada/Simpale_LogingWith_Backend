package com.asaru.logo;
import com.google.gson.annotations.SerializedName;
public class LogInRequest {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public LogInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
