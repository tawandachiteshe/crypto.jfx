package com.dickensdev.cryptojfx.service;

import com.dickensdev.cryptojfx.model.PayloadModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CryptoPriceService {
    String apiKey = "e1e28273fda39f230b47cce8cbf7fba3851d2233";
    @GET("/v1/assets")
    @Headers({"Content-Type:application/json", "X-API-Key:" + apiKey})
    Call<PayloadModel>  getCoinData();
}
