package com.uc.finalproject.ui.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);

        new Handler().postDelayed()

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
