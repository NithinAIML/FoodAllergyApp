package com.db.foodallergyapp.Activities;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.db.foodallergyapp.R;

import java.util.Objects;

import de.markusfisch.android.cameraview.widget.CameraView;

public class ScannerActivity extends AppCompatActivity {

    CameraView camera_view;
    private int mFromCameraOrRec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.scanner));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        try {
            Intent mIntent1 = getIntent();
            mFromCameraOrRec = mIntent1.getIntExtra("mFromCameraOrRec", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Button btn_getInstantResult = findViewById(R.id.btn_getInstantResult);
        btn_getInstantResult.setOnClickListener(view -> {
            Intent mIntent = new Intent(ScannerActivity.this, ScannerResultActivity.class);
            startActivity(mIntent);
            finish();
        });

        camera_view = findViewById(R.id.camera_view);
        camera_view.openAsync(CameraView.findCameraId(
                Camera.CameraInfo.CAMERA_FACING_BACK));
        camera_view.setOnCameraListener(new CameraView.OnCameraListener() {
            @Override
            public void onConfigureParameters(Camera.Parameters parameters) {

            }

            @Override
            public void onCameraError() {
                Toast.makeText(ScannerActivity.this, "Please Wait... \n Camera is in boot Mode..!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCameraReady(Camera camera) {
                Toast.makeText(ScannerActivity.this, "Please Wait... \n Camera is in boot Mode..!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPreviewStarted(Camera camera) {
                new Handler().postDelayed(() -> {
                    camera_view.close();
                    Intent mIntent = new Intent(ScannerActivity.this, ScannerResultActivity.class);
                    if (mFromCameraOrRec == 1) {
                        mIntent.putExtra("mFromCameraOrRec", 1);
                    } else if (mFromCameraOrRec == 2) {
                        mIntent.putExtra("mFromCameraOrRec", 2);
                    }
                    startActivity(mIntent);
                    finish();
                }, 2000);
            }

            @Override
            public void onCameraStopping(Camera camera) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}