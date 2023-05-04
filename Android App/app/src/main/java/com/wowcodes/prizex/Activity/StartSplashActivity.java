package com.mercury.redeem.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class StartSplashActivity extends AppCompatActivity {


    public BindingService videoService;
    SavePref savePref;
    private static final String MyPREFERENCES = "DoctorPrefrance";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startsplash);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        savePref = new SavePref(StartSplashActivity.this);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPreferences.getBoolean("userfirsttime",false)){
                    checkloginornot();
                }
                else {
                    Intent i = new Intent(getBaseContext(), IntroductionActivity.class);
                    startActivity(i);
                    finish();
                }



            }
        }, 1500);


    }


    public void checkloginornot() {

        {

            if (savePref.getUserId().equalsIgnoreCase("0")) {


                Intent intent = new Intent(StartSplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            } else {

                getprofile();

            }


        }
    }


    public void getprofile() {

        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    try {

                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();
                        if (arrayList.get(0).getBan().equalsIgnoreCase("0")) {
                            Intent intent = new Intent(StartSplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            savePref.setUserPhone("");
                            savePref.setUserId("0");
                            savePref.setemail("");


                            Intent intent = new Intent(StartSplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }catch (Exception e){
                        e.printStackTrace();

                    }



                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }


}
