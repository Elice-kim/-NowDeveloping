package com.example.elice.speshopapp.brand;

import com.example.elice.speshopapp.brand.data.BrandMainData;
import com.example.elice.speshopapp.brand.data.DetailBrandData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by elice.kim on 2017. 5. 29..
 */

public interface BrandSetting {

    @GET("brands/")
    Call<BrandMainData> getBrandMain();

    @GET("brands/{id}/1")
    Call<DetailBrandData> getProductList(@Path("id") int brandId);

}
