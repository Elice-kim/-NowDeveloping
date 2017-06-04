package com.example.elice.speshopapp.widget;

import android.view.View;

/**
 * Created by elice.kim on 2017. 6. 3..
 */

public class ViewUtil {

    public static void setVisible(View view, boolean isVisible) {
        if (isVisible) {
            view.setVisibility(View.VISIBLE);
            return;
        }
        view.setVisibility(View.GONE);
    }
}
