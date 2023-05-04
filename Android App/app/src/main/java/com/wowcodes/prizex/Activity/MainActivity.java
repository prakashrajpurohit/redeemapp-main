package com.mercury.redeem.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.mercury.redeem.Fragments.CoinHistoryFragment;
import com.mercury.redeem.Fragments.EarnFragment;
import com.mercury.redeem.Fragments.GetoWinnerFragment;
import com.mercury.redeem.Fragments.HomeFragment;
import com.mercury.redeem.Fragments.YouFragment;
import com.mercury.redeem.Modelclas.SettingModel;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    public static String stringAbout, stringCondi, stringPrivacy, stringContact, stringHowTo, stringEmail;
    public BindingService videoService;
    ViewPager viewPager;
    CoinHistoryFragment shopFragment;
    HomeFragment homeFragment;
    LinearLayout lvlCoin;
    TextView txtGetCoinTop;
    EarnFragment earnFragment;
    GetoWinnerFragment winnerFragment;
    YouFragment youFragment;
    BottomNavigationView bottomNavigationView;
    MenuItem prevMenuItem;
    SavePref savePref;
    TextView txtAucname;
    ImageView imgHelp;
    String page="1",fcmtoken;
    LinearLayout nointernetlayout;


    private String TAG="MainActivity";
    private AdView mAdView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        viewPager = findViewById(R.id.viewpager);
        txtAucname = findViewById(R.id.txtAucname);
        lvlCoin = findViewById(R.id.lvlCoin);
        imgHelp = findViewById(R.id.imgHelp);
        txtGetCoinTop = findViewById(R.id.txtGetCoinTop);
        nointernetlayout=(LinearLayout)findViewById(R.id.nointernetlayout);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        savePref = new SavePref(MainActivity.this);
// TODO: You can change the About Us Link from Below;)
        stringAbout = "https://biddwinn.co.in/About-Us.html";
        getsetting();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        try {
            if(getIntent()!=null)
            {
                page=getIntent().getStringExtra("page");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       /* AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.getMessage());
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Log.d(TAG, "Ad was loaded.");
                    }
                });*/

     /*   mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad was shown.");
            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.d(TAG, "Ad failed to show.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad was dismissed.");
                mRewardedAd = null;
            }
        });*/
        imgHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, VisitWebsiteActivity.class);
                // TODO: You can change the How It Works Link from Below;)
                i.putExtra("url", "https://prizex.in/How-to-play.html/");
                i.putExtra("name", "How to play");
                startActivity(i);
            }
        });

        txtAucname.setText("Let's Bid & Win");

        lvlCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_you:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_winner:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_auction:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_shop:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.action_earn:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });


        setupViewPager(viewPager);
        viewPager.setCurrentItem(2);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(1).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        try {
            if(page.equals("1"))
            {
                viewPager.setCurrentItem(0);
            }
            else if(page.equals("2"))
            {
                viewPager.setCurrentItem(1);
            }
            else if(page.equals("3"))
            {
                viewPager.setCurrentItem(2);
            }
            else if(page.equals("4"))
            {
                viewPager.setCurrentItem(3);
            }
            else if(page.equals("5"))
            {
                viewPager.setCurrentItem(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        isNetworkConnected();
        gettoken();
    }

    private void gettoken() {
        final String[] token = {""};
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isComplete()){
                    token[0] = task.getResult();
                    fcmtoken=token[0];
                    Log.e("AppConstants", "onComplete: new Token got: "+token[0] );

                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        getprofile();


    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            //getItems();
            //nointernetlayout.setVisibility(View.GONE);

        }
        else
        {
            // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getApplicationContext(), NoInternetActivity.class);
            startActivity(intent);
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        youFragment = new YouFragment();
        winnerFragment = new GetoWinnerFragment();
        homeFragment = new HomeFragment();
        shopFragment = new CoinHistoryFragment();
        earnFragment = new EarnFragment();

        adapter.addFragment(youFragment);
        adapter.addFragment(winnerFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(shopFragment);
        adapter.addFragment(earnFragment);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if (i == 0) {
                    txtAucname.setText("Your Profile");
                }
                if (i == 1) {
                    txtAucname.setText("Winners");
                }
                if (i == 2) {
                    txtAucname.setText("Let's Bid & Win");
                }
                if (i == 3) {
                    txtAucname.setText("Coin Shop");
                }
                if (i == 4) {
                    txtAucname.setText("Earn Coins");
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        dialog.setContentView(R.layout.dialog_close);
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Button exit = dialog.findViewById(R.id.exit);
        Button cancel = dialog.findViewById(R.id.cancel);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finishAffinity();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void getprofile() {
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {


                    try {


                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();

                        txtGetCoinTop.setText(arrayList.get(0).getWallet());
                        txtAucname.setText("Let's Bid & Win");

                    }catch (Exception e){
                        e.printStackTrace();

                    }



                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }

    public void getsetting() {
        try {
            callseting().enqueue(new Callback<SettingModel>() {
                @Override
                public void onResponse(Call<SettingModel> call, retrofit2.Response<SettingModel> response) {


                    try {
                        ArrayList<SettingModel.Setting_model_Inner> arrayList = response.body().getJSON_DATA();

                        stringCondi = arrayList.get(0).getAbout_us();
                        stringPrivacy = arrayList.get(0).getApp_privacy_policy();
                        stringContact = arrayList.get(0).getAbout_us();
                        stringHowTo = arrayList.get(0).getHow_to_play();
                        stringEmail = arrayList.get(0).getApp_email();

                    }catch (Exception e){
                        e.printStackTrace();


                    }



                }

                @Override
                public void onFailure(Call<SettingModel> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<SettingModel> callseting() {
        return videoService.settings();
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }


}