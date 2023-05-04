package com.mercury.redeem.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.mercury.redeem.R;


public class WebViewActivity extends AppCompatActivity {
    ImageView imgBackk;
    TextView txtAucname;

    WebView mywebview;
    String url,from,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        imgBackk=(ImageView)findViewById(R.id.imgBackk);
        txtAucname=(TextView)findViewById(R.id.txtAucname);
        mywebview = (WebView) findViewById(R.id.webView);

        try {
            if(getIntent()!=null)
            {
                url=getIntent().getStringExtra("url");
                from=getIntent().getStringExtra("from");
                title=getIntent().getStringExtra("title");

                Log.e("URL",url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(title.equals("1")){
            txtAucname.setText("Terms and Conditions");
        }
        else if(title.equals("2")){
            txtAucname.setText("Seller info");
        }
        else
        {
            txtAucname.setText("Play and Win");
        }

        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(from.equalsIgnoreCase("main"))
                {
                    Intent intent=new Intent(WebViewActivity.this,MainActivity.class);
                    intent.putExtra("page","2");
                    startActivity(intent);
                }
                else if(from.equalsIgnoreCase("categories"))
                {

                    Intent intent=new Intent(WebViewActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(WebViewActivity.this,IntroductionActivity.class);
                    startActivity(intent);
                }

            }
        });




        isNetworkConnected();

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(from.equalsIgnoreCase("main"))
        {
            Intent intent=new Intent(WebViewActivity.this,MainActivity.class);
            intent.putExtra("page","2");
            startActivity(intent);
        }
        else if(from.equalsIgnoreCase("categories"))
        {
            Intent intent=new Intent(WebViewActivity.this,MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Intent intent=new Intent(WebViewActivity.this,IntroductionActivity.class);
            startActivity(intent);
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            //nointernetlayout.setVisibility(View.GONE);
            if(TextUtils.isEmpty(url))
            {
                Toast.makeText(getApplicationContext(),"Invalid URL",Toast.LENGTH_SHORT).show();
                finish();
            }
        else
            {
                mywebview.setWebViewClient(new MyWebViewClient());

                mywebview.loadUrl(url);
                mywebview.getSettings().setJavaScriptEnabled(true);
                mywebview.loadUrl(url); // load the url on the web view
            }

        }
        else
        {
            // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getApplicationContext(), NoInternetActivity.class);
            startActivity(intent);
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}