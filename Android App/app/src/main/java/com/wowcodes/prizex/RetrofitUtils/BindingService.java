package com.mercury.redeem.RetrofitUtils;

import com.mercury.redeem.Modelclas.AddOrder;
import com.mercury.redeem.Modelclas.AllBidder;
import com.mercury.redeem.Modelclas.GetCategories;
import com.mercury.redeem.Modelclas.GetCoin;
import com.mercury.redeem.Modelclas.GetSellerDetails;
import com.mercury.redeem.Modelclas.GetWallet;
import com.mercury.redeem.Modelclas.GetBidUser;
import com.mercury.redeem.Modelclas.GetOffersLive;
import com.mercury.redeem.Modelclas.GetOffersWinner;
import com.mercury.redeem.Modelclas.GetOrderUser;
import com.mercury.redeem.Modelclas.GetTransaction;
import com.mercury.redeem.Modelclas.LoginModel;
import com.mercury.redeem.Modelclas.RegisterModel;
import com.mercury.redeem.Modelclas.SettingModel;
import com.mercury.redeem.Modelclas.SuccessModel;
import com.mercury.redeem.Modelclas.UserProfile;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface BindingService {


    @GET("api.php?get_offers_live")
    Call<GetOffersLive> get_offers_live(
    );



    @GET("api.php?get_offers_upcomming")
    Call<GetOffersLive> get_offers_upcomming(
    );
    @GET("api.php?get_offers_quiz")
    Call<GetOffersLive> get_offers_quiz(
    );
    @GET("api.php?get_coin_list")
    Call<GetCoin> get_coin_list(
    );

    @GET("api.php?settings")
    Call<SettingModel> settings(
    );


    @FormUrlEncoded
    @POST("api.php?get_offers_winner")
    Call<GetOffersWinner> get_offers_winner(
            @Field("u_id") String name

    );


    @FormUrlEncoded
    @POST("api.php?add_bid_multi")
    Call<SuccessModel> add_bid_multi(
            @Field("add_bid_multi") String name

    );
    @FormUrlEncoded
    @POST("api.php?get_order_user")
    Call<GetOrderUser> get_order_user(
            @Field("u_id") String u_id

    );


    @FormUrlEncoded
    @POST("api.php?forgotpassword")
    Call<SuccessModel> forgotpassword(
            @Field("phone") String phone

    );

    @FormUrlEncoded
    @POST("api.php?change_password")
    Call<SuccessModel> change_password(
            @Field("phone") String phone,
            @Field("password") String password

    );


    @FormUrlEncoded
    @POST("api.php?mobilenumberverify_setting")
    Call<RegisterModel> mobilenumberverify_setting(
            @Field("phone") String phone,
            @Field("confirm_code") String confirm_code);


    @FormUrlEncoded
    @POST("api.php?postUsermobileRegister")
    Call<SuccessModel> postUserRegister(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("refferal_code") String refferal_code,
            @Field("password") String password,
            @Field("device_id") String device_id
    );
    @FormUrlEncoded
    @POST("api.php?add_order")
    Call<AddOrder> add_order(
            @Field("u_id") String u_id,
            @Field("offer_id") String offer_id,
            @Field("total_amount") String total_amount,
            @Field("dis_amount") String dis_amount,
            @Field("pay_amount") String pay_amount,
            @Field("o_address") String o_address
    );


    @FormUrlEncoded
    @POST("api.php?postUserLogin")
    Call<LoginModel> postUserLogin(
            @Field("phone") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("api.php?get_offers_id")
    Call<AllBidder> get_offers_id(
            @Field("o_id") String o_id,
            @Field("u_id") String u_id
    );

    @FormUrlEncoded
    @POST("api.php?getUserProfile")
    Call<UserProfile> getUserProfile    (
            @Field("id") String id
    );


    @FormUrlEncoded
    @POST("api.php?get_bid_user")
    Call<GetBidUser> get_bid_user(
            @Field("u_id") String o_id
    );


    @FormUrlEncoded
    @POST("api.php?get_transaction")
    Call<GetTransaction> get_transaction(
            @Field("user_id") String o_id
    );

    @FormUrlEncoded
    @POST("api.php?get_wallet_passbook")
    Call<GetWallet> get_wallet_passbook(
            @Field("user_id") String o_id
    );

    @FormUrlEncoded
    @POST("api.php?add_bid")
    Call<SuccessModel> add_bid(
            @Field("u_id") String u_id,
            @Field("o_id") String o_id,
            @Field("bd_value") String bd_value,
            @Field("bd_amount") String bd_amount
    );

    @FormUrlEncoded
    @POST("api.php?postUserwalletUpdate")
    Call<SuccessModel> postUserwalletUpdate(
            @Field("user_id") String u_id,
            @Field("wallet") String o_id,
            @Field("package_id") String bd_value,
            @Field("order_id") String bd_amount
    );


    @Multipart
    @POST("api.php?postUserProfileUpdate")
    Call<SuccessModel> postUserProfileUpdate(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part image,
            @Part("id") RequestBody id,
            @Part("password") RequestBody password
    );
    @GET("api.php?get_items")
    Call<GetCategories> get_categories(
    );
    @GET("api.php?get_seller")
    Call<GetSellerDetails> get_seller(
    );


}