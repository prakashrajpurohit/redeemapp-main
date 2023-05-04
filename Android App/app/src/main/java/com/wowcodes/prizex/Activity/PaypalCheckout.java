package com.mercury.redeem.Activity;
import static com.mercury.redeem.Constants.PAYPAL_BASE_URL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;


public class PaypalCheckout extends AppCompatActivity
{
    TextView orderID_label,amt;
    Button confirm_btn;
    String orderID;
    public BindingService videoService;
    String[] strArray = Paypalpay.senduserdata();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_checkout);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        //get the orderID from the query parameter
        Uri redirectUri = getIntent().getData();
        orderID = redirectUri.getQueryParameter("token");
        orderID_label = (TextView) findViewById(R.id.orderID);
        amt = (TextView) findViewById(R.id.amt);
        orderID_label.setText("Order ID: " + orderID);
        amt.setText("Amount: $" +strArray[4]);
        captureOrder(orderID);
        //add an onClick listener to the confirm button
        confirm_btn = findViewById(R.id.confirm_btn);
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //function to finalize the payment
                Intent i=new Intent(PaypalCheckout.this, MainActivity.class);
                startActivity(i);
            }
        });
        // ATTENTION: This was auto-generated to handle app links.

    }

    void captureOrder(String orderID){
        //get the accessToken from MainActivity
        String accessToken = Paypalpay.getMyAccessToken();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Accept", "application/json");
        client.addHeader("Content-type", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);

        client.post(PAYPAL_BASE_URL+"/v2/checkout/orders/"+orderID+"/capture", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                Log.e("RESPONSE", response);
                Toast.makeText(PaypalCheckout.this, "Payment Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    postUserwalletUpdate();
                    //redirect back to home page of app
                   // Intent intent = new Intent(PaypalCheckout.this, MainActivity.class);
                   // startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void postUserwalletUpdate() {

        try {
            calladdbidApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {
                  //  System.out.println("kajalarraysuccess");
                    try {
                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(PaypalCheckout.this, ""+arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){
                            Toast.makeText(PaypalCheckout.this, "Payment Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(PaypalCheckout.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                           // System.out.println("kajalaarrrfail");
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
      //  System.out.println("kajalarray"+strArray[0]+" "+strArray[1]+" "+strArray[2]+" "+orderID);
        return videoService.postUserwalletUpdate(strArray[0], strArray[1], strArray[2], orderID);
    }
}
