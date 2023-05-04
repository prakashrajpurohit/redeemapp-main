package com.mercury.redeem.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.CoinAdapter;
import com.mercury.redeem.Modelclas.GetCoin;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GetCoinActivity extends AppCompatActivity {

    RecyclerView recyclerGetCoin;
    TextView txtGetCoin,txtGetCoinHis;
    LinearLayout lvlGetCoin;
    TextView txtShare;
    SavePref savePref;
    TextView txtTran;
    String referalCode;



    public BindingService videoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_get_coin);



        txtTran =findViewById(R.id.text_tran);

        txtGetCoin=findViewById(R.id.txtGetCoin);
        txtShare=findViewById(R.id.txtShare);
        txtGetCoinHis=findViewById(R.id.txtGetCoinHis);
        savePref=new SavePref(GetCoinActivity.this);

        ImageView imgBackk=findViewById(R.id.imgBackk);
        TextView txtAucname=findViewById(R.id.txtAucname);
       txtAucname.setText("Your Coins");

        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        recyclerGetCoin =findViewById(R.id.recycler);
        lvlGetCoin =findViewById(R.id.linearlay);
        recyclerGetCoin.setLayoutManager(new LinearLayoutManager(GetCoinActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);



        txtTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetCoinActivity.this, WalletPassbookActivity.class);
                startActivity(i);

            }
        });

        txtGetCoinHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(GetCoinActivity.this, GetCoinTraActivity.class);
                startActivity(i);



            }
        });

        txtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    String sAux = "Invite your friends and share code  " + referalCode + "\n\n";
                    sAux = sAux + Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        getcoinapi();
        getprofile();



    }


    public void getcoinapi() {
        lvlGetCoin.setVisibility(View.VISIBLE);
        try {
            callcoinApi().enqueue(new Callback<GetCoin>() {
                @Override
                public void onResponse(Call<GetCoin> call, retrofit2.Response<GetCoin> response) {

                    try {
                        lvlGetCoin.setVisibility(View.GONE);
                        ArrayList<GetCoin.Get_Coin_Inner> arrayList = response.body().getJSON_DATA();
                        recyclerGetCoin.setAdapter(new CoinAdapter(GetCoinActivity.this, arrayList));

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlGetCoin.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<GetCoin> call, Throwable t) {
                    lvlGetCoin.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<GetCoin> callcoinApi() {
        return videoService.get_coin_list();
    }



    public void getprofile() {
        lvlGetCoin.setVisibility(View.VISIBLE);
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    try {
                        lvlGetCoin.setVisibility(View.GONE);
                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();
                        txtGetCoin.setText(arrayList.get(0).getWallet()+" Coins Available");

                        referalCode =arrayList.get(0).getCode();
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlGetCoin.setVisibility(View.GONE);

                    }



                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    lvlGetCoin.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }



}