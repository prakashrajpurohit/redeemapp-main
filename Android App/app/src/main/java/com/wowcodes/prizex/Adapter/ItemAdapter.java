package com.mercury.redeem.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mercury.redeem.Modelclas.GetOffersLive;
import com.mercury.redeem.Activity.CategoryDetailsActivity;
import com.mercury.redeem.Modelclas.GetCategories;
import com.mercury.redeem.R;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ViewHolder viewHolder;
    Context mContext;
    String s;
    static String check;
    String start_date;
   List<GetCategories.JSONDATum> chaptersModelArrayList;
    View view;
    ItemClickListener itemClickListener;
    LinearLayout layoutHead;

    @Override
    public long getItemId(int position) {
        return position;
    }
    public interface  ItemClickListener
    {
        void itemClick(String id,String name,String image,String cc);
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public ItemAdapter(Context context, List<GetCategories.JSONDATum> chaptersModelArrayList,ItemClickListener itemClickListener,String s) {
        this.mContext = context;
        this.chaptersModelArrayList = chaptersModelArrayList;
        this.itemClickListener=itemClickListener;
        this.s=s;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


             view = LayoutInflater.from(mContext).inflate(R.layout.recycler_adapteritem2, parent, false);




        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        Thread myThread = null;



        //    Runnable myRunnableThread = new CountDownRunner(holder.tx_time,chaptersModelArrayList.get(position).getO_edate()+" "+chaptersModelArrayList.get(position).getO_etime());
         //   myThread= new Thread(myRunnableThread);
         //   myThread.start();




if(chaptersModelArrayList.get(position).getcId().equals("1"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());
}
else if(chaptersModelArrayList.get(position).getcId().equals("2"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("3"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("4"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("5"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("6"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("7"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("8"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("9"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("10"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("11"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("12"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("13"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("14"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("15"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("16"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("17"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("18"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("19"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("20"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}

else if(chaptersModelArrayList.get(position).getcId().equals("21"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("22"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("23"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("24"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("25"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("26"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("27"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("28"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("29"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("30"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}

else if(chaptersModelArrayList.get(position).getcId().equals("31"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("32"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("33"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("34"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("35"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("36"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("37"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("38"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("39"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("40"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}

else if(chaptersModelArrayList.get(position).getcId().equals("41"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("42"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("43"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("44"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("45"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("46"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("47"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("48"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("49"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
else if(chaptersModelArrayList.get(position).getcId().equals("50"))
{
    itemClickListener.itemClick(chaptersModelArrayList.get(position).getcId(),chaptersModelArrayList.get(position).getcName(),chaptersModelArrayList.get(position).getcImage(),chaptersModelArrayList.get(position).getcColor());

}
holder.txtName.setText(chaptersModelArrayList.get(position).getoName());
        holder.txtPrice.setText("Worth: $"+chaptersModelArrayList.get(position).getoPrice()+"/-");
        holder.txtAmount.setText(chaptersModelArrayList.get(position).getoAmount());

        if (chaptersModelArrayList.get(position).getoType().equalsIgnoreCase("1")){
            holder.txtType.setText("Lowest Unique Bid");
        }else if (chaptersModelArrayList.get(position).getoType().equalsIgnoreCase("2")){
            holder.txtType.setText("Highest Unique Bid");
        }else if (chaptersModelArrayList.get(position).getoType().equalsIgnoreCase("3")){
            holder.txtType.setText("Redeem Item");
        }else if (chaptersModelArrayList.get(position).getoType().equalsIgnoreCase("4")){
            holder.txtType.setText("Lucky Draw");
        }else{
            holder.txtType.setText("");
        }

        try {
            Glide.with(mContext)
                    .load(chaptersModelArrayList.get(position).getoImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.imageview);


        } catch (Exception e) {
            e.printStackTrace();

        }

        if(s.equals("2"))
        {
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) layoutHead.getLayoutParams();

            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
           params.height = 400;
           // params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            ViewGroup.LayoutParams params1 = (ViewGroup.LayoutParams) holder.imageview.getLayoutParams();

            params1.width = ViewGroup.LayoutParams.MATCH_PARENT;
           // params1.height = ViewGroup.LayoutParams.WRAP_CONTENT;

           params1.height = 400;
            holder.imageview.setCornerRadius(10,10,10,10);
            holder.txtAmount.setVisibility(View.GONE);
            holder.txtPrice.setVisibility(View.GONE);
            holder.txtName.setVisibility(View.GONE);
            holder.txtType.setVisibility(View.GONE);
            holder.linear3.setVisibility(View.GONE);
            holder.view1.setVisibility(View.GONE);

        }
        else
        {
            ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) layoutHead.getLayoutParams();


            //params.width = 300;

            if(params.width>375)
            {
                params.width= params.width/3+300;

            }
            else if(params.width<=375)
            {
                params.width= params.width/3+200;

            }

            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

            holder.txtAmount.setVisibility(View.VISIBLE);
            holder.txtPrice.setVisibility(View.VISIBLE);
            holder.txtName.setVisibility(View.VISIBLE);
            holder.txtType.setVisibility(View.VISIBLE);
            holder.linear3.setVisibility(View.VISIBLE);
            holder.view1.setVisibility(View.VISIBLE);


        }


        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                    Intent i=new Intent(mContext, CategoryDetailsActivity.class);
                    i.putExtra("image",chaptersModelArrayList.get(position).getoImage());
                    i.putExtra("name",chaptersModelArrayList.get(position).getoName());
                i.putExtra("type",chaptersModelArrayList.get(position).getoType());
                i.putExtra("desc",chaptersModelArrayList.get(position).getoDesc());
                i.putExtra("edate",chaptersModelArrayList.get(position).getoEdate());
                i.putExtra("etime",chaptersModelArrayList.get(position).getoEtime());
                i.putExtra("coins",chaptersModelArrayList.get(position).getoAmount());
                i.putExtra("oid",chaptersModelArrayList.get(position).getoId());
                i.putExtra("qty",chaptersModelArrayList.get(position).getoQty());
                i.putExtra("oamt",chaptersModelArrayList.get(position).getoAmount());
                i.putExtra("link",chaptersModelArrayList.get(position).getoLink());
                i.putExtra("colorcode",chaptersModelArrayList.get(position).getcColor());
                i.putExtra("cdesc",chaptersModelArrayList.get(position).getcDesc());
                i.putExtra("umax",chaptersModelArrayList.get(position).getoUmax());
                i.putExtra("limit",chaptersModelArrayList.get(position).getoUlimit());
                i.putExtra("totalbids",chaptersModelArrayList.get(position).getTotalbids());
                i.putExtra("id",chaptersModelArrayList.get(position).getId());
                mContext.startActivity(i);



            }
        });
    }


    @Override
    public int getItemCount() {
        return chaptersModelArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView imageview;
        TextView txtName,txtType,txtPrice,txtAmount,txtBids;
        LinearLayout linear3;
        View view1;
      //  CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.image);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtName=itemView.findViewById(R.id.txtName);
            txtType=itemView.findViewById(R.id.txtType);
           // card=itemView.findViewById(R.id.card);
            txtAmount=itemView.findViewById(R.id.txtAmount);
           layoutHead=itemView.findViewById(R.id.linearheader);
            linear3=itemView.findViewById(R.id.linear3);
            view1=itemView.findViewById(R.id.view);



        }
    }


    public void doWork(final TextView textView, final String o_etime)
    {
        ((Activity)mContext).runOnUiThread(new Runnable()
        {
            public void run()
            {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                start_date = currentDate + " " + currentTime;
                findDifference(start_date, textView, o_etime);
            }
        });
    }


    class CountDownRunner implements Runnable

    {
        TextView textView;
        String o_etime;

        public CountDownRunner(TextView tx_time, String o_etime) {
            this.textView=tx_time;
            this.o_etime=o_etime;
        }

        // @Override
        public void run()
        {
            while(!Thread.currentThread().isInterrupted())
            {
                try
                {

                    doWork(textView,o_etime);
                    Thread.sleep(1000); // Pause of 1 Second
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
                catch(Exception e)
                {
                }
            }
        }
    }


    static void findDifference(String start_date,
                               TextView textView, String end_date) {

        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");


        // Try Block
        try {

            // parse method is used to parse
            // the text from a string to
            // produce the date
            Date d1 = sdf.parse(start_date);
            Date d2 = sdf.parse(end_date);




            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                    = d2.getTime() - d1.getTime();


            long difference_In_Seconds
                    = (difference_In_Time
                    / 1000)
                    % 60;

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;

            long difference_In_Hours
                    = (difference_In_Time
                    / (1000 * 60 * 60))
                    % 24;

            long difference_In_Years
                    = (difference_In_Time
                    / (1000l * 60 * 60 * 24 * 365));

            long difference_In_Days
                    = (difference_In_Time
                    / (1000 * 60 * 60 * 24))
                    % 365;

            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds

            System.out.print(
                    "Difference "
                            + "between two dates is: ");

            String diff= difference_In_Days
                    + "d: "
                    + difference_In_Hours
                    + "h: "
                    + difference_In_Minutes
                    + "m: "
                    + difference_In_Seconds
                    + "s";


            String timer="0d: 0h: 0m: 0s";
            if (timer.equalsIgnoreCase(diff)){
                textView.setText("Auction Closed");

            }else if (difference_In_Days<0 || difference_In_Hours<0  || difference_In_Minutes<0  || difference_In_Seconds<0){
                textView.setText("Auction Closed");

            }
            else {
                textView.setText("Auction Ends in:- " + diff);

            }

        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }



}