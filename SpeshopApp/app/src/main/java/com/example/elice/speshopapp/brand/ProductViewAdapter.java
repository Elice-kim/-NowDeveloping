package com.example.elice.speshopapp.brand;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.brand.data.DetailBrandData;

import java.util.ArrayList;

/**
 * Created by elice.kim on 2017. 5. 30..
 */

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewAdapter.ProductViewHolder> {

    Context mContext;
    ArrayList<DetailBrandData.Datum.Product> brandProduct;

    public ProductViewAdapter(Context mContext, ArrayList<DetailBrandData.Datum.Product> brandProduct) {
        this.mContext = mContext;
        this.brandProduct = brandProduct;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.brand_product_list, parent,false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        DetailBrandData.Datum.Product productInfo = brandProduct.get(position);

        Glide.with(mContext).load(productInfo.imageUrl).into(holder.productImg);
        holder.productName.setText(productInfo.productName);
        holder.productMemo.setText(productInfo.productMemo);
        holder.productPrice.setText(productInfo.productPrice+" 원");

    }

    @Override
    public int getItemCount() {
        return brandProduct.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView productImg,likeBtn;
        TextView productName, productMemo, productPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productImg = (ImageView) itemView.findViewById(R.id.brandProduct);
            likeBtn = (ImageView) itemView.findViewById(R.id.likeBtn);
            productName = (TextView) itemView.findViewById(R.id.productFullName);
            productMemo = (TextView) itemView.findViewById(R.id.productMemo);
            productPrice = (TextView) itemView.findViewById(R.id.productFullPrice);
        }
    }
}
