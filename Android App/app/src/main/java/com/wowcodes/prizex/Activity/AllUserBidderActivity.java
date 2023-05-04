package com.mercury.redeem.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.UserBiddersAdapter;
import com.mercury.redeem.Modelclas.AllBidder;
import com.mercury.redeem.Modelclas.UserBid;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class AllUserBidderActivity extends AppCompatActivity {

    public BindingService videoService;
    RecyclerView recyclerYourBid;
    LinearLayout lvlYourBid;
    SavePref savePref;
    String oId;
    TextView txtNoBid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passbook);
        savePref = new SavePref(AllUserBidderActivity.this);
        ImageView imgBackk = findViewById(R.id.imgBackk);
        TextView txtAucname = findViewById(R.id.txtAucname);
        oId = getIntent().getStringExtra("o_id");
        txtAucname.setText("Your Bid");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerYourBid = findViewById(R.id.recycler);
        lvlYourBid = findViewById(R.id.linearlay);
        txtNoBid = findViewById(R.id.txtNoBid);
        txtNoBid.setVisibility(View.GONE);
        recyclerYourBid.setLayoutManager(new LinearLayoutManager(AllUserBidderActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        getofferapi();
    }


    public void getofferapi() {
        lvlYourBid.setVisibility(View.VISIBLE);
        try {
            callofferApi().enqueue(new Callback<AllBidder>() {
                @Override
                public void onResponse(Call<AllBidder> call, retrofit2.Response<AllBidder> response) {

                    lvlYourBid.setVisibility(View.GONE);
                    ArrayList<UserBid> arrayList = response.body().getJSON_DATA().get(0).getUser_bid();
                    recyclerYourBid.setAdapter(new UserBiddersAdapter(AllUserBidderActivity.this, arrayList));

                    if (arrayList.size() == 0) {
                        txtNoBid.setVisibility(View.VISIBLE);
                    } else {
                        txtNoBid.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<AllBidder> call, Throwable t) {
                    lvlYourBid.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            lvlYourBid.setVisibility(View.GONE);

        }


    }


    private Call<AllBidder> callofferApi() {


        return videoService.get_offers_id(oId, savePref.getUserId());
    }
}
