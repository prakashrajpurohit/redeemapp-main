package com.mercury.redeem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mercury.redeem.Modelclas.RegisterModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class PhoneVerificationnActivity extends AppCompatActivity {

    public BindingService videoService;
    Button buttonVerifyNow;
    SavePref savePref;
    ImageView imgBackBtn;
    String getOtp;
    EditText edEt4, edEt3, edEt2, edEt1;
    LinearLayout lvlPhoneVerification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonevarification);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        savePref = new SavePref(PhoneVerificationnActivity.this);
        lvlPhoneVerification = findViewById(R.id.linearlay);
        edEt1 = findViewById(R.id.edEt1);
        imgBackBtn = findViewById(R.id.imgBackBtn);
        edEt2 = findViewById(R.id.edEt2);
        buttonVerifyNow = findViewById(R.id.buttonVerifyNow);
        edEt3 = findViewById(R.id.edEt3);
        edEt4 = findViewById(R.id.edEt4);


        edEt1.addTextChangedListener(new GenericTextWatcher(edEt1));
        edEt2.addTextChangedListener(new GenericTextWatcher(edEt2));
        edEt3.addTextChangedListener(new GenericTextWatcher(edEt3));
        edEt4.addTextChangedListener(new GenericTextWatcher(edEt4));



        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonVerifyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOtp = edEt1.getText().toString() + edEt2.getText().toString() + edEt3.getText().toString() + edEt4.getText().toString();

                getregapi();
            }
        });

    }


    public void getregapi() {
        lvlPhoneVerification.setVisibility(View.VISIBLE);
        try {
            callregisterApi().enqueue(new Callback<RegisterModel>() {
                @Override
                public void onResponse(Call<RegisterModel> call, retrofit2.Response<RegisterModel> response) {


                    try {

                        ArrayList<RegisterModel.Register_model_Inner> arrayList = response.body().getJSON_DATA();


                        String msg;
                        msg = arrayList.get(0).getMsg();

                        Toast.makeText(PhoneVerificationnActivity.this, "" + msg, Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")) {
                            Intent i = new Intent(PhoneVerificationnActivity.this, ChangepassActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            lvlPhoneVerification.setVisibility(View.GONE);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlPhoneVerification.setVisibility(View.GONE);

                    }




                }

                @Override
                public void onFailure(Call<RegisterModel> call, Throwable t) {
                    lvlPhoneVerification.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<RegisterModel> callregisterApi() {

        return videoService.mobilenumberverify_setting(savePref.getUserPhone(), getOtp);
    }

    public class GenericTextWatcher implements TextWatcher {
        private final View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.edEt1:
                    if (text.length() == 1)
                        edEt2.requestFocus();
                    break;
                case R.id.edEt2:
                    if (text.length() == 1)
                        edEt3.requestFocus();
                    else if (text.length() == 0)
                        edEt1.requestFocus();
                    break;
                case R.id.edEt3:
                    if (text.length() == 1)
                        edEt4.requestFocus();
                    else if (text.length() == 0)
                        edEt2.requestFocus();
                    break;
                case R.id.edEt4:
                    if (text.length() == 0)
                        edEt3.requestFocus();

                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PhoneVerificationnActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}