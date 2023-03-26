package com.db.foodallergyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {
private ProgressBar pb_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        pb_progress = findViewById(R.id.pb_progress);

        new Handler().postDelayed(() -> {
            pb_progress.setVisibility(View.GONE);
            Intent mIntent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(mIntent);
            finish();
        },3000);
    }
}