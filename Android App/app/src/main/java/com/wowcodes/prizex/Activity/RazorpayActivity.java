package com.mercury.redeem.Activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import com.mercury.redeem.CheckoutActivityJava;
import com.mercury.redeem.Modelclas.GetCoin;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class RazorpayActivity extends AppCompatActivity implements PaymentResultListener {

    TextView txtPay , paymentpaypal,txtStripe,txtFluttrWave,txtPayumoney,txtPaytm,txtMpesa;
    ImageView imgBackk;
    public BindingService videoService;
    SavePref savePref;
    LinearLayout lvlRazorpayActi;
    String oId, packageId, getWallet;
    TextView txtAucname;
    TextView txtSetName,txtGetCoin,txtAmount;
    CardView cardView;

    GetCoin.Get_Coin_Inner coin_inner;
    String paymentIntentClientSecret;
    String payumoneykey="uTK7XFZC";
    public static final String MONEY_HASHPAYUMONEY = "https://wowcodes.in/payumoney.php";

    int random =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);
        txtPay=findViewById(R.id.txtPay);
        imgBackk=findViewById(R.id.imgBackk);
        txtAucname=findViewById(R.id.txtAucname);
        txtStripe=findViewById(R.id.stripeid);

        txtSetName=findViewById(R.id.txtSetName);
        txtGetCoin=findViewById(R.id.txtGetCoin);
        lvlRazorpayActi =findViewById(R.id.linearlay);
        txtAmount=findViewById(R.id.txtAmount);
        cardView =findViewById(R.id.card);
        txtFluttrWave=findViewById(R.id.flutterwavepayid);
        txtPaytm=findViewById(R.id.paytmpayid);
        txtPayumoney=findViewById(R.id.payumoneyid);
        txtMpesa=findViewById(R.id.txtMpesa);

        txtAucname.setText("Payment Methods");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        savePref=new SavePref(RazorpayActivity.this);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        coin_inner= (GetCoin.Get_Coin_Inner) getIntent().getSerializableExtra("list");

        txtSetName.setText(coin_inner.getC_name());
        txtGetCoin.setText(coin_inner.getC_coin());
        txtAmount.setText(" $ "+coin_inner.getC_amount());
        getWallet =coin_inner.getC_coin();
        packageId =coin_inner.getC_id();


        txtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lvlRazorpayActi.setVisibility(View.VISIBLE);
                startPayment(coin_inner.getC_amount());
            }
        });
        paymentpaypal=findViewById(R.id.paypalpayid);
        paymentpaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RazorpayActivity.this, ""+coin_inner.getC_amount(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RazorpayActivity.this, Paypalpay.class);
                //  intent2.putExtra("phonenumber", coin_inner.getC_amount());
                intent.putExtra("coins", coin_inner.getC_coin());
                intent.putExtra("wallet", getWallet);
                intent.putExtra("packageid", packageId);
                intent.putExtra("amt", coin_inner.getC_amount());
                startActivity(intent);
            }
        });
        txtMpesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RazorpayActivity.this, ""+coin_inner.getC_amount(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RazorpayActivity.this, mpesaActivity.class);
                //  intent2.putExtra("phonenumber", coin_inner.getC_amount());
                intent.putExtra("coins", coin_inner.getC_coin());
                intent.putExtra("wallet", getWallet);
                intent.putExtra("packageid", packageId);
                intent.putExtra("amt", coin_inner.getC_amount());
                startActivity(intent);
            }
        });
        txtStripe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RazorpayActivity.this, CheckoutActivityJava.class);
                //  intent2.putExtra("phonenumber", coin_inner.getC_amount());
               // intent.putExtra("plan_name", coin_inner.getC_coin());
               intent.putExtra("plan_price", Integer.parseInt(coin_inner.getC_amount()));
                intent.putExtra("list",coin_inner);

                //Log.e("AM",coin_inner.getC_amount()+"::"+coin_inner.getC_name());
                startActivity(intent);
            }
        });

        txtFluttrWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makepaymentflutterwave(coin_inner.getC_amount());

                Log.e("AMY",coin_inner.getC_amount());
            }
        });

        txtPaytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* random= ThreadLocalRandom.current().nextInt(1, 1000000);

                Log.e("Random",String.valueOf(random)+"");
                Intent intent = new Intent(RazorpayActivity.this, PaytmPayActivity.class);
                intent.putExtra("amount", coin_inner.getC_amount());
                intent.putExtra("coinid", coin_inner.getC_id());
                intent.putExtra("oid",String.valueOf(random));
                intent.putExtra("list",coin_inner);
                intent.putExtra("getWallet",coin_inner.getC_coin());

                startActivity(intent);*/



            }
        });

        txtPayumoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //makepaymentpayumoney(coin_inner.getC_amount());
            }
        });
    }


    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        oId =razorpayPaymentID;
        postUserwalletUpdate();
    }

    @Override
    public void onPaymentError(int i, String s) {

        lvlRazorpayActi.setVisibility(View.GONE);
    }


    public void startPayment(String amou) {


        final Context activity=this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", coin_inner.getC_name());
            options.put("description", "");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "");
            options.put("currency", "INR");

            int priceint = Integer.parseInt(amou);


            try {
                priceint = priceint * 100;
            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }


            options.put("amount", priceint + "");


            JSONObject preFill = new JSONObject();
            preFill.put("email", savePref.getemail());
            preFill.put("contact",savePref.getUserPhone());
            options.put("prefill", preFill);

            co.open((Activity) activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void postUserwalletUpdate() {
        lvlRazorpayActi.setVisibility(View.VISIBLE);
        try {
            calladdbidApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {

                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(RazorpayActivity.this, ""+arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){
                            Intent i=new Intent(RazorpayActivity.this, MainActivity.class);
                            startActivity(i);
                        }else{
                            lvlRazorpayActi.setVisibility(View.GONE);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlRazorpayActi.setVisibility(View.GONE);

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

        return videoService.postUserwalletUpdate(savePref.getUserId(), getWallet, packageId, oId);
    }

    private void makepaymentflutterwave(String c_amount) {
// TODO: Change the Flutterwave Payment Details below ;)
        new RavePayManager(this)
                .setAmount(Double.parseDouble(c_amount))
                .setEmail("hello@wowcodes.in")
                .setCountry("ZA")
                .setCurrency("ZAR")
                .setfName("Wow Codes")
                .setlName("Wow")
                .setNarration("Coin Purchase")
                .setPublicKey("FLWPUBK-81797a4166591b6b5f3e5650a77dbe36-X")
                .setEncryptionKey("952970628f508400688317af")
                .setTxRef(System.currentTimeMillis() + "Ref")
                .acceptAccountPayments(true)
                .acceptCardPayments(true)
                .acceptMpesaPayments(true)
                .acceptBankTransferPayments(true)
                .onStagingEnv(false)
                .shouldDisplayFee(true)
                .showStagingLabel(true)
                .initialize();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_LONG).show();
                postUserwalletUpdate();
               // addbid();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_LONG).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_LONG).show();
            }
        }
        /*else if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {

            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);
            ResultModel resultModel = data.getParcelableExtra(PayUmoneyFlowManager.ARG_RESULT);

            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {

                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    showAlert("Payment Successful");
                    postUserwalletUpdate();
                    //addbid();
                } else if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.CANCELLED)) {
                    showAlert("Payment Cancelled");
                } else if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.FAILED)) {
                    showAlert("Payment Failed");
                }

            } else if (resultModel != null && resultModel.getError() != null) {
                Toast.makeText(this, "Error check log", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Both objects are null", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_CANCELED) {
            showAlert("Payment Cancelledd");
        }*/
    }
    /*private void makepaymentpayumoney(String c_amount) {


        PayUmoneySdkInitializer.PaymentParam.Builder builder = new PayUmoneySdkInitializer.PaymentParam.Builder();
        builder.setAmount(c_amount)
                .setTxnId(System.currentTimeMillis() + "")
                .setPhone("9339932830")
                .setProductName(coin_inner.getC_name())
                .setFirstName("Wow Codes")
                .setEmail("hello@wowcodes.in")
                .setsUrl("https://payuresponse.firebaseapp.com/success")
                .setfUrl("https://payuresponse.firebaseapp.com/failure")
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(false)
                .setKey(payumoneykey)

                .setMerchantId("6423740");

        try {
            PayUmoneySdkInitializer.PaymentParam mPaymentParams = builder.build();
            calculateHashInServer(mPaymentParams);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }

    private void calculateHashInServer(final PayUmoneySdkInitializer.PaymentParam mPaymentParams) {
        //ProgressUtils.showLoadingDialog(this);
        String url = MONEY_HASHPAYUMONEY;
        StringRequest request = new StringRequest(Request.Method.POST, url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // mTxvBuy.setEnabled(true);
                        String merchantHash = "";

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            merchantHash = jsonObject.getString("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //ProgressUtils.cancelLoading();

                        if (merchantHash.isEmpty() || merchantHash.equals("")) {
                            Toast.makeText(RazorpayActivity.this, "Could not generate hash", Toast.LENGTH_SHORT).show();
                        } else {
                            mPaymentParams.setMerchantHash(merchantHash);

                            PayUmoneyFlowManager.startPayUMoneyFlow(mPaymentParams, RazorpayActivity.this, R.style.AppBaseTheme, true);
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTxvBuy.setEnabled(true);
                        if (error instanceof NoConnectionError) {
                            Toast.makeText(RazorpayActivity.this, "Connect to internet Volley", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RazorpayActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //  ProgressUtils.cancelLoading();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return mPaymentParams.getParams();
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
*/
    private void showAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}