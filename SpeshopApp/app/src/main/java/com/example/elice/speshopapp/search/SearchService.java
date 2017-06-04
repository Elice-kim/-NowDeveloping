package com.example.elice.speshopapp.search;

import com.example.elice.speshopapp.search.data.SearchData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by elice.kim on 2017. 5. 31..
 */

public interface SearchService {

    @FormUrlEncoded
    @POST("products/")
    Call<SearchData> getSearch(@Field("search") String keyword);
}
