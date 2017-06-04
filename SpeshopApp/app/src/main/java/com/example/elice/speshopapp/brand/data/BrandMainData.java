package com.example.elice.speshopapp.brand.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elice.kim on 2017. 5. 29..
 */

public class BrandMainData {

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

        @SerializedName("brand_id")
        @Expose
        public Integer brandId;
        @SerializedName("brand_name")
        @Expose
        public String brandName;
        @SerializedName("brand_memo")
        @Expose
        public String brandMemo;
        @SerializedName("brand_image_url")
        @Expose
        public String brandImageUrl;
        @SerializedName("brand_clicked")
        @Expose
        public Integer brandClicked;

    }
}
