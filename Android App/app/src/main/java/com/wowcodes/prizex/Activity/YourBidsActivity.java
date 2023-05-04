package com.mercury.redeem.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.BidUserAdapter;
import com.mercury.redeem.Modelclas.GetBidUser;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class YourBidsActivity extends AppCompatActivity {

    RecyclerView recyclerYourBidsacti;
    LinearLayout lvlYourBidsacti;

    SavePref savePref;
    public BindingService videoService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);
        recyclerYourBidsacti =findViewById(R.id.recycler);
        lvlYourBidsacti =findViewById(R.id.linearlay);
        recyclerYourBidsacti.setLayoutManager(new LinearLayoutManager(YourBidsActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        savePref=new SavePref(YourBidsActivity.this);
        gettraapi();
    }

    public void gettraapi() {
        lvlYourBidsacti.setVisibility(View.VISIBLE);
        try {
            callgettraApi().enqueue(new Callback<GetBidUser>() {
                @Override
                public void onResponse(Call<GetBidUser> call, retrofit2.Response<GetBidUser> response) {

                    try {

                        lvlYourBidsacti.setVisibility(View.GONE);
                        ArrayList<GetBidUser.Get_biduser_Inner> arrayList = response.body().getJSON_DATA();

                        recyclerYourBidsacti.setAdapter(new BidUserAdapter(YourBidsActivity.this, arrayList));

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlYourBidsacti.setVisibility(View.GONE);

                    }



                }

                @Override
                public void onFailure(Call<GetBidUser> call, Throwable t) {
                    lvlYourBidsacti.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<GetBidUser> callgettraApi() {
        return videoService.get_bid_user(savePref.getUserId());
    }

    
}