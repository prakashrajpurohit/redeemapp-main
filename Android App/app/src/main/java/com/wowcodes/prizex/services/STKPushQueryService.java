package com.mercury.redeem.services;
import com.mercury.redeem.model.AccessToken;
import com.mercury.redeem.model.STKPushQuery;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface STKPushQueryService {
    @POST("mpesa/stkpushquery/v1/query")
    Call<STKPushQuery.Mpesa_Push_Response> sendPushQuery(@Body STKPushQuery stkPushQuery);
    @GET("oauth/v1/generate?grant_type=client_credentials")
    Call<AccessToken> getAccessToken();
}