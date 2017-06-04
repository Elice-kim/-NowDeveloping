package com.example.elice.speshopapp.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.elice.speshopapp.R;

/**
 * Created by elice.kim on 2017. 5. 27..
 */

public class KeywordItemView extends FrameLayout {

    private TextView keywordTextView;
    private String keyword;
    private int categoryId;

    private TagClickListener tagClickListener;

    public KeywordItemView(@NonNull Context context) {
        this(context, null);
    }

    public KeywordItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeywordItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.item_keyword, this);
        keywordTextView = (TextView) view.findViewById(R.id.keywordTextView);
        keywordTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tagClickListener != null){
                    tagClickListener.onTagClicked(categoryId);
                }
            }
        });
    }

    public void setTagClickListener(TagClickListener tagClickListener) {
        this.tagClickListener = tagClickListener;
    }

    public void setKeyword(String keyword, int categoryId) {
        this.keyword = keyword;
        this.categoryId = categoryId;
        keywordTextView.setText(keyword);
    }

    public interface TagClickListener {
        void onTagClicked(int categoryId);
    }
}
