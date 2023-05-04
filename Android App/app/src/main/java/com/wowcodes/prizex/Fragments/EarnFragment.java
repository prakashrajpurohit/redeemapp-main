package com.mercury.redeem.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class EarnFragment extends Fragment {

    TextView txtRefercode,txtShare;
    LinearLayout lvlEarn;
    String code;
    public BindingService videoService;
    SavePref savePref;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_earn, container, false);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        savePref=new SavePref(getContext());
        txtRefercode=view.findViewById(R.id.txtRefercode);
        txtShare=view.findViewById(R.id.txtShare);
        lvlEarn =view.findViewById(R.id.linearlay);

        txtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    // TODO: You can change the Share message Text from below ;)
                    String sAux = "Invite your friends and share code  " + code + "\n\n";
                    sAux = sAux + Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getApplicationContext().getPackageName());
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        getprofile();
        return view;

    }



    public void getprofile() {
        lvlEarn.setVisibility(View.VISIBLE);
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    try {
                        lvlEarn.setVisibility(View.GONE);
                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();
                        txtRefercode.setText(arrayList.get(0).getCode());
                        code=arrayList.get(0).getCode();

                    }catch (Exception e){
                        e.printStackTrace();
                        lvlEarn.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    lvlEarn.setVisibility(View.GONE);
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
