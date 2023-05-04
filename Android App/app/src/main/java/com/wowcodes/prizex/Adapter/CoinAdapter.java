package com.mercury.redeem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.PaymentResultListener;
import com.mercury.redeem.Activity.RazorpayActivity;
import com.mercury.redeem.Modelclas.GetCoin;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> implements PaymentResultListener {
    ViewHolder viewHolder;
    Context mContext;
    String s;
    SavePref savePref;
    public BindingService videoService;
    String mainamount;
    String o_id,package_id,wallet;
    ArrayList<GetCoin.Get_Coin_Inner> coinModelArrayList;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public CoinAdapter(Context context, ArrayList<GetCoin.Get_Coin_Inner> coinModelArrayList) {
        this.mContext = context;
        this.coinModelArrayList = coinModelArrayList;
        savePref=new SavePref(mContext);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_coinadapter, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtSetName.setText(coinModelArrayList.get(position).getC_name());
        holder.txtGetCoin.setText(coinModelArrayList.get(position).getC_coin());
        holder.txtAmount.setText("$"+coinModelArrayList.get(position).getC_amount());
        mainamount=coinModelArrayList.get(position).getC_amount();
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallet=coinModelArrayList.get(position).getC_coin();
                package_id=coinModelArrayList.get(position).getC_id();
                Intent i=new Intent(mContext, RazorpayActivity.class);
                i.putExtra("list",coinModelArrayList.get(position));
                mContext.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return coinModelArrayList.size();
    }

    @Override
    public void onPaymentSuccess(final String razorpayPaymentID) {
        o_id=razorpayPaymentID;
    }

    @Override
    public void onPaymentError(int i, String s) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView txtSetName,txtGetCoin,txtAmount;
        CardView card;
        ViewHolder(View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageview);
            txtSetName=itemView.findViewById(R.id.txtSetName);
            txtGetCoin=itemView.findViewById(R.id.txtGetCoin);
            txtAmount=itemView.findViewById(R.id.txtAmount);
            card=itemView.findViewById(R.id.card);
        }
    }
}