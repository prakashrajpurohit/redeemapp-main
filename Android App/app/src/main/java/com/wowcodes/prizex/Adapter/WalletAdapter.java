package com.mercury.redeem.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.mercury.redeem.Modelclas.GetWallet;
import com.mercury.redeem.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> implements PaymentResultListener {
    ViewHolder viewHolder;
    Context mContext;
    String s;
    ArrayList<GetWallet.Get_Wallet_Inner> coinModelArrayList;

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public WalletAdapter(Context context, ArrayList<GetWallet.Get_Wallet_Inner> coinModelArrayList) {
        this.mContext = context;
        this.coinModelArrayList = coinModelArrayList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_walletadapter, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {



        holder.txtSetName.setText(coinModelArrayList.get(position).getC_name());
        holder.txtGetCoin.setText(coinModelArrayList.get(position).getC_coin());
        holder.tx_date.setText(coinModelArrayList.get(position).getWp_date());
        holder.txtAmount.setText("$ "+coinModelArrayList.get(position).getC_amount());

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



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageview;
        TextView txtSetName,txtGetCoin,txtAmount,tx_date;
        CardView card;
        LinearLayout card1;


        ViewHolder(View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageview);

            txtSetName=itemView.findViewById(R.id.txtSetName);
            txtGetCoin=itemView.findViewById(R.id.txtGetCoin);
            txtAmount=itemView.findViewById(R.id.txtAmount);
            card=itemView.findViewById(R.id.card);
            tx_date=itemView.findViewById(R.id.tx_date);
            card1=itemView.findViewById(R.id.card1);
        }
    }


    public void onPaymentSuccess(String s) {

    }

    @Override
    public void onPaymentError(int i, String s) {

    }


    public void startPayment() {


        final Context activity=mContext;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "LUKS18");
            options.put("description", "Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "");
            options.put("currency", "INR");


            int priceint = 0;


            try {

                String s = "100";
                s = s.replace(" ", "");
                s = s.replace("₹", "");
                s = s.replace("/-", "");

                if (s.contains(".")) {


                    priceint = Integer.valueOf((int) Math.round(Double.parseDouble(s)));
                    priceint = priceint * 100;


                } else {
                    priceint = Integer.parseInt(s);
                    priceint = priceint * 100;


                }


            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }



            options.put("amount", 100 + "");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact","28936289635");
            options.put("prefill", preFill);

            co.open((Activity) activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}