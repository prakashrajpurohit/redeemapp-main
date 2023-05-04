package com.mercury.redeem.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mercury.redeem.Adapter.GetOrderAdapter;
import com.mercury.redeem.Modelclas.GetOrderUser;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class GetOrderActivity extends AppCompatActivity {

    RecyclerView recyclerGetOrder;
    LinearLayout lvlGetOrder;
    SavePref savePref;

    public BindingService videoService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_passbook);
        savePref=new SavePref(GetOrderActivity.this);
        ImageView imgBackk=findViewById(R.id.imgBackk);
        TextView txtAucname=findViewById(R.id.txtAucname);
        txtAucname.setText("Purchase History");
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        recyclerGetOrder =findViewById(R.id.recycler);
        lvlGetOrder =findViewById(R.id.linearlay);
        recyclerGetOrder.setLayoutManager(new LinearLayoutManager(GetOrderActivity.this));
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        getcoinapi();
    }


    public void getcoinapi() {
        lvlGetOrder.setVisibility(View.VISIBLE);
        try {
            callcoinApi().enqueue(new Callback<GetOrderUser>() {
                @Override
                public void onResponse(Call<GetOrderUser> call, retrofit2.Response<GetOrderUser> response) {

                    try {
                        lvlGetOrder.setVisibility(View.GONE);
                        ArrayList<GetOrderUser.Get_order_user_Inner> arrayList = response.body().getJSON_DATA();
                        recyclerGetOrder.setAdapter(new GetOrderAdapter(GetOrderActivity.this, arrayList));
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlGetOrder.setVisibility(View.GONE);


                    }


                }

                @Override
                public void onFailure(Call<GetOrderUser> call, Throwable t) {
                    lvlGetOrder.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<GetOrderUser> callcoinApi() {
        return videoService.get_order_user(savePref.getUserId());
    }
}