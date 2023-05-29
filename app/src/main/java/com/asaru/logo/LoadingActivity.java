package com.asaru.logo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
public class LoadingActivity extends AppCompatActivity {
    private static final long GIF_DISPLAY_DURATION = 5000;
    private ImageView gifImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        gifImageView = findViewById(R.id.gif);
        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(gifImageView);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
            startActivity(intent);

            finish();
        }, GIF_DISPLAY_DURATION);
    }
}