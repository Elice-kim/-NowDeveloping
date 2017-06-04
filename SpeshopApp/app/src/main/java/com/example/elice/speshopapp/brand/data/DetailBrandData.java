package com.example.elice.speshopapp.brand.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elice.kim on 2017. 5. 30..
 */

public class DetailBrandData {

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
        @SerializedName("products")
        @Expose
        public List<Product> products = null;

        public class Product {

            @SerializedName("product_id")
            @Expose
            public Integer productId;
            @SerializedName("product_name")
            @Expose
            public String productName;
            @SerializedName("product_price")
            @Expose
            public Integer productPrice;
            @SerializedName("product_memo")
            @Expose
            public String productMemo;
            @SerializedName("brand_id")
            @Expose
            public Integer brandId;
            @SerializedName("image_url")
            @Expose
            public String imageUrl;
            @SerializedName("shop_url")
            @Expose
            public String shopUrl;
            @SerializedName("click_count")
            @Expose
            public Integer clickCount;
            @SerializedName("favorite_count")
            @Expose
            public Integer favoriteCount;
            @SerializedName("createdAt")
            @Expose
            public Object createdAt;
            @SerializedName("updatedAt")
            @Expose
            public Object updatedAt;

        }

    }
}
