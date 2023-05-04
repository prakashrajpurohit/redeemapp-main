package com.mercury.redeem.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.TansactionDetailAdapter;
import com.mercury.redeem.Modelclas.GetTransaction;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GetCoinTraActivity extends AppCompatActivity {

    RecyclerView recyclerGetCoinTra;
    LinearLayout lvlGetCoinTra;

    SavePref savePref;
    public BindingService videoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__bid);
        recyclerGetCoinTra =findViewById(R.id.recycler);
        lvlGetCoinTra =findViewById(R.id.linearlay);
        savePref=new SavePref(GetCoinTraActivity.this);

        ImageView imgBackk=findViewById(R.id.imgBackk);
        TextView txtAucname=findViewById(R.id.txtAucname);
        txtAucname.setText("Coin Transactions");

        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerGetCoinTra.setLayoutManager(new LinearLayoutManager(GetCoinTraActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);



        gettraapi();



    }


    public void gettraapi() {
        lvlGetCoinTra.setVisibility(View.VISIBLE);
        try {
            callgettraApi().enqueue(new Callback<GetTransaction>() {
                @Override
                public void onResponse(Call<GetTransaction> call, retrofit2.Response<GetTransaction> response) {

                    try {
                        lvlGetCoinTra.setVisibility(View.GONE);
                        ArrayList<GetTransaction.Get_transaction_Inner> arrayList = response.body().getJSON_DATA();

                        recyclerGetCoinTra.setAdapter(new TansactionDetailAdapter(GetCoinTraActivity.this, arrayList));
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlGetCoinTra.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<GetTransaction> call, Throwable t) {
                    lvlGetCoinTra.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<GetTransaction> callgettraApi() {
        return videoService.get_transaction(savePref.getUserId());
    }



}