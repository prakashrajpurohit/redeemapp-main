package com.mercury.redeem.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Modelclas.GetOrderUser;
import com.mercury.redeem.R;

import java.util.ArrayList;

public class GetOrderAdapter extends RecyclerView.Adapter<GetOrderAdapter.ViewHolder> {
    ViewHolder viewHolder;
    Context mContext;
    String s;
    ArrayList<GetOrderUser.Get_order_user_Inner> coinModelArrayList;


    public GetOrderAdapter(Context context, ArrayList<GetOrderUser.Get_order_user_Inner> coinModelArrayList) {
        this.mContext = context;
        this.coinModelArrayList = coinModelArrayList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_getorderadapter, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {



        holder.txtSetName.setText(coinModelArrayList.get(position).getO_name());
        holder.tx_date.setText(coinModelArrayList.get(position).getOrder_date());
        holder.txtAmount.setText(coinModelArrayList.get(position).getDis_amount());
        holder.txtPayamount.setText(coinModelArrayList.get(position).getPay_amount());

        if(coinModelArrayList.get(position).getOrder_status().equals("1"))
        {
            holder.txtStatus.setText("Order Recieved");
            holder.txtStatusd.setText("We have recieved your order and it will be processed soon");
            holder.txtStatus.setTextColor(Color.parseColor("#4131D1"));

        }

        else if(coinModelArrayList.get(position).getOrder_status().equals("2"))
        {
            holder.txtStatus.setText("Processing Order");
            holder.txtStatusd.setText("We have processing your order and it will be shipped soon");
            holder.txtStatus.setTextColor(Color.parseColor("#CCD131"));

        }
        else if(coinModelArrayList.get(position).getOrder_status().equals("3"))
        {
            holder.txtStatus.setText("Order Shipped");
            holder.txtStatusd.setText("Hurray! Your Order is on the way to get delivered.");
            holder.txtStatus.setTextColor(Color.parseColor("#D18C31"));

        }
        else if(coinModelArrayList.get(position).getOrder_status().equals("4"))
        {
            holder.txtStatus.setText("Order Delivered");
            holder.txtStatusd.setText("We have successfully delivered your Order, don't forget to tag us on social media :)");
            holder.txtStatus.setTextColor(Color.parseColor("#279832"));

        }
        else if(coinModelArrayList.get(position).getOrder_status().equals("5"))
        {
            holder.txtStatus.setText("Order Rejected");
            holder.txtStatusd.setText("We have detected some unethical ways used by you to earn coins, \n" +
                    "For any questions you can email us.");

            holder.txtStatus.setTextColor(Color.parseColor("#EE1111"));
        }
        try {

            Glide.with(mContext)
                    .load(coinModelArrayList.get(position).getO_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imageview);
        }catch (Exception e){
            e.printStackTrace();

        }





        holder.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }


    @Override
    public int getItemCount() {
        return coinModelArrayList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView txtSetName,txtGetCoin,txtAmount,tx_date,txtPayamount,txtStatus,txtStatusd;
        CardView card;
        LinearLayout card1;


        ViewHolder(View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageview);
            txtSetName=itemView.findViewById(R.id.txtSetName);
            txtGetCoin=itemView.findViewById(R.id.txtGetCoin);
            txtStatus=itemView.findViewById(R.id.txtStatus);
            txtAmount=itemView.findViewById(R.id.txtAmount);
            txtStatusd=itemView.findViewById(R.id.txtStatusd);
            card=itemView.findViewById(R.id.card);
            tx_date=itemView.findViewById(R.id.tx_date);
            card1=itemView.findViewById(R.id.card1);
            txtPayamount=itemView.findViewById(R.id.txtPayamount);
        }
    }






}