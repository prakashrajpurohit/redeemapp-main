package com.mercury.redeem.Activity;

import static com.mercury.redeem.Constants.CLIENT_ID;
import static com.mercury.redeem.Constants.CLIENT_SECRET;
import static com.mercury.redeem.Constants.PAYPAL_BASE_URL;
import static com.mercury.redeem.Constants.PAYPAL_CURRENCY;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;


import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;


/**
 *
 * THIS FILE IS OVERWRITTEN BY `androidSDK/src/<general|partner>sampleAppJava.
 * ANY UPDATES TO THIS FILE WILL BE REMOVED IN RELEASES.
 *
 * Basic sample using the SDK to make a payment or consent to future payments.
 *
 * For sample mobile backend interactions, see
 * https://github.com/paypal/rest-api-sdk-python/tree/master/samples/mobile_backend
 */
public class Paypalpay extends Activity {

    public BindingService videoService;
    private SavePref savePref;
    private static String amount,wallet,packageid,old,userId ;
    private static String accessToken;
    public static String getMyAccessToken(){
        return accessToken;
    }
    public static String[] senduserdata() {
        String[] strArray = new String[5];
        strArray[0] = userId;
        strArray[1] = wallet;
        strArray[2] = packageid;
        strArray[3] = old;
        strArray[4] = amount;
        return strArray;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypalpay);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        savePref=new SavePref(Paypalpay.this);
        userId = savePref.getUserId();
        EditText amte = findViewById(R.id.amountedit);
        amount = getIntent().getStringExtra("amt");
        wallet = getIntent().getStringExtra("wallet");
        packageid = getIntent().getStringExtra("packageid");
        old = getIntent().getStringExtra("packageid");
        amte.setText("Order Amount $" + amount);
        amte.setClickable(false);
        amte.setEnabled(false);
        Button payPalButton;
        payPalButton = (Button) findViewById(R.id.buyItBtn);
        getAccessToken();
        payPalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  System.out.println("kajaltoken"+accessToken);
                 createOrder();
                // createOrder(); //this will trigger the checkout flow
                // createOrder(); //this will trigger the checkout flow
            }
        });

    }

    String encodeStringToBase64(){
        String input = CLIENT_ID+":"+CLIENT_SECRET;
        String encode = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encode = Base64.getEncoder().encodeToString(input.getBytes());
        }
        return encode;
        //getEncoder().encodeToString(input.getBytes());
    }

    void getAccessToken(){
        String AUTH = encodeStringToBase64();
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Accept", "application/json");
        client.addHeader("Content-type", "application/x-www-form-urlencoded");
        client.addHeader("Authorization", "Basic "+ AUTH);
        String jsonString = "grant_type=client_credentials";

        HttpEntity entity = new StringEntity(jsonString, "utf-8");

        client.post(this, PAYPAL_BASE_URL+"/v1/oauth2/token", entity, "application/x-www-form-urlencoded",new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                Log.e("RESPONSE", response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
                try {
                    JSONObject jobj = new JSONObject(response);
                    accessToken = jobj.getString("access_token");
                 //   System.out.println("kajal"+accessToken);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void createOrder(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Accept", "application/json");
        client.addHeader("Content-type", "application/json");
        client.addHeader("Authorization", "Bearer " + accessToken);

        String order = "{"
                + "\"intent\": \"CAPTURE\","
                + "\"purchase_units\": [\n" +
                "      {\n" +
                "        \"amount\": {\n" +
                "          \"currency_code\": \""+PAYPAL_CURRENCY+"\",\n" +
                "          \"value\": \""+amount+"\"\n" +
                "        }\n" +
                "      }\n" +
                "    ],\"application_context\": {\n" +
                "        \"brand_name\": \"PRIZEX\",\n" +
                "        \"return_url\": \"https://wowcodes.shop/raffle/paypal/checkout.php\",\n" +
                "        \"cancel_url\": \"https://wowcodes.shop/raffle/paypal/checkout.php\"\n" +
                "    }}";
       // System.out.println("kajalstring"+order);
        HttpEntity entity = new StringEntity(order, "utf-8");

        client.post(this, PAYPAL_BASE_URL+"/v2/checkout/orders", entity, "application/json",new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
              //  System.out.println("kajalfail");
                Log.e("RESPONSE", response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String response) {
             //   System.out.println("kajalsuccess");
                Log.i("RESPONSE", response);
                try {
                    JSONArray links = new JSONObject(response).getJSONArray("links");

                    //iterate the array to get the approval link
                    for (int i = 0; i < links.length(); ++i) {
                        String rel = links.getJSONObject(i).getString("rel");
                        if (rel.equals("approve")){
                            String link = links.getJSONObject(i).getString("href");
                         //   System.out.println("kajallink"+link);
                            //redirect to this link via CCT
                       //    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                       //     builder.setToolbarColor(ContextCompat.getColor(Paypalpay.this, R.color.paypal));
                          //  CustomTabsIntent customTabsIntent = builder.build();
                            //customTabsIntent.launchUrl(Paypalpay.this, Uri.parse(link));
                           Intent browserIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
                            startActivity(browserIntent);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    protected void displayResultText(String result) {
        ((TextView)findViewById(R.id.txtResult)).setText("Result : " + result);
        Toast.makeText(
                getApplicationContext(),
                result, Toast.LENGTH_LONG)
                .show();
    }





}
