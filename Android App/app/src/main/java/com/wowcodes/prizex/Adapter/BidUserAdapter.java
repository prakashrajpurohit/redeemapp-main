package com.mercury.redeem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Modelclas.GetBidUser;
import com.mercury.redeem.R;

import java.util.ArrayList;

public class BidUserAdapter extends RecyclerView.Adapter<BidUserAdapter.ViewHolder> {
    ViewHolder viewHolder;
    Context mContext;
    String s;
    ArrayList<GetBidUser.Get_biduser_Inner> coinModelArrayList;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public BidUserAdapter(Context context, ArrayList<GetBidUser.Get_biduser_Inner> coinModelArrayList) {
        this.mContext = context;
        this.coinModelArrayList = coinModelArrayList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_bidadapter, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {



        holder.txtSetName.setText(coinModelArrayList.get(position).getO_name());
        holder.tx_date.setText(coinModelArrayList.get(position).getBd_date());
        holder.txtAmount.setText("$ "+coinModelArrayList.get(position).getBd_amount());
        holder.txtBids.setText("Bid value:"+coinModelArrayList.get(position).getBd_value());


        try {
            Glide.with(mContext)
                    .load(coinModelArrayList.get(position).getO_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imageview);
        }catch (Exception e){
            e.printStackTrace();

        }




        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return coinModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView txtSetName,tx_date,txtAmount,txtBids;
        CardView card;


        ViewHolder(View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageview);

            txtSetName=itemView.findViewById(R.id.txtSetName);
            tx_date=itemView.findViewById(R.id.tx_date);
            txtAmount=itemView.findViewById(R.id.txtAmount);
            card=itemView.findViewById(R.id.card);
            txtBids=itemView.findViewById(R.id.txtBids);



        }
    }

}