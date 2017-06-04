package com.example.elice.speshopapp.brand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.apimodule.DefaultRestClient;
import com.example.elice.speshopapp.brand.data.DetailBrandData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrandDetailActivity extends AppCompatActivity {

    ImageButton backBtn;
    RecyclerView productView;
    ProductViewAdapter adapter;
    ArrayList<DetailBrandData.Datum.Product> baseArray;
    ArrayList<DetailBrandData.Datum.Product> resultArray;
    TextView titleName;
    TextView brandDetailName;
    TextView brandDetail;
    ImageView brandImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_detail);

        productView = (RecyclerView) findViewById(R.id.brandDetailRecycle);
        titleName = (TextView) findViewById(R.id.brandTitle);
        brandImage = (ImageView) findViewById(R.id.brandImage);
        brandDetailName = (TextView) findViewById(R.id.brandDetailName);
        brandDetail = (TextView) findViewById(R.id.brandDetail);
        baseArray = new ArrayList<>();
        resultArray = new ArrayList<>();
        adapter = new ProductViewAdapter(this, baseArray);
        productView.setAdapter(adapter);
        productView.setLayoutManager(new GridLayoutManager(this, 2));

        Intent brandIntent = getIntent();
        int brandId = brandIntent.getIntExtra("brandId", 0);

        backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtnAction();
        updateBrandDetail(brandId);
    }

    private void updateBrandDetail(final int brandId) {
        DefaultRestClient<BrandSetting> brandSetting = new DefaultRestClient<>();
        BrandSetting brand = brandSetting.getService(BrandSetting.class);
        Call<DetailBrandData> productCall = brand.getProductList(brandId);
        productCall.enqueue(new Callback<DetailBrandData>() {
            @Override
            public void onResponse(Call<DetailBrandData> call, Response<DetailBrandData> response) {
                if(response.isSuccessful()){

                    Glide.with(getApplicationContext()).load(response.body().data.get(0).brandImageUrl)
                            .into(brandImage);
                    titleName.setText(response.body().data.get(0).brandName);
                    brandDetailName.setText(response.body().data.get(0).brandName);
                    brandDetail.setText(response.body().data.get(0).brandMemo);

                    resultArray = (ArrayList<DetailBrandData.Datum.Product>) response.body().data.get(0).products;

                    for(DetailBrandData.Datum.Product data : resultArray){
                        baseArray.add(data);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailBrandData> call, Throwable t) {

            }
        });

    }

    private void backBtnAction() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
