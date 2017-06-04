package com.example.elice.speshopapp.brand;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.brand.data.BrandMainData;

import java.util.ArrayList;

/**
 * Created by elice.kim on 2017. 5. 29..
 */

public class BrandMainAdapter extends RecyclerView.Adapter<BrandMainAdapter.BrandMainViewHolder> {

    ArrayList<BrandMainData.Datum> brandArray;
    Context mContext;

    public BrandMainAdapter(ArrayList<BrandMainData.Datum> brandArray, Context mContext) {
        this.brandArray = brandArray;
        this.mContext = mContext;
    }

    @Override
    public BrandMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.brand_main, parent, false);
        return new BrandMainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BrandMainViewHolder holder, int position) {
        final BrandMainData.Datum brand = brandArray.get(position);

        Glide.with(mContext).load(brand.brandImageUrl).into(holder.brandMain);
        holder.brandName.setText(brand.brandName);
        holder.brandDescript.setText(brand.brandMemo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(mContext, BrandDetailActivity.class);
                detailIntent.putExtra("brandId", brand.brandId);
                Log.d("dfa", "brand"+brand.brandId);
                mContext.startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brandArray.size();
    }

    public class BrandMainViewHolder extends RecyclerView.ViewHolder{

        ImageView brandMain;
        TextView brandName, brandDescript;

        public BrandMainViewHolder(View itemView) {
            super(itemView);
            brandMain = (ImageView) itemView.findViewById(R.id.brandMain);
            brandName = (TextView) itemView.findViewById(R.id.brandMainName);
            brandDescript = (TextView) itemView.findViewById(R.id.brandDescription);
        }
    }
}
