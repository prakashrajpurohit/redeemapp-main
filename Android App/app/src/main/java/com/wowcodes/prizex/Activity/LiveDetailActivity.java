package com.mercury.redeem.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Modelclas.AllBidder;
import com.mercury.redeem.Modelclas.AllBidderInner;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;

public class LiveDetailActivity extends AppCompatActivity {
    public BindingService videoService;
    String checkStatus;
    TextView txtBid;
    String oId;
    LinearLayout lvlLive;
    SavePref savePref;
    String getBids;
    ImageView imgItemImg;
    String startDate;
    String totalWallet;
    String oAmount;
    TextView txtClose;
    TextView txtPay;
    ArrayList<String> arrayListsimple = new ArrayList<>();
    TextView txtBids;
    TextView txtYourr;
    TextView txtSetName;
    TextView txtBidAbove;
    TextView txtExt;
    ArrayList<AllBidderInner> arrayList;
    String getAmount;
    TextView txtAucname;
    EditText edForgotNumbid;
    EditText edRange2, edRange1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_detail);

        oId = getIntent().getStringExtra("O_id");
        checkStatus = getIntent().getStringExtra("check");
        ImageView imgBackk = findViewById(R.id.imgBackk);
        txtAucname = findViewById(R.id.txtAucname);
        txtSetName = findViewById(R.id.txtSetName);
        imgBackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        savePref = new SavePref(LiveDetailActivity.this);
        lvlLive = findViewById(R.id.linearlay);
        txtBid = findViewById(R.id.txtBid);
        edRange2 = findViewById(R.id.edRange2);
        edRange1 = findViewById(R.id.edRange1);
        txtBidAbove = findViewById(R.id.txtBidAbove);
        edForgotNumbid = findViewById(R.id.edForgotNumbid);
        imgItemImg = findViewById(R.id.imgItemImg);
        txtClose = findViewById(R.id.txtClose);
        txtPay = findViewById(R.id.txtPay);
        txtBids = findViewById(R.id.txtBids);
        txtYourr = findViewById(R.id.txtYourr);
        txtExt = findViewById(R.id.txtExt);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);


        if (checkStatus.equalsIgnoreCase("live")) {
            edForgotNumbid.setFocusable(true);
            txtBid.setBackground(getResources().getDrawable(R.drawable.btn_bg1));
        } else {
            txtBid.setBackground(getResources().getDrawable(R.drawable.btn_bggray));
            edForgotNumbid.setFocusable(false);

        }

        txtBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LiveDetailActivity.this, AllBidderActivity.class);
                i.putExtra("o_id", oId);
                startActivity(i);
            }
        });
        txtBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!edForgotNumbid.getText().toString().equalsIgnoreCase("") && !edRange1.getText().toString().equalsIgnoreCase("") && !edRange2.getText().toString().equalsIgnoreCase("")) {
                        Toast.makeText(LiveDetailActivity.this, "Please Add only one value at a time...", Toast.LENGTH_SHORT).show();
                    } else {
                        if (edForgotNumbid.getText().toString().equalsIgnoreCase("")) {
                            if (edRange1.getText().toString().equalsIgnoreCase("") || edRange2.getText().toString().equalsIgnoreCase("")) {
                                Toast.makeText(LiveDetailActivity.this, "Please Enter some Bid Value", Toast.LENGTH_SHORT).show();
                            } else if (Double.parseDouble(edRange1.getText().toString()) >= Double.parseDouble(arrayList.get(0).getO_min()) && Double.parseDouble(edRange2.getText().toString()) <= Double.parseDouble(arrayList.get(0).getO_max())) {

                                if (Double.parseDouble(edRange1.getText().toString()) >= Double.parseDouble(edRange2.getText().toString())) {
                                    Toast.makeText(LiveDetailActivity.this, "Please add Bid value in min to max", Toast.LENGTH_SHORT).show();
                                } else {
                                    final Dialog dialog = new Dialog(LiveDetailActivity.this);
                                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                    dialog.setContentView(R.layout.dialog_bidone);
                                    Window window = dialog.getWindow();
                                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    Button ok = dialog.findViewById(R.id.ok);
                                    Button cancel = dialog.findViewById(R.id.cancel);
                                    TextView d_title = dialog.findViewById(R.id.d_title);
                                    TextView d_title1 = dialog.findViewById(R.id.d_title1);
                                    TextView bottomtxt = dialog.findViewById(R.id.bottomtxt);
                                    d_title1.setText("Bid Confirmation");
                                    d_title.setText("Lowest unique bid Range  $" + twoPlaceDecimal(edRange1.getText().toString()) + " to $" + twoPlaceDecimal(edRange2.getText().toString()));
                                    bottomtxt.setText("For every Single Bid " + arrayList.get(0).getO_amount() + " coin will be deducted from your coin balance on confirmation.");
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
                                            double x = Double.parseDouble(edRange1.getText().toString()) - 0.01;
                                            DecimalFormat form = new DecimalFormat("#.##");
                                            while (x < Double.parseDouble(edRange2.getText().toString())) {
                                                x = x + 0.01;
                                                arrayListsimple.add("" + Double.valueOf(form.format(x)));
                                            }

                                            Log.i("onClick", "onClick: "+arrayListsimple.size());

                                            if (arrayListsimple.size()>102){
                                                arrayListsimple.clear();
                                                final Dialog dialog = new Dialog(LiveDetailActivity.this);
                                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                                dialog.setContentView(R.layout.dialog_notbid);
                                                Window window = dialog.getWindow();
                                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                                Button ok = dialog.findViewById(R.id.ok);
                                                TextView d_title = dialog.findViewById(R.id.d_title);
                                                d_title.setText("You Can Only Place Max 100 Bids At A Time Using Bid Range Feature :(");
                                                ok.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        //arrayListsimple.clear();
                                                        edRange1.setText("");
                                                        edRange2.setText("");

                                                        dialog.dismiss();
                                                    }
                                                });
                                                dialog.show();
                                            }else {
                                                addbidrange();
                                            }
                                            //
                                        }
                                    });
                                    dialog.show();
                                }
                            } else {
                                final Dialog dialog = new Dialog(LiveDetailActivity.this);
                                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                dialog.setContentView(R.layout.dialog_notbid);
                                Window window = dialog.getWindow();
                                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                Button ok = dialog.findViewById(R.id.ok);
                                TextView d_title = dialog.findViewById(R.id.d_title);
                                d_title.setText("Your unique bid of $" + edRange1.getText().toString() + " & $" + edRange2.getText().toString() + "  was not submitted because you can only enter bid value between $" + arrayList.get(0).getO_min() + " to $" + arrayList.get(0).getO_max());
                                ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                    }
                                });
                                dialog.show();
                            }
                        } else {
                            DecimalFormat form = new DecimalFormat("0.00");
                            getBids = form.format(Double.parseDouble(edForgotNumbid.getText().toString()));
                            if (checkStatus.equalsIgnoreCase("live")) {
                                String codemin = arrayList.get(0).getO_min();
                                String codedoublemin = form.format(Double.parseDouble(codemin));
                                String codemax = arrayList.get(0).getO_max();
                                String codedoublemax = form.format(Double.parseDouble(codemax));
                                if (Double.parseDouble(getBids)
                                        >= Double.parseDouble(codedoublemin)
                                        && Double.parseDouble(getBids) <=
                                        Double.parseDouble(codedoublemax)) {
                                    final Dialog dialog = new Dialog(LiveDetailActivity.this);
                                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                    dialog.setContentView(R.layout.dialog_bid);
                                    Window window = dialog.getWindow();
                                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    Button ok = dialog.findViewById(R.id.ok);
                                    Button cancel = dialog.findViewById(R.id.cancel);
                                    TextView d_title = dialog.findViewById(R.id.d_title);
                                    TextView bottomtxt = dialog.findViewById(R.id.bottomtxt);
                                    d_title.setText("Single lowest unique bid of $" + getBids);
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
                                            if (Integer.valueOf(totalWallet) >= Integer.valueOf(oAmount)) {
                                                addbid();
                                            } else {
                                                Toast.makeText(LiveDetailActivity.this, "Insufficient Coins,Please Add Some Coins to participate in this Auction", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    dialog.show();
                                } else {
                                    final Dialog dialog = new Dialog(LiveDetailActivity.this);
                                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                                    dialog.setContentView(R.layout.dialog_notbid);
                                    Window window = dialog.getWindow();
                                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    Button ok = dialog.findViewById(R.id.ok);
                                    TextView d_title = dialog.findViewById(R.id.d_title);
                                    d_title.setText("Your unique bid of $" + edForgotNumbid.getText().toString() + "  was not submitted because you can only enter bid value between $" + arrayList.get(0).getO_min() + " to $" + arrayList.get(0).getO_max());
                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(LiveDetailActivity.this, "Something Went Wrong!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtYourr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LiveDetailActivity.this, AllUserBidderActivity.class);
                i.putExtra("o_id", oId);
                startActivity(i);
            }
        });
        getofferapi();
        getprofile();
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
                        Glide.with(LiveDetailActivity.this)
                                .load(arrayList.get(0).getO_image())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgItemImg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    txtPay.setText("Play For " + arrayList.get(0).getO_amount());
                    oAmount = arrayList.get(0).getO_amount();
                    txtSetName.setText(arrayList.get(0).getO_name());
                    txtAucname.setText(arrayList.get(0).getO_name());
                    txtBids.setText(arrayList.get(0).getTotal_users() + " Bidders");
                    txtBidAbove.setText("Bid Above $" + arrayList.get(0).getO_min());
                    getAmount = arrayList.get(0).getO_amount();
                    try {
                        if (arrayList.get(0).getWon_id().equalsIgnoreCase(savePref.getUserId())) {
                            txtExt.setText("Kudos, You are currently winning");
                        } else if (arrayList.get(0).getWon_id().equalsIgnoreCase("0")) {
                            txtExt.setText("No One is winning right now");
                        } else {
                            txtExt.setText(arrayList.get(0).getWon_name() + " Is Currently Winning..");
                        }
                    } catch (Exception e) {
                        txtExt.setText("");
                    }
                    Thread myThread = null;
                    Runnable myRunnableThread = new CountDownRunner(txtClose, arrayList.get(0).getO_edate() + " " + arrayList.get(0).getO_etime());
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
                txtBid.setBackground(getResources().getDrawable(R.drawable.btn_bggray));
                txtBid.setClickable(false);
                edForgotNumbid.setFocusable(false);
            } else if (difference_In_Days < 0 || difference_In_Hours < 0 || difference_In_Minutes < 0 || difference_In_Seconds < 0) {
                txtBid.setBackground(getResources().getDrawable(R.drawable.btn_bggray));
                edForgotNumbid.setFocusable(false);
                txtBid.setClickable(false);
                textView.setText("It's Gone.. The Auction Has Ended");
            } else {
                textView.setText("Auction Ends in:- " + diff);
            }
        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void addbid() {
        lvlLive.setVisibility(View.VISIBLE);
        try {
            calladdbidApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {
                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(LiveDetailActivity.this, "" + arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")) {
                            Intent i = new Intent(LiveDetailActivity.this, MainActivity.class);
                            startActivity(i);
                            edForgotNumbid.setText("");
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

        return videoService.add_bid(savePref.getUserId(), oId, getBids, getAmount);
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

    public void addbidrange() {


        lvlLive.setVisibility(View.VISIBLE);
        try {
            calladdbidrangeApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {


                    try {

                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(LiveDetailActivity.this, "" + arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")) {

                            Intent i = new Intent(LiveDetailActivity.this, MainActivity.class);
                            startActivity(i);
                            edForgotNumbid.setText("");
                            edRange1.setText("");
                            edRange2.setText("");
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

    private Call<SuccessModel> calladdbidrangeApi() {

        return videoService.add_bid_multi("" + getJsonObj(arrayListsimple));
    }

    private JSONArray getJsonObj(ArrayList<String> arrayList) {


        JSONArray jArray = new JSONArray();

        for (int i = 0; i < arrayList.size(); i++) {
            JSONObject jGroup = new JSONObject();

            try {
                jGroup.put("u_id", savePref.getUserId());
                jGroup.put("o_id", oId);

                jGroup.put("bd_value", twoPlaceDecimal(arrayList.get(i)));
                jGroup.put("bd_amount", getAmount);
                jGroup.put("type_no", "1");


                jArray.put(jGroup);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return jArray;

    }

    public String twoPlaceDecimal(String input) {


        DecimalFormat form = new DecimalFormat("0.00");


        return form.format(Double.parseDouble(input)) + "";

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
}