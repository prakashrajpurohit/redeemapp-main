package com.mercury.redeem.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.mercury.redeem.Activity.NoInternetActivity;
import com.mercury.redeem.Adapter.ItemAdapter;
import com.mercury.redeem.Modelclas.GetCategories;
import com.mercury.redeem.R;
import com.mercury.redeem.RetrofitUtils.BindingService;
import com.mercury.redeem.RetrofitUtils.RetrofitVideoApiBaseUrl;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment  {



    String TAG="TAG";

    RecyclerView recyclerViewLive,recyclerViewUpcoming,recyclerViewWinners,recyclerViewFour;
    RecyclerView recyclerViewFive,recyclerViewSix,recyclerViewSeven,recyclerViewEight,recyclerViewNine,recyclerViewTen;
    RecyclerView recyclerViewEleven,recyclerViewTwelve,recyclerViewthirteen,recyclerViewfourteen,recyclerViewfifteen;
    RecyclerView recyclerViewsixteen,recyclerViewseventeen,recyclerVieweighteen,recyclerViewnineteen,recyclerViewtwenty;
    RecyclerView recyclerViewTwentyOne,recyclerViewTwentyTwo,recyclerViewTwentyThree,recyclerViewTwentyFour,recyclerViewTwentyFive;
    RecyclerView recyclerViewTwentySix,recyclerViewTwentySeven,recyclerViewTwentyEight,recyclerViewTwentyNine,recyclerViewThirty;
    RecyclerView recyclerViewThirtyOne,recyclerViewThirtyTwo,recyclerViewThirtyThree,recyclerViewThirtyFour,recyclerViewThirtyFive;
    RecyclerView recyclerViewThirtySix,recyclerViewThirtySeven,recyclerViewThirtyEight,recyclerViewThirtyNine,recyclerViewFourty;
    RecyclerView recyclerViewFourtyOne,recyclerViewFourtyTwo,recyclerViewFourtyThree,recyclerViewFourtyFour,recyclerViewFourtyFive;
    RecyclerView recyclerViewFourtySix,recyclerViewFourtySeven,recyclerViewFourtyEight,recyclerViewFourtyNine,recyclerViewFifty;
    public BindingService videoService;
    TextView txtCategoryOne,txtCategoryTwo,txtCategoryThree,txtCatFour;
    TextView txtCatFive,txtCatSix,txtCatSeven,txtCatEight,txtCatNine,txtCatTen;
    TextView txtCatEleve,txtCatTwelve,txtCatThirteen,txtCatFourteen,txtCatFifteen;
    TextView txtCatSixteen,txtCatSeventeen,txtCatEighteen,txtCatNineteen,txtCatTwenty;
    TextView txtCatTwentyOne,txtCatTwentyTwo,txtCatTwentyThree,txtCatTwentyFour,txtCatTwentyFive;
    TextView txtCatTwentySix,txtCatTwentySeven,txtCatTwentyEight,txtCatTwentyNine,txtCatThirty;
    TextView txtCatThirtyOne,txtCatThirtyTwo,txtCatThirtyThree,txtCatThirtyFour,txtCatThirtyFive;
    TextView txtCatThirtySix,txtCatThirtySeven,txtCatThirtyEight,txtCatThirtyNine,txtCatFourty;
    TextView txtCatFourtyOne,txtCatFourtyTwo,txtCatFourtyThree,txtCatFourtyFour,txtCatFourtyFive;
    TextView txtCatFourtySix,txtCatFourtySeven,txtCatFourtyEight,txtCatFourtyNine,txtCatFifty;
    TextView txtCdescOne,txtCdescTwo,txtCdescThree,txtCDescFour,txtCDescFive;
    TextView txtCDescSix,txtCDescSeven,txtCDescEight,txtCDescNine,txtCDescTen;
    TextView txtCDescEleven,txtCDescTwelve,txtCDescThirteen,txtCDescFourteen,txtCDescFifteen;
    TextView txtCDescSixteen,txtCDescSeventeen,txtCDescEighteen,txtCDescNineteen,txtCDescTwenty;
    TextView txtCDescTwentyOne,txtCDescTwentyTwo,txtCDescTwentyThree,txtCDescTwentyFour,txtCDescTwentyFive;
    TextView txtCDescTwentySix,txtCDescTwentySeven,txtCDescTwentyEight,txtCDescTwentyNine,txtCDescThirty;
    TextView txtCDescThirtyOne,txtCDescThirtyTwo,txtCDescThirtyThree,txtCDescThirtyFour,txtCDescThirtyFive;
    TextView txtCDescThirtySix,txtCDescThirtySeven,txtCDescThirtyEight,txtCDescThirtyNine,txtCDescFourty;
    TextView txtCDescFourtyOne,txtCDescFourtyTwo,txtCDescFourtyThree,txtCDescFourtyFour,txtCDescFourtyFive;
    TextView txtCDescFourtySix,txtCDescFourtySeven,txtCDescFourtyEight,txtCDescFourtyNine,txtCDescFifty;
    ItemAdapter.ItemClickListener itemClickListener;
    String cid;
    ItemAdapter itemAdapter;
    List<GetCategories.JSONDATum> catone;
    List<GetCategories.JSONDATum> cattwo;
    List<GetCategories.JSONDATum> catthree;
    List<GetCategories.JSONDATum> catfour;
    List<GetCategories.JSONDATum> catfive;
    List<GetCategories.JSONDATum> catsix;
    List<GetCategories.JSONDATum> catseven;
    List<GetCategories.JSONDATum> cateight;
    List<GetCategories.JSONDATum> catnine;
    List<GetCategories.JSONDATum> catten;
    List<GetCategories.JSONDATum> cateleven;
    List<GetCategories.JSONDATum> cattwelve;
    List<GetCategories.JSONDATum> catthirteen;
    List<GetCategories.JSONDATum> catfourteen;
    List<GetCategories.JSONDATum> catfifteen;
    List<GetCategories.JSONDATum> catsixteen;
    List<GetCategories.JSONDATum> catseventeen;
    List<GetCategories.JSONDATum> cateighteen;
    List<GetCategories.JSONDATum> catnineteen;
    List<GetCategories.JSONDATum> cattwenty;

    List<GetCategories.JSONDATum> cattwentyone;
    List<GetCategories.JSONDATum> cattwentytwo;
    List<GetCategories.JSONDATum> cattwentythree;
    List<GetCategories.JSONDATum> cattwentyfour;
    List<GetCategories.JSONDATum> cattwentyfive;
    List<GetCategories.JSONDATum> cattwentysix;
    List<GetCategories.JSONDATum> cattwentyseven;
    List<GetCategories.JSONDATum> cattwentyeight;
    List<GetCategories.JSONDATum> cattwentynine;
    List<GetCategories.JSONDATum> catthirty;
    List<GetCategories.JSONDATum> catthirtyone;
    List<GetCategories.JSONDATum> catthirtytwo;
    List<GetCategories.JSONDATum> catthirtythree;
    List<GetCategories.JSONDATum> catthirtyfour;
    List<GetCategories.JSONDATum> catthirtyfive;
    List<GetCategories.JSONDATum> catthirtysix;
    List<GetCategories.JSONDATum> catthirtyseven;
    List<GetCategories.JSONDATum> catthirtyeight;
    List<GetCategories.JSONDATum> catthirtynine;
    List<GetCategories.JSONDATum> catfourty;
    List<GetCategories.JSONDATum> catfourtyone;
    List<GetCategories.JSONDATum> catfourtytwo;
    List<GetCategories.JSONDATum> catfourtythree;
    List<GetCategories.JSONDATum> catfourtyfour;
    List<GetCategories.JSONDATum> catfourtyfive;
    List<GetCategories.JSONDATum> catfourtysix;
    List<GetCategories.JSONDATum> catfourtyseven;
    List<GetCategories.JSONDATum> catfourtyeight;
    List<GetCategories.JSONDATum> catfourtynine;
    List<GetCategories.JSONDATum> catfifty;

    List<GetCategories.JSONDATum> arrayList=new ArrayList<>();
    ImageView imgOne,imgTwo,imgThree,imgFour;
    ImageView imgFive,imgSix,imgSeven,imgEight,imgNine,imgTen;
    ImageView imgEleven,imgTwelve,imgThirteen,imgFourteen,imgFifteen,imgSixteen;
    ImageView imgSeventeen,imgEighteen,imgNineteen,imgTwenty;
    ImageView imgTwentyOne,imgTwentyTwo,imgTwentyThree,imgTwentyFour,imgTwentyFive;
    ImageView imgTwentySix,imgTwentySeven,imgTwentyEight,imgTwentyNine,imgThirty;
    ImageView imgThirtyOne,imgThirtyTwo,imgThirtyThree,imgThirtyFour,imgThirtyFive;
    ImageView imgThirtySix,imgThirtySeven,imgThirtyEight,imgThirtyNine,imgFourty;
    ImageView imgFourtyOne,imgFourtyTwo,imgFourtyThree,imgFourtyFour,imgFourtyFive;
    ImageView imgFourtySix,imgFourtySeven,imgFourtyEight,imgFourtyNine,imgFifty;
    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4;

    LinearLayout linearLayout5,linearLayout6,linearLayout7,linearLayout8,linearLayout9,linearLayout10;

    LinearLayout linearLayout11,linearLayout12,linearLayout13,linearLayout14,linearLayout15,linearLayout16;
    LinearLayout linearLayout17,linearLayout18,linearLayout19,linearLayout20;
    LinearLayout linearLayout21,linearLayout22,linearLayout23,linearLayout24,linearLayout25,linearLayout26;
    LinearLayout linearLayout27,linearLayout28,linearLayout29,linearLayout30;
    LinearLayout linearLayout31,linearLayout32,linearLayout33,linearLayout34,linearLayout35,linearLayout36;
    LinearLayout linearLayout37,linearLayout38,linearLayout39,linearLayout40;
    LinearLayout linearLayout41,linearLayout42,linearLayout43,linearLayout44,linearLayout45,linearLayout46;
    LinearLayout linearLayout47,linearLayout48,linearLayout49,linearLayout50;
    private SwipeRefreshLayout swipeRefreshLayout;
    ColorDrawable colorDrawable;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerViewLive=(RecyclerView)view.findViewById(R.id.recyclerviewlive);

        recyclerViewUpcoming=(RecyclerView)view.findViewById(R.id.recyclerviewupcoming);
        recyclerViewWinners=(RecyclerView)view.findViewById(R.id.recyclerviewwinners);
        recyclerViewFour=(RecyclerView)view.findViewById(R.id.recyclerviewFour);
        recyclerViewFive=(RecyclerView)view.findViewById(R.id.recyclerviewFive);
        recyclerViewSix=(RecyclerView)view.findViewById(R.id.recyclerviewSix);
        recyclerViewSeven=(RecyclerView)view.findViewById(R.id.recyclerviewSeven);
        recyclerViewEight=(RecyclerView)view.findViewById(R.id.recyclerviewEight);
        recyclerViewNine=(RecyclerView)view.findViewById(R.id.recyclerviewNine);
        recyclerViewTen=(RecyclerView)view.findViewById(R.id.recyclerviewTen);

        recyclerViewEleven=(RecyclerView)view.findViewById(R.id.recyclerviewEleven);
        recyclerViewTwelve=(RecyclerView)view.findViewById(R.id.recyclerviewTwelve);
        recyclerViewthirteen=(RecyclerView)view.findViewById(R.id.recyclerviewThirteen);
        recyclerViewfourteen=(RecyclerView)view.findViewById(R.id.recyclerviewFourteen);
        recyclerViewfifteen=(RecyclerView)view.findViewById(R.id.recyclerviewFifteen);
        recyclerViewsixteen=(RecyclerView)view.findViewById(R.id.recyclerviewSixteen);
        recyclerViewseventeen=(RecyclerView)view.findViewById(R.id.recyclerviewSeventeen);
        recyclerVieweighteen=(RecyclerView)view.findViewById(R.id.recyclerviewEighteen);
        recyclerViewnineteen=(RecyclerView)view.findViewById(R.id.recyclerviewNineteen);
        recyclerViewtwenty=(RecyclerView)view.findViewById(R.id.recyclerviewTwenty);

        recyclerViewTwentyOne=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyOne);
        recyclerViewTwentyTwo=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyTwo);
        recyclerViewTwentyThree=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyThree);
        recyclerViewTwentyFour=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyFour);
        recyclerViewTwentyFive=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyFive);
        recyclerViewTwentySix=(RecyclerView)view.findViewById(R.id.recyclerviewTwentySix);
        recyclerViewTwentySeven=(RecyclerView)view.findViewById(R.id.recyclerviewTwentySeven);
        recyclerViewTwentyEight=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyEight);
        recyclerViewTwentyNine=(RecyclerView)view.findViewById(R.id.recyclerviewTwentyNine);
        recyclerViewThirty=(RecyclerView)view.findViewById(R.id.recyclerviewThirty);


        recyclerViewThirtyOne=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyOne);
        recyclerViewThirtyTwo=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyTwo);
        recyclerViewThirtyThree=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyThree);
        recyclerViewThirtyFour=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyFour);
        recyclerViewThirtyFive=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyFive);
        recyclerViewThirtySix=(RecyclerView)view.findViewById(R.id.recyclerviewThirtySix);
        recyclerViewThirtySeven=(RecyclerView)view.findViewById(R.id.recyclerviewThirtySeven);
        recyclerViewThirtyEight=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyEight);
        recyclerViewThirtyNine=(RecyclerView)view.findViewById(R.id.recyclerviewThirtyNine);
        recyclerViewFourty=(RecyclerView)view.findViewById(R.id.recyclerviewFourty);


        recyclerViewFourtyOne=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyOne);
        recyclerViewFourtyTwo=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyTwo);
        recyclerViewFourtyThree=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyThree);
        recyclerViewFourtyFour=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyFour);
        recyclerViewFourtyFive=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyFive);
        recyclerViewFourtySix=(RecyclerView)view.findViewById(R.id.recyclerviewFourtySix);
        recyclerViewFourtySeven=(RecyclerView)view.findViewById(R.id.recyclerviewFourtySeven);
        recyclerViewFourtyEight=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyEight);
        recyclerViewFourtyNine=(RecyclerView)view.findViewById(R.id.recyclerviewFourtyNine);
        recyclerViewFifty=(RecyclerView)view.findViewById(R.id.recyclerviewFifty);
        
        txtCategoryOne=(TextView)view.findViewById(R.id.txtCatOne);
        txtCategoryTwo=(TextView)view.findViewById(R.id.txtCatTwo);
        txtCategoryThree=(TextView)view.findViewById(R.id.txtCatThree);
        txtCatFour=(TextView)view.findViewById(R.id.txtCatFour);
        txtCatFive=(TextView)view.findViewById(R.id.txtCatFive);
        txtCatSix=(TextView)view.findViewById(R.id.txtCatSix);
        txtCatSeven=(TextView)view.findViewById(R.id.txtCatSeven);
        txtCatEight=(TextView)view.findViewById(R.id.txtCatEight);
        txtCatNine=(TextView)view.findViewById(R.id.txtCatNine);
        txtCatTen=(TextView)view.findViewById(R.id.txtCatTen);

        txtCatEleve=(TextView)view.findViewById(R.id.txtCatEleven);
        txtCatTwelve=(TextView)view.findViewById(R.id.txtCatTwelve);
        txtCatThirteen=(TextView)view.findViewById(R.id.txtCatThirteen);
        txtCatFourteen=(TextView)view.findViewById(R.id.txtCatFourteen);
        txtCatFifteen=(TextView)view.findViewById(R.id.txtCatFifteen);
        txtCatSixteen=(TextView)view.findViewById(R.id.txtCatSixteen);
        txtCatSeventeen=(TextView)view.findViewById(R.id.txtCatSeventeen);
        txtCatEighteen=(TextView)view.findViewById(R.id.txtCatEighteen);
        txtCatNineteen=(TextView)view.findViewById(R.id.txtCatNineteen);
        txtCatTwenty=(TextView)view.findViewById(R.id.txtCatTwenty);


        txtCatTwentyOne=(TextView)view.findViewById(R.id.txtCatTwentyOne);
        txtCatTwentyTwo=(TextView)view.findViewById(R.id.txtCatTwentyTwo);
        txtCatTwentyThree=(TextView)view.findViewById(R.id.txtCatTwentyThree);
        txtCatTwentyFour=(TextView)view.findViewById(R.id.txtCatTwentyFour);
        txtCatTwentyFive=(TextView)view.findViewById(R.id.txtCatTwentyFive);
        txtCatTwentySix=(TextView)view.findViewById(R.id.txtCatTwentySix);
        txtCatTwentySeven=(TextView)view.findViewById(R.id.txtCatTwentySeven);
        txtCatTwentyEight=(TextView)view.findViewById(R.id.txtCatTwentyEight);
        txtCatTwentyNine=(TextView)view.findViewById(R.id.txtCatTwentyNine);
        txtCatThirty=(TextView)view.findViewById(R.id.txtCatThirty);

        txtCatThirtyOne=(TextView)view.findViewById(R.id.txtCatThirtyOne);
        txtCatThirtyTwo=(TextView)view.findViewById(R.id.txtCatThirtyTwo);
        txtCatThirtyThree=(TextView)view.findViewById(R.id.txtCatThirtyThree);
        txtCatThirtyFour=(TextView)view.findViewById(R.id.txtCatThirtyFour);
        txtCatThirtyFive=(TextView)view.findViewById(R.id.txtCatThirtyFive);
        txtCatThirtySix=(TextView)view.findViewById(R.id.txtCatThirtySix);
        txtCatThirtySeven=(TextView)view.findViewById(R.id.txtCatThirtySeven);
        txtCatThirtyEight=(TextView)view.findViewById(R.id.txtCatThirtyEight);
        txtCatThirtyNine=(TextView)view.findViewById(R.id.txtCatThirtyNine);
        txtCatFourty=(TextView)view.findViewById(R.id.txtCatFourty);

        txtCatFourtyOne=(TextView)view.findViewById(R.id.txtCatFourtyOne);
        txtCatFourtyTwo=(TextView)view.findViewById(R.id.txtCatFourtyTwo);
        txtCatFourtyThree=(TextView)view.findViewById(R.id.txtCatFourtyThree);
        txtCatFourtyFour=(TextView)view.findViewById(R.id.txtCatFourtyFour);
        txtCatFourtyFive=(TextView)view.findViewById(R.id.txtCatFourtyFive);
        txtCatFourtySix=(TextView)view.findViewById(R.id.txtCatFourtySix);
        txtCatFourtySeven=(TextView)view.findViewById(R.id.txtCatFourtySeven);
        txtCatFourtyEight=(TextView)view.findViewById(R.id.txtCatFourtyEight);
        txtCatFourtyNine=(TextView)view.findViewById(R.id.txtCatFourtyNine);
        txtCatFifty=(TextView)view.findViewById(R.id.txtCatFifty);

        imgOne=(ImageView)view.findViewById(R.id.imgOne);
        imgTwo=(ImageView)view.findViewById(R.id.imgTwo);
        imgThree=(ImageView)view.findViewById(R.id.imgThree);
        imgFour=(ImageView)view.findViewById(R.id.imgFour);
        imgFive=(ImageView)view.findViewById(R.id.imgFive);
        imgSix=(ImageView)view.findViewById(R.id.imgSix);
        imgSeven=(ImageView)view.findViewById(R.id.imgSeven);
        imgEight=(ImageView)view.findViewById(R.id.imgEight);
        imgNine=(ImageView)view.findViewById(R.id.imgNine);
        imgTen=(ImageView)view.findViewById(R.id.imgTen);

        imgEleven=(ImageView)view.findViewById(R.id.imgEleven);
        imgTwelve=(ImageView)view.findViewById(R.id.imgTwelve);
        imgThirteen=(ImageView)view.findViewById(R.id.imgThirteen);
        imgFourteen=(ImageView)view.findViewById(R.id.imgFourteen);
        imgFifteen=(ImageView)view.findViewById(R.id.imgFifteen);
        imgSixteen=(ImageView)view.findViewById(R.id.imgSixteen);
        imgSeventeen=(ImageView)view.findViewById(R.id.imgSeventeen);
        imgEighteen=(ImageView)view.findViewById(R.id.imgEighteen);
        imgNineteen=(ImageView)view.findViewById(R.id.imgNineteen);
        imgTwenty=(ImageView)view.findViewById(R.id.imgTwenty);

        imgTwentyOne=(ImageView)view.findViewById(R.id.imgTwentyOne);
        imgTwentyTwo=(ImageView)view.findViewById(R.id.imgTwentyTwo);
        imgTwentyThree=(ImageView)view.findViewById(R.id.imgTwentyThree);
        imgTwentyFour=(ImageView)view.findViewById(R.id.imgTwentyFour);
        imgTwentyFive=(ImageView)view.findViewById(R.id.imgTwentyFive);
        imgTwentySix=(ImageView)view.findViewById(R.id.imgTwentySix);
        imgTwentySeven=(ImageView)view.findViewById(R.id.imgTwentySeven);
        imgTwentyEight=(ImageView)view.findViewById(R.id.imgTwentyEight);
        imgTwentyNine=(ImageView)view.findViewById(R.id.imgTwentyNine);
        imgThirty=(ImageView)view.findViewById(R.id.imgThirty);

        imgThirtyOne=(ImageView)view.findViewById(R.id.imgThirtyOne);
        imgThirtyTwo=(ImageView)view.findViewById(R.id.imgThirtyTwo);
        imgThirtyThree=(ImageView)view.findViewById(R.id.imgThirtyThree);
        imgThirtyFour=(ImageView)view.findViewById(R.id.imgThirtyFour);
        imgThirtyFive=(ImageView)view.findViewById(R.id.imgThirtyFive);
        imgThirtySix=(ImageView)view.findViewById(R.id.imgThirtySix);
        imgThirtySeven=(ImageView)view.findViewById(R.id.imgThirtySeven);
        imgThirtyEight=(ImageView)view.findViewById(R.id.imgThirtyEigth);
        imgThirtyNine=(ImageView)view.findViewById(R.id.imgThirtyNine);
        imgFourty=(ImageView)view.findViewById(R.id.imgFourty);


        imgFourtyOne=(ImageView)view.findViewById(R.id.imgFourtyOne);
        imgFourtyTwo=(ImageView)view.findViewById(R.id.imgFourtyTwo);
        imgFourtyThree=(ImageView)view.findViewById(R.id.imgFourtyThree);
        imgFourtyFour=(ImageView)view.findViewById(R.id.imgFourtyFour);
        imgFourtyFive=(ImageView)view.findViewById(R.id.imgFourtyFive);
        imgFourtySix=(ImageView)view.findViewById(R.id.imgFourtySix);
        imgFourtySeven=(ImageView)view.findViewById(R.id.imgFourtySeven);
        imgFourtyEight=(ImageView)view.findViewById(R.id.imgFourtyEight);
        imgFourtyNine=(ImageView)view.findViewById(R.id.imgFourtyNine);
        imgFifty=(ImageView)view.findViewById(R.id.imgFifty);

        linearLayout1=(LinearLayout)view.findViewById(R.id.linear1);
        linearLayout2=(LinearLayout)view.findViewById(R.id.linear2);
        linearLayout3=(LinearLayout)view.findViewById(R.id.linear3);
        linearLayout4=(LinearLayout)view.findViewById(R.id.linear4);
        linearLayout5=(LinearLayout)view.findViewById(R.id.linear5);
        linearLayout6=(LinearLayout)view.findViewById(R.id.linear6);
        linearLayout7=(LinearLayout)view.findViewById(R.id.linear7);
        linearLayout8=(LinearLayout)view.findViewById(R.id.linear8);
        linearLayout9=(LinearLayout)view.findViewById(R.id.linear9);
        linearLayout10=(LinearLayout)view.findViewById(R.id.linear10);

        linearLayout11=(LinearLayout)view.findViewById(R.id.linear11);
        linearLayout12=(LinearLayout)view.findViewById(R.id.linear12);
        linearLayout13=(LinearLayout)view.findViewById(R.id.linear13);
        linearLayout14=(LinearLayout)view.findViewById(R.id.linear14);
        linearLayout15=(LinearLayout)view.findViewById(R.id.linear15);
        linearLayout16=(LinearLayout)view.findViewById(R.id.linear16);
        linearLayout17=(LinearLayout)view.findViewById(R.id.linear17);
        linearLayout18=(LinearLayout)view.findViewById(R.id.linear18);
        linearLayout19=(LinearLayout)view.findViewById(R.id.linear19);
        linearLayout20=(LinearLayout)view.findViewById(R.id.linear20);

        linearLayout21=(LinearLayout)view.findViewById(R.id.linear21);
        linearLayout22=(LinearLayout)view.findViewById(R.id.linear22);
        linearLayout23=(LinearLayout)view.findViewById(R.id.linear23);
        linearLayout24=(LinearLayout)view.findViewById(R.id.linear24);
        linearLayout25=(LinearLayout)view.findViewById(R.id.linear25);
        linearLayout26=(LinearLayout)view.findViewById(R.id.linear26);
        linearLayout27=(LinearLayout)view.findViewById(R.id.linear27);
        linearLayout28=(LinearLayout)view.findViewById(R.id.linear28);
        linearLayout29=(LinearLayout)view.findViewById(R.id.linear29);
        linearLayout30=(LinearLayout)view.findViewById(R.id.linear30);


        linearLayout31=(LinearLayout)view.findViewById(R.id.linear31);
        linearLayout32=(LinearLayout)view.findViewById(R.id.linear32);
        linearLayout33=(LinearLayout)view.findViewById(R.id.linear33);
        linearLayout34=(LinearLayout)view.findViewById(R.id.linear34);
        linearLayout35=(LinearLayout)view.findViewById(R.id.linear35);
        linearLayout36=(LinearLayout)view.findViewById(R.id.linear36);
        linearLayout37=(LinearLayout)view.findViewById(R.id.linear37);
        linearLayout38=(LinearLayout)view.findViewById(R.id.linear38);
        linearLayout39=(LinearLayout)view.findViewById(R.id.linear39);
        linearLayout40=(LinearLayout)view.findViewById(R.id.linear40);

        linearLayout41=(LinearLayout)view.findViewById(R.id.linear41);
        linearLayout42=(LinearLayout)view.findViewById(R.id.linear42);
        linearLayout44=(LinearLayout)view.findViewById(R.id.linear44);
        linearLayout44=(LinearLayout)view.findViewById(R.id.linear44);
        linearLayout45=(LinearLayout)view.findViewById(R.id.linear45);
        linearLayout46=(LinearLayout)view.findViewById(R.id.linear46);
        linearLayout47=(LinearLayout)view.findViewById(R.id.linear47);
        linearLayout48=(LinearLayout)view.findViewById(R.id.linear48);
        linearLayout49=(LinearLayout)view.findViewById(R.id.linear49);
        linearLayout50=(LinearLayout)view.findViewById(R.id.linear50);
        
        txtCdescOne=(TextView)view.findViewById(R.id.txtCdescOne);
        txtCdescTwo=(TextView)view.findViewById(R.id.txtCdescTwo);
        txtCdescThree=(TextView)view.findViewById(R.id.txtCdescThree);
        txtCDescFour=(TextView)view.findViewById(R.id.txtCdescFour);
        txtCDescFive=(TextView)view.findViewById(R.id.txtCdescFive);
        txtCDescSix=(TextView)view.findViewById(R.id.txtCdescSix);
        txtCDescSeven=(TextView)view.findViewById(R.id.txtCdescSeven);
        txtCDescEight=(TextView)view.findViewById(R.id.txtCdescEight);
        txtCDescNine=(TextView)view.findViewById(R.id.txtCdescNine);
        txtCDescTen=(TextView)view.findViewById(R.id.txtCdescTen);

        txtCDescEleven=(TextView)view.findViewById(R.id.txtCdescEleven);
        txtCDescTwelve=(TextView)view.findViewById(R.id.txtCdescTwelve);
        txtCDescThirteen=(TextView)view.findViewById(R.id.txtCdescThirteen);
        txtCDescFourteen=(TextView)view.findViewById(R.id.txtCdescFourteen);
        txtCDescFifteen=(TextView)view.findViewById(R.id.txtCdescFifteen);
        txtCDescSixteen=(TextView)view.findViewById(R.id.txtCdescSixteen);
        txtCDescSeventeen=(TextView)view.findViewById(R.id.txtCdescSeventeen);
        txtCDescEighteen=(TextView)view.findViewById(R.id.txtCdescEighteen);
        txtCDescNineteen=(TextView)view.findViewById(R.id.txtCdescNineteen);
        txtCDescTwenty=(TextView)view.findViewById(R.id.txtCdescTwenty);

        txtCDescTwentyOne=(TextView)view.findViewById(R.id.txtCdescTwentyOne);
        txtCDescTwentyTwo=(TextView)view.findViewById(R.id.txtCdescTwentyTwo);
        txtCDescTwentyThree=(TextView)view.findViewById(R.id.txtCdescTwentyThree);
        txtCDescTwentyFour=(TextView)view.findViewById(R.id.txtCdescTwentyFour);
        txtCDescTwentyFive=(TextView)view.findViewById(R.id.txtCdescTwentyFive);
        txtCDescTwentySix=(TextView)view.findViewById(R.id.txtCdescTwentySix);
        txtCDescTwentySeven=(TextView)view.findViewById(R.id.txtCdescTwentySeven);
        txtCDescTwentyEight=(TextView)view.findViewById(R.id.txtCdescTwentyEight);
        txtCDescTwentyNine=(TextView)view.findViewById(R.id.txtCdescTwentyNine);
        txtCDescThirty=(TextView)view.findViewById(R.id.txtCdescThirty);

        txtCDescThirtyOne=(TextView)view.findViewById(R.id.txtCdescThirtyOne);
        txtCDescThirtyTwo=(TextView)view.findViewById(R.id.txtCdescThirtyTwo);
        txtCDescThirtyThree=(TextView)view.findViewById(R.id.txtCdescThirtyThree);
        txtCDescThirtyFour=(TextView)view.findViewById(R.id.txtCdescThirtyFour);
        txtCDescThirtyFive=(TextView)view.findViewById(R.id.txtCdescThirtyFive);
        txtCDescThirtySix=(TextView)view.findViewById(R.id.txtCdescThirtySix);
        txtCDescThirtySeven=(TextView)view.findViewById(R.id.txtCdescThirtySeven);
        txtCDescThirtyEight=(TextView)view.findViewById(R.id.txtCdescThirtyEight);
        txtCDescThirtyNine=(TextView)view.findViewById(R.id.txtCdescThirtyNine);
        txtCDescFourty=(TextView)view.findViewById(R.id.txtCdescFourty);
        
        
        txtCDescFourtyOne=(TextView)view.findViewById(R.id.txtCdescFourtyOne);
        txtCDescFourtyTwo=(TextView)view.findViewById(R.id.txtCdescFourtyTwo);
        txtCDescFourtyThree=(TextView)view.findViewById(R.id.txtCdescFourtyThree);
        txtCDescFourtyFour=(TextView)view.findViewById(R.id.txtCdescFourtyFour);
        txtCDescFourtyFive=(TextView)view.findViewById(R.id.txtCdescFourtyFive);
        txtCDescFourtySix=(TextView)view.findViewById(R.id.txtCdescFourtySix);
        txtCDescFourtySeven=(TextView)view.findViewById(R.id.txtCdescFourtySeven);
        txtCDescFourtyEight=(TextView)view.findViewById(R.id.txtCdescFourtyEight);
        txtCDescFourtyNine=(TextView)view.findViewById(R.id.txtCdescFourtyNine);
        txtCDescFifty=(TextView)view.findViewById(R.id.txtCdescFifty);
        
        videoService = RetrofitVideoApiBaseUrl.getClient().create(BindingService.class);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

       /* MobileAds.initialize(getContext());
        AdLoader adLoader = new AdLoader.Builder(getContext(), "ca-app-pub-3940256099942544/2247696110")
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().withMainBackgroundColor(colorDrawable).build();
                        TemplateView template = view.findViewById(R.id.my_template);
                        TemplateView template2 = view.findViewById(R.id.my_template2);
                        TemplateView template3 = view.findViewById(R.id.my_template3);
                        TemplateView template4 = view.findViewById(R.id.my_template4);
                        TemplateView template5 = view.findViewById(R.id.my_template5);
                        TemplateView template6 = view.findViewById(R.id.my_template6);
                        TemplateView template7 = view.findViewById(R.id.my_template7);
                        TemplateView template8 = view.findViewById(R.id.my_template8);
                        TemplateView template9 = view.findViewById(R.id.my_template9);

                        template.setStyles(styles);
                        template.setNativeAd(nativeAd);

                        template2.setStyles(styles);
                        template2.setNativeAd(nativeAd);

                        template3.setStyles(styles);
                        template3.setNativeAd(nativeAd);

                        template4.setStyles(styles);
                        template4.setNativeAd(nativeAd);

                        template5.setStyles(styles);
                        template5.setNativeAd(nativeAd);

                        template6.setStyles(styles);
                        template6.setNativeAd(nativeAd);

                        template7.setStyles(styles);
                        template7.setNativeAd(nativeAd);

                        template8.setStyles(styles);
                        template8.setNativeAd(nativeAd);

                        template9.setStyles(styles);
                        template9.setNativeAd(nativeAd);
                    }
                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());*/

        itemClickListener=new ItemAdapter.ItemClickListener() {
            @Override
            public void itemClick(String id,String name,String image,String cc) {
                cid=id;

                if(id.equals("1"))
                {
                    txtCategoryOne.setText(name);
                    txtCategoryOne.setTextColor(Color.parseColor("#"+cc));
                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgOne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                }
                else if(id.equals("2"))
                {
                    txtCategoryTwo.setText(name);
                    txtCategoryTwo.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("3"))
                {
                    txtCategoryThree.setText(name);
                    txtCategoryThree.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThree);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("4"))
                {
                    txtCatFour.setText(name);
                    txtCatFour.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFour);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("5"))
                {
                    txtCatFive.setText(name);
                    txtCatFive.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFive);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("6"))
                {
                    txtCatSix.setText(name);
                    txtCatSix.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgSix);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("7"))
                {
                    txtCatSeven.setText(name);
                    txtCatSeven.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgSeven);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("8"))
                {
                    txtCatEight.setText(name);
                    txtCatEight.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgEight);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("9"))
                {
                    txtCatNine.setText(name);
                    txtCatNine.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgNine);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("10"))
                {
                    txtCatTen.setText(name);
                    txtCatTen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("11"))
                {
                    txtCatEleve.setText(name);
                    txtCatEleve.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgEleven);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("12"))
                {
                    txtCatTwelve.setText(name);
                    txtCatTwelve.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwelve);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("13"))
                {
                    txtCatThirteen.setText(name);
                    txtCatThirteen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirteen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("14"))
                {
                    txtCatFourteen.setText(name);
                    txtCatFourteen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourteen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("15"))
                {
                    txtCatFifteen.setText(name);
                    txtCatFifteen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFifteen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("16"))
                {
                    txtCatSixteen.setText(name);
                    txtCatSixteen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgSixteen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("17"))
                {
                    txtCatSeventeen.setText(name);
                    txtCatSeventeen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgSeventeen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("18"))
                {
                    txtCatEighteen.setText(name);
                    txtCatEighteen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgEighteen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("19"))
                {
                    txtCatNineteen.setText(name);
                    txtCatNineteen.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgNineteen);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("20"))
                {
                    txtCatTwenty.setText(name);
                    txtCatTwenty.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwenty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("21"))
                {
                    txtCatTwentyOne.setText(name);
                    txtCatTwentyOne.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyOne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("22"))
                {
                    txtCatTwentyTwo.setText(name);
                    txtCatTwentyTwo.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyTwo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("23"))
                {
                    txtCatTwentyThree.setText(name);
                    txtCatTwentyThree.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyThree);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("24"))
                {
                    txtCatTwentyFour.setText(name);
                    txtCatTwentyFour.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyFour);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("25"))
                {
                    txtCatTwentyFive.setText(name);
                    txtCatTwentyFive.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyFive);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("26"))
                {
                    txtCatTwentySix.setText(name);
                    txtCatTwentySix.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentySix);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("27"))
                {
                    txtCatTwentySeven.setText(name);
                    txtCatTwentySeven.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentySeven);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("28"))
                {
                    txtCatTwentyEight.setText(name);
                    txtCatTwentyEight.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyEight);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("29"))
                {
                    txtCatTwentyNine.setText(name);
                    txtCatTwentyNine.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgTwentyNine);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("30"))
                {
                    txtCatThirty.setText(name);
                    txtCatThirty.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                else if(id.equals("31"))
                {
                    txtCatThirtyOne.setText(name);
                    txtCatThirtyOne.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyOne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("32"))
                {
                    txtCatThirtyTwo.setText(name);
                    txtCatThirtyTwo.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyTwo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("33"))
                {
                    txtCatThirtyThree.setText(name);
                    txtCatThirtyThree.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyThree);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("34"))
                {
                    txtCatThirtyFour.setText(name);
                    txtCatThirtyFour.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyFour);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("35"))
                {
                    txtCatThirtyFive.setText(name);
                    txtCatThirtyFive.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyFive);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("36"))
                {
                    txtCatThirtySix.setText(name);
                    txtCatThirtySix.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtySix);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("37"))
                {
                    txtCatThirtySeven.setText(name);
                    txtCatThirtySeven.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtySeven);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("38"))
                {
                    txtCatThirtyEight.setText(name);
                    txtCatThirtyEight.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyEight);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("39"))
                {
                    txtCatThirtyNine.setText(name);
                    txtCatThirtyNine.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirtyNine);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("40"))
                {
                    txtCatThirty.setText(name);
                    txtCatThirty.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgThirty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                else if(id.equals("41"))
                {
                    txtCatFourtyOne.setText(name);
                    txtCatFourtyOne.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyOne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(id.equals("42"))
                {
                    txtCatFourtyTwo.setText(name);
                    txtCatFourtyTwo.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyTwo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("43"))
                {
                    txtCatFourtyThree.setText(name);
                    txtCatFourtyThree.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyThree);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("44"))
                {
                    txtCatFourtyFour.setText(name);
                    txtCatFourtyFour.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyFour);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("45"))
                {
                    txtCatFourtyFive.setText(name);
                    txtCatFourtyFive.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyFive);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("46"))
                {
                    txtCatFourtySix.setText(name);
                    txtCatFourtySix.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtySix);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("47"))
                {
                    txtCatFourtySeven.setText(name);
                    txtCatFourtySeven.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtySeven);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("48"))
                {
                    txtCatFourtyEight.setText(name);
                    txtCatFourtyEight.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyEight);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("49"))
                {
                    txtCatFourtyNine.setText(name);
                    txtCatFourtyNine.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFourtyNine);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                else if(id.equals("50"))
                {
                    txtCatFifty.setText(name);
                    txtCatFifty.setTextColor(Color.parseColor("#"+cc));

                    try {
                        Glide.with(getContext())
                                .load(image)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .fitCenter()
                                .into(imgFifty);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                    getItems();

                    Toast.makeText(getActivity(), "Refreshed", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                    //nointernetlayout.setVisibility(View.GONE);


            }
        });


          //  getItems();




        isNetworkConnected();
        return view;
    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getActivity(). getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected())
        {
            getItems();
            //nointernetlayout.setVisibility(View.GONE);

        }
else
        {
           // nointernetlayout.setVisibility(View.VISIBLE);
            Intent intent=new Intent(getContext(), NoInternetActivity.class);
            startActivity(intent);
        }
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


    public void getItems() {
        try {
            getCategoriesCall().enqueue(new Callback<GetCategories>() {
                @Override
                public void onResponse(Call<GetCategories> call, retrofit2.Response<GetCategories> response) {

                    try {
                     arrayList = response.body().getJsonData();


                        catone=new ArrayList<>();
                        catone.clear();
                        cattwo=new ArrayList<>();
                        cattwo.clear();
                        catthree=new ArrayList<>();
                        catthree.clear();
                        catfour=new ArrayList<>();
                        catfour.clear();
                        catfive=new ArrayList<>();
                        catfive.clear();
                        catsix=new ArrayList<>();
                        catsix.clear();
                        catseven=new ArrayList<>();
                        catseven.clear();
                        cateight=new ArrayList<>();
                        cateight.clear();
                        catnine=new ArrayList<>();
                        catnine.clear();
                        catten=new ArrayList<>();
                        catten.clear();

                        cateleven=new ArrayList<>();
                        cateleven.clear();
                        cattwelve=new ArrayList<>();
                        cattwelve.clear();
                        catthirteen=new ArrayList<>();
                        catthirteen.clear();
                        catfourteen=new ArrayList<>();
                        catfourteen.clear();
                        catfifteen=new ArrayList<>();
                        catfifteen.clear();
                        catsixteen=new ArrayList<>();
                        catsixteen.clear();
                        catseventeen=new ArrayList<>();
                        catseventeen.clear();
                        cateighteen=new ArrayList<>();
                        cateighteen.clear();
                        catnineteen=new ArrayList<>();
                        catnineteen.clear();
                        cattwenty=new ArrayList<>();
                        cattwenty.clear();

                        cattwentyone=new ArrayList<>();
                        cattwentyone.clear();
                        cattwentytwo=new ArrayList<>();
                        cattwentytwo.clear();
                        cattwentythree=new ArrayList<>();
                        cattwentythree.clear();
                        cattwentyfour=new ArrayList<>();
                        cattwentyfour.clear();
                        cattwentyfive=new ArrayList<>();
                        cattwentyfive.clear();
                        cattwentysix=new ArrayList<>();
                        cattwentysix.clear();
                        cattwentyseven=new ArrayList<>();
                        cattwentyseven.clear();
                        cattwentyeight=new ArrayList<>();
                        cattwentyeight.clear();
                        cattwentynine=new ArrayList<>();
                        cattwentynine.clear();
                        catthirty=new ArrayList<>();
                        catthirty.clear();

                        catthirtyone=new ArrayList<>();
                        catthirtyone.clear();
                        catthirtytwo=new ArrayList<>();
                        catthirtytwo.clear();
                        catthirtythree=new ArrayList<>();
                        catthirtythree.clear();
                        catthirtyfour=new ArrayList<>();
                        catthirtyfour.clear();
                        catthirtyfive=new ArrayList<>();
                        catthirtyfive.clear();
                        catthirtysix=new ArrayList<>();
                        catthirtysix.clear();
                        catthirtyseven=new ArrayList<>();
                        catthirtyseven.clear();
                        catthirtyeight=new ArrayList<>();
                        catthirtyeight.clear();
                        catthirtynine=new ArrayList<>();
                        catthirtynine.clear();
                        catfourty=new ArrayList<>();
                        catfourty.clear();

                        catfourtyone=new ArrayList<>();
                        catfourtyone.clear();
                        catfourtytwo=new ArrayList<>();
                        catfourtytwo.clear();
                        catfourtythree=new ArrayList<>();
                        catfourtythree.clear();
                        catfourtyfour=new ArrayList<>();
                        catfourtyfour.clear();
                        catfourtyfive=new ArrayList<>();
                        catfourtyfive.clear();
                        catfourtysix=new ArrayList<>();
                        catfourtysix.clear();
                        catfourtyseven=new ArrayList<>();
                        catfourtyseven.clear();
                        catfourtyeight=new ArrayList<>();
                        catfourtyeight.clear();
                        catfourtynine=new ArrayList<>();
                        catfourtynine.clear();
                        catfifty=new ArrayList<>();
                        catfifty.clear();
                      for(int i=0;i<=arrayList.size();i++)
                      {


                          switch (arrayList.get(i).getcId()) {

                              case "1":


                                  Log.e("TAG", "ONE");
                                  linearLayout1.setVisibility(View.VISIBLE);
                                  recyclerViewLive.setVisibility(View.VISIBLE);

                                  catone.add(arrayList.get(i));
                                  txtCdescOne.setVisibility(View.VISIBLE);
                                  txtCdescOne.setText(catone.get(0).getcDesc());


                                  if (arrayList.get(i).getcView().equals("1"))
                                  {

                                      Log.e("TAG","C1");
                                      itemAdapter = new ItemAdapter(getContext(), catone, itemClickListener,"1");
                                      recyclerViewLive.setAdapter(itemAdapter);
                                      recyclerViewLive.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))
                                  {
                                      Log.e("TAG","C2");
                                      itemAdapter = new ItemAdapter(getContext(), catone, itemClickListener,"2");
                                      recyclerViewLive.setAdapter(itemAdapter);
                                      recyclerViewLive.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }

                                  else if(arrayList.get(i).getcView().equals("3"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catone, itemClickListener,"1");
                                      recyclerViewLive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewLive.setLayoutManager(layoutManager);


                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catone, itemClickListener,"2");
                                      recyclerViewLive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewLive.setLayoutManager(layoutManager);

                                  }
                                  break;
                              case "2":
                                  Log.e("TAG", "TWO");
                                  linearLayout2.setVisibility(View.VISIBLE);
                                  recyclerViewUpcoming.setVisibility(View.VISIBLE);

                                  cattwo.add(arrayList.get(i));
                                  txtCdescTwo.setVisibility(View.VISIBLE);
                                  txtCdescTwo.setText(cattwo.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwo, itemClickListener,"1");
                                      recyclerViewUpcoming.setAdapter(itemAdapter);
                                      recyclerViewUpcoming.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwo, itemClickListener,"2");
                                      recyclerViewUpcoming.setAdapter(itemAdapter);
                                      recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwo, itemClickListener,"1");
                                      recyclerViewUpcoming.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewUpcoming.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwo, itemClickListener,"2");
                                      recyclerViewUpcoming.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewUpcoming.setLayoutManager(layoutManager);
                                  }
                                  break;
                              case "3":
                                  Log.e("TAG", "THREE");
                                  linearLayout3.setVisibility(View.VISIBLE);
                                  recyclerViewWinners.setVisibility(View.VISIBLE);

                                  catthree.add(arrayList.get(i));
                                  txtCdescThree.setVisibility(View.VISIBLE);
                                  txtCdescThree.setText(catthree.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthree, itemClickListener,"1");
                                      recyclerViewWinners.setAdapter(itemAdapter);
                                      recyclerViewWinners.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthree, itemClickListener,"2");
                                      recyclerViewWinners.setAdapter(itemAdapter);
                                      recyclerViewWinners.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthree, itemClickListener,"1");
                                      recyclerViewWinners.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewWinners.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthree, itemClickListener,"2");
                                      recyclerViewWinners.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewWinners.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "4":
                                  Log.e("TAG", "FOUR");
                                  linearLayout4.setVisibility(View.VISIBLE);
                                  recyclerViewFour.setVisibility(View.VISIBLE);

                                  catfour.add(arrayList.get(i));
                                  txtCDescFour.setVisibility(View.VISIBLE);
                                  txtCDescFour.setText(catfour.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfour, itemClickListener,"1");
                                      recyclerViewFour.setAdapter(itemAdapter);
                                      recyclerViewFour.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfour, itemClickListener,"2");
                                      recyclerViewFour.setAdapter(itemAdapter);
                                      recyclerViewFour.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfour, itemClickListener,"1");
                                      recyclerViewFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFour.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfour, itemClickListener,"2");
                                      recyclerViewFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFour.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "5":
                                  
                                  linearLayout5.setVisibility(View.VISIBLE);
                                  recyclerViewFive.setVisibility(View.VISIBLE);

                                  catfive.add(arrayList.get(i));
                                  txtCDescFive.setVisibility(View.VISIBLE);
                                  txtCDescFive.setText(catfive.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfive, itemClickListener,"1");
                                      recyclerViewFive.setAdapter(itemAdapter);
                                      recyclerViewFive.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfive, itemClickListener,"2");
                                      recyclerViewFive.setAdapter(itemAdapter);
                                      recyclerViewFive.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfive, itemClickListener,"1");
                                      recyclerViewFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFive.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfive, itemClickListener,"2");
                                      recyclerViewFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFive.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "6":

                                  linearLayout6.setVisibility(View.VISIBLE);
                                  recyclerViewSix.setVisibility(View.VISIBLE);

                                  catsix.add(arrayList.get(i));

                                  txtCDescSix.setVisibility(View.VISIBLE);
                                  txtCDescSix.setText(catsix.get(0).getcDesc());
                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsix, itemClickListener,"1");
                                      recyclerViewSix.setAdapter(itemAdapter);
                                      recyclerViewSix.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsix, itemClickListener,"2");
                                      recyclerViewSix.setAdapter(itemAdapter);
                                      recyclerViewSix.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsix, itemClickListener,"1");
                                      recyclerViewSix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewSix.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsix, itemClickListener,"2");
                                      recyclerViewSix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewSix.setLayoutManager(layoutManager);
                                  }
                                  break;
                              case "7":

                                  linearLayout7.setVisibility(View.VISIBLE);
                                  recyclerViewSeven.setVisibility(View.VISIBLE);

                                  catseven.add(arrayList.get(i));
                                  txtCDescSeven.setVisibility(View.VISIBLE);
                                  txtCDescSeven.setText(catseven.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseven, itemClickListener,"1");
                                      recyclerViewSeven.setAdapter(itemAdapter);
                                      recyclerViewSeven.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseven, itemClickListener,"2");
                                      recyclerViewSeven.setAdapter(itemAdapter);
                                      recyclerViewSeven.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseven, itemClickListener,"1");
                                      recyclerViewSeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewSeven.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseven, itemClickListener,"2");
                                      recyclerViewSeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewSeven.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "8":

                                  linearLayout8.setVisibility(View.VISIBLE);
                                  recyclerViewEight.setVisibility(View.VISIBLE);

                                  cateight.add(arrayList.get(i));
                                  txtCDescEight.setVisibility(View.VISIBLE);
                                  txtCDescEight.setText(cateight.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateight, itemClickListener,"1");
                                      recyclerViewEight.setAdapter(itemAdapter);
                                      recyclerViewEight.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateight, itemClickListener,"2");
                                      recyclerViewEight.setAdapter(itemAdapter);
                                      recyclerViewEight.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateight, itemClickListener,"1");
                                      recyclerViewEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewEight.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateight, itemClickListener,"2");
                                      recyclerViewEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewEight.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "9":

                                  linearLayout9.setVisibility(View.VISIBLE);
                                  recyclerViewNine.setVisibility(View.VISIBLE);

                                  catnine.add(arrayList.get(i));

                                  txtCDescNine.setVisibility(View.VISIBLE);
                                  txtCDescNine.setText(catnine.get(0).getcDesc());
                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnine, itemClickListener,"1");
                                      recyclerViewNine.setAdapter(itemAdapter);
                                      recyclerViewNine.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnine, itemClickListener,"2");
                                      recyclerViewNine.setAdapter(itemAdapter);
                                      recyclerViewNine.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnine, itemClickListener,"1");
                                      recyclerViewNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewNine.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnine, itemClickListener,"2");
                                      recyclerViewNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewNine.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "10":

                                  linearLayout10.setVisibility(View.VISIBLE);
                                  recyclerViewTen.setVisibility(View.VISIBLE);

                                  catten.add(arrayList.get(i));
                                  txtCDescTen.setVisibility(View.VISIBLE);
                                  txtCDescTen.setText(catten.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catten, itemClickListener,"1");
                                      recyclerViewTen.setAdapter(itemAdapter);
                                      recyclerViewTen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catten, itemClickListener,"2");
                                      recyclerViewTen.setAdapter(itemAdapter);
                                      recyclerViewTen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catten, itemClickListener,"1");
                                      recyclerViewTen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catten, itemClickListener,"2");
                                      recyclerViewTen.setAdapter(itemAdapter);
                                      recyclerViewTen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTen.setLayoutManager(layoutManager);
                                  }
                                  break;


                              case "11":

                                  linearLayout11.setVisibility(View.VISIBLE);
                                  recyclerViewEleven.setVisibility(View.VISIBLE);

                                  cateleven.add(arrayList.get(i));
                                  txtCDescEleven.setVisibility(View.VISIBLE);
                                  txtCDescEleven.setText(cateleven.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateleven, itemClickListener,"1");
                                      recyclerViewEleven.setAdapter(itemAdapter);
                                      recyclerViewEleven.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateleven, itemClickListener,"2");
                                      recyclerViewEleven.setAdapter(itemAdapter);
                                      recyclerViewEleven.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateleven, itemClickListener,"1");
                                      recyclerViewEleven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewEleven.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateleven, itemClickListener,"2");
                                      recyclerViewEleven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewEleven.setLayoutManager(layoutManager);
                                  }
                                  break;


                              case "12":

                                  linearLayout12.setVisibility(View.VISIBLE);
                                  recyclerViewTwelve.setVisibility(View.VISIBLE);

                                  cattwelve.add(arrayList.get(i));
                                  txtCDescTwelve.setVisibility(View.VISIBLE);
                                  txtCDescTwelve.setText(cattwelve.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwelve, itemClickListener,"1");
                                      recyclerViewTwelve.setAdapter(itemAdapter);
                                      recyclerViewTwelve.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwelve, itemClickListener,"2");
                                      recyclerViewTwelve.setAdapter(itemAdapter);
                                      recyclerViewTwelve.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwelve, itemClickListener,"1");
                                      recyclerViewTwelve.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwelve.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwelve, itemClickListener,"2");
                                      recyclerViewTwelve.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwelve.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "13":

                                  linearLayout13.setVisibility(View.VISIBLE);
                                  recyclerViewthirteen.setVisibility(View.VISIBLE);

                                  catthirteen.add(arrayList.get(i));
                                  txtCDescThirteen.setVisibility(View.VISIBLE);
                                  txtCDescThirteen.setText(catthirteen.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirteen, itemClickListener,"1");
                                      recyclerViewthirteen.setAdapter(itemAdapter);
                                      recyclerViewthirteen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirteen, itemClickListener,"2");
                                      recyclerViewthirteen.setAdapter(itemAdapter);
                                      recyclerViewthirteen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirteen, itemClickListener,"1");
                                      recyclerViewthirteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewthirteen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirteen, itemClickListener,"2");
                                      recyclerViewthirteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewthirteen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "14":

                                  linearLayout14.setVisibility(View.VISIBLE);
                                  recyclerViewfourteen.setVisibility(View.VISIBLE);

                                  catfourteen.add(arrayList.get(i));
                                  txtCDescFourteen.setVisibility(View.VISIBLE);
                                  txtCDescFourteen.setText(catten.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourteen, itemClickListener,"1");
                                      recyclerViewfourteen.setAdapter(itemAdapter);
                                      recyclerViewfourteen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourteen, itemClickListener,"2");
                                      recyclerViewfourteen.setAdapter(itemAdapter);
                                      recyclerViewfourteen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourteen, itemClickListener,"1");
                                      recyclerViewfourteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewfourteen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourteen, itemClickListener,"2");
                                      recyclerViewfourteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewfourteen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "15":

                                  linearLayout15.setVisibility(View.VISIBLE);
                                  recyclerViewfifteen.setVisibility(View.VISIBLE);

                                  catfifteen.add(arrayList.get(i));
                                  txtCDescFifteen.setVisibility(View.VISIBLE);
                                  txtCDescFifteen.setText(catten.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifteen, itemClickListener,"1");
                                      recyclerViewfifteen.setAdapter(itemAdapter);
                                      recyclerViewfifteen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifteen, itemClickListener,"2");
                                      recyclerViewfifteen.setAdapter(itemAdapter);
                                      recyclerViewfifteen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifteen, itemClickListener,"1");
                                      recyclerViewfifteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewfifteen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifteen, itemClickListener,"2");
                                      recyclerViewfifteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewfifteen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "16":

                                  linearLayout16.setVisibility(View.VISIBLE);
                                  recyclerViewsixteen.setVisibility(View.VISIBLE);

                                  catsixteen.add(arrayList.get(i));
                                  txtCDescSixteen.setVisibility(View.VISIBLE);
                                  txtCDescSixteen.setText(catsixteen.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsixteen, itemClickListener,"1");
                                      recyclerViewsixteen.setAdapter(itemAdapter);
                                      recyclerViewsixteen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsixteen, itemClickListener,"2");
                                      recyclerViewsixteen.setAdapter(itemAdapter);
                                      recyclerViewsixteen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsixteen, itemClickListener,"1");
                                      recyclerViewsixteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewsixteen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catsixteen, itemClickListener,"2");
                                      recyclerViewsixteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewsixteen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "17":

                                  linearLayout17.setVisibility(View.VISIBLE);
                                  recyclerViewseventeen.setVisibility(View.VISIBLE);

                                  catseventeen.add(arrayList.get(i));
                                  txtCDescSeventeen.setVisibility(View.VISIBLE);
                                  txtCDescSeventeen.setText(catseventeen.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseventeen, itemClickListener,"1");
                                      recyclerViewseventeen.setAdapter(itemAdapter);
                                      recyclerViewseventeen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseventeen, itemClickListener,"2");
                                      recyclerViewseventeen.setAdapter(itemAdapter);
                                      recyclerViewseventeen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseventeen, itemClickListener,"1");
                                      recyclerViewseventeen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewseventeen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catseventeen, itemClickListener,"2");
                                      recyclerViewseventeen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewseventeen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "18":

                                  linearLayout18.setVisibility(View.VISIBLE);
                                  recyclerVieweighteen.setVisibility(View.VISIBLE);

                                  cateighteen.add(arrayList.get(i));
                                  txtCDescEighteen.setVisibility(View.VISIBLE);
                                  txtCDescEighteen.setText(cateighteen.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateighteen, itemClickListener,"1");
                                      recyclerVieweighteen.setAdapter(itemAdapter);
                                      recyclerVieweighteen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateighteen, itemClickListener,"2");
                                      recyclerVieweighteen.setAdapter(itemAdapter);
                                      recyclerVieweighteen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateighteen, itemClickListener,"1");
                                      recyclerVieweighteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerVieweighteen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cateighteen, itemClickListener,"2");
                                      recyclerVieweighteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerVieweighteen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "19":

                                  linearLayout19.setVisibility(View.VISIBLE);
                                  recyclerViewnineteen.setVisibility(View.VISIBLE);

                                  catnineteen.add(arrayList.get(i));
                                  txtCDescNineteen.setVisibility(View.VISIBLE);
                                  txtCDescNineteen.setText(catnineteen.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnineteen, itemClickListener,"1");
                                      recyclerViewnineteen.setAdapter(itemAdapter);
                                      recyclerViewnineteen.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnineteen, itemClickListener,"2");
                                      recyclerViewnineteen.setAdapter(itemAdapter);
                                      recyclerViewnineteen.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnineteen, itemClickListener,"1");
                                      recyclerViewnineteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewnineteen.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catnineteen, itemClickListener,"2");
                                      recyclerViewnineteen.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewnineteen.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "20":

                                  linearLayout20.setVisibility(View.VISIBLE);
                                  recyclerViewtwenty.setVisibility(View.VISIBLE);

                                  cattwenty.add(arrayList.get(i));
                                  txtCDescTwenty.setVisibility(View.VISIBLE);
                                  txtCDescTwenty.setText(cattwenty.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwenty, itemClickListener,"1");
                                      recyclerViewtwenty.setAdapter(itemAdapter);
                                      recyclerViewtwenty.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwenty, itemClickListener,"2");
                                      recyclerViewtwenty.setAdapter(itemAdapter);
                                      recyclerViewtwenty.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwenty, itemClickListener,"1");
                                      recyclerViewtwenty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewtwenty.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwenty, itemClickListener,"2");
                                      recyclerViewtwenty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewtwenty.setLayoutManager(layoutManager);
                                  }
                                  break;


                              case "21":

                                  linearLayout21.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyOne.setVisibility(View.VISIBLE);

                                  cattwentyone.add(arrayList.get(i));
                                  txtCDescTwentyOne.setVisibility(View.VISIBLE);
                                  txtCDescTwentyOne.setText(cattwentyone.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyone, itemClickListener,"1");
                                      recyclerViewTwentyOne.setAdapter(itemAdapter);
                                      recyclerViewTwentyOne.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyone, itemClickListener,"2");
                                      recyclerViewTwentyOne.setAdapter(itemAdapter);
                                      recyclerViewTwentyOne.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyone, itemClickListener,"1");
                                      recyclerViewTwentyOne.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyOne.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyone, itemClickListener,"2");
                                      recyclerViewTwentyOne.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyOne.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "22":

                                  linearLayout22.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyTwo.setVisibility(View.VISIBLE);

                                  cattwentytwo.add(arrayList.get(i));
                                  txtCDescTwentyTwo.setVisibility(View.VISIBLE);
                                  txtCDescTwentyTwo.setText(cattwentytwo.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentytwo, itemClickListener,"1");
                                      recyclerViewTwentyTwo.setAdapter(itemAdapter);
                                      recyclerViewTwentyTwo.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentytwo, itemClickListener,"2");
                                      recyclerViewTwentyTwo.setAdapter(itemAdapter);
                                      recyclerViewTwentyTwo.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentytwo, itemClickListener,"1");
                                      recyclerViewTwentyTwo.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyTwo.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentytwo, itemClickListener,"2");
                                      recyclerViewTwentyTwo.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyTwo.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "23":

                                  linearLayout23.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyThree.setVisibility(View.VISIBLE);

                                  cattwentythree.add(arrayList.get(i));
                                  txtCDescTwentyThree.setVisibility(View.VISIBLE);
                                  txtCDescTwentyThree.setText(cattwentythree.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentythree, itemClickListener,"1");
                                      recyclerViewTwentyThree.setAdapter(itemAdapter);
                                      recyclerViewTwentyThree.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentythree, itemClickListener,"2");
                                      recyclerViewTwentyThree.setAdapter(itemAdapter);
                                      recyclerViewTwentyThree.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentythree, itemClickListener,"1");
                                      recyclerViewTwentyThree.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyThree.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentythree, itemClickListener,"2");
                                      recyclerViewTwentyThree.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyThree.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "24":

                                  linearLayout24.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyFour.setVisibility(View.VISIBLE);

                                  cattwentyfour.add(arrayList.get(i));
                                  txtCDescTwentyFour.setVisibility(View.VISIBLE);
                                  txtCDescTwentyFour.setText(cattwentyfour.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfour, itemClickListener,"1");
                                      recyclerViewTwentyFour.setAdapter(itemAdapter);
                                      recyclerViewTwentyFour.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfour, itemClickListener,"2");
                                      recyclerViewTwentyFour.setAdapter(itemAdapter);
                                      recyclerViewTwentyFour.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfour, itemClickListener,"1");
                                      recyclerViewTwentyFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyFour.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfour, itemClickListener,"2");
                                      recyclerViewTwentyFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyFour.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "25":

                                  linearLayout25.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyFive.setVisibility(View.VISIBLE);

                                  cattwentyfive.add(arrayList.get(i));
                                  txtCDescTwentyFive.setVisibility(View.VISIBLE);
                                  txtCDescTwentyFive.setText(cattwentyfive.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfive, itemClickListener,"1");
                                      recyclerViewTwentyFive.setAdapter(itemAdapter);
                                      recyclerViewTwentyFive.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfive, itemClickListener,"2");
                                      recyclerViewTwentyFive.setAdapter(itemAdapter);
                                      recyclerViewTwentyFive.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfive, itemClickListener,"1");
                                      recyclerViewTwentyFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyFive.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyfive, itemClickListener,"2");
                                      recyclerViewTwentyFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyFive.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "26":

                                  linearLayout26.setVisibility(View.VISIBLE);
                                  recyclerViewTwentySix.setVisibility(View.VISIBLE);

                                  cattwentysix.add(arrayList.get(i));
                                  txtCDescTwentySix.setVisibility(View.VISIBLE);
                                  txtCDescTwentySix.setText(cattwentysix.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentysix, itemClickListener,"1");
                                      recyclerViewTwentySix.setAdapter(itemAdapter);
                                      recyclerViewTwentySix.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentysix, itemClickListener,"2");
                                      recyclerViewTwentySix.setAdapter(itemAdapter);
                                      recyclerViewTwentySix.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentysix, itemClickListener,"1");
                                      recyclerViewTwentySix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentySix.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentysix, itemClickListener,"2");
                                      recyclerViewTwentySix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentySix.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "27":

                                  linearLayout27.setVisibility(View.VISIBLE);
                                  recyclerViewTwentySeven.setVisibility(View.VISIBLE);

                                  cattwentyseven.add(arrayList.get(i));
                                  txtCDescTwentySeven.setVisibility(View.VISIBLE);
                                  txtCDescTwentySeven.setText(cattwentyseven.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyseven, itemClickListener,"1");
                                      recyclerViewTwentySeven.setAdapter(itemAdapter);
                                      recyclerViewTwentySeven.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyseven, itemClickListener,"2");
                                      recyclerViewTwentySeven.setAdapter(itemAdapter);
                                      recyclerViewTwentySeven.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyseven, itemClickListener,"1");
                                      recyclerViewTwentySeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentySeven.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyseven, itemClickListener,"2");
                                      recyclerViewTwentySeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentySeven.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "28":

                                  linearLayout28.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyEight.setVisibility(View.VISIBLE);

                                  cattwentyeight.add(arrayList.get(i));
                                  txtCDescTwentyEight.setVisibility(View.VISIBLE);
                                  txtCDescTwentyEight.setText(cattwentyeight.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyeight, itemClickListener,"1");
                                      recyclerViewTwentyEight.setAdapter(itemAdapter);
                                      recyclerViewTwentyEight.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyeight, itemClickListener,"2");
                                      recyclerViewTwentyEight.setAdapter(itemAdapter);
                                      recyclerViewTwentyEight.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyeight, itemClickListener,"1");
                                      recyclerViewTwentyEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyEight.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentyeight, itemClickListener,"2");
                                      recyclerViewTwentyEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyEight.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "29":

                                  linearLayout29.setVisibility(View.VISIBLE);
                                  recyclerViewTwentyNine.setVisibility(View.VISIBLE);

                                  cattwentynine.add(arrayList.get(i));
                                  txtCDescTwentyNine.setVisibility(View.VISIBLE);
                                  txtCDescTwentyNine.setText(cattwentynine.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentynine, itemClickListener,"1");
                                      recyclerViewTwentyNine.setAdapter(itemAdapter);
                                      recyclerViewTwentyNine.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentynine, itemClickListener,"2");
                                      recyclerViewTwentyNine.setAdapter(itemAdapter);
                                      recyclerViewTwentyNine.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentynine, itemClickListener,"1");
                                      recyclerViewTwentyNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyNine.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), cattwentynine, itemClickListener,"2");
                                      recyclerViewTwentyNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewTwentyNine.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "30":

                                  linearLayout30.setVisibility(View.VISIBLE);
                                  recyclerViewThirty.setVisibility(View.VISIBLE);

                                  catthirty.add(arrayList.get(i));
                                  txtCDescThirty.setVisibility(View.VISIBLE);
                                  txtCDescThirty.setText(catthirty.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirty, itemClickListener,"1");
                                      recyclerViewThirty.setAdapter(itemAdapter);
                                      recyclerViewThirty.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirty, itemClickListener,"2");
                                      recyclerViewThirty.setAdapter(itemAdapter);
                                      recyclerViewThirty.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirty, itemClickListener,"1");
                                      recyclerViewThirty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirty.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirty, itemClickListener,"2");
                                      recyclerViewThirty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirty.setLayoutManager(layoutManager);
                                  }
                                  break;


                              case "31":

                                  linearLayout31.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyOne.setVisibility(View.VISIBLE);

                                  catthirtyone.add(arrayList.get(i));
                                  txtCDescThirtyOne.setVisibility(View.VISIBLE);
                                  txtCDescThirtyOne.setText(catthirtyone.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyone, itemClickListener,"1");
                                      recyclerViewThirtyOne.setAdapter(itemAdapter);
                                      recyclerViewThirtyOne.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyone, itemClickListener,"2");
                                      recyclerViewThirtyOne.setAdapter(itemAdapter);
                                      recyclerViewThirtyOne.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyone, itemClickListener,"1");
                                      recyclerViewThirtyOne.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyOne.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyone, itemClickListener,"2");
                                      recyclerViewThirtyOne.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyOne.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "32":

                                  linearLayout32.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyTwo.setVisibility(View.VISIBLE);

                                  catthirtytwo.add(arrayList.get(i));
                                  txtCDescThirtyTwo.setVisibility(View.VISIBLE);
                                  txtCDescThirtyTwo.setText(catthirtytwo.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtytwo, itemClickListener,"1");
                                      recyclerViewThirtyTwo.setAdapter(itemAdapter);
                                      recyclerViewThirtyTwo.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtytwo, itemClickListener,"2");
                                      recyclerViewThirtyTwo.setAdapter(itemAdapter);
                                      recyclerViewThirtyTwo.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtytwo, itemClickListener,"1");
                                      recyclerViewThirtyTwo.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyTwo.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtytwo, itemClickListener,"2");
                                      recyclerViewThirtyTwo.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyTwo.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "33":

                                  linearLayout33.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyThree.setVisibility(View.VISIBLE);

                                  catthirtythree.add(arrayList.get(i));
                                  txtCDescThirtyThree.setVisibility(View.VISIBLE);
                                  txtCDescThirtyThree.setText(catthirtythree.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtythree, itemClickListener,"1");
                                      recyclerViewThirtyThree.setAdapter(itemAdapter);
                                      recyclerViewThirtyThree.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtythree, itemClickListener,"2");
                                      recyclerViewThirtyThree.setAdapter(itemAdapter);
                                      recyclerViewThirtyThree.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtythree, itemClickListener,"1");
                                      recyclerViewThirtyThree.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyThree.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtythree, itemClickListener,"2");
                                      recyclerViewThirtyThree.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyThree.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "34":

                                  linearLayout34.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyFour.setVisibility(View.VISIBLE);

                                  catthirtyfour.add(arrayList.get(i));
                                  txtCDescThirtyFour.setVisibility(View.VISIBLE);
                                  txtCDescThirtyFour.setText(catthirtyfour.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfour, itemClickListener,"1");
                                      recyclerViewThirtyFour.setAdapter(itemAdapter);
                                      recyclerViewThirtyFour.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfour, itemClickListener,"2");
                                      recyclerViewThirtyFour.setAdapter(itemAdapter);
                                      recyclerViewThirtyFour.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfour, itemClickListener,"1");
                                      recyclerViewThirtyFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyFour.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfour, itemClickListener,"2");
                                      recyclerViewThirtyFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyFour.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "35":

                                  linearLayout35.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyFive.setVisibility(View.VISIBLE);

                                  catthirtyfive.add(arrayList.get(i));
                                  txtCDescThirtyFive.setVisibility(View.VISIBLE);
                                  txtCDescThirtyFive.setText(catthirtyfive.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfive, itemClickListener,"1");
                                      recyclerViewThirtyFive.setAdapter(itemAdapter);
                                      recyclerViewThirtyFive.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfive, itemClickListener,"2");
                                      recyclerViewThirtyFive.setAdapter(itemAdapter);
                                      recyclerViewThirtyFive.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfive, itemClickListener,"1");
                                      recyclerViewThirtyFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyFive.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyfive, itemClickListener,"2");
                                      recyclerViewThirtyFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyFive.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "36":

                                  linearLayout36.setVisibility(View.VISIBLE);
                                  recyclerViewThirtySix.setVisibility(View.VISIBLE);

                                  catthirtysix.add(arrayList.get(i));
                                  txtCDescThirtySix.setVisibility(View.VISIBLE);
                                  txtCDescThirtySix.setText(catthirtysix.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtysix, itemClickListener,"1");
                                      recyclerViewThirtySix.setAdapter(itemAdapter);
                                      recyclerViewThirtySix.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtysix, itemClickListener,"2");
                                      recyclerViewThirtySix.setAdapter(itemAdapter);
                                      recyclerViewThirtySix.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtysix, itemClickListener,"1");
                                      recyclerViewThirtySix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtySix.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtysix, itemClickListener,"2");
                                      recyclerViewThirtySix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtySix.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "37":

                                  linearLayout37.setVisibility(View.VISIBLE);
                                  recyclerViewThirtySeven.setVisibility(View.VISIBLE);

                                  catthirtyseven.add(arrayList.get(i));
                                  txtCDescThirtySeven.setVisibility(View.VISIBLE);
                                  txtCDescThirtySeven.setText(catthirtyseven.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyseven, itemClickListener,"1");
                                      recyclerViewThirtySeven.setAdapter(itemAdapter);
                                      recyclerViewThirtySeven.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyseven, itemClickListener,"2");
                                      recyclerViewThirtySeven.setAdapter(itemAdapter);
                                      recyclerViewThirtySeven.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyseven, itemClickListener,"1");
                                      recyclerViewThirtySeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtySeven.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyseven, itemClickListener,"2");
                                      recyclerViewThirtySeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtySeven.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "38":

                                  linearLayout38.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyEight.setVisibility(View.VISIBLE);

                                  catthirtyeight.add(arrayList.get(i));
                                  txtCDescThirtyEight.setVisibility(View.VISIBLE);
                                  txtCDescThirtyEight.setText(catthirtyeight.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyeight, itemClickListener,"1");
                                      recyclerViewThirtyEight.setAdapter(itemAdapter);
                                      recyclerViewThirtyEight.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyeight, itemClickListener,"2");
                                      recyclerViewThirtyEight.setAdapter(itemAdapter);
                                      recyclerViewThirtyEight.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyeight, itemClickListener,"1");
                                      recyclerViewThirtyEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyEight.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtyeight, itemClickListener,"2");
                                      recyclerViewThirtyEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyEight.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "39":

                                  linearLayout39.setVisibility(View.VISIBLE);
                                  recyclerViewThirtyNine.setVisibility(View.VISIBLE);

                                  catthirtynine.add(arrayList.get(i));
                                  txtCDescThirtyNine.setVisibility(View.VISIBLE);
                                  txtCDescThirtyNine.setText(catthirtynine.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtynine, itemClickListener,"1");
                                      recyclerViewThirtyNine.setAdapter(itemAdapter);
                                      recyclerViewThirtyNine.setLayoutManager(new GridLayoutManager(getContext(), 3));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtynine, itemClickListener,"2");
                                      recyclerViewThirtyNine.setAdapter(itemAdapter);
                                      recyclerViewThirtyNine.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtynine, itemClickListener,"1");
                                      recyclerViewThirtyNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyNine.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catthirtynine, itemClickListener,"2");
                                      recyclerViewThirtyNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewThirtyNine.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "40":

                                  linearLayout40.setVisibility(View.VISIBLE);
                                  recyclerViewFourty.setVisibility(View.VISIBLE);

                                  catfourty.add(arrayList.get(i));
                                  txtCDescFourty.setVisibility(View.VISIBLE);
                                  txtCDescFourty.setText(catfourty.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourty, itemClickListener,"1");
                                      recyclerViewFourty.setAdapter(itemAdapter);
                                      recyclerViewFourty.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourty, itemClickListener,"2");
                                      recyclerViewFourty.setAdapter(itemAdapter);
                                      recyclerViewFourty.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourty, itemClickListener,"1");
                                      recyclerViewFourty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourty.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourty, itemClickListener,"2");
                                      recyclerViewFourty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourty.setLayoutManager(layoutManager);
                                  }
                                  break;


                              case "41":

                                  linearLayout41.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyOne.setVisibility(View.VISIBLE);

                                  catfourtyone.add(arrayList.get(i));
                                  txtCDescFourtyOne.setVisibility(View.VISIBLE);
                                  txtCDescFourtyOne.setText(catfourtyone.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyone, itemClickListener,"1");
                                      recyclerViewFourtyOne.setAdapter(itemAdapter);
                                      recyclerViewFourtyOne.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyone, itemClickListener,"2");
                                      recyclerViewFourtyOne.setAdapter(itemAdapter);
                                      recyclerViewFourtyOne.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyone, itemClickListener,"1");
                                      recyclerViewFourtyOne.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyOne.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyone, itemClickListener,"2");
                                      recyclerViewFourtyOne.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyOne.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "42":

                                  linearLayout42.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyTwo.setVisibility(View.VISIBLE);

                                  catfourtytwo.add(arrayList.get(i));
                                  txtCDescFourtyTwo.setVisibility(View.VISIBLE);
                                  txtCDescFourtyTwo.setText(catfourtytwo.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtytwo, itemClickListener,"1");
                                      recyclerViewFourtyTwo.setAdapter(itemAdapter);
                                      recyclerViewFourtyTwo.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtytwo, itemClickListener,"2");
                                      recyclerViewFourtyTwo.setAdapter(itemAdapter);
                                      recyclerViewFourtyTwo.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtytwo, itemClickListener,"1");
                                      recyclerViewFourtyTwo.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyTwo.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtytwo, itemClickListener,"2");
                                      recyclerViewFourtyTwo.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyTwo.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "43":

                                  linearLayout43.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyThree.setVisibility(View.VISIBLE);

                                  catfourtythree.add(arrayList.get(i));
                                  txtCDescFourtyThree.setVisibility(View.VISIBLE);
                                  txtCDescFourtyThree.setText(catfourtythree.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtythree, itemClickListener,"1");
                                      recyclerViewFourtyThree.setAdapter(itemAdapter);
                                      recyclerViewFourtyThree.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtythree, itemClickListener,"2");
                                      recyclerViewFourtyThree.setAdapter(itemAdapter);
                                      recyclerViewFourtyThree.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtythree, itemClickListener,"1");
                                      recyclerViewFourtyThree.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyThree.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtythree, itemClickListener,"2");
                                      recyclerViewFourtyThree.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyThree.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "44":

                                  linearLayout44.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyFour.setVisibility(View.VISIBLE);

                                  catfourtyfour.add(arrayList.get(i));
                                  txtCDescFourtyFour.setVisibility(View.VISIBLE);
                                  txtCDescFourtyFour.setText(catfourtyfour.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfour, itemClickListener,"1");
                                      recyclerViewFourtyFour.setAdapter(itemAdapter);
                                      recyclerViewFourtyFour.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfour, itemClickListener,"2");
                                      recyclerViewFourtyFour.setAdapter(itemAdapter);
                                      recyclerViewFourtyFour.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfour, itemClickListener,"1");
                                      recyclerViewFourtyFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyFour.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfour, itemClickListener,"2");
                                      recyclerViewFourtyFour.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyFour.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "45":

                                  linearLayout45.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyFive.setVisibility(View.VISIBLE);

                                  catfourtyfive.add(arrayList.get(i));
                                  txtCDescFourtyFive.setVisibility(View.VISIBLE);
                                  txtCDescFourtyFive.setText(catfourtyfive.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfive, itemClickListener,"1");
                                      recyclerViewFourtyFive.setAdapter(itemAdapter);
                                      recyclerViewFourtyFive.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfive, itemClickListener,"2");
                                      recyclerViewFourtyFive.setAdapter(itemAdapter);
                                      recyclerViewFourtyFive.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfive, itemClickListener,"1");
                                      recyclerViewFourtyFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyFive.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyfive, itemClickListener,"2");
                                      recyclerViewFourtyFive.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyFive.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "46":

                                  linearLayout46.setVisibility(View.VISIBLE);
                                  recyclerViewFourtySix.setVisibility(View.VISIBLE);

                                  catfourtysix.add(arrayList.get(i));
                                  txtCDescFourtySix.setVisibility(View.VISIBLE);
                                  txtCDescFourtySix.setText(catfourtysix.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtysix, itemClickListener,"1");
                                      recyclerViewFourtySix.setAdapter(itemAdapter);
                                      recyclerViewFourtySix.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtysix, itemClickListener,"2");
                                      recyclerViewFourtySix.setAdapter(itemAdapter);
                                      recyclerViewFourtySix.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtysix, itemClickListener,"1");
                                      recyclerViewFourtySix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtySix.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtysix, itemClickListener,"2");
                                      recyclerViewFourtySix.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtySix.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "47":

                                  linearLayout47.setVisibility(View.VISIBLE);
                                  recyclerViewFourtySeven.setVisibility(View.VISIBLE);

                                  catfourtyseven.add(arrayList.get(i));
                                  txtCDescFourtySeven.setVisibility(View.VISIBLE);
                                  txtCDescFourtySeven.setText(catfourtyseven.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyseven, itemClickListener,"1");
                                      recyclerViewFourtySeven.setAdapter(itemAdapter);
                                      recyclerViewFourtySeven.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyseven, itemClickListener,"2");
                                      recyclerViewFourtySeven.setAdapter(itemAdapter);
                                      recyclerViewFourtySeven.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyseven, itemClickListener,"1");
                                      recyclerViewFourtySeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtySeven.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyseven, itemClickListener,"2");
                                      recyclerViewFourtySeven.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtySeven.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "48":

                                  linearLayout48.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyEight.setVisibility(View.VISIBLE);

                                  catfourtyeight.add(arrayList.get(i));
                                  txtCDescFourtyEight.setVisibility(View.VISIBLE);
                                  txtCDescFourtyEight.setText(catfourtyeight.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyeight, itemClickListener,"1");
                                      recyclerViewFourtyEight.setAdapter(itemAdapter);
                                      recyclerViewFourtyEight.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyeight, itemClickListener,"2");
                                      recyclerViewFourtyEight.setAdapter(itemAdapter);
                                      recyclerViewFourtyEight.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyeight, itemClickListener,"1");
                                      recyclerViewFourtyEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyEight.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtyeight, itemClickListener,"2");
                                      recyclerViewFourtyEight.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyEight.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "49":

                                  linearLayout49.setVisibility(View.VISIBLE);
                                  recyclerViewFourtyNine.setVisibility(View.VISIBLE);

                                  catfourtynine.add(arrayList.get(i));
                                  txtCDescFourtyNine.setVisibility(View.VISIBLE);
                                  txtCDescFourtyNine.setText(catfourtynine.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtynine, itemClickListener,"1");
                                      recyclerViewFourtyNine.setAdapter(itemAdapter);
                                      recyclerViewFourtyNine.setLayoutManager(new GridLayoutManager(getContext(), 4));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtynine, itemClickListener,"2");
                                      recyclerViewFourtyNine.setAdapter(itemAdapter);
                                      recyclerViewFourtyNine.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtynine, itemClickListener,"1");
                                      recyclerViewFourtyNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyNine.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfourtynine, itemClickListener,"2");
                                      recyclerViewFourtyNine.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFourtyNine.setLayoutManager(layoutManager);
                                  }
                                  break;

                              case "50":

                                  linearLayout50.setVisibility(View.VISIBLE);
                                  recyclerViewFifty.setVisibility(View.VISIBLE);

                                  catfifty.add(arrayList.get(i));
                                  txtCDescFifty.setVisibility(View.VISIBLE);
                                  txtCDescFifty.setText(catfifty.get(0).getcDesc());

                                  if (arrayList.get(i).getcView().equals("1"))
                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifty, itemClickListener,"1");
                                      recyclerViewFifty.setAdapter(itemAdapter);
                                      recyclerViewFifty.setLayoutManager(new GridLayoutManager(getContext(), 2));

                                  }
                                  else if(arrayList.get(i).getcView().equals("2"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifty, itemClickListener,"2");
                                      recyclerViewFifty.setAdapter(itemAdapter);
                                      recyclerViewFifty.setLayoutManager(new LinearLayoutManager(getContext()));

                                  }
                                  else if(arrayList.get(i).getcView().equals("3"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifty, itemClickListener,"1");
                                      recyclerViewFifty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFifty.setLayoutManager(layoutManager);
                                  }
                                  else if(arrayList.get(i).getcView().equals("4"))

                                  {
                                      itemAdapter = new ItemAdapter(getContext(), catfifty, itemClickListener,"2");
                                      recyclerViewFifty.setAdapter(itemAdapter);
                                      LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true);
                                      layoutManager.setStackFromEnd(true);

                                      recyclerViewFifty.setLayoutManager(layoutManager);
                                  }
                                  break;
                          }
                      }


                   /*     arrayList.get(i).getcId(),arrayList.get(i).getcName(),arrayList.get(i).getcImage(),arrayList.get(i).getcView(),arrayList.get(i).getoId(),
                                arrayList.get(i).getoName(), arrayList.get(i).getoImage(),arrayList.get(i).getoDesc(),arrayList.get(i).getoDate(),arrayList.get(i).getoEdate(),
                                arrayList.get(i).getoStime(),arrayList.get(i).getoEtime(),arrayList.get(i).getoAmount(),arrayList.get(i).getoType(),arrayList.get(i).getoMin(),
                                arrayList.get(i).getoMax(),arrayList.get(i).getoPrice(),arrayList.get(i).getoOimage(),arrayList.get(i).getoCurrency(),arrayList.get(i).getoStars(),
                                arrayList.get(i).getoMembership(),arrayList.get(i).getoQty(),arrayList.get(i).getoCat(),arrayList.get(i).getoUmax(),arrayList.get(i).getoUlimit(),
                                arrayList.get(i).getoStatus(),arrayList.get(i).getcStatus()
*/


                    } catch (Exception e) {
                        e.printStackTrace();

                    }


                }

                @Override
                public void onFailure(Call<GetCategories> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private Call<GetCategories> getCategoriesCall() {
        return videoService.get_categories();
    }



}
