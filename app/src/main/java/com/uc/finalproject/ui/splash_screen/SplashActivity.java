package com.uc.finalproject.ui.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;

public class SplashActivity extends AppCompatActivity {
    private static int splashtime = 4000;
    SharedPreferences sharedPreferences;
    Boolean fistTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_splash);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        fistTime = sharedPreferences.getBoolean("firstTime", true);

        if (fistTime){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    fistTime = false;
                    editor.putBoolean("firstTime",fistTime);
                    editor.apply();
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },splashtime);
        }
        else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
