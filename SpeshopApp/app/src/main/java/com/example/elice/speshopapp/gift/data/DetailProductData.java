package com.example.elice.speshopapp.gift.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by elice.kim on 2017. 5. 29..
 */

public class DetailProductData {

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
        @SerializedName("image_urls")
        @Expose
        public List<ImageUrl> imageUrls = null;
        @SerializedName("brand")
        @Expose
        public Brand brand;
        @SerializedName("favorite")
        @Expose
        public Favorite favorite;

        public class ImageUrl {

            @SerializedName("image_url")
            @Expose
            public String imageUrl;
        }

        public class Favorite {

            @SerializedName("liked")
            @Expose
            public Integer liked;
        }

        public class Brand {

            @SerializedName("brand_name")
            @Expose
            public String brandName;
        }

    }
}
