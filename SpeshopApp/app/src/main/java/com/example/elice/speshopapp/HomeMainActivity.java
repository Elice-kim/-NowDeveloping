package com.example.elice.speshopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.elice.speshopapp.brand.BrandFragment;
import com.example.elice.speshopapp.gift.GiftFragment;
import com.example.elice.speshopapp.list.DayListFragment;
import com.example.elice.speshopapp.more.MoreFragment;
import com.example.elice.speshopapp.search.SearchActivity;

import java.util.ArrayList;

public class HomeMainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    MyFragmentAdapter adapter;
    ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        searchIcon = (ImageView) findViewById(R.id.searchIcon);
        setFragmentAdapter(viewPager);
        moveToSearch();
    }

    private void setFragmentAdapter(ViewPager viewPager) {
        adapter = new MyFragmentAdapter(getSupportFragmentManager());
        adapter.appendFragment(GiftFragment.newInstance(), getResources().getString(R.string.gift));
        adapter.appendFragment(DayListFragment.newInstance(), getResources().getString(R.string.list));
        adapter.appendFragment(BrandFragment.newInstance(), getResources().getString(R.string.brand));
        adapter.appendFragment(MoreFragment.newInstance(), getResources().getString(R.string.more));
        viewPager.setAdapter(adapter);
    }

    private void moveToSearch() {
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> menuFragment = new ArrayList<>();
        ArrayList<String> tabTitle = new ArrayList<>();

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void appendFragment(Fragment fragment, String title) {
            menuFragment.add(fragment);
            tabTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return menuFragment.get(position);
        }

        @Override
        public int getCount() {
            return menuFragment.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }
}
