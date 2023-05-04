package com.mercury.redeem.Activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Adapter.TicketAdapter;
import com.mercury.redeem.Modelclas.AllBidder;
import com.mercury.redeem.Modelclas.AllBidderInner;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.Modelclas.UserBid;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class RaffleDetailActivity extends AppCompatActivity {

    TextView txtTitle,txtItemName,txtItemEnd,txtItemStart,txtItemPrice,txtTotalBids,txtViewAll,txtForCoin,txtItemDesc;
    ImageView imgBack,imgItemImg;
    String title,oId,checkStatus,startDate,totalWallet,getBids,getAmount,bdis;
    TextView txtClose,txtauctiontime,txtnotickets;
    LinearLayout lvlLive,layoutButton;
    public BindingService videoService;
    SavePref savePref;
    ArrayList<AllBidderInner> arrayList;
    TicketAdapter ticketAdapter;
    ArrayList<UserBid> ticketlist=new ArrayList<>();
    int random =0;
    RecyclerView recyclerView;
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    TextView txt1,txt2,txt3,txt4;
    String randomticket,txt12,txt13,txt14;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle);
        txtTitle=(TextView)findViewById(R.id.txtAucname);
        imgBack=(ImageView)findViewById(R.id.imgBackk);
        txtClose = findViewById(R.id.txtClose);
        lvlLive = findViewById(R.id.linearlay);
        imgItemImg = findViewById(R.id.imgItemImg);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        txtauctiontime=(TextView)findViewById(R.id.txtauctiontime);
        layoutButton = findViewById(R.id.layoutButton);
        txtItemName=(TextView)findViewById(R.id.txtItemName);
        txtItemDesc=(TextView)findViewById(R.id.txtItemDesc);
        txtTotalBids=(TextView)findViewById(R.id.txtTotalBids);
        txtViewAll=(TextView)findViewById(R.id.txtViewAll);
        txtForCoin=(TextView)findViewById(R.id.txtForCoin);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txt3=(TextView)findViewById(R.id.txt3);
        txt4=(TextView)findViewById(R.id.txt4);
        txtnotickets=(TextView)findViewById(R.id.txtnotickets);

        savePref = new SavePref(RaffleDetailActivity.this);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
// Set the VMTV movement method so that it can scroll.


        // Alternately you may also pass in the (long) duration between scroll
        // moves and the (int) pixelYOffSet amount that you wish to scroll by.
        // Using the following method calls:
        // VMTV.setDuration(duration);
        // VMTV.setPixelYOffSet(pixelYOffSet);

        // Optionally pause the marquee to wait for the Activity to start.



        try {
            if(getIntent()!=null)
            {
                title=getIntent().getStringExtra("title");
                oId = getIntent().getStringExtra("O_id");
                checkStatus = getIntent().getStringExtra("check");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // getprofile();
        //  getofferapi();

        txtViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(RaffleDetailActivity.this,ViewAllTicketsActivity.class);
                intent.putExtra("o_id",oId);
                startActivity(intent);

            }
        });


        txtTotalBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RaffleDetailActivity.this,AllBidderActivity.class);
                intent.putExtra("o_id", oId);
                intent.putExtra("status","2");

                startActivity(intent);

            }
        });


        layoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //random= ThreadLocalRandom.current().nextInt(1000, 9999);



                randomAlphaNumeric(4,"1");



            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // getticketapi();

        // opencall();

        isNetworkConnected();

    }

    private void opencall() {
        randomAlphaNumeric(4,"2");
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            //nointernetlayout.setVisibility(View.GONE);

            getprofile();
            getticketapi();
            getofferapi();
            opencall();

        }
        else
        {
            // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getApplicationContext(), NoInternetActivity.class);
            startActivity(intent);
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void openticketdialog(final String s, final String randomono) {

        final Dialog dialog = new Dialog(RaffleDetailActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_ticket);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        Button ok = dialog.findViewById(R.id.ok);
        TextView d_title = dialog.findViewById(R.id.d_title);
        d_title.setText("Your Ticket Number");
        Button cancel = dialog.findViewById(R.id.cancel);
        LinearLayout layout=dialog.findViewById(R.id.ticketlayout);
        TextView txtTicketNo=dialog.findViewById(R.id.txtTixketNo);
        TextView bottomtxt=dialog.findViewById(R.id.bottomtxt);

        if(s.equals("1"))
        {
            bottomtxt.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            txtTicketNo.setText(String.valueOf(randomono));

        }
        else
        {
            bottomtxt.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(s.equals("1"))
                {

                    dialog.dismiss();
                    Log.e("RR",randomono);
//bdis=randomono;
                    //addbid(randomono);

                 /*   Intent intent=new Intent(LiveDetailActivityNew.this,MainActivity.class);
                    intent.putExtra("page","1");
                    startActivity(intent);*/
                    reload();
                }
                else
                {
                    Intent intent=new Intent(RaffleDetailActivity.this,MainActivity.class);
                    intent.putExtra("page","2");
                    startActivity(intent);
                    dialog.dismiss();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    private void openaddbiddialog(final String ss) {
        final Dialog dialog = new Dialog(RaffleDetailActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_bid);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button ok = dialog.findViewById(R.id.ok);
        Button cancel = dialog.findViewById(R.id.cancel);
        TextView d_title = dialog.findViewById(R.id.d_title);
        TextView bottomtxt = dialog.findViewById(R.id.bottomtxt);
        d_title.setText("Are you sure you want to purchase one ticket? ");
        bottomtxt.setText(arrayList.get(0).getO_amount() + " Coins will be deducted from your coin balance on confirmation");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
               /* if (Integer.valueOf(totalWallet) >= Integer.valueOf(getAmount)) {
                   randomAlphaNumeric(4);

                } else {

                    Toast.makeText(LiveDetailActivityNew.this, "Insufficient Coins,Please Add Some Coins to participate in this Auction", Toast.LENGTH_SHORT).show();
                }*/

                bdis=ss;

                addbid(ss);

            }
        });
        dialog.show();
    }

    public void getofferapi() {
        lvlLive.setVisibility(View.VISIBLE);
        try {
            callofferApi().enqueue(new Callback<AllBidder>() {
                @Override
                public void onResponse(Call<AllBidder> call, retrofit2.Response<AllBidder> response) {

                    lvlLive.setVisibility(View.GONE);
                    arrayList = response.body().getJSON_DATA();
                    try {
                        Glide.with(RaffleDetailActivity.this)
                                .load(arrayList.get(0).getO_image())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgItemImg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    txtItemName.setText(arrayList.get(0).getO_name());
                    txtTitle.setText(arrayList.get(0).getO_name());
                    txtItemDesc.setText(arrayList.get(0).getO_desc());
                    txtTotalBids.setText(arrayList.get(0).getTotal_users() + " Participants");

                    txtForCoin.setText("for "+arrayList.get(0).getO_amount()+" Coin");
                    getAmount=arrayList.get(0).getO_amount();


                    Thread myThread = null;
                    Runnable myRunnableThread = new CountDownRunner(txtauctiontime, arrayList.get(0).getO_edate() + " " + arrayList.get(0).getO_etime());
                    myThread = new Thread(myRunnableThread);
                    myThread.start();
                }

                @Override
                public void onFailure(Call<AllBidder> call, Throwable t) {
                    lvlLive.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<AllBidder> callofferApi() {
        return videoService.get_offers_id(oId, savePref.getUserId());
    }

    class CountDownRunner implements Runnable {
        TextView textView;
        String o_etime;

        public CountDownRunner(TextView tx_time, String o_etime) {
            this.textView = tx_time;
            this.o_etime = o_etime;
        }

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork(textView, o_etime);
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }

    public void doWork(final TextView textView, final String o_etime) {
        runOnUiThread(new Runnable() {
            public void run() {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                startDate = currentDate + " " + currentTime;
                findDifference(startDate, textView, o_etime);
            }
        });
    }

    void findDifference(String start_date,
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
            String diff = difference_In_Days
                    + "d: "
                    + difference_In_Hours
                    + "h: "
                    + difference_In_Minutes
                    + "m: "
                    + difference_In_Seconds
                    + "s";

            String timer = "0d: 0h: 0m: 0s";
            if (timer.equalsIgnoreCase(diff)) {
                textView.setText("It's Gone.. The Auction Has Ended");
                txtClose.setText("It's Gone.. The Auction Has Ended");

                //  txtBid.setBackground(getResources().getDrawable(R.drawable.btn_bggray));
                // txtBid.setClickable(false);
                // edForgotNumbid.setFocusable(false);
            } else if (difference_In_Days < 0 || difference_In_Hours < 0 || difference_In_Minutes < 0 || difference_In_Seconds < 0) {
                //txtBid.setBackground(getResources().getDrawable(R.drawable.btn_bggray));
                // edForgotNumbid.setFocusable(false);
                //  txtBid.setClickable(false);
                textView.setText("It's Gone.. The Auction Has Ended");
                txtClose.setText("It's Gone.. The Auction Has Ended");

            } else {
                textView.setText(diff);
                txtClose.setText("Raffle Ends in:- " + diff);
            }
        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void getprofile() {
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {


                    try {
                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();

                        totalWallet = arrayList.get(0).getWallet();


                    }catch (Exception e){
                        e.printStackTrace();
                        lvlLive.setVisibility(View.GONE);

                    }



                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    lvlLive.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }
    public void addbid(String s) {
        lvlLive.setVisibility(View.VISIBLE);
        try {
            calladdbidApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {
                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        //  Toast.makeText(LiveDetailActivityNew.this, "" + arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")) {

                            Toast.makeText(getApplicationContext(),"Ticket Purchased Successfully!!",Toast.LENGTH_SHORT).show();

                            openticketdialog("1",bdis);

                            Log.e("DD",getBids+"::"+bdis);
                            //edForgotNumbid.setText("");
                        } else {
                            lvlLive.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlLive.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                    lvlLive.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<SuccessModel> calladdbidApi() {

        Log.e("Values",oId+"::"+bdis+"::"+getAmount);

        return videoService.add_bid(savePref.getUserId(), oId, bdis, getAmount);
    }
    public void getticketapi() {
        try {
            callofferApii().enqueue(new Callback<AllBidder>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onResponse(Call<AllBidder> call, retrofit2.Response<AllBidder> response) {

                    ticketlist = response.body().getJSON_DATA().get(0).getUser_bid();

                    if(ticketlist.size()!=0)
                    {

                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new TicketAdapter(RaffleDetailActivity.this, ticketlist,"1","5"));
                        LinearLayoutManager layoutManager = new LinearLayoutManager(RaffleDetailActivity.this,LinearLayoutManager.HORIZONTAL,true);
                        layoutManager.setStackFromEnd(true);

                        recyclerView.setLayoutManager(layoutManager);
                        txtnotickets.setVisibility(View.GONE);

                    }

                    else
                    {
                        txtnotickets.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<AllBidder> call, Throwable t) {
                    // lvlYourBid.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // lvlYourBid.setVisibility(View.GONE);

        }


    }


    private Call<AllBidder> callofferApii() {

        Log.e("LL",oId+" "+savePref.getUserId());


        return videoService.get_offers_id(oId, savePref.getUserId());
    }

    public  String randomAlphaNumeric(int count,String s) {
        StringBuilder builder = new StringBuilder();
        int character = 0;
        while (count-- != 0) {
            character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }


        if(s.equals("1"))
        {

            if(Double.parseDouble(totalWallet)>0)
            {
                Log.e("TAG","IF");
                //openticketdialog("1", ss);
                openaddbiddialog(randomticket);
            }
            else
            {
                Log.e("TAG","ELSE");

                openticketdialog("2", randomticket);

            }
        }
        else
        {
            String ss=builder.toString();
            randomticket=ss;

            txt1.setText( String.valueOf(ss.charAt(0)));
            txt2.setText( String.valueOf(ss.charAt(1)));

            txt3.setText( String.valueOf(ss.charAt(2)));
            txt4.setText( String.valueOf(ss.charAt(3)));
            blink();


        }

        Log.e("BB",builder.toString()+"::"+String.valueOf(character)+"::");
        return builder.toString();
    }



    private void blink() {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        txt1.startAnimation(anim);
        txt2.startAnimation(anim);
        txt3.startAnimation(anim);
        txt4.startAnimation(anim);

    }


}
