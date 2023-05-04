package com.mercury.redeem.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Activity.OfferDetailActivity;
import com.mercury.redeem.Modelclas.GetOffersWinner;
import com.mercury.redeem.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OfferWinnerAdapter extends RecyclerView.Adapter<OfferWinnerAdapter.ViewHolder> {
    ViewHolder viewHolder;
    Context mContext;
    String index;
    String s;
    ArrayList<GetOffersWinner.Get_offers_winner_Inner> chaptersModelArrayList;


    public OfferWinnerAdapter(Context context, ArrayList<GetOffersWinner.Get_offers_winner_Inner> chaptersModelArrayList) {
        this.mContext = context;
        this.chaptersModelArrayList = chaptersModelArrayList;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_o_winneradapter, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {




        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date sourceDate = dateFormat.parse(chaptersModelArrayList.get(position).geto_edate());

            SimpleDateFormat targetFormat = new SimpleDateFormat("dd MMMM yyyy");
            String targetdatevalue = targetFormat.format(sourceDate);

            holder.tx_date.setText(targetdatevalue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (chaptersModelArrayList.get(position).getO_type().equalsIgnoreCase("1")) {


            holder.tx_lbid.setVisibility(View.VISIBLE);
            holder.tx_wlbid.setVisibility(View.VISIBLE);
            holder.tx_hbid.setVisibility(View.GONE);
            holder.tx_winbid.setText("Winning Bid:- $" + chaptersModelArrayList.get(position).getLbd_value());
            holder.tx_whbid.setVisibility(View.GONE);
        } else if (chaptersModelArrayList.get(position).getO_type().equalsIgnoreCase("2")) {


            holder.tx_lbid.setVisibility(View.GONE);
            holder.tx_wlbid.setVisibility(View.GONE);
            holder.tx_hbid.setText("Highest unique bid");
            holder.tx_hbid.setVisibility(View.VISIBLE);
            holder.tx_winbid.setText("Winning Bid:- $" + chaptersModelArrayList.get(position).getHbd_value());
            holder.tx_whbid.setVisibility(View.VISIBLE);
        } else {

            holder.tx_lbid.setVisibility(View.GONE);
            holder.tx_wlbid.setVisibility(View.GONE);
            holder.tx_hbid.setText("Lucky Draw");
            holder.tx_hbid.setVisibility(View.VISIBLE);
            holder.tx_winbid.setText("Winning Ticket:- #" + chaptersModelArrayList.get(position).getHbd_value());
            holder.tx_whbid.setVisibility(View.VISIBLE);
        }


        try {
            Glide.with(mContext)
                    .load(chaptersModelArrayList.get(position).getO_image())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imageview);
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (chaptersModelArrayList.get(position).getH_won().equalsIgnoreCase("1")) {
            holder.tx_whbid.setText("Congrats, You Won!");
            index = "won";
            holder.tx_whbid.setTextColor(mContext.getResources().getColor(R.color.white));



            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!chaptersModelArrayList.get(position).getO_click().equalsIgnoreCase("1")) {
                        if (chaptersModelArrayList.get(position).getH_won().equalsIgnoreCase("1")) {
                            index = "hwon";
                            Intent i = new Intent(mContext, OfferDetailActivity.class);
                            i.putExtra("result", chaptersModelArrayList.get(position));
                            i.putExtra("won", index);
                            mContext.startActivity(i);

                        } else if (chaptersModelArrayList.get(position).getL_won().equalsIgnoreCase("1")) {
                            index = "lwon";
                            Intent i = new Intent(mContext, OfferDetailActivity.class);
                            i.putExtra("result", chaptersModelArrayList.get(position));
                            i.putExtra("won", index);
                            mContext.startActivity(i);

                        } else {
                            index = "";


                        }
                    } else {
                        Toast.makeText(mContext, "Hey! We Have Already Recieved Your Order", Toast.LENGTH_SHORT).show();
                    }




                }
            });

        } else {
            index = "";
            holder.tx_whbid.setText("Won by:- " + chaptersModelArrayList.get(position).getHname());
            holder.tx_whbid.setTextColor(mContext.getResources().getColor(R.color.white));

        }

        if (chaptersModelArrayList.get(position).getL_won().equalsIgnoreCase("1")) {
            holder.tx_wlbid.setText("Congrats, You Won!");
            index = "won";
            holder.tx_wlbid.setTextColor(mContext.getResources().getColor(R.color.white));


            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!chaptersModelArrayList.get(position).getO_click().equalsIgnoreCase("1")) {
                        if (chaptersModelArrayList.get(position).getH_won().equalsIgnoreCase("1")) {
                            index = "hwon";
                            Intent i = new Intent(mContext, OfferDetailActivity.class);
                            i.putExtra("result", chaptersModelArrayList.get(position));
                            i.putExtra("won", index);
                            mContext.startActivity(i);

                        } else if (chaptersModelArrayList.get(position).getL_won().equalsIgnoreCase("1")) {
                            index = "lwon";
                            Intent i = new Intent(mContext, OfferDetailActivity.class);
                            i.putExtra("result", chaptersModelArrayList.get(position));
                            i.putExtra("won", index);
                            mContext.startActivity(i);

                        } else {
                            index = "";


                        }
                    } else {
                        Toast.makeText(mContext, "Hey! We Have Already Recieved Your Order", Toast.LENGTH_SHORT).show();
                    }




                }
            });

        } else {
            index = "";

            holder.tx_wlbid.setText("Won by:- " + chaptersModelArrayList.get(position).getLname());
            holder.tx_wlbid.setTextColor(mContext.getResources().getColor(R.color.white));


        }


        holder.tx_name.setText(chaptersModelArrayList.get(position).getO_name());
        holder.tx_lbid.setText("Lowest unique bid");

    }


    @Override
    public int getItemCount() {
        return chaptersModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView tx_name;
        TextView tx_date;
        TextView tx_winbid;
        TextView tx_wlbid;
        CardView card;
        TextView tx_hbid;
        TextView tx_whbid;
        TextView tx_lbid;


        ViewHolder(View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);
            tx_winbid = itemView.findViewById(R.id.tx_winbid);
            tx_name = itemView.findViewById(R.id.tx_name);
            tx_date = itemView.findViewById(R.id.tx_date);
            tx_wlbid = itemView.findViewById(R.id.tx_wlbid);
            tx_hbid = itemView.findViewById(R.id.tx_hbid);
            tx_lbid = itemView.findViewById(R.id.tx_lbid);
            tx_whbid = itemView.findViewById(R.id.tx_whbid);
            card = itemView.findViewById(R.id.card);


        }
    }


}