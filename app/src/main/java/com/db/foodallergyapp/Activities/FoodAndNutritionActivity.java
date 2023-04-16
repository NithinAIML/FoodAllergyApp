package com.db.foodallergyapp.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.db.foodallergyapp.R;

import java.util.Objects;

public class FoodAndNutritionActivity extends AppCompatActivity {
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_nutrition);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.FoodsAndNutrition));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CardView cv_rec = findViewById(R.id.cv_rec);
        LinearLayout ll_rec = findViewById(R.id.ll_rec);

        CardView cv_Dairy = findViewById(R.id.cv_Dairy);
        CardView cv_Meat = findViewById(R.id.cv_Meat);
        CardView cv_Baked = findViewById(R.id.cv_Baked);

        LinearLayout ll_Dairy = findViewById(R.id.ll_Dairy);
        LinearLayout ll_Meat = findViewById(R.id.ll_Meat);
        LinearLayout ll_Baked = findViewById(R.id.ll_Baked);

        cv_Dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv_Dairy.setBackground(ContextCompat.getDrawable(FoodAndNutritionActivity.this, R.drawable.selected));
                cv_Meat.setBackground(null);
                cv_Baked.setBackground(null);

                ll_Dairy.setVisibility(View.VISIBLE);
                ll_Baked.setVisibility(View.GONE);
                ll_Meat.setVisibility(View.GONE);
                ll_rec.setVisibility(View.VISIBLE);
            }
        });

        cv_Meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv_Meat.setBackground(ContextCompat.getDrawable(FoodAndNutritionActivity.this, R.drawable.selected));
                cv_Dairy.setBackground(null);
                cv_Baked.setBackground(null);

                ll_Meat.setVisibility(View.VISIBLE);
                ll_Dairy.setVisibility(View.GONE);
                ll_Baked.setVisibility(View.GONE);
                ll_rec.setVisibility(View.GONE);
            }
        });

        cv_Baked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv_Baked.setBackground(ContextCompat.getDrawable(FoodAndNutritionActivity.this, R.drawable.selected));
                cv_Dairy.setBackground(null);
                cv_Meat.setBackground(null);

                ll_Baked.setVisibility(View.VISIBLE);
                ll_Meat.setVisibility(View.GONE);
                ll_Dairy.setVisibility(View.GONE);
                ll_rec.setVisibility(View.GONE);
            }
        });

        cv_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClicked) {
                    isClicked = true;
                    ll_rec.setVisibility(View.VISIBLE);
                } else {
                    isClicked = false;
                    ll_rec.setVisibility(View.GONE);
                }

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