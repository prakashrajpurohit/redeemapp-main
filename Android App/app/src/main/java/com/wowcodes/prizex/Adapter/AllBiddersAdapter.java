package com.mercury.redeem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mercury.redeem.Constants;
import com.mercury.redeem.Modelclas.AllBid;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;
import com.razorpay.PaymentResultListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllBiddersAdapter extends RecyclerView.Adapter<AllBiddersAdapter.ViewHolder> implements PaymentResultListener {
    public BindingService videoService;
    ViewHolder viewHolder;
    Context mContext;
    String s;
    SavePref savePref;

    String o_id;
    ArrayList<AllBid> coinModelArrayList;


    public AllBiddersAdapter(Context context, ArrayList<AllBid> coinModelArrayList) {
        this.mContext = context;
        this.coinModelArrayList = coinModelArrayList;
        savePref = new SavePref(mContext);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);


    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_bidadapternew, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        holder.txtSetName.setText(coinModelArrayList.get(position).getName());
        holder.tx_date.setText(coinModelArrayList.get(position).getBd_date());
        holder.txtAmount.setText("" + coinModelArrayList.get(position).getTotal_amount() + " Bids");
        holder.txtBids.setText("Bid value: " + coinModelArrayList.get(position).getBd_value());

        try {
            Glide.with(mContext)
                    .load(Constants.imageurl + coinModelArrayList.get(position).getImage())
                    .placeholder(R.drawable.img_user)
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

    @Override
    public void onPaymentSuccess(final String razorpayPaymentID) {
        o_id = razorpayPaymentID;


    }

    @Override
    public void onPaymentError(int i, String s) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageview;
        TextView txtSetName, txtBids, txtAmount, tx_date;
        CardView card;


        ViewHolder(View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);

            txtSetName = itemView.findViewById(R.id.txtSetName);
            txtBids = itemView.findViewById(R.id.txtBids);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            card = itemView.findViewById(R.id.card);
            tx_date = itemView.findViewById(R.id.tx_date);


        }
    }


}