package com.asaru.logo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("api/login") // Replace with your login endpoint path
    Call<LoginResponse> login(@Body LogInRequest loginRequest);

    @POST("api/signup") // Replace with your signup endpoint path
    Call<SignupResponse> signup(@Body SignupRequest signupRequest);

}
