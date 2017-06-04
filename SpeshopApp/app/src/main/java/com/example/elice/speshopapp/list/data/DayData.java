package com.example.elice.speshopapp.list.data;

/**
 * Created by elice.kim on 2017. 6. 2..
 */

public class DayData {

    String yearMonth,dayName, dayDate, dDay, goPresent;
    int dayImage;
    boolean needShow;
    public boolean isCheckBoxShowed;
    public boolean isChecked;

    public DayData(String yearMonth, String dayName, String dayDate, String dDay, String goPresent, int dayImage, boolean needShow) {
        this.yearMonth = yearMonth;
        this.dayName = dayName;
        this.dayDate = dayDate;
        this.dDay = dDay;
        this.goPresent = goPresent;
        this.dayImage = dayImage;
        this.needShow = needShow;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public String getDayName() {
        return dayName;
    }

    public String getDayDate() {
        return dayDate;
    }

    public String getdDay() {
        return dDay;
    }

    public String getGoPresent() {
        return goPresent;
    }

    public int getDayImage() {
        return dayImage;
    }

    public boolean isNeedShow() {
        return needShow;
    }
}
