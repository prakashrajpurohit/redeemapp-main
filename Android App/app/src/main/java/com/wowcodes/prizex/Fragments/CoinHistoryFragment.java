package com.mercury.redeem.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Activity.GetCoinTraActivity;
import com.mercury.redeem.Activity.VisitWebsiteActivity;
import com.mercury.redeem.Activity.WalletPassbookActivity;
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

public class CoinHistoryFragment extends Fragment {
    RecyclerView recyclerCoinhist;
    TextView txtGetCoin,txtGetCoinHis;
    ImageView imgPrivacy;
    LinearLayout lvlCoinhistr;
    TextView txtShare;
    SavePref savePref;
    TextView txtTran;
    String code;
    public BindingService videoService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_coin, container, false);
        txtTran =view.findViewById(R.id.text_tran);

        txtGetCoin=view.findViewById(R.id.txtGetCoin);
        txtShare=view.findViewById(R.id.txtShare);
        imgPrivacy=view.findViewById(R.id.imgPrivacy);
        txtGetCoinHis=view.findViewById(R.id.txtGetCoinHis);
        savePref=new SavePref(getContext());
        recyclerCoinhist =view.findViewById(R.id.recycler);
        lvlCoinhistr =view.findViewById(R.id.linearlay);


        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        recyclerCoinhist.setLayoutManager(new LinearLayoutManager(getContext()));
        txtTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), WalletPassbookActivity.class);
                startActivity(i);

            }
        });
        imgPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), VisitWebsiteActivity.class);
                i.putExtra("url","https://luckyboli.com/faq/");
                i.putExtra("name","Faq");
                startActivity(i);
            }
        });

        txtGetCoinHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), GetCoinTraActivity.class);
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
                    String sAux = "Invite your friends and share code  " + code + "\n\n";
                    sAux = sAux + Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getApplicationContext().getPackageName());
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        getcoinapi();

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        getprofile();


    }

    public void getcoinapi() {
        lvlCoinhistr.setVisibility(View.VISIBLE);
        try {
            callcoinApi().enqueue(new Callback<GetCoin>() {
                @Override
                public void onResponse(Call<GetCoin> call, retrofit2.Response<GetCoin> response) {

                    try {
                        lvlCoinhistr.setVisibility(View.GONE);
                        ArrayList<GetCoin.Get_Coin_Inner> arrayList = response.body().getJSON_DATA();
                        recyclerCoinhist.setAdapter(new CoinAdapter(getContext(), arrayList));
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlCoinhistr.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<GetCoin> call, Throwable t) {
                    lvlCoinhistr.setVisibility(View.GONE);
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
        lvlCoinhistr.setVisibility(View.VISIBLE);
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    lvlCoinhistr.setVisibility(View.GONE);
                    ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();
                    txtGetCoin.setText(arrayList.get(0).getWallet()+" Coins Avaliable");

                    code=arrayList.get(0).getCode();


                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    lvlCoinhistr.setVisibility(View.GONE);
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
