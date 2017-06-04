package com.example.elice.speshopapp.gift;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.gift.data.UpdateProductData;

import java.util.ArrayList;

/**
 * Created by elice.kim on 2017. 5. 29..
 */

public class GiftImageViewAdapter extends RecyclerView.Adapter<GiftImageViewAdapter.GiftViewHolder> {

//    private ArrayList<GiftMainData.Datum> mainList;
    private ArrayList<UpdateProductData.Datum> giftList;
    private Context mContext;

    public GiftImageViewAdapter(ArrayList<UpdateProductData.Datum> giftList, Context mContext) {
        this.giftList = giftList;
//        this.mainList = mainList;
        this.mContext = mContext;
    }

    @Override
    public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gift_product_main, parent, false);
        return new GiftViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GiftViewHolder holder, int position) {

//        GiftMainData.Datum main = mainList.get(position);
        final UpdateProductData.Datum gift = giftList.get(position);

//        Glide.with(mContext).load(main.imageUrl).into(holder.giftImg);
        Glide.with(mContext).load(gift.product.shopUrl).into(holder.giftImg);
        holder.giftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailActivity = new Intent(mContext, ProductDetailActivity.class);
                detailActivity.putExtra("productId",gift.productId);
                mContext.startActivity(detailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    public class GiftViewHolder extends RecyclerView.ViewHolder {

        ImageView giftImg;

        public GiftViewHolder(View itemView) {
            super(itemView);
            giftImg = (ImageView) itemView.findViewById(R.id.giftImage);
        }
    }
}
