package com.example.elice.speshopapp.gift;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.apimodule.DefaultRestClient;
import com.example.elice.speshopapp.gift.data.FilterActivity;
import com.example.elice.speshopapp.gift.data.KeywordData;
import com.example.elice.speshopapp.gift.data.UpdateProductData;
import com.example.elice.speshopapp.widget.KeywordItemView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftFragment extends Fragment {

    LinearLayout keywordLayout;
    RecyclerView giftView;
    ArrayList<UpdateProductData.Datum> giftArray;
    ArrayList<UpdateProductData.Datum> resultArray;
//    ArrayList<GiftMainData.Datum> mainArray;
//    ArrayList<GiftMainData.Datum> baseArray;
    GiftImageViewAdapter viewAdapter;
    FloatingActionButton filterBtn;

    public GiftFragment() {
        // Required empty public constructor
    }

    public static GiftFragment newInstance() {
        GiftFragment giftFragment = new GiftFragment();
        return giftFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gift, container, false);
        keywordLayout = (LinearLayout) v.findViewById(R.id.keywordLayout);
        giftView = (RecyclerView) v.findViewById(R.id.giftRecyclerView);
        filterBtn = (FloatingActionButton) v.findViewById(R.id.filterBtn);
        giftArray = new ArrayList<>();
//        mainArray = new ArrayList<>();
//        baseArray = new ArrayList<>();
        viewAdapter = new GiftImageViewAdapter(giftArray, getContext());
        giftView.setAdapter(viewAdapter);
        giftView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        giftView.setNestedScrollingEnabled(false);
        moveToFilter();
//        giftMain();
        updateKeyword();

        return v;
    }

    private void moveToFilter() {
        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filter = new Intent(getContext(), FilterActivity.class);
                startActivity(filter);
            }
        });
    }

//    private void giftMain() {
//        DefaultRestClient<GiftMainService> giftMain = new DefaultRestClient<>();
//        GiftMainService gift = giftMain.getService(GiftMainService.class);
//        Call<GiftMainData> callGift = gift.getGiftMain();
//        callGift.enqueue(new Callback<GiftMainData>() {
//            @Override
//            public void onResponse(Call<GiftMainData> call, Response<GiftMainData> response) {
//                if (response.isSuccessful()) {
//                    mainArray = (ArrayList<GiftMainData.Datum>) response.body().data;
//
//                    for(GiftMainData.Datum main : mainArray){
//                        baseArray.add(main);
//                        viewAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GiftMainData> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void updateKeyword() {
        DefaultRestClient<GiftMainService> keywordRecommend = new DefaultRestClient<>();
        GiftMainService keyword = keywordRecommend.getService(GiftMainService.class);
        Call<KeywordData> call = keyword.getKeyword();
        call.enqueue(new Callback<KeywordData>() {
            @Override
            public void onResponse(Call<KeywordData> call, Response<KeywordData> response) {
                if (response.isSuccessful()) {
                    KeywordData keyword = response.body();

                    for (KeywordData.Datum data : keyword.data) {
                        KeywordItemView keywordItemView = new KeywordItemView(getContext());
                        keywordItemView.setKeyword(data.categoryName, data.categoryId);
                        keywordLayout.addView(keywordItemView);

                        keywordItemView.setTagClickListener(new KeywordItemView.TagClickListener() {
                            @Override
                            public void onTagClicked(final int categoryId) {
                                //서버에 categoryId를 이용해서 리스트를 가져옴

                                DefaultRestClient<GiftMainService> keywordImg = new DefaultRestClient<GiftMainService>();
                                GiftMainService giftList = keywordImg.getService(GiftMainService.class);
                                Call<UpdateProductData> call2 = giftList.getProduct(categoryId);
                                call2.enqueue(new Callback<UpdateProductData>() {
                                    @Override
                                    public void onResponse(Call<UpdateProductData> call, Response<UpdateProductData> response) {
                                        if (response.isSuccessful()) {
                                            resultArray = (ArrayList<UpdateProductData.Datum>) response.body().data;

                                            giftArray.clear();
                                            for (UpdateProductData.Datum data : resultArray) {
                                                giftArray.add(data);
                                                viewAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<UpdateProductData> call, Throwable t) {

                                    }
                                });
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<KeywordData> call, Throwable t) {

            }
        });
    }

}
