package com.example.elice.speshopapp.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elice.speshopapp.R;
import com.example.elice.speshopapp.list.data.DayData;
import com.example.elice.speshopapp.widget.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elice.kim on 2017. 6. 2..
 */

public class DayViewAdapter extends RecyclerView.Adapter<DayViewAdapter.DayViewHolder> {

    private Context mContext;
    private ArrayList<DayData> data;

    public DayViewAdapter(Context mContext, ArrayList<DayData> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public List<DayData> getDayList() {
        return data;
    }

    @Override
    public DayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.day_list_main, parent, false);
        return new DayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DayViewHolder holder, int position) {
        final DayData dayData = data.get(position);
        String totalDay = dayData.getYearMonth();
        String year = totalDay.substring(0, 4) + "년 ";
        String day = totalDay.substring(5, 6) + "월";

        holder.dayImage.setImageResource(dayData.getDayImage());
        holder.dayName.setText(dayData.getDayName());
        holder.dayDate.setText(dayData.getDayDate());
        holder.dDay.setText(dayData.getdDay());
        holder.goPresent.setText(dayData.getGoPresent());

        if (dayData.isNeedShow()) {
            holder.yearMonth.setText(year + day);
        }
        //else로 하면 true인 뷰들은 저장이 안됨 //리사이클러뷰는 재활용해서 계속 불리므로
        ViewUtil.setVisible(holder.yearMonth, dayData.isNeedShow());
        ViewUtil.setVisible(holder.goPresent, !dayData.isCheckBoxShowed);
        ViewUtil.setVisible(holder.removeCheckBox, dayData.isCheckBoxShowed);
        holder.removeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dayData.isChecked = isChecked;
            }
        });
        holder.removeCheckBox.setChecked(dayData.isChecked);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sdfa", "Adfs");
                Intent customIntent = new Intent(mContext, CustomizeActivity.class);
                mContext.startActivity(customIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {

        ImageView dayImage;
        TextView yearMonth, dayName, dayDate, dDay, goPresent;
        CheckBox removeCheckBox;
        CardView cardView;

        public DayViewHolder(View itemView) {
            super(itemView);

            dayImage = (ImageView) itemView.findViewById(R.id.dayImage);
            dayName = (TextView) itemView.findViewById(R.id.dayName);
            dayDate = (TextView) itemView.findViewById(R.id.dayDate);
            dDay = (TextView) itemView.findViewById(R.id.dDay);
            goPresent = (TextView) itemView.findViewById(R.id.goPresent);
            yearMonth = (TextView) itemView.findViewById(R.id.yearMonth);
            removeCheckBox = (CheckBox) itemView.findViewById(R.id.removeCheckBtn);
            cardView = (CardView) itemView.findViewById(R.id.itemCard);
        }
    }
}
