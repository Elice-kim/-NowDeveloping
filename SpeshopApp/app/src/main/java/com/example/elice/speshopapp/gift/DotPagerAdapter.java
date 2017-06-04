package com.example.elice.speshopapp.gift;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.elice.speshopapp.R;

import java.util.ArrayList;

/**
 * Created by elice.kim on 2017. 6. 4..
 */

public class DotPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<String> mResources = new ArrayList<>();

    public DotPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmResources(ArrayList<String> mResources) {
        this.mResources = mResources;
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.product_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        Glide.with(mContext).load(mResources.get(position)).into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
