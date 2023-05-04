package com.mercury.redeem.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.AllBiddersAdapter;
import com.mercury.redeem.Modelclas.AllBidder;
import com.mercury.redeem.Modelclas.AllBid;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class AllBidderActivity extends AppCompatActivity {

    public BindingService videoService;
    RecyclerView recyclerAllBidder;
    LinearLayout lvlAllBidder;
    SavePref savePref;
    String oId;
    TextView txtNoBid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passbook_all);
        savePref = new SavePref(AllBidderActivity.this);
        ImageView imgBackk = findViewById(R.id.imgBackk);
        TextView txtAucname = findViewById(R.id.txtAucname);
        oId = getIntent().getStringExtra("o_id");
        txtAucname.setText("All Participants");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerAllBidder = findViewById(R.id.recycler);
        lvlAllBidder = findViewById(R.id.linearlay);
        txtNoBid = findViewById(R.id.txtNoBid);
        txtNoBid.setVisibility(View.GONE);

        recyclerAllBidder.setLayoutManager(new LinearLayoutManager(AllBidderActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        getofferapi();
    }


    public void getofferapi() {
        lvlAllBidder.setVisibility(View.VISIBLE);
        try {
            callofferApi().enqueue(new Callback<AllBidder>() {
                @Override
                public void onResponse(Call<AllBidder> call, retrofit2.Response<AllBidder> response) {

                    lvlAllBidder.setVisibility(View.GONE);
                    ArrayList<AllBid> arrayList = response.body().getJSON_DATA().get(0).getAll_bid();
                    recyclerAllBidder.setAdapter(new AllBiddersAdapter(AllBidderActivity.this, arrayList));
                    if (arrayList.size() == 0) {
                        txtNoBid.setVisibility(View.VISIBLE);
                    } else {
                        txtNoBid.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<AllBidder> call, Throwable t) {
                    lvlAllBidder.setVisibility(View.GONE);
                    txtNoBid.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            lvlAllBidder.setVisibility(View.GONE);
            txtNoBid.setVisibility(View.VISIBLE);
        }


    }


    private Call<AllBidder> callofferApi() {
        return videoService.get_offers_id(oId, savePref.getUserId());
    }
}