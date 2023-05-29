package com.asaru.logo;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public SignupRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
