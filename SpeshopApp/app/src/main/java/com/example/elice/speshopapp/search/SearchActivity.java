package com.example.elice.speshopapp.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.apimodule.DefaultRestClient;
import com.example.elice.speshopapp.search.data.SearchData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    TextView totalText;
    ImageButton backBtn;
    EditText searchText;
    ArrayList<SearchData.Datum> searchArray;
    ArrayList<SearchData.Datum> baseArray;
    SearchViewAdapter adapter;
    RecyclerView searchRecycle;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchText = (EditText) findViewById(R.id.searchBar);
        totalText = (TextView) findViewById(R.id.totalSearch);
        backBtn = (ImageButton) findViewById(R.id.backBtn);
        searchArray = new ArrayList<>();
        baseArray = new ArrayList<>();
        searchRecycle = (RecyclerView) findViewById(R.id.searchRecycle);
        adapter = new SearchViewAdapter(baseArray, this);
        searchRecycle.setAdapter(adapter);
        searchRecycle.setLayoutManager(new GridLayoutManager(this, 2));
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        gobackBtn();
        searchText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    String searchKey = searchText.getText().toString();
//                    searchResult(searchKey);
                    imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


    }

    private void gobackBtn() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void searchResult(String searchKey) {

        DefaultRestClient<SearchService> search = new DefaultRestClient<>();
        SearchService service = search.getService(SearchService.class);
        Call<SearchData> callSearch = service.getSearch(searchKey);
        callSearch.enqueue(new Callback<SearchData>() {
            @Override
            public void onResponse(Call<SearchData> call, Response<SearchData> response) {
                if(response.isSuccessful()){

                    searchArray = (ArrayList<SearchData.Datum>) response.body().data;

                    if(searchArray.size()>0){
                        totalText.setText("총 "+searchArray.size()+"개의 상품리스트");

                        baseArray.clear();
                        for(SearchData.Datum data: searchArray){
                            baseArray.add(data);
                            adapter.notifyDataSetChanged();
                        }
                    }
                    else{

                    }
                }
            }

            @Override
            public void onFailure(Call<SearchData> call, Throwable t) {

            }
        });
    }
}
