package com.example.elice.speshopapp.gift.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elice.kim on 2017. 5. 27..
 */

public class KeywordData {

    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("msg")
    @Expose
    public String msg;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("total")
    @Expose
    public Integer total;

    public class Datum {

        @SerializedName("category_id")
        @Expose
        public Integer categoryId;
        @SerializedName("category_name")
        @Expose
        public String categoryName;

    }
}
