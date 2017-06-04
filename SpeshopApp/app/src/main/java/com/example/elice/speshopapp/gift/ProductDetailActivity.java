package com.example.elice.speshopapp.gift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.apimodule.DefaultRestClient;
import com.example.elice.speshopapp.gift.data.DetailProductData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ImageButton backBtn;
    private ViewPager imageViewpager;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private DotPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent product = getIntent();
        int productId = product.getIntExtra("productId", 1);

        imageViewpager = (ViewPager) findViewById(R.id.imageViewPager);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        mAdapter = new DotPagerAdapter(ProductDetailActivity.this);
        imageViewpager.setAdapter(mAdapter);
        imageViewpager.setCurrentItem(0);
        imageViewpager.addOnPageChangeListener(this);
        requestToServer(productId);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setUiPageViewController();
    }

    private void requestToServer(int productId) {
        DefaultRestClient<ProductDetailService> service = new DefaultRestClient<>();
        ProductDetailService productService = service.getService(ProductDetailService.class);
        Call<DetailProductData> callProduct = productService.getProductInfo(productId);
        callProduct.enqueue(new Callback<DetailProductData>() {
            @Override
            public void onResponse(Call<DetailProductData> call, Response<DetailProductData> response) {

                if (response.isSuccessful()) {
                    DetailProductData data = response.body();
                    int size = data.data.get(0).imageUrls.size();
                    Log.d("sfad", "sdfasdf" + size);

                    ArrayList<String> mImageResources = new ArrayList<String>();
                    for (int i = 0; i < size; i++) {
                        mImageResources.add(data.data.get(0).imageUrls.get(i).imageUrl);
                    }
                    mAdapter.setmResources(mImageResources);
                    mAdapter.notifyDataSetChanged();
                    drawDots();
                }
            }

            @Override
            public void onFailure(Call<DetailProductData> call, Throwable t) {

            }
        });

    }

    private void setUiPageViewController() {
        if (mAdapter.getCount() == 0) {
            return;
        }
        drawDots();
    }

    private void drawDots() {
        int itemSize = mAdapter.getCount();
        dots = new ImageView[itemSize];
        for (int i = 0; i < itemSize; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.nonselecteditem_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 10, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
