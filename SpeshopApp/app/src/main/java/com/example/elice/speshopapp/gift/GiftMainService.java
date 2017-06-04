package com.example.elice.speshopapp.gift;

import com.example.elice.speshopapp.gift.data.GiftMainData;
import com.example.elice.speshopapp.gift.data.KeywordData;
import com.example.elice.speshopapp.gift.data.UpdateProductData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by elice.kim on 2017. 5. 27..
 */

public interface GiftMainService {

    @GET("categorys/")
    Call<KeywordData> getKeyword();

    @GET("categorys/{id}")
    Call<UpdateProductData> getProduct(@Path("id") int categoryId);

    @POST("products/")
    Call<GiftMainData> getGiftMain();
}
