package com.mercury.redeem.services;
import com.mercury.redeem.model.AccessToken;
import com.mercury.redeem.model.STKPush;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface STKPushService {

    @POST("mpesa/stkpush/v1/processrequest")
    Call<STKPush.Mpesa_Push_Response> sendPush(@Body STKPush stkPush);

    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();

}