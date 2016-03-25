package com.playbig.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.playbig.R;
import com.playbig.fragment.Fragment_quickbet;
import com.playbig.fragment.Fragment_tournament;
import com.playbig.modal.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/9/2016.
 */ public class  MainActivity  extends BaseActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        txt_title = (TextView)findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().setTitle("Welcome User");






        txt_title.setText("Welcome User") ;

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_quickbet(), "Quickbet");
        adapter.addFragment(new Fragment_tournament(), "Tournaments");
        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);





    }

    @Override
    public void initVariable() {

    }

    @Override

    public void initView() {

    }

    @Override
    public void addAdapter() {

    }

    @Override
    public void loadData() {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }









    }
}


