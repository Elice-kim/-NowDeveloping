package com.example.elice.speshopapp.more;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.elice.speshopapp.R;

public class EditMyInfoActivity extends AppCompatActivity {

    TextView editImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_info);
        editImage = (TextView) findViewById(R.id.profileEdit);

    }
}
