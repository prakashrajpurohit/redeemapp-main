package com.mercury.redeem.Activity;

import static com.mercury.redeem.Constants.BUSINESS_SHORT_CODE;
import static com.mercury.redeem.Constants.CALLBACKURL;
import static com.mercury.redeem.Constants.PARTYB;
import static com.mercury.redeem.Constants.PASSKEY;
import static com.mercury.redeem.Constants.TRANSACTION_TYPE;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;
import com.mercury.redeem.Utils;
import com.mercury.redeem.model.AccessToken;
import com.mercury.redeem.model.STKPush;
import com.mercury.redeem.model.STKPushQuery;
import com.mercury.redeem.services.DarajaApiClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class mpesaActivity extends AppCompatActivity implements View.OnClickListener {
    public BindingService videoService;
    SavePref savePref;
    String aamount, wallet, packageid, old, amountrupee, timestampresponse, message;
    EditText mAmount;
    EditText mPhone;
    TextView txtMpesa;
    String referenceID;

    private DarajaApiClient mApiClient;
    private ProgressDialog mProgressDialog;
    Button buyItBtnM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa);
        mAmount = (EditText) findViewById(R.id.amountedit);
        mPhone = (EditText) findViewById(R.id.MpesaMobile);
        ButterKnife.bind(this);
        mProgressDialog = new ProgressDialog(this);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        savePref = new SavePref(mpesaActivity.this);
        EditText amte = findViewById(R.id.amountedit);
        aamount = getIntent().getStringExtra("amt");
        wallet = getIntent().getStringExtra("wallet");
        packageid = getIntent().getStringExtra("packageid");
        old = getIntent().getStringExtra("packageid");
        amte.setText("Order Amount $" + aamount);
        amte.setClickable(false);
        amte.setEnabled(false);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        mApiClient = new DarajaApiClient();
        mApiClient.setIsDebug(true);
        buyItBtnM = (Button) findViewById(R.id.buyItBtnM);//Set True to enable logging, false to disable.
        buyItBtnM.setOnClickListener(this);
        getAccessToken();
    }

    public void getAccessToken() {
        mApiClient.setGetAccessToken(true);
        mApiClient.mpesaService().getAccessToken().enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(@NonNull Call<AccessToken> call, @NonNull Response<AccessToken> response) {
                if (response.isSuccessful()) {
                    mApiClient.setAuthToken(
                            response.body().accessToken);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccessToken> call, @NonNull Throwable t) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == buyItBtnM) {
            String phone_number = mPhone.getText().toString();
            String amount = mAmount.getText().toString();
            if(phone_number.isEmpty() || phone_number == null){
                Toast.makeText(mpesaActivity.this, "Please enter phone no.", Toast.LENGTH_SHORT).show();
            }
            else {
                performSTKPush(phone_number, amount);
            }
        }
    }

    public void performSTKPush(String phone_number, String amount) {
        mProgressDialog.setMessage("Processing your request");
        mProgressDialog.setTitle("Please Wait...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();
        String timestamp = Utils.getTimestamp();
        timestampresponse = timestamp;
        STKPush stkPush = new STKPush(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                TRANSACTION_TYPE,
                String.valueOf(amount),
                Utils.sanitizePhoneNumber(phone_number),
                PARTYB,
                Utils.sanitizePhoneNumber(phone_number),
                CALLBACKURL,
                "CompanyXLTD", //Account reference
                "Payment of X"  //Transaction description
        );

        mApiClient.setGetAccessToken(false);

        //Sending the data to the Mpesa API, remember to remove the logging when in production.
        mApiClient.mpesaService().sendPush(stkPush).enqueue(new Callback<STKPush.Mpesa_Push_Response>() {
            @Override
            public void onResponse(@NonNull Call<STKPush.Mpesa_Push_Response> call, @NonNull retrofit2.Response<STKPush.Mpesa_Push_Response> response) {
                try {
                    if (response.isSuccessful()) {
                        message = "Request sent on Mpesa";
                        openmpesadialog();
                        referenceID = response.body().getCheckoutRequestID();
                        mProgressDialog.setMessage("Payment Confirmation in progress");
                        long longTime1 = System.currentTimeMillis();
                        Runnable runnable = new Runnable() { //New Thread
                            @Override
                            public void run() {
                                long longTime2 = System.currentTimeMillis();
                                long longTimeDifference = longTime2 - longTime1;
                                performSTKPushQuery(referenceID);
                            }
                        };
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(runnable, 30000);
                        Timber.d("post submitted to API. %s", response.body());
                    }
                    else if (response.errorBody().string().contains("Invalid PhoneNumber")) {
                        message = "Invalid PhoneNumber!";
                        openmpesadialog();
                        mProgressDialog.dismiss();
                    }
                    else {
                        message = "Payment Failed! Try again.";
                        openmpesadialog();
                        mProgressDialog.dismiss();
                        Timber.e("Response %s", response.errorBody().string());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPush.Mpesa_Push_Response> call, @NonNull Throwable t) {
                message = "Payment Failed! Try again.";
                openmpesadialog();
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void openresponsedialog() {
        final Dialog dialog = new Dialog(mpesaActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.mpesa_success);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        Button cancel = dialog.findViewById(R.id.okaybtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void openmpesadialog() {
        final Dialog dialog = new Dialog(mpesaActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.mpesa_success);
        Window window = dialog.getWindow();
        txtMpesa = (TextView) dialog.findViewById(R.id.txtMpesa);
        txtMpesa.setText(message);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        Button cancel = dialog.findViewById(R.id.okaybtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void performSTKPushQuery(String referenceID) {
        String timestamp = Utils.getTimestamp();
        STKPushQuery stkPushQuery = new STKPushQuery(
                BUSINESS_SHORT_CODE,
                Utils.getPassword(BUSINESS_SHORT_CODE, PASSKEY, timestamp),
                timestamp,
                referenceID//Transaction description
        );

        mApiClient.setGetAccessToken(false);
        mApiClient.mpesaQueryService().sendPushQuery(stkPushQuery).enqueue(new Callback<STKPushQuery.Mpesa_Push_Response>() {
            @Override
            public void onResponse(@NonNull Call<STKPushQuery.Mpesa_Push_Response> call, @NonNull Response<STKPushQuery.Mpesa_Push_Response> response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getResultCode().equalsIgnoreCase("0")) {
                            Timber.d("post submitted to API. %s", response.body());
                            message = "Payment Successful";
                            openmpesadialog();
                            postUserwalletUpdate();
                        } else {
                            message = "Payment Failed ! Try again.";
                         //   System.out.println("kajalarraympesa"+savePref.getUserId()+" "+wallet+" "+packageid+" "+old);
                            openmpesadialog();
                            mProgressDialog.dismiss();
                            Timber.e("Response %s", response.errorBody().string());
                        }
                    }

                    else
                        if(response.errorBody().string().contains("The transaction is being processed")) {
                          performSTKPushQuery(referenceID);
                        }
                        else{
                            message = "Payment Failed ! Try again.";
                            openmpesadialog();
                            mProgressDialog.dismiss();
                            Timber.e("Response %s", response.errorBody().string());
                        }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<STKPushQuery.Mpesa_Push_Response> call, @NonNull Throwable t) {
                message = "Payment Failed ! Try again.";
                openmpesadialog();
                mProgressDialog.dismiss();
                Timber.e(t);
            }
        });
    }

    public void postUserwalletUpdate() {

        try {
            calladdbidApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {

                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(mpesaActivity.this, ""+arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();
                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){
                            Intent i=new Intent(mpesaActivity.this, MainActivity.class);
                            startActivity(i);
                        }else{

                        }

                    }catch (Exception e){
                        e.printStackTrace();


                    }


                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Call<SuccessModel> calladdbidApi() {
   //     System.out.println("kajalarraympesa"+savePref.getUserId()+" "+wallet+" "+packageid+" "+old);
        return videoService.postUserwalletUpdate(savePref.getUserId(), wallet, packageid, referenceID);
    }

}