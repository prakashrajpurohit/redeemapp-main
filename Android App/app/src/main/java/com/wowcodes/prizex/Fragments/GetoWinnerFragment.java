package com.mercury.redeem.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mercury.redeem.Adapter.OfferWinnerAdapter;
import com.mercury.redeem.Modelclas.GetOffersWinner;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GetoWinnerFragment extends Fragment {
    RecyclerView recyclerGetowinner;
    public BindingService videoService;
    LinearLayout lvlGetowinner;
    SavePref savePref;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_winner, container, false);
        recyclerGetowinner =view.findViewById(R.id.recycler);
        lvlGetowinner =view.findViewById(R.id.linearlay);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getliveofferapi();
                Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerGetowinner.setLayoutManager(new LinearLayoutManager(getContext()));
        savePref=new SavePref(getContext());
        getliveofferapi();

        return view;

    }




    public void getliveofferapi(){
        lvlGetowinner.setVisibility(View.VISIBLE);
        try {
            callliveofferApi().enqueue(new Callback<GetOffersWinner>() {
                @Override
                public void onResponse(Call<GetOffersWinner> call, retrofit2.Response<GetOffersWinner> response) {

                    try {
                        lvlGetowinner.setVisibility(View.GONE);
                        ArrayList<GetOffersWinner.Get_offers_winner_Inner> arrayList= response.body().getJSON_DATA();
                        recyclerGetowinner.setAdapter(new OfferWinnerAdapter(getContext(),arrayList));
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlGetowinner.setVisibility(View.GONE);

                    }

            }

                @Override
                public void onFailure(Call<GetOffersWinner> call, Throwable t) {
                    lvlGetowinner.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private Call<GetOffersWinner> callliveofferApi() {
        return videoService.get_offers_winner(savePref.getUserId());
    }



}
