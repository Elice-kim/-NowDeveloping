package com.example.elice.speshopapp.gift;

import com.example.elice.speshopapp.gift.data.DetailProductData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by elice.kim on 2017. 6. 4..
 */

public interface ProductDetailService {

    @GET("products/{productsId}/1")
    Call<DetailProductData> getProductInfo(@Path("productsId") int productId );
}
