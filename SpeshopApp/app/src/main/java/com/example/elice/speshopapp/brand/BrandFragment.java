package com.example.elice.speshopapp.brand;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.apimodule.DefaultRestClient;
import com.example.elice.speshopapp.brand.data.BrandMainData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrandFragment extends Fragment {

    private RecyclerView brandView;
    private BrandMainAdapter brandAdapter;
    private ArrayList<BrandMainData.Datum> baseArray;
    private ArrayList<BrandMainData.Datum> resultArray;

    public BrandFragment() {
        // Required empty public constructor
    }

    public static BrandFragment newInstance(){
        BrandFragment brandFragment = new BrandFragment();
        return brandFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_brand, container, false);
        brandView = (RecyclerView) v.findViewById(R.id.brandRecyclerView);
        baseArray = new ArrayList<>();
        brandAdapter = new BrandMainAdapter(baseArray, getContext());
        brandView.setAdapter(brandAdapter);
        brandView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        
        updateBrandMain();
        
        return v;
    }

    private void updateBrandMain() {

        DefaultRestClient<BrandSetting> brandUpdate = new DefaultRestClient<>();
        BrandSetting brand = brandUpdate.getService(BrandSetting.class);
        Call<BrandMainData> callBrand = brand.getBrandMain();
        callBrand.enqueue(new Callback<BrandMainData>() {
            @Override
            public void onResponse(Call<BrandMainData> call, Response<BrandMainData> response) {
                if(response.isSuccessful()){
                    resultArray = (ArrayList<BrandMainData.Datum>) response.body().data;


                    for(BrandMainData.Datum brand : resultArray){
                        Log.d("sdsf","adsfs");
                        baseArray.add(brand);
                        brandAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<BrandMainData> call, Throwable t) {

            }
        });
    }

}
