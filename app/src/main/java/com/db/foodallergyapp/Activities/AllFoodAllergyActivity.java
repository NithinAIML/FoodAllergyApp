package com.db.foodallergyapp.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.db.foodallergyapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AllFoodAllergyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_food_allergy);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.FoodEllergy));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LinearLayout ll_MyAllergies = findViewById(R.id.ll_MyAllergies);
        LinearLayout ll_FoodAllergies = findViewById(R.id.ll_FoodAllergies);

        CardView cv_MyAllergies = findViewById(R.id.cv_MyAllergies);
        cv_MyAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_MyAllergies.setVisibility(View.VISIBLE);
                ll_FoodAllergies.setVisibility(View.GONE);
            }
        });

        CardView cv_FoodAllergies = findViewById(R.id.cv_FoodAllergies);
        cv_FoodAllergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_MyAllergies.setVisibility(View.GONE);
                ll_FoodAllergies.setVisibility(View.VISIBLE);
            }
        });


        FloatingActionButton mFAB = findViewById(R.id.mFAB);
        mFAB.setOnClickListener(view -> {
            Dialog mDialog = new Dialog(AllFoodAllergyActivity.this);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.setContentView(R.layout.layout_for_add_allergies);
            mDialog.setCancelable(true);

            TextInputEditText et_name = mDialog.findViewById(R.id.et_name);
            TextInputEditText et_food = mDialog.findViewById(R.id.et_food);
            TextInputEditText et_symptoms = mDialog.findViewById(R.id.et_symptoms);

            Button btn_cancel = mDialog.findViewById(R.id.btn_cancel);
            btn_cancel.setOnClickListener(view1 -> mDialog.dismiss());

            Button btn_save = mDialog.findViewById(R.id.btn_save);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    if (et_name.getText().toString().trim().equalsIgnoreCase("")) {
                        Toast.makeText(AllFoodAllergyActivity.this, "Empty Allergy Name !!", Toast.LENGTH_SHORT).show();

                    } else if (et_food.getText().toString().trim().equalsIgnoreCase("")) {
                        Toast.makeText(AllFoodAllergyActivity.this, "Empty Food Name !!", Toast.LENGTH_SHORT).show();

                    } else if (et_symptoms.getText().toString().trim().equalsIgnoreCase("")) {
                        Toast.makeText(AllFoodAllergyActivity.this, "Empty Symptoms Name !!", Toast.LENGTH_SHORT).show();

                    } else {
                        SweetAlertDialog dialog = new SweetAlertDialog(AllFoodAllergyActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                        dialog.setCancelable(false);
                        dialog.show();

                        new Handler().postDelayed(() -> {
                            dialog.dismissWithAnimation();
                            Toast.makeText(AllFoodAllergyActivity.this, "Save Successfully !!", Toast.LENGTH_SHORT).show();
                        }, 3000);
                    }
                }
            });
            mDialog.show();
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