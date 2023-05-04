package com.mercury.redeem.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Modelclas.AddOrder;
import com.mercury.redeem.Modelclas.AllBidderInner;
import com.mercury.redeem.Modelclas.GetOffersWinner;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class OfferDetailActivity extends AppCompatActivity {
    LinearLayout lvlOrderDetail;
    SavePref savePref;
    ImageView imgItemImg;


    EditText edForgotNumbid;
    TextView txtClose,txtPay,txtBids,txtSave,txtYourr,txtSetName;
    public BindingService videoService;
    ArrayList<AllBidderInner> arrayList;
    GetOffersWinner.Get_offers_winner_Inner offers_winner_list;
    String getAmount;
    String getWon;
    TextView txtAucname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        offers_winner_list= (GetOffersWinner.Get_offers_winner_Inner) getIntent().getSerializableExtra("result");


        getWon=getIntent().getStringExtra("won");
        ImageView imgBackk=findViewById(R.id.imgBackk);
        txtAucname=findViewById(R.id.txtAucname);
        txtSetName=findViewById(R.id.txtSetName);

        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        savePref=new SavePref(OfferDetailActivity.this);
        lvlOrderDetail =findViewById(R.id.linearlay);
        txtSave=findViewById(R.id.txtSave);
        edForgotNumbid=findViewById(R.id.edForgotNumbid);
        imgItemImg=findViewById(R.id.imgItemImg);
        txtClose=findViewById(R.id.txtClose);
        txtPay=findViewById(R.id.txtPay);
        txtBids=findViewById(R.id.txtBids);
        txtYourr=findViewById(R.id.txtYourr);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        txtPay.setText("Play For "+offers_winner_list.getTotal_amount());
        txtSetName.setText(offers_winner_list.getO_name());
        txtAucname.setText(offers_winner_list.getO_name());



        try {

            Glide.with(OfferDetailActivity.this)
                    .load(offers_winner_list.getO_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(imgItemImg);

        }catch (Exception e){
            e.printStackTrace();

        }


        txtBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edForgotNumbid.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(OfferDetailActivity.this, "Please Enter Your Address", Toast.LENGTH_SHORT).show();
                }else{
                    addbid();

                }

            }
        });

        txtYourr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }




    public void addbid() {
        lvlOrderDetail.setVisibility(View.VISIBLE);
        try {
            calladdbidApi().enqueue(new Callback<AddOrder>() {
                @Override
                public void onResponse(Call<AddOrder> call, retrofit2.Response<AddOrder> response) {

                    try {

                        ArrayList<AddOrder.Add_Order_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(OfferDetailActivity.this, ""+arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getO_status().equalsIgnoreCase("1")){
                            Intent i=new Intent(OfferDetailActivity.this,MainActivity.class);
                            startActivity(i);
                            edForgotNumbid.setText("");
                        }else{
                            lvlOrderDetail.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlOrderDetail.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<AddOrder> call, Throwable t) {
                    lvlOrderDetail.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<AddOrder> calladdbidApi() {

        if (getWon.equalsIgnoreCase("lwon")) {

            return videoService.add_order(savePref.getUserId(), offers_winner_list.getO_id(), offers_winner_list.getLbd_amount(), "0", offers_winner_list.getLbd_amount(), edForgotNumbid.getText().toString());

        }

        else{
            return videoService.add_order(savePref.getUserId(),offers_winner_list.getO_id(),offers_winner_list.getHbd_amount(),"",offers_winner_list.getHbd_amount(),edForgotNumbid.getText().toString());
        }
    }






}