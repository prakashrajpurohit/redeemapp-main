package com.mercury.redeem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mercury.redeem.Modelclas.UserBid;
import com.mercury.redeem.R;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    ViewHolder viewHolder;
    Context mContext;
    ArrayList<UserBid> ticketlist=new ArrayList<>();
    String s,count;
    LinearLayout layout;
    int returnsize=1;
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public TicketAdapter(Context context, ArrayList<UserBid> ticketlist, String s, String count) {
        this.mContext = context;
this.ticketlist=ticketlist;
this.s=s;
this.count=count;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(s.equals("1"))
        {
             view = LayoutInflater.from(mContext).inflate(R.layout.item_tickettwo, parent, false);

        }
        else
        {
             view = LayoutInflater.from(mContext).inflate(R.layout.item_ticket, parent, false);

        }
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtTicket.setText("# "+ticketlist.get(position).getBd_value());

/*
        if(s.equals("1"))
{
    ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) layout.getLayoutParams();

    params.width = 200;
    returnsize=5;

}
else
{
    Log.e("TAG","ELSE");
    ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) layout.getLayoutParams();

    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
    returnsize=ticketlist.size();

}*/

/*
if(count.equals("5"))
{
}
else
{
    Log.e("LK",ticketlist.size()+"");

}
*/



    }


    @Override
    public int getItemCount() {
        return ticketlist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTicket;



        ViewHolder(View itemView) {
            super(itemView);

            txtTicket=itemView.findViewById(R.id.txtTixketNo);
            layout=itemView.findViewById(R.id.layout);

        }
    }




}