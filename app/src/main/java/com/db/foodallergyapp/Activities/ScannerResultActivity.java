package com.db.foodallergyapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.db.foodallergyapp.MainActivity;
import com.db.foodallergyapp.R;

import java.util.Objects;

public class ScannerResultActivity extends AppCompatActivity {
    private boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_result);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.FoodResult));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CardView cv_SkinReaction = findViewById(R.id.cv_SkinReaction);
        CardView cv_DigestiveProblm = findViewById(R.id.cv_DigestiveProblm);
        CardView cv_Rec = findViewById(R.id.cv_Rec);
        LinearLayout ll_rec_sec = findViewById(R.id.ll_rec_sec);

        cv_SkinReaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOpenDialogSkinRec();
            }
        });

        cv_DigestiveProblm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOpenDialogDigestive();
            }
        });

        cv_Rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isSelected){
                    isSelected = true;
                    ll_rec_sec.setVisibility(View.VISIBLE);

                }else {
                    isSelected = false;
                    ll_rec_sec.setVisibility(View.GONE);
                }
            }
        });

        try {
            Intent mIntent = getIntent();
            int intValue = mIntent.getIntExtra("mFromCameraOrRec", 0);
            LinearLayout ll_rec = findViewById(R.id.ll_rec);
            if (intValue == 1) {
                ll_rec.setVisibility(View.GONE);
            } else if (intValue == 2) {
                ll_rec.setVisibility(View.VISIBLE);
            } else {
                ll_rec.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mOpenDialogSkinRec() {
        Dialog mDialog = new Dialog(ScannerResultActivity.this);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.layout_for_skin_reactions);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    private void mOpenDialogDigestive() {
        Dialog mDialog = new Dialog(ScannerResultActivity.this);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.layout_for_digestive);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent mIntent = new Intent(ScannerResultActivity.this, MainActivity.class);
            startActivity(mIntent);
            finish();
        }
        return true;
    }
}