package com.mercury.redeem.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.mercury.redeem.Modelclas.AddOrder;
import com.mercury.redeem.Modelclas.GetSellerDetails;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

public class CategoryDetailsActivity extends AppCompatActivity {
    SavePref savePref;
    public BindingService videoService;
    ImageView imgBackBtn, imgShareBtn, imgCategory;
    TextView txtItemName, txtItemType, txtItemDesc, txtButtonName, txtCoins;
    LinearLayout otypethreelayout, otypetwolayout, otypeonelayout, otypefivelayout, otypefourlayout;
    LinearLayout layoutbtnRedeem, layoutbtnHUB, layoutbtnLUB, layoutbtnRaffle, layoutbtnDraw;
    TextView txtAuctionEndTimeHUB, txtAuctionEndTimeLUB, txtAuctionEndTimeRaffle, txtAuctionEndTimeDraw, txtReadMore, txtItemSeller;
    String name, type, etime, edate, image, desc, coins, SellerName, SellerAbout, SellerLink, SellerImage;
    String start_date, status = "0", qty;
    String totalbids = "0", availableqty = "0";
    TextView txtName, txtLink, txtAbout;
    CircleImageView imageSeller;
    public TextView txtDrawEndsIn, txtRaffleEndsIn, txtAuctionEndsLUB, txtAuctionEndsHUB, txtQty, txtQtyStock;
    String txtstatus = "0", oid, totalWallet, oAmt, email, colorcode, umax, cdesc, olink, olimit, seller;
    EditText edtEmail;

    int bidlistsize;
    private FirebaseAuth mAuth;
    private static final String DEEP_LINK_URL = "https://bidkingapp.page.link/7Yoh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorydetailsnew);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        mAuth = FirebaseAuth.getInstance();
        imgBackBtn = (ImageView) findViewById(R.id.imgBackBtn);
        imgShareBtn = (ImageView) findViewById(R.id.imgShareBtn);
        imgCategory = (ImageView) findViewById(R.id.imgCategory);
        txtButtonName = (TextView) findViewById(R.id.txtButtonName);
        txtItemName = (TextView) findViewById(R.id.txtItemName);
        txtItemType = (TextView) findViewById(R.id.txtItemType);
        txtItemDesc = (TextView) findViewById(R.id.txtItemDesc);
        txtItemSeller = (TextView) findViewById(R.id.txtItemSeller);
        otypeonelayout = (LinearLayout) findViewById(R.id.otypeonelayout);
        otypethreelayout = (LinearLayout) findViewById(R.id.otypethreelayout);
        otypetwolayout = (LinearLayout) findViewById(R.id.otypetwolayout);
        otypefivelayout = (LinearLayout) findViewById(R.id.otypefivelayout);
        otypefourlayout = (LinearLayout) findViewById(R.id.otypefourlayout);
        layoutbtnRedeem = (LinearLayout) findViewById(R.id.layoutbtnRedeem);
        layoutbtnHUB = (LinearLayout) findViewById(R.id.layoutbtnHUB);
        layoutbtnLUB = (LinearLayout) findViewById(R.id.layoutbtnLUB);
        layoutbtnRaffle = (LinearLayout) findViewById(R.id.layoutbtnRaffle);
        layoutbtnDraw = (LinearLayout) findViewById(R.id.layoutbtnDraw);
        txtAuctionEndTimeHUB = (TextView) findViewById(R.id.txtAuctionEndTimeHUB);
        txtAuctionEndTimeLUB = (TextView) findViewById(R.id.txtAuctionEndTimeLUB);
        txtAuctionEndTimeRaffle = (TextView) findViewById(R.id.txtAuctionEndTimeRaffle);
        txtAuctionEndTimeDraw = (TextView) findViewById(R.id.txtAuctionEndTimeDraw);
        txtCoins = (TextView) findViewById(R.id.txtCoins);
        txtReadMore = (TextView) findViewById(R.id.txtReadMore);
        txtDrawEndsIn = (TextView) findViewById(R.id.txtDrawEndsIn);
        txtRaffleEndsIn = (TextView) findViewById(R.id.txtRaffleEndsIn);
        txtAuctionEndsLUB = (TextView) findViewById(R.id.txtAuctionEndsLUB);
        txtAuctionEndsHUB = (TextView) findViewById(R.id.txtAuctionEndsHUB);
        txtQty = (TextView) findViewById(R.id.txtQty);
        txtQtyStock = (TextView) findViewById(R.id.txtQtyStock);

        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);

        savePref = new SavePref(CategoryDetailsActivity.this);

        isNetworkConnected();

        handledynamiclinks();


        imgShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                   /* Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    // TODO: You can change the Share message Text from below ;)
                    String sAux = "Download bidking App and get free Gift Cards, Products. Download the App now" +"\n\n";
                    sAux = sAux + Uri.parse("https://play.google.com/store/apps/details?id=com.bidking.app");
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));*/
                    shareDeepLink(DEEP_LINK_URL);

                    //handledynamiclinks();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        imgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            if (getIntent() != null) {
                name = getIntent().getStringExtra("name");
                type = getIntent().getStringExtra("type");
                etime = getIntent().getStringExtra("etime");
                edate = getIntent().getStringExtra("edate");
                image = getIntent().getStringExtra("image");
                desc = getIntent().getStringExtra("desc");
                coins = getIntent().getStringExtra("coins");
                oid = getIntent().getStringExtra("oid");
                qty = getIntent().getStringExtra("qty");
                oAmt = getIntent().getStringExtra("oamt");
                colorcode = getIntent().getStringExtra("colorcode");
                umax = getIntent().getStringExtra("umax");
                cdesc = getIntent().getStringExtra("cdesc");
                olink = getIntent().getStringExtra("link");
                olimit = getIntent().getStringExtra("limit");
                totalbids = getIntent().getStringExtra("totalbids");
                seller = getIntent().getStringExtra("id");
                if (TextUtils.isEmpty(olimit)) {
                    olimit = "1";
                } else {
                    olimit = getIntent().getStringExtra("limit");
                }

                Log.e("::", name + "::" + type + "::" + etime + "::" + olimit);

            }
            availableqty = String.valueOf(Integer.parseInt(qty) - Integer.parseInt(totalbids));

        } catch (Exception e) {
            e.printStackTrace();
        }
        getSeller();
        txtItemName.setText(name);
        txtItemType.setText("Item Type: " + type);
        txtItemSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWindialog();
            }
        });


        txtItemDesc.post(new Runnable() {
            @Override
            public void run() {
                int lineCount = txtItemDesc.getLineCount();

                Log.e("LC", lineCount + "");

                if (lineCount > 4) {
                    txtReadMore.setVisibility(View.VISIBLE);
                    txtItemDesc.setMaxLines(4);
                } else {

                }
                // Use lineCount here
            }
        });
        txtItemDesc.setText(desc);
        txtReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status.equals("0")) {
                    txtItemDesc.setMaxLines(30);
                    txtReadMore.setText("Read Less");
                    status = "1";

                } else {
                    txtItemDesc.setMaxLines(10);
                    txtReadMore.setText("Read More..");
                    status = "0";
                }


            }
        });

        try {
            Glide.with(getApplicationContext())
                    .load(image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(imgCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        layoutbtnHUB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryDetailsActivity.this, AuctionDetailActivity.class);
                i.putExtra("O_id", oid);
                i.putExtra("check", "live");
                startActivity(i);
            }
        });
        layoutbtnLUB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryDetailsActivity.this, LiveDetailActivity.class);
                i.putExtra("O_id", oid);
                i.putExtra("check", "live");
                startActivity(i);
            }
        });
        layoutbtnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryDetailsActivity.this, RaffleDetailActivity.class);
                i.putExtra("O_id", oid);
                i.putExtra("check", "draw");
                startActivity(i);

            }
        });
        layoutbtnRaffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryDetailsActivity.this, RaffleDetailActivity.class);
                i.putExtra("O_id", oid);
                i.putExtra("check", "raffle");
                startActivity(i);
            }
        });

        layoutbtnRedeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (Integer.parseInt(availableqty) > 0) {
                        if (Integer.valueOf(totalWallet) >= Integer.valueOf(oAmt)) {

                            //gettraapi();
                            openemaildialog();

                        } else {
                            opendialog();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Currently, Out of Stock!", Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        if (type.equals("1")) {
            txtButtonName.setText("Lowest Unique Bid");
            otypeonelayout.setVisibility(View.VISIBLE);
            otypetwolayout.setVisibility(View.GONE);
            otypethreelayout.setVisibility(View.GONE);
            otypefourlayout.setVisibility(View.GONE);
            otypefivelayout.setVisibility(View.GONE);
            txtQty.setVisibility(View.GONE);

            Thread myThread = null;

            Runnable myRunnableThread = new CountDownRunner(txtAuctionEndTimeLUB, edate + " " + etime);
            myThread = new Thread(myRunnableThread);
            myThread.start();


        } else if (type.equals("2")) {
            txtButtonName.setText("Highest Unique Bid");
            otypeonelayout.setVisibility(View.GONE);
            otypetwolayout.setVisibility(View.VISIBLE);
            otypethreelayout.setVisibility(View.GONE);
            otypefourlayout.setVisibility(View.GONE);
            otypefivelayout.setVisibility(View.GONE);
            txtQty.setVisibility(View.GONE);

            Thread myThread = null;


            Runnable myRunnableThread = new CountDownRunner(txtAuctionEndTimeHUB, edate + " " + etime);
            myThread = new Thread(myRunnableThread);
            myThread.start();
        } else if (type.equals("3")) {
            txtButtonName.setText("Redeem");
            otypeonelayout.setVisibility(View.GONE);
            otypetwolayout.setVisibility(View.GONE);
            otypethreelayout.setVisibility(View.VISIBLE);
            otypefourlayout.setVisibility(View.GONE);
            otypefivelayout.setVisibility(View.GONE);
            txtCoins.setText(coins + " Coins");

            if (availableqty.equals("0") || availableqty.startsWith("-")) {
                txtQtyStock.setVisibility(View.VISIBLE);
                txtQty.setVisibility(View.GONE);
            } else {
                txtQty.setVisibility(View.VISIBLE);
                txtQty.setText("Only " + availableqty + " Left in Stock\uD83D\uDE31");
                txtQtyStock.setVisibility(View.GONE);
            }
        } else if (type.equals("4")) {
            txtButtonName.setText("Lucky Draw");
            otypeonelayout.setVisibility(View.GONE);
            otypetwolayout.setVisibility(View.GONE);
            otypethreelayout.setVisibility(View.GONE);
            otypefourlayout.setVisibility(View.VISIBLE);
            otypefivelayout.setVisibility(View.GONE);
            txtQty.setVisibility(View.GONE);

            Thread myThread = null;

            Runnable myRunnableThread = new CountDownRunner(txtAuctionEndTimeDraw, edate + " " + etime);
            myThread = new Thread(myRunnableThread);
            myThread.start();
        } else if (type.equals("5")) {
            txtButtonName.setText("Raffle");
            otypeonelayout.setVisibility(View.GONE);
            otypetwolayout.setVisibility(View.GONE);
            otypethreelayout.setVisibility(View.GONE);
            otypefourlayout.setVisibility(View.GONE);
            otypefivelayout.setVisibility(View.VISIBLE);
            txtQty.setVisibility(View.GONE);


            Thread myThread = null;

            Runnable myRunnableThread = new CountDownRunner(txtAuctionEndTimeRaffle, edate + " " + etime);
            myThread = new Thread(myRunnableThread);
            myThread.start();
        } else if (type.equals("6")) {


            String url = olink;

            Log.e("URL", url + "::" + olink);

            if (TextUtils.isEmpty(url)) {
                // Toast.makeText(getApplicationContext(),"Invalid URL",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                /*if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;*/
                Intent intent = new Intent(CategoryDetailsActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("from", "main");
                intent.putExtra("title", "");

                startActivity(intent);
            }

          /*
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);*/

        }

    }


    private void shareDeepLink(String deepLink) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "bidking");
        intent.putExtra(Intent.EXTRA_TEXT, deepLink);

        startActivity(intent);
    }


    private void handledynamiclinks() {


        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();

                            Log.e("DynamicLink", deepLink.toString() + "");


                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("TAG", "getDynamicLink:onFailure", e);
                    }
                });
    }


    private void openemaildialog() {
        final Dialog dialog = new Dialog(CategoryDetailsActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_redeem);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        Button ok = dialog.findViewById(R.id.ok);
        TextView d_title = dialog.findViewById(R.id.d_title);
        edtEmail = dialog.findViewById(R.id.edtEmail);
        d_title.setText("Redemption Confirmation \uD83D\uDE07");
        Button cancel = dialog.findViewById(R.id.cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                } else {

                    addbid(email);


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

    private void opendialog() {
        final Dialog dialog = new Dialog(CategoryDetailsActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_insufficientcoins);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        Button ok = dialog.findViewById(R.id.ok);
        TextView d_title = dialog.findViewById(R.id.d_title);
        d_title.setText("Insufficient Coins");
        Button cancel = dialog.findViewById(R.id.cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryDetailsActivity.this, MainActivity.class);
                intent.putExtra("page", "2");
                startActivity(intent);
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

    }

    public void doWork(final TextView textView, final String o_etime) {
        (CategoryDetailsActivity.this).runOnUiThread(new Runnable() {
            public void run() {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                start_date = currentDate + " " + currentTime;
                findDifference(start_date, textView, o_etime, type);
            }
        });
    }

    class CountDownRunner implements Runnable {
        TextView textView;
        String o_etime;

        public CountDownRunner(TextView tx_time, String o_etime) {
            this.textView = tx_time;
            this.o_etime = o_etime;
        }

        // @Override
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


    void findDifference(String start_date,
                        TextView textView, String end_date, String type) {

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
                textView.setText("Auction Closed");
                textView.setTextColor(Color.parseColor("#00b712"));
                txtDrawEndsIn.setVisibility(View.GONE);
                txtRaffleEndsIn.setVisibility(View.GONE);
                txtAuctionEndsLUB.setVisibility(View.GONE);
                txtAuctionEndsHUB.setVisibility(View.GONE);


            } else if (difference_In_Days < 0 || difference_In_Hours < 0 || difference_In_Minutes < 0 || difference_In_Seconds < 0) {
                textView.setText("Auction Closed");
                textView.setTextColor(Color.parseColor("#ED3124"));
                txtDrawEndsIn.setVisibility(View.GONE);
                txtRaffleEndsIn.setVisibility(View.GONE);
                txtAuctionEndsLUB.setVisibility(View.GONE);
                txtAuctionEndsHUB.setVisibility(View.GONE);
            } else {
                textView.setText(diff);
                textView.setTextColor(Color.parseColor("#00b712"));

                if (type.equals("1")) {
                    txtDrawEndsIn.setVisibility(View.GONE);
                    txtRaffleEndsIn.setVisibility(View.GONE);
                    txtAuctionEndsLUB.setVisibility(View.VISIBLE);
                    txtAuctionEndsHUB.setVisibility(View.GONE);
                } else if (type.equals("2")) {
                    txtDrawEndsIn.setVisibility(View.GONE);
                    txtRaffleEndsIn.setVisibility(View.GONE);
                    txtAuctionEndsLUB.setVisibility(View.GONE);
                    txtAuctionEndsHUB.setVisibility(View.VISIBLE);
                } else if (type.equals("4")) {
                    txtDrawEndsIn.setVisibility(View.VISIBLE);
                    txtRaffleEndsIn.setVisibility(View.GONE);
                    txtAuctionEndsLUB.setVisibility(View.GONE);
                    txtAuctionEndsHUB.setVisibility(View.GONE);
                } else if (type.equals("5")) {
                    txtDrawEndsIn.setVisibility(View.GONE);
                    txtRaffleEndsIn.setVisibility(View.VISIBLE);
                    txtAuctionEndsLUB.setVisibility(View.GONE);
                    txtAuctionEndsHUB.setVisibility(View.GONE);
                } else if (type.equals("6")) {
                    txtDrawEndsIn.setVisibility(View.GONE);
                    txtRaffleEndsIn.setVisibility(View.GONE);
                    txtAuctionEndsLUB.setVisibility(View.GONE);
                    txtAuctionEndsHUB.setVisibility(View.GONE);
                }
            }

        }

        // Catch the Exception
        catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void openWindialog() {
        final Dialog dialog = new Dialog(CategoryDetailsActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.activity_sellerdetails);
        Window window = dialog.getWindow();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        window.setLayout((int)(width*.86),(int)(height*.6));
                //(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        txtName= dialog.findViewById(R.id.sellername);
        txtAbout= dialog.findViewById(R.id.aboutSeller);
        Button txtLink1 = dialog.findViewById(R.id.linkSeller);
        imageSeller = dialog.findViewById(R.id.imageSeller);
        txtName.setText(SellerName);
        txtAbout.setText(SellerAbout);
        txtAbout.setMovementMethod(new ScrollingMovementMethod());
        txtLink1.setMovementMethod(LinkMovementMethod.getInstance());
        if(SellerLink.isEmpty()) {
                    txtLink1.setClickable(false);
                    txtLink1.setEnabled(false);
                }
                else {
            txtLink1.setClickable(true);
            txtLink1.setEnabled(true);
        }
        txtLink1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //    Uri SellerLink_uri = Uri.parse(SellerLink);
                //  System.out.println("Kajal_url"+SellerLink_uri);
                //Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                //browserIntent.setData(SellerLink_uri);
                //(Uri.parse(SellerLink));
                //    if(browserIntent.resolveActivity(getPackageManager()) != null){
                //   startActivity(browserIntent);
                //}
                              String url = SellerLink;
            Log.e("URL", url + "::" + SellerLink);

            if (TextUtils.isEmpty(url)) {
                // Toast.makeText(getApplicationContext(),"Invalid URL",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                /*if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://" + url;*/
                Intent intent = new Intent(CategoryDetailsActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("from", "categories");
                intent.putExtra("title", "2");
                startActivity(intent);
                }
            }
        });

        Glide.with(this).load(SellerImage).placeholder(R.drawable.img_user).circleCrop().into(imageSeller);
        //Button ok = dialog.findViewById(R.id.ok);
        // TextView d_title = dialog.findViewById(R.id.d_title);
        //  edtEmail=dialog.findViewById(R.id.edtEmail);
        //  d_title.setText("Redemption Confirmation \uD83D\uDE07");
        //  Button cancel = dialog.findViewById(R.id.cancel);

    }

    public void getSeller() {
        try {
            callgetApiSeller().enqueue(new Callback<GetSellerDetails>() {
                @Override
                public void onResponse(Call<GetSellerDetails> call, retrofit2.Response<GetSellerDetails> response) {
                    String SellerId;
                    try {
                        List<GetSellerDetails.JSONDATum> arrayList = new ArrayList<>(response.body().getJsonData());
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                            GetSellerDetails.JSONDATum reqsellers = arrayList.stream()
                                    .filter( reqseller -> seller.equals(reqseller.getId()))
                                    .findAny()
                                    .orElse(null);
                            SellerName = reqsellers.getName();
                            System.out.println("kajaldata"+SellerName);
                            SellerAbout = reqsellers.getAbout();
                            SellerLink = reqsellers.getLink();
                            SellerImage = reqsellers.getImage();
                            txtItemSeller.setText("Sold by " + SellerName);
                        }
                        if(SellerName!=null) {
                            txtItemSeller.setClickable(true);
                            txtItemSeller.setEnabled(true);
                        }
                        else {
                            txtItemSeller.setClickable(false);
                            txtItemSeller.setEnabled(false);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        //lvlLive.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<GetSellerDetails> call, Throwable t) {
                    //lvlLive.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Call<GetSellerDetails> callgetApiSeller() {
        return videoService.get_seller();
    }

    public void getprofile() {
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    try {
                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();

                        totalWallet = arrayList.get(0).getWallet();


                    } catch (Exception e) {
                        e.printStackTrace();
                        //lvlLive.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    //lvlLive.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void addbid(String s) {
        //lvlOrderDetail.setVisibility(View.VISIBLE);
        try {
            calladdbidApi().enqueue(new Callback<AddOrder>() {
                @Override
                public void onResponse(Call<AddOrder> call, retrofit2.Response<AddOrder> response) {
                    try {

                        ArrayList<AddOrder.Add_Order_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(CategoryDetailsActivity.this, "" + arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getO_status().equalsIgnoreCase("1")) {
                          /*  Intent i=new Intent(CategoryDetailsActivity.this,MainActivity.class);
                            startActivity(i);*/
                            updatewallet();
                            //edForgotNumbid.setText("");
                        } else {
                            //lvlOrderDetail.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // lvlOrderDetail.setVisibility(View.GONE);

                    }


                }

                @Override
                public void onFailure(Call<AddOrder> call, Throwable t) {
                    //lvlOrderDetail.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<AddOrder> calladdbidApi() {


        Log.e("values", oid + "::" + oAmt + "::" + email + "::" + coins);
        return videoService.add_order(savePref.getUserId(), oid, coins, "", coins, email);

    }


    public void updatewallet() {
        // lvlLive.setVisibility(View.VISIBLE);
        try {
            calladdbidApii().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {
                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        //  Toast.makeText(CategoryDetailsActivity.this, "" + arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();


                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")) {
                          /*  Intent i = new Intent(CategoryDetailsActivity.this, MainActivity.class);
                            startActivity(i);*/
                            // edForgotNumbid.setText("");

                            //postUserwalletUpdate();

                            openconfirmorderdialog();
                            Log.e("Sucees", "S");
                        } else {
                            //lvlLive.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // lvlLive.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                    // lvlLive.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<SuccessModel> calladdbidApii() {

        return videoService.add_bid(savePref.getUserId(), oid, coins, coins);
    }

    private void openconfirmorderdialog() {

        final Dialog dialog = new Dialog(CategoryDetailsActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_orderconfirmed);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.layoutmypurchases);
        LinearLayout ratinglayout = (LinearLayout) dialog.findViewById(R.id.layoutrateapp);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(CategoryDetailsActivity.this, MainActivity.class);
                i.putExtra("page", "1");
                startActivity(i);
            }
        });

        ratinglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//showRateApp();

                //getReviewInfo();

            }
        });
    }

   /* private void getReviewInfo() {
        reviewManager = ReviewManagerFactory.create(getApplicationContext());
        Task<ReviewInfo> manager = reviewManager.requestReviewFlow();
        manager.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
                startReviewFlow();
            } else {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void startReviewFlow() {
        if (reviewInfo != null) {
            Task<Void> flow = reviewManager.launchReviewFlow(this, reviewInfo);
            flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    Toast.makeText(getApplicationContext(), "App Rated Succesfully", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(), "App not Rated", Toast.LENGTH_LONG).show();

            Log.e("KK",reviewInfo.toString());
        }
    }*/
    /*public void showRateApp() {
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = manager.launchReviewFlow(CategoryDetailsActivity.this, reviewInfo);
                flow.addOnCompleteListener(task1 -> {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                });
            } else {
                // There was some problem, continue regardless of the result.
                // show native rate app dialog on error
                showRateAppFallbackDialog();
            }
        });
    }

    */

    /**
     * Showing native dialog with three buttons to review the app
     * Redirect user to playstore to review the app
     *//*
    private void showRateAppFallbackDialog() {
        new MaterialAlertDialogBuilder(CategoryDetailsActivity.this)
                .setTitle(R.string.rate_app_title)
                .setMessage(R.string.rate_app_message)
                .setPositiveButton(R.string.rate_btn_pos, (dialog, which) -> redirectToPlayStore())
                .setNegativeButton(R.string.rate_btn_neg,
                        (dialog, which) -> {
                            // take action when pressed not now
                        })
                .setNeutralButton(R.string.rate_btn_nut,
                        (dialog, which) -> {
                            // take action when pressed remind me later
                        })
                .setOnDismissListener(dialog -> {
                })
                .show();
    }

    // redirecting user to PlayStore
    public void redirectToPlayStore() {
        final String appPackageName = getPackageName();
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException exception) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }*/


    /*public void gettraapi() {
      //  lvlYourBidsacti.setVisibility(View.VISIBLE);
        try {
            callgettraApi().enqueue(new Callback<GetBidUser>() {
                @Override
                public void onResponse(Call<GetBidUser> call, retrofit2.Response<GetBidUser> response) {

                    try {

                      //  lvlYourBidsacti.setVisibility(View.GONE);
                        ArrayList<GetBidUser.Get_biduser_Inner> arrayList = response.body().getJSON_DATA();
                        bidlistsize=arrayList.size();

                        Log.e("BB",bidlistsize+"::"+olimit);

                        if(bidlistsize<=Integer.parseInt(olimit))
                        {


                        }
else
                        {
                            Toast.makeText(getApplicationContext(),"Your limit has been exceeded",Toast.LENGTH_SHORT).show();
                        }


                       // recyclerYourBidsacti.setAdapter(new BidUserAdapter(YourBidsActivity.this, arrayList));

                    }catch (Exception e){
                        e.printStackTrace();
                    //    lvlYourBidsacti.setVisibility(View.GONE);

                    }



                }

                @Override
                public void onFailure(Call<GetBidUser> call, Throwable t) {
                   // lvlYourBidsacti.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<GetBidUser> callgettraApi() {
        return videoService.get_bid_user(savePref.getUserId());
    }*/
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
            //nointernetlayout.setVisibility(View.GONE);

            getprofile();
        } else {
            // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent = new Intent(getApplicationContext(), NoInternetActivity.class);
            startActivity(intent);
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}