package com.db.foodallergyapp.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.db.foodallergyapp.R;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SearchInFoodActivity extends AppCompatActivity {
    private boolean isSearchClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_in_food);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(getResources().getString(R.string.Search));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CardView cv_cow_milk = findViewById(R.id.cv_cow_milk);
        CardView cv_peanuts = findViewById(R.id.cv_peanuts);

        LinearLayout ll_parent = findViewById(R.id.ll_parent);
        EditText et_search = findViewById(R.id.et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    String mStr = editable.toString().trim();
                    if (mStr.equalsIgnoreCase("")) {
                        if (isSearchClicked) {
                            cv_cow_milk.setVisibility(View.GONE);
                            cv_peanuts.setVisibility(View.GONE);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(view -> {
            String mStr = String.valueOf(et_search.getText()).trim();
            if (mStr.equalsIgnoreCase("")) {
                ll_parent.setVisibility(View.GONE);
                Toast.makeText(SearchInFoodActivity.this, "Empty Search...!", Toast.LENGTH_SHORT).show();
            } else {
                SweetAlertDialog mDialog = new SweetAlertDialog(SearchInFoodActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                mDialog.setTitleText(getResources().getString(R.string.gettingDetails));
                mDialog.setCancelable(false);
                mDialog.show();

                new Handler().postDelayed(() -> {
                    mDialog.dismissWithAnimation();

                    if (String.valueOf(et_search.getText()).trim().equalsIgnoreCase("Cow Milk")
                            || String.valueOf(et_search.getText()).trim().equalsIgnoreCase("Milk")) {
                        cv_cow_milk.setVisibility(View.VISIBLE);
                        cv_peanuts.setVisibility(View.GONE);

                    } else if (String.valueOf(et_search.getText()).trim().equalsIgnoreCase("Peanut")) {
                        cv_cow_milk.setVisibility(View.GONE);
                        cv_peanuts.setVisibility(View.VISIBLE);
                    }
                    isSearchClicked = true;
                }, 2000);

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