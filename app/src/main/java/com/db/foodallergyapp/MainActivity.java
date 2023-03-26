package com.db.foodallergyapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.db.foodallergyapp.Activities.AllFoodAllergyActivity;
import com.db.foodallergyapp.Activities.FoodAndNutritionActivity;
import com.db.foodallergyapp.Activities.ProfileActivity;
import com.db.foodallergyapp.Activities.ScannerActivity;
import com.db.foodallergyapp.Activities.ScannerResultActivity;
import com.db.foodallergyapp.Activities.SearchInFoodActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 2;
    public static boolean mIsActivityActive = false;
    private boolean doubleBackToExitPressedOnce = false;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private TextView tv_riban;
    private CardView cv_scanner, cv_scanner_And_Rec, cv_FoodAndNutrition, cv_AddAllFood, cv_SearchFood,
            cv_MyProfile;
    private int mFromCameraOrRec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIDs();

        Toolbar toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.Open, R.string.Close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
        CircularImageView iv_CircularImageViewMultipleAccount = toolbar.findViewById(R.id.iv_CircularImageViewMultipleAccount);
        mTitle.setText(getResources().getString(R.string.dashboard));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tv_riban.setSelected(true);

        iv_CircularImageViewMultipleAccount.setOnClickListener(view -> {
            Dialog mDialog = new Dialog(MainActivity.this);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.setContentView(R.layout.layout_for_profile);
            mDialog.setCancelable(true);
            mDialog.show();
        });

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            try {
                int id = menuItem.getItemId();
                if (id == R.id.nav_User) {
                    mDrawerLayout.closeDrawers();
                    SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                    dialog.setCancelable(false);
                    dialog.show();

                    new Handler().postDelayed(() -> {
                        dialog.dismissWithAnimation();
                        Intent mIntent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(mIntent);
                    }, 2000);

                } else if (id == R.id.nav_FoodScanner) {
                    mDrawerLayout.closeDrawers();
                    mOpenFileChooser(1);

                } else if (id == R.id.nav_FoodAllEllergy) {
                    mDrawerLayout.closeDrawers();
                    SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                    dialog.setCancelable(false);
                    dialog.show();

                    new Handler().postDelayed(() -> {
                        dialog.dismissWithAnimation();
                        Intent mIntent = new Intent(MainActivity.this, AllFoodAllergyActivity.class);
                        startActivity(mIntent);
                    }, 2000);

                } else if (id == R.id.nav_Search) {
                    mDrawerLayout.closeDrawers();

                    SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                    dialog.setCancelable(false);
                    dialog.show();

                    new Handler().postDelayed(() -> {
                        dialog.dismissWithAnimation();
                        Intent mIntent = new Intent(MainActivity.this, SearchInFoodActivity.class);
                        startActivity(mIntent);
                    }, 2000);

                } else if (id == R.id.nav_Help) {
                    mDrawerLayout.closeDrawers();
                    getHelp();

                } else if (id == R.id.nav_logout) {
                    mDrawerLayout.closeDrawers();
                    getAboutUs();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        });

        cv_scanner.setOnClickListener(view -> {
            mOpenFileChooser(1);
        });

        cv_scanner_And_Rec.setOnClickListener(view -> {
            mOpenFileChooser(2);
        });

        cv_FoodAndNutrition.setOnClickListener(view -> {
            SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            dialog.setTitleText(getResources().getString(R.string.pleaseWait));
            dialog.setCancelable(false);
            dialog.show();

            new Handler().postDelayed(() -> {
                dialog.dismissWithAnimation();
                Intent mIntent = new Intent(MainActivity.this, FoodAndNutritionActivity.class);
                startActivity(mIntent);
            }, 2000);
        });

        cv_AddAllFood = findViewById(R.id.cv_AddAllFood);
        cv_AddAllFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                dialog.setCancelable(false);
                dialog.show();

                new Handler().postDelayed(() -> {
                    dialog.dismissWithAnimation();
                    Intent mIntent = new Intent(MainActivity.this, AllFoodAllergyActivity.class);
                    startActivity(mIntent);
                }, 2000);
            }
        });

        cv_SearchFood = findViewById(R.id.cv_SearchFood);
        cv_SearchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                dialog.setCancelable(false);
                dialog.show();

                new Handler().postDelayed(() -> {
                    dialog.dismissWithAnimation();
                    Intent mIntent = new Intent(MainActivity.this, SearchInFoodActivity.class);
                    startActivity(mIntent);
                }, 2000);
            }
        });

        cv_MyProfile = findViewById(R.id.cv_MyProfile);
        cv_MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                dialog.setTitleText(getResources().getString(R.string.pleaseWait));
                dialog.setCancelable(false);
                dialog.show();

                new Handler().postDelayed(() -> {
                    dialog.dismissWithAnimation();
                    Intent mIntent = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(mIntent);
                }, 2000);
            }
        });

    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        try {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_IMAGE_CAPTURE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mTakePictureIntent();
            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermission();
                }
            }
        }
    }

    private void mOpenFileChooser(int mFromCameraOrRec) {
        this.mFromCameraOrRec = mFromCameraOrRec;
        final BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
        dialog.setContentView(R.layout.layout_file_picker);
        dialog.setCanceledOnTouchOutside(false);

        TextView tv_close = (TextView) dialog.findViewById(R.id.tv_close);
        ImageView iv_camera = (ImageView) dialog.findViewById(R.id.iv_camera);
        ImageView iv_choose_gallery = (ImageView) dialog.findViewById(R.id.iv_choose_gallery);

        assert tv_close != null;
        tv_close.setOnClickListener(view -> dialog.dismiss());

        assert iv_camera != null;
        iv_camera.setOnClickListener(view -> {
            dialog.dismiss();
            SweetAlertDialog mDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mDialog.setTitleText(getResources().getString(R.string.pleaseWait));
            mDialog.setCancelable(false);
            mDialog.show();

            new Handler().postDelayed(() -> {
                mDialog.dismissWithAnimation();
                mTakePictureIntent();
            }, 1000);
        });

        assert iv_choose_gallery != null;
        iv_choose_gallery.setOnClickListener(view -> {
            dialog.dismiss();
            SweetAlertDialog mDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
            mDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            mDialog.setTitleText(getResources().getString(R.string.pleaseWait));
            mDialog.setCancelable(false);
            mDialog.show();

            new Handler().postDelayed(() -> {
                mDialog.dismissWithAnimation();
                mStartScanner();
            }, 1000);
        });

        dialog.show();
    }

    private void mStartScanner() {
        Intent mIntent = new Intent(MainActivity.this, ScannerActivity.class);
        if (mFromCameraOrRec == 1) {
            mIntent.putExtra("mFromCameraOrRec", 1);
        } else if (mFromCameraOrRec == 2) {
            mIntent.putExtra("mFromCameraOrRec", 2);
        }
        startActivity(mIntent);

//        ScanOptions mOptions = new ScanOptions();
//        mOptions.setPrompt("Scan & Get Instant Result");
//        mOptions.setBeepEnabled(true);
//        mOptions.setOrientationLocked(true);
//        mOptions.setCaptureActivity(CaptureAct.class);
//        barLanucher.launch(mOptions);
    }

    private void mTakePictureIntent() {
        if (checkPermission()) {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, 101);
        } else {
            requestPermission();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SweetAlertDialog dialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText(getResources().getString(R.string.gettingDetails));
        dialog.setCancelable(false);
        dialog.show();

        new Handler().postDelayed(() -> {
            dialog.dismissWithAnimation();
            Intent mIntent = new Intent(MainActivity.this, ScannerResultActivity.class);
            if (mFromCameraOrRec == 1) {
                mIntent.putExtra("mFromCameraOrRec", 1);
            } else if (mFromCameraOrRec == 2) {
                mIntent.putExtra("mFromCameraOrRec", 2);
            }
            startActivity(mIntent);
        }, 1000);

    }

    private void getAboutUs() {
        Dialog mDialog = new Dialog(MainActivity.this);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.layout_for_about);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    private void getHelp() {

        Dialog mDialog = new Dialog(MainActivity.this);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.setContentView(R.layout.layout_for_help);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    ActivityResultLauncher<ScanOptions> barLanucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_SHORT).show();
            Intent mIntent = new Intent(MainActivity.this, ScannerResultActivity.class);
            if (mFromCameraOrRec == 1) {
                mIntent.putExtra("mFromCameraOrRec", 1);
            } else if (mFromCameraOrRec == 2) {
                mIntent.putExtra("mFromCameraOrRec", 2);
            }
            startActivity(mIntent);
            finish();
        }
    });

    private void findViewByIDs() {
        tv_riban = findViewById(R.id.tv_riban);
        cv_scanner = findViewById(R.id.cv_scanner);
        cv_scanner_And_Rec = findViewById(R.id.cv_scanner_And_Rec);
        cv_FoodAndNutrition = findViewById(R.id.cv_FoodAndNutrition);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mActionBarDrawerToggle.onOptionsItemSelected(item);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.backpresstoexit), Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}