package com.mercury.redeem.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.WalletAdapter;
import com.mercury.redeem.Modelclas.GetWallet;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class WalletPassbookActivity extends AppCompatActivity {

    RecyclerView recyclerWalletPass;
    LinearLayout lvlWalletPass;
    SavePref savePref;

    public BindingService videoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passbook);
        savePref=new SavePref(WalletPassbookActivity.this);
        ImageView imgBackk=findViewById(R.id.imgBackk);
        TextView txtAucname=findViewById(R.id.txtAucname);
        txtAucname.setText("Coin Purchases");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerWalletPass =findViewById(R.id.recycler);
        lvlWalletPass =findViewById(R.id.linearlay);
        recyclerWalletPass.setLayoutManager(new LinearLayoutManager(WalletPassbookActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        getcoinapi();
    }


    public void getcoinapi() {
        lvlWalletPass.setVisibility(View.VISIBLE);
        try {
            callcoinApi().enqueue(new Callback<GetWallet>() {
                @Override
                public void onResponse(Call<GetWallet> call, retrofit2.Response<GetWallet> response) {


                    try {


                        lvlWalletPass.setVisibility(View.GONE);
                        ArrayList<GetWallet.Get_Wallet_Inner> arrayList = response.body().getJSON_DATA();
                        recyclerWalletPass.setAdapter(new WalletAdapter(WalletPassbookActivity.this, arrayList));

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlWalletPass.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<GetWallet> call, Throwable t) {
                    lvlWalletPass.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<GetWallet> callcoinApi() {
        return videoService.get_wallet_passbook(savePref.getUserId());
    }
}