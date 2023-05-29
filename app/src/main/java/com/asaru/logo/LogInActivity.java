package com.asaru.logo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        TextView signupButton = findViewById(R.id.register);
        Button loginButton = findViewById(R.id.LogIn);
        apiService = ApiClient.getClient().create(ApiService.class);



        loginButton.setOnClickListener(v -> {
            String username = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            LogInRequest loginRequest = new LogInRequest(username, password);
            Call<LoginResponse> call = apiService.login(loginRequest);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        // Login successful, handle the response
                        LoginResponse loginResponse = response.body();
                        String message = loginResponse.getMessage();
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    } else {
                        // Login failed, handle the error response
                        LoginResponse loginResponse = response.body();
                        String message = loginResponse.getMessage();
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {

                    String errorMessage = t.getMessage();
                    Toast.makeText(getApplicationContext(), "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    Log.e("LoginFailure", "Login request failed: " + errorMessage);
                }
            });
        });

        signupButton.setOnClickListener(v -> {
            String username = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            SignupRequest signupRequest = new SignupRequest(username, password);
            Call<SignupResponse> call = apiService.signup(signupRequest);

            call.enqueue(new Callback<SignupResponse>() {
                @Override
                public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                    SignupResponse signupResponse = response.body();
                    String message = signupResponse.getMessage();
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                        Log.e("LoginFailure", "Login request failed: " + message);
                    }
                }

                @Override
                public void onFailure(Call<SignupResponse> call, Throwable t) {
                    String errorMessage = t.getMessage();
                    Toast.makeText(getApplicationContext(), "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                    Log.e("LoginFailure", "Login request failed: " + errorMessage);
                }
            });
        });
    }
}