package com.mercury.redeem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rilixtech.CountryCodePicker;
import com.mercury.redeem.Modelclas.LoginModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {

    EditText edForgotNum,edPass;
    SavePref savePref;
    TextView txtSignupButton,txtForgetPassw;
    LinearLayout lvlLoginPage;
    TextView txtLoginActi;
    CountryCodePicker ccp;
    String countrycode;

    public BindingService videoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        savePref = new SavePref(LoginActivity.this);

        edForgotNum=findViewById(R.id.edForgotNum);
        txtForgetPassw=findViewById(R.id.txtForgetPassw);
        lvlLoginPage =findViewById(R.id.linearlay);
        edPass=findViewById(R.id.edPass);
        txtSignupButton=findViewById(R.id.txtSignupButton);
        txtLoginActi=findViewById(R.id.txtLoginActi);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);

        Log.e("CCP",ccp.getSelectedCountryCode());


        txtSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);

            }
        });
        txtLoginActi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()){
                    getloginapi();
                }
            }
        });


        txtForgetPassw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(i);
                finish();
            }
        });
    }



    private boolean validation() {

        if (TextUtils.isEmpty(edForgotNum.getText().toString())) {
            Toast.makeText(this, "Please Enter All Detail", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(edPass.getText().toString())) {
            Toast.makeText(this, "Please Enter All Detail", Toast.LENGTH_LONG).show();
            return false;
        }


        return true;

    }



    public void getloginapi(){
        lvlLoginPage.setVisibility(View.VISIBLE);
        try {
            callloginApi().enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, retrofit2.Response<LoginModel> response) {

                    try {


                        ArrayList<LoginModel.Login_model_Inner> arrayList= response.body().getJSON_DATA();

                        String msg;
                        msg=arrayList.get(0).getMsg();
                        Toast.makeText(LoginActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){
                            savePref.setUserId(arrayList.get(0).getId());
                            savePref.setUserPhone(arrayList.get(0).getPhone());
                            savePref.setemail(arrayList.get(0).getEmail());
                            Intent i=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                        }else {
                            lvlLoginPage.setVisibility(View.GONE);
                        }


                    }catch (Exception e){
                        e.printStackTrace();
                        lvlLoginPage.setVisibility(View.GONE);

                    }




                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    lvlLoginPage.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private Call<LoginModel> callloginApi() {
        return videoService.postUserLogin(edForgotNum.getText().toString(),edPass.getText().toString());
    }

}