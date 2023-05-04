package com.mercury.redeem.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mercury.redeem.Adapter.TicketAdapter;
import com.mercury.redeem.Modelclas.AllBidder;
import com.mercury.redeem.Modelclas.UserBid;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ViewAllTicketsActivity extends AppCompatActivity {

    public BindingService videoService;
    RecyclerView recyclerYourBid;
    LinearLayout lvlYourBid;
    SavePref savePref;
    String oId;
    TextView txtNoBid;
    ArrayList<UserBid> ticketlist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewalltickets);
        savePref = new SavePref(ViewAllTicketsActivity.this);
        ImageView imgBackk = findViewById(R.id.imgBackk);
        TextView txtAucname = findViewById(R.id.txtAucname);
        oId = getIntent().getStringExtra("o_id");
        txtAucname.setText("Your Tickets");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerYourBid = findViewById(R.id.recycler_view);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        //getofferapi();
        isNetworkConnected();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            //nointernetlayout.setVisibility(View.GONE);

            getofferapi();

        }
        else
        {
            // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getApplicationContext(), NoInternetActivity.class);
            startActivity(intent);
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void getofferapi() {
      //  lvlYourBid.setVisibility(View.VISIBLE);
        try {
            callofferApi().enqueue(new Callback<AllBidder>() {
                @Override
                public void onResponse(Call<AllBidder> call, retrofit2.Response<AllBidder> response) {

                   // lvlYourBid.setVisibility(View.GONE);
                    ticketlist = response.body().getJSON_DATA().get(0).getUser_bid();
                    recyclerYourBid.setAdapter(new TicketAdapter(ViewAllTicketsActivity.this, ticketlist,"2","6"));
                    //recyclerYourBid.setLayoutManager(new LinearLayoutManager(ViewAllTicketsActivity.this));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ViewAllTicketsActivity.this,LinearLayoutManager.VERTICAL,true);
                    layoutManager.setStackFromEnd(true);

                    recyclerYourBid.setLayoutManager(layoutManager);
                   /* if (arrayList.size() == 0) {
                        txtNoBid.setVisibility(View.VISIBLE);
                    } else {
                        txtNoBid.setVisibility(View.GONE);
                    }*/

                }

                @Override
                public void onFailure(Call<AllBidder> call, Throwable t) {
                   // lvlYourBid.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
          //  lvlYourBid.setVisibility(View.GONE);

        }


    }


    private Call<AllBidder> callofferApi() {

        Log.e("LL",oId+" "+savePref.getUserId());


        return videoService.get_offers_id(oId, savePref.getUserId());
    }
}
