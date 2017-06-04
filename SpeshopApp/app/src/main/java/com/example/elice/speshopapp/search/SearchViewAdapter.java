package com.example.elice.speshopapp.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.search.data.SearchData;

import java.util.ArrayList;

/**
 * Created by elice.kim on 2017. 5. 31..
 */

public class SearchViewAdapter extends RecyclerView.Adapter<SearchViewAdapter.SearchViewHolder> {

    ArrayList<SearchData.Datum> searchArray;
    Context mContext;

    public SearchViewAdapter(ArrayList<SearchData.Datum> searchArray, Context mContext) {
        this.searchArray = searchArray;
        this.mContext = mContext;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.search_result, parent, false);
        return new SearchViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        SearchData.Datum result = searchArray.get(position);

        Glide.with(mContext).load(result.shopUrl).into(holder.productImg);
        holder.productName.setText(result.productName);
        holder.productBrand.setText(result.productMemo);
        holder.productPrice.setText(result.productPrice+" 원");

    }

    @Override
    public int getItemCount() {
        return searchArray.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{

        ImageView productImg,likeBtn;
        TextView productName, productBrand, productPrice;

        public SearchViewHolder(View itemView) {
            super(itemView);

            productImg = (ImageView) itemView.findViewById(R.id.searchProduct);
            likeBtn = (ImageView) itemView.findViewById(R.id.likeBtn);
            productName = (TextView) itemView.findViewById(R.id.searchName);
            productBrand = (TextView) itemView.findViewById(R.id.searchBrand);
            productPrice = (TextView) itemView.findViewById(R.id.searchPrice);

        }
    }
}
