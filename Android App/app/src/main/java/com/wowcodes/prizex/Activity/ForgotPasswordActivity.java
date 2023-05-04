package com.mercury.redeem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;


public class ForgotPasswordActivity extends AppCompatActivity {

    EditText edForgotNum;
    SavePref savePref;
    LinearLayout lvlSubmit;
    LinearLayout lvlForgotPass;
    ImageView imgBackBtn;
    public BindingService videoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.gray));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        savePref=new SavePref(ForgotPasswordActivity.this);
        edForgotNum=findViewById(R.id.edForgotNum);
        lvlSubmit=findViewById(R.id.lvlSubmit);
        imgBackBtn=findViewById(R.id.imgBackBtn);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        lvlForgotPass =findViewById(R.id.linearlay);
        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        lvlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                forpass();

            }
        });

    }


    public void forpass(){
        lvlForgotPass.setVisibility(View.VISIBLE);
        try {
            callloginApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {
                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList= response.body().getJSON_DATA();

                        String msg;
                        msg=arrayList.get(0).getMsg();
                        Toast.makeText(ForgotPasswordActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){

                            savePref.setUserPhone(edForgotNum.getText().toString());

                            Intent i=new Intent(ForgotPasswordActivity.this, PhoneVerificationnActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            lvlForgotPass.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlForgotPass.setVisibility(View.GONE);

                    }
                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                    lvlForgotPass.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private Call<SuccessModel> callloginApi() {
        return videoService.forgotpassword(edForgotNum.getText().toString());
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}