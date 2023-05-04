package com.mercury.redeem.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mercury.redeem.Activity.EditProfileActivity;
import com.mercury.redeem.Activity.GetCoinActivity;
import com.mercury.redeem.Activity.GetCoinTraActivity;
import com.mercury.redeem.Activity.GetOrderActivity;
import com.mercury.redeem.Activity.IMPFeedbackActivity;
import com.mercury.redeem.Activity.LoginActivity;
import com.mercury.redeem.Activity.NoInternetActivity;
import com.mercury.redeem.Activity.VisitWebsiteActivity;
import com.mercury.redeem.Activity.WalletPassbookActivity;
import com.mercury.redeem.Activity.YourBidsActivity;
import com.mercury.redeem.Adapter.CoinAdapter;
import com.mercury.redeem.Adapter.GetOrderAdapter;
import com.mercury.redeem.Modelclas.GetCoin;
import com.mercury.redeem.Modelclas.GetOrderUser;
import com.mercury.redeem.Modelclas.UserProfile;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;
import com.mercury.redeem.SavePref;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;

import static com.mercury.redeem.Constants.imageurl;

public class YouFragment extends Fragment {


    public BindingService videoService;
    RecyclerView recyclerYoufrag,recyclerView;
    TextView txtTran;
    TextView txtGetCoin, txtGetCoinHis;
    LinearLayout lvlYoufrag;
    TextView txtSetName, txtShare, txtProfile, txtCoinlist,txtWallet, txtLogout, txtPurchase, txtBid;
    SavePref savePref;
    String code;
    CircleImageView imageProfile;
    TextView txtAbout, txtCond, txtPrivacy, txtContact, txtHowto,txtnotickets;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_you, container, false);
        savePref = new SavePref(getContext());
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        lvlYoufrag = view.findViewById(R.id.linearlay);
        imageProfile = view.findViewById(R.id.imageProfile);
        txtProfile = view.findViewById(R.id.txtProfile);
        txtWallet = view.findViewById(R.id.tv_wallet);
        txtBid = view.findViewById(R.id.txtBid);
        recyclerYoufrag = view.findViewById(R.id.recycler);
        txtLogout = view.findViewById(R.id.txtLogout);
        txtPurchase = view.findViewById(R.id.txtPurchase);
        txtHowto = view.findViewById(R.id.txtHowto);
        txtTran = view.findViewById(R.id.text_tran);
        txtAbout = view.findViewById(R.id.text_about);
        txtCond = view.findViewById(R.id.txtCond);
        txtPrivacy = view.findViewById(R.id.txtPrivacy);
        txtContact = view.findViewById(R.id.txtContact);
        txtSetName = view.findViewById(R.id.txtSetName);
        txtShare = view.findViewById(R.id.txtShare);
        txtGetCoin = view.findViewById(R.id.txtGetCoin);
        txtGetCoinHis = view.findViewById(R.id.txtGetCoinHis);
        txtCoinlist = view.findViewById(R.id.txtCoinlist);
        recyclerView = view.findViewById(R.id.recyclerviwq);
        txtnotickets=view.findViewById(R.id.txtnotickets);
        recyclerYoufrag.setLayoutManager(new LinearLayoutManager(getContext()));

        isNetworkConnected();
        txtAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), VisitWebsiteActivity.class);
                // TODO: You can change About Us Link below  ;)
                i.putExtra("url", "https://biddwinn.co.in/About-Us.html");
                i.putExtra("name", "About Us");
                startActivity(i);

            }
        });
        txtCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), IMPFeedbackActivity.class);
                i.putExtra("check", "1");
                startActivity(i);
            }
        });

        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), IMPFeedbackActivity.class);
                i.putExtra("check", "1");
                startActivity(i);
            }
        });
        // TODO: You can change Contact us Whatsapp number below ;)
        txtContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setPackage("com.whatsapp").setData(Uri.parse("https://Wa.me/919506033105"));

                startActivity(intent);
            }
        });
        // TODO: You can change the How it Works Link below  ;)
        txtHowto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://biddwinn.co.in/How-to-play.html"));
                startActivity(intent);
            }
        });

        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePref.setUserPhone("");
                savePref.setUserId("0");
                savePref.setemail("");
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();

            }
        });

        txtGetCoinHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GetCoinTraActivity.class);
                startActivity(i);

            }
        });


        txtBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), YourBidsActivity.class);
                startActivity(i);

            }
        });


        txtProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditProfileActivity.class);
                getContext().startActivity(i);
            }
        });

        txtCoinlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GetCoinActivity.class);
                getContext().startActivity(i);

            }
        });

        txtTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), WalletPassbookActivity.class);
                getContext().startActivity(i);

            }
        });

        txtPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GetOrderActivity.class);
                getContext().startActivity(i);

            }
        });


        txtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
                    // TODO: You can change the share app text message from below ;)
                    String sAux = "Invite your friends and share code  " + code + "\n\n";
                    sAux = sAux + Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getApplicationContext().getPackageName());
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "Choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        return view;

    }

    public void getprofile() {
        lvlYoufrag.setVisibility(View.VISIBLE);
        try {
            callgetApi().enqueue(new Callback<UserProfile>() {
                @Override
                public void onResponse(Call<UserProfile> call, retrofit2.Response<UserProfile> response) {

                    try {

                        lvlYoufrag.setVisibility(View.GONE);
                        ArrayList<UserProfile.User_profile_Inner> arrayList = response.body().getJSON_DATA();
                        txtGetCoin.setText(arrayList.get(0).getWallet() + " Coins Avaliable");

                        code = arrayList.get(0).getCode();
                        txtSetName.setText(arrayList.get(0).getName());
                        savePref.setUserPhone(arrayList.get(0).getPhone());
                        savePref.setemail(arrayList.get(0).getEmail());
                        if (arrayList.get(0).getImage().equalsIgnoreCase("")) {

                            try {

                                Glide.with(getContext())
                                        .load(R.drawable.img_user)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .fitCenter()
                                        .into(imageProfile);
                            }catch (Exception e){
                                e.printStackTrace();

                            }

                        } else {
                            try {
                                Glide.with(getContext())
                                        .load(imageurl + arrayList.get(0).getImage())
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .fitCenter()
                                        .into(imageProfile);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlYoufrag.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<UserProfile> call, Throwable t) {
                    lvlYoufrag.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Call<UserProfile> callgetApi() {
        return videoService.getUserProfile(savePref.getUserId());
    }

    public void getcoinapi() {
        lvlYoufrag.setVisibility(View.VISIBLE);
        try {
            callcoinApi().enqueue(new Callback<GetCoin>() {
                @Override
                public void onResponse(Call<GetCoin> call, retrofit2.Response<GetCoin> response) {

                    try {
                        lvlYoufrag.setVisibility(View.GONE);
                        ArrayList<GetCoin.Get_Coin_Inner> arrayList = response.body().getJSON_DATA();
                        recyclerYoufrag.setAdapter(new CoinAdapter(getContext(), arrayList));
                    }catch (Exception e){
                        e.printStackTrace();
                        lvlYoufrag.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<GetCoin> call, Throwable t) {
                    lvlYoufrag.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private Call<GetCoin> callcoinApi() {
        return videoService.get_coin_list();
    }

    public void getordersapi() {
        //lvlGetOrder.setVisibility(View.VISIBLE);
        try {
            callcoinApii().enqueue(new Callback<GetOrderUser>() {
                @Override
                public void onResponse(Call<GetOrderUser> call, retrofit2.Response<GetOrderUser> response) {

                    try {
                        // lvlGetOrder.setVisibility(View.GONE);
                        ArrayList<GetOrderUser.Get_order_user_Inner> arrayList = response.body().getJSON_DATA();

                        if(arrayList.size()>0)
                        {
                            recyclerView.setAdapter(new GetOrderAdapter(getContext(), arrayList));
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setVisibility(View.VISIBLE);
                            txtnotickets.setVisibility(View.GONE);
                        }
                        else
                        {
                            recyclerView.setVisibility(View.GONE);
                            txtnotickets.setVisibility(View.VISIBLE);
                        }
                        Log.e("O",arrayList+"");
                    }catch (Exception e){
                        e.printStackTrace();
                        // lvlGetOrder.setVisibility(View.GONE);


                    }


                }

                @Override
                public void onFailure(Call<GetOrderUser> call, Throwable t) {
                    //lvlGetOrder.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<GetOrderUser> callcoinApii() {
        return videoService.get_order_user(savePref.getUserId());
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            //nointernetlayout.setVisibility(View.GONE);
            getprofile();

            getcoinapi();
            getordersapi();
        }
        else
        {
            // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getContext(), NoInternetActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
