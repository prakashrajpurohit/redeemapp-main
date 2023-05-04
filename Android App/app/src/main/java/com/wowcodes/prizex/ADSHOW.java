package com.mercury.redeem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.mercury.redeem.Activity.MainActivity;
import com.mercury.redeem.Modelclas.GetCoin;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class ADSHOW extends AppCompatActivity {
     RewardedVideoAd rewardedVideoAd;
    private RewardedAd mRewardedAd;
    public BindingService videoService;
    SavePref savePref;

    String getWallet,packageId,oId;
    ArrayList<GetCoin.Get_Coin_Inner> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_s_h_o_w);
        savePref=new SavePref(ADSHOW.this);
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
      //  listmethode();

        getWallet ="50";
        packageId ="second";
        oId="adsbenifite";
        Button admob=findViewById(R.id.adreward_bttn);
        Button facebook =findViewById(R.id.fbrewardbtn);

        admob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showadmobreward();
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showfacebook();
            }
        });


    }
    private Call<GetCoin> callcoinApi() {
        return videoService.get_coin_list();
    }
    private void listmethode()
    {
        try {
            callcoinApi().enqueue(new Callback<GetCoin>() {
                @Override
                public void onResponse(Call<GetCoin> call, retrofit2.Response<GetCoin> response) {

                    try {
                       // lvlYoufrag.setVisibility(View.GONE);
                        arrayList = response.body().getJSON_DATA();
                     //   recyclerYoufrag.setAdapter(new CoinAdapter(getContext(), arrayList));
                    }catch (Exception e){
                        e.printStackTrace();
                       // lvlYoufrag.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<GetCoin> call, Throwable t) {
                   // lvlYoufrag.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showfacebook()
    {
        AudienceNetworkAds.initialize(this);
        //banner       1372463509617191_1372463572950518
        // rewardedd    1372463509617191_1644534835743389
        rewardedVideoAd = new RewardedVideoAd(this, "YOUR_PLACEMENT_ID");
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {
            @Override
            public void onError(Ad ad, AdError error) {
                // Rewarded video ad failed to load
                //Toast.makeText(ADSHOW.this, ""+ad, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Rewarded video ad is loaded and ready to be displayed
                Toast.makeText(ADSHOW.this, "loaded", Toast.LENGTH_SHORT).show();
                rewardedVideoAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Rewarded video ad clicked
                // Log.d(TAG, "Rewarded video ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onRewardedVideoCompleted() {
                // Rewarded Video View Complete - the video has been played to the end.
                // You can use this event to initialize your reward
                //  Log.d(TAG, "Rewarded video completed!");

                // Call method to give reward
                // giveReward();
                postUserwalletUpdate();
            }

            @Override
            public void onRewardedVideoClosed() {
                // The Rewarded Video ad was closed - this can occur during the video
                // by closing the app, or closing the end card.
                //   Log.d(TAG, "Rewarded video ad closed!");
            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());


    }

    private void showadmobreward()
    {
        AdRequest vadRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                vadRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Toast.makeText(ADSHOW.this, ""+loadAdError, Toast.LENGTH_SHORT).show();
                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        Toast.makeText(ADSHOW.this, "rloaded", Toast.LENGTH_SHORT).show();
                        mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                // Log.d(TAG, "Ad was shown.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                                // Called when ad fails to show.
                                //Log.d(TAG, "Ad failed to show.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                // Log.d(TAG, "Ad was dismissed.");
                                mRewardedAd = null;
                            }
                        });
                        Activity activityContext = ADSHOW.this;
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                //  Log.d(TAG, "The user earned the reward.");
                                int rewardAmount = rewardItem.getAmount();
                                String rewardType = rewardItem.getType();
                                postUserwalletUpdate();
                            }
                        });
                    }
                });

        if (mRewardedAd != null) {
            Activity activityContext = ADSHOW.this;
            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                    // Handle the reward.
                    //  Log.d(TAG, "The user earned the reward.");
                    int rewardAmount = rewardItem.getAmount();
                    String rewardType = rewardItem.getType();
                    postUserwalletUpdate();
                }
            });
        } else {
            Toast.makeText(this, "not loaded", Toast.LENGTH_SHORT).show();
        }

    }
    public void postUserwalletUpdate() {

        try {
            calladdbidApi().enqueue(new Callback<SuccessModel>() {
                @Override
                public void onResponse(Call<SuccessModel> call, retrofit2.Response<SuccessModel> response) {

                    try {

                        ArrayList<SuccessModel.Suc_Model_Inner> arrayList = response.body().getJSON_DATA();
                        Toast.makeText(ADSHOW.this, ""+arrayList.get(0).getMsg(), Toast.LENGTH_SHORT).show();
                        if (arrayList.get(0).getSuccess().equalsIgnoreCase("1")){
                            Intent i=new Intent(ADSHOW.this, MainActivity.class);
                            startActivity(i);
                        }else{

                        }

                    }catch (Exception e){
                        e.printStackTrace();


                    }


                }

                @Override
                public void onFailure(Call<SuccessModel> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<SuccessModel> calladdbidApi() {


        return videoService.postUserwalletUpdate(savePref.getUserId(), getWallet, packageId, oId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ADSHOW.this, MainActivity.class);
        startActivity(i);
    }
}