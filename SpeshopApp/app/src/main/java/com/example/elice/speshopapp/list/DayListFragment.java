package com.example.elice.speshopapp.list;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.list.data.DayData;
import com.example.elice.speshopapp.widget.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayListFragment extends Fragment {

    ArrayList<DayData> dataArray;
    FloatingActionButton addDaysBtn;
    RecyclerView dayView;
    DayViewAdapter adapter;
    TextView removeBtn;
    TextView allSelectBtn;


    public DayListFragment() {
        // Required empty public constructor
    }

    public static DayListFragment newInstance() {
        DayListFragment dayListFragment = new DayListFragment();
        return dayListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day_list, container, false);
        addDaysBtn = (FloatingActionButton) v.findViewById(R.id.addDays);
        dayView = (RecyclerView) v.findViewById(R.id.dayList);
        removeBtn = (TextView) v.findViewById(R.id.removeDaysBtn);
        removeBtn.setOnClickListener(changeToCheckListener);
        allSelectBtn = (TextView) v.findViewById(R.id.allSelectBtn);
        allSelectBtn.setOnClickListener(allCheckedChangeListener);
        dataArray = new ArrayList<>();
        adapter = new DayViewAdapter(getContext(), dataArray);
        dayView.setAdapter(adapter);
        dayView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        moveToAddDays();
        prepareMovie();

        return v;
    }

    private void moveToAddDays() {
        addDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addDay = new Intent(getContext(), AddDayActivity.class);
                startActivity(addDay);
            }
        });
    }

    View.OnClickListener changeToCheckListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean isCheckBoxShowed = removeBtn.getText().toString().equals("삭제");

            if(isCheckBoxShowed){
                removeBtn.setText("편집");
            }else{
                removeBtn.setText("삭제");
            }
            ViewUtil.setVisible(allSelectBtn, !isCheckBoxShowed);
            List<DayData> dataList = adapter.getDayList();
            for(DayData dayData : dataList){
                dayData.isCheckBoxShowed = !isCheckBoxShowed;
            }
            adapter.notifyDataSetChanged();
        }
    };

    View.OnClickListener allCheckedChangeListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            List<DayData> dataList = adapter.getDayList();
            for(DayData day : dataList){
                day.isChecked = true;
            }
            adapter.notifyDataSetChanged();
        }
    };

    private void prepareMovie() {


        DayData movie = new DayData("20170601", "발렌타인", "6월1일", "D-day", "선물하기", R.drawable.ic_job_no_touch, true);
        dataArray.add(movie);

        movie = new DayData("20170604", "생일", "6월4일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);

        movie = new DayData("20170609", "100일", "6월9일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170614", "중간고사 시험", "6월14일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170622", "하하하", "6월22일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170625", "하하하", "6월25일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170703", "하하하", "7월03일", "D-day", "선물하기", R.drawable.ic_job_no_touch, true);
        dataArray.add(movie);

        movie = new DayData("20170705", "하하하", "7월05일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170709", "하하하", "7월09일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170728", "하하하", "7월28일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);
        movie = new DayData("20170730", "하하하", "7월30일", "D-day", "선물하기", R.drawable.ic_job_no_touch, false);
        dataArray.add(movie);


        adapter.notifyDataSetChanged();
    }


}
