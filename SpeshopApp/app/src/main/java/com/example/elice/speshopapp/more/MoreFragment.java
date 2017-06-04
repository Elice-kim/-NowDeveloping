package com.example.elice.speshopapp.more;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.elice.speshopapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    ImageView editBtn;

    public MoreFragment() {
        // Required empty public constructor
    }

    public static MoreFragment newInstance(){
        MoreFragment moreFragment = new MoreFragment();
        return moreFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_more, container, false);
        editBtn = (ImageView) v.findViewById(R.id.editProfile);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editInfo = new Intent(getContext(), EditMyInfoActivity.class);
                startActivity(editInfo);
            }
        });
        return v;
    }

}
