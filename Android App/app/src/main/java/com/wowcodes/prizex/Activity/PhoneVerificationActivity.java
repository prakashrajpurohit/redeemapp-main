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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mercury.redeem.Modelclas.RegisterModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;

public class PhoneVerificationActivity extends AppCompatActivity {

    public BindingService videoService;
    Button buttonVerifyNow;
    SavePref savePref;
    ImageView imgBackBtn;
    String getOtp;
    EditText edEt4, edEt3, edEt2, edEt1, edEt5, edEt6;
    LinearLayout lvlPhoneVerification;
    FirebaseAuth fAuth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    String verificationId, phone;
    PhoneAuthProvider.ForceResendingToken token;

    TextView enterOtp, agree;
    LinearLayout otpText, otp, resend, invalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonevarification);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        savePref = new SavePref(PhoneVerificationActivity.this);
        lvlPhoneVerification = findViewById(R.id.linearlay);
        edEt1 = findViewById(R.id.edEt1);
        imgBackBtn = findViewById(R.id.imgBackBtn);
        edEt2 = findViewById(R.id.edEt2);
        buttonVerifyNow = findViewById(R.id.buttonVerifyNow);
        edEt3 = findViewById(R.id.edEt3);
        edEt4 = findViewById(R.id.edEt4);
        edEt5 = findViewById(R.id.edEt5);
        edEt6 = findViewById(R.id.edEt6);

        fAuth=FirebaseAuth.getInstance();

        enterOtp=findViewById(R.id.enterOtp);
        agree=findViewById(R.id.agree);
        otpText=findViewById(R.id.otpText);
        otp=findViewById(R.id.otp);
        resend=findViewById(R.id.resend);
        invalid=findViewById(R.id.invalid);

        edEt1.addTextChangedListener(new GenericTextWatcher(edEt1));
        edEt2.addTextChangedListener(new GenericTextWatcher(edEt2));
        edEt3.addTextChangedListener(new GenericTextWatcher(edEt3));
        edEt4.addTextChangedListener(new GenericTextWatcher(edEt4));
        edEt5.addTextChangedListener(new GenericTextWatcher(edEt5));
        edEt6.addTextChangedListener(new GenericTextWatcher(edEt6));

        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        phone="+"+phone;

        callbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
               /* final String code= phoneAuthCredential.getSmsCode();
                System.out.println("kajalfirehere");
                if(code!=null){
                    System.out.println("kajalhere2"+code);
                    edEt1.setText(code.charAt(0));
                    edEt2.setText(code.charAt(1));
                    edEt3.setText(code.charAt(2));
                    edEt4.setText(code.charAt(3));
                    edEt5.setText(code.charAt(4));
                    edEt6.setText(code.charAt(5));

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                    authenticateUser(credential);
                    getregapi();
                    Intent i=new Intent(getBaseContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }*/
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(PhoneVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId=s;
                token=forceResendingToken;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                resend.setVisibility(View.VISIBLE);
            }
        };

        verifyPhoneNumber(phone);

        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonVerifyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonVerifyNow.getText().toString().equalsIgnoreCase("I Agree")){
                    enterOtp.setVisibility(View.VISIBLE);
                    agree.setVisibility(View.GONE);
                    otpText.setVisibility(View.VISIBLE);
                    otp.setVisibility(View.VISIBLE);
                    buttonVerifyNow.setText("Verify");
                }
                else {
                    getOtp = edEt1.getText().toString() + edEt2.getText().toString() + edEt3.getText().toString() + edEt4.getText().toString() + edEt5.getText().toString() + edEt6.getText().toString();

                    if(getOtp.length()!=6){
                        invalid.setVisibility(View.VISIBLE);
                    }
                    else {
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, getOtp);
                        authenticateUser(credential);
                    }
                }
            }
        });

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPhoneNumber(phone);
                resend.setVisibility(View.GONE);
                Toast.makeText(PhoneVerificationActivity.this, "OTP re-sent ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verifyPhoneNumber(String phoneNum){
        PhoneAuthOptions options= PhoneAuthOptions.newBuilder(fAuth)
                .setActivity(this)
                .setPhoneNumber(phoneNum)
                .setTimeout(5L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void authenticateUser(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(PhoneVerificationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                getregapi();
                Intent i=new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PhoneVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(PhoneVerificationActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")) {
                            savePref.setUserId(arrayList.get(0).getId());
                            savePref.setUserPhone(arrayList.get(0).getPhone());
                            savePref.setemail(arrayList.get(0).getEmail());
                            Intent i = new Intent(PhoneVerificationActivity.this, MainActivity.class);
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
        return videoService.mobilenumberverify_setting(savePref.getUserPhone(), "1234");
    }

    public class GenericTextWatcher implements TextWatcher {
        private final View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
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
                        edEt2.requestFocus();
                    break;
                case R.id.edEt3:
                    if (text.length() == 1)
                        edEt4.requestFocus();
                    else if (text.length() == 0)
                        edEt3.requestFocus();
                    break;
                case R.id.edEt4:
                    if(text.length()==1)
                        edEt5.requestFocus();
                    else if (text.length() == 0)
                        edEt4.requestFocus();
                    break;
                case R.id.edEt5:
                    if(text.length()==1)
                        edEt6.requestFocus();
                    else if(text.length()==0)
                        edEt5.requestFocus();
                    break;
                case R.id.edEt6:
                    if(text.length()==0)
                        edEt6.requestFocus();
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
        Intent i = new Intent(PhoneVerificationActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
