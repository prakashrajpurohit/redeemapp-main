package com.mercury.redeem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.rilixtech.CountryCodePicker;
import com.mercury.redeem.Modelclas.SettingModel;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    EditText edRegisterName, edRegisterEmail,edForgotNum,edRegisterCode,edPass;
    TextView txtLoginActi;
    SavePref savePref;
    TextView txtPrivacy;
    public BindingService videoService;
    LinearLayout lvlRegister;
    CheckBox checkBox;
    private String androidDeviceId;
    String getPrivacy;
    CountryCodePicker ccp;
    String countrycode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        savePref = new SavePref(RegisterActivity.this);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        androidDeviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        checkBox = findViewById(R.id.checkbox);
        txtPrivacy = findViewById(R.id.txtPrivacy);

        edRegisterName =findViewById(R.id.txtYourName);
        edRegisterEmail =findViewById(R.id.txtEmailId);
        edForgotNum=findViewById(R.id.edForgotNum);
        edRegisterName =findViewById(R.id.txtYourName);
        lvlRegister =findViewById(R.id.linearlay);
        edRegisterCode=findViewById(R.id.edRegisterCode);
        edPass=findViewById(R.id.edPass);
        txtLoginActi=findViewById(R.id.txtLoginActi);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);

        Log.e("CCP",ccp.getSelectedCountryCode());


        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(RegisterActivity.this, PrivacyPolicyActivity.class);
                i.putExtra("check", getPrivacy);
                startActivity(i);

            }
        });

        txtLoginActi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()){
                    if (checkBox.isChecked()) {
                        savePref.setUserPhone(edForgotNum.getText().toString());
                        getregapi();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Please select Privacy Policy", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        getsetting();
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

        if (TextUtils.isEmpty(edRegisterEmail.getText().toString())) {
            Toast.makeText(this, "Please Enter All Detail", Toast.LENGTH_LONG).show();
            return false;
        }
        if (TextUtils.isEmpty(edRegisterName.getText().toString())) {
            Toast.makeText(this, "Please Enter All Detail", Toast.LENGTH_LONG).show();
            return false;
        }


        return true;

    }


    public void getregapi(){
        lvlRegister.setVisibility(View.VISIBLE);
        try {
            callregisterApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    ArrayList<SuccessModel.Suc_Model_Inner> arrayList= response.body().getJSON_DATA();


                    String msg;
                    msg=arrayList.get(0).getMsg();

                    Toast.makeText(RegisterActivity.this, ""+msg, Toast.LENGTH_SHORT).show();


                    if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){

                        Intent i=new Intent(RegisterActivity.this, PhoneVerificationActivity.class);
                        i.putExtra("phone",""+ccp.getSelectedCountryCode()+""+edForgotNum.getText().toString());
                        startActivity(i);
                        finish();
                    }else {
                        lvlRegister.setVisibility(View.GONE);
                    }


                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                    lvlRegister.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private Call<SuccessModel> callregisterApi() {
        return videoService.postUserRegister(edRegisterName.getText().toString(), edRegisterEmail.getText().toString(),edForgotNum.getText().toString(),edRegisterCode.getText().toString(),edPass.getText().toString(),androidDeviceId);
    }




    public void getsetting() {
        lvlRegister.setVisibility(View.VISIBLE);
        try {
            callseting().enqueue(new Callback<SettingModel>() {
                @Override
                public void onResponse(Call<SettingModel> call, retrofit2.Response<SettingModel> response) {


                    try {
                        lvlRegister.setVisibility(View.GONE);

                        ArrayList<SettingModel.Setting_model_Inner> arrayList = response.body().getJSON_DATA();


                        getPrivacy =arrayList.get(0).getApp_privacy_policy();

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlRegister.setVisibility(View.GONE);

                    }



                }

                @Override
                public void onFailure(Call<SettingModel> call, Throwable t) {
                    lvlRegister.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<SettingModel> callseting() {
        return videoService.settings();
    }


}
