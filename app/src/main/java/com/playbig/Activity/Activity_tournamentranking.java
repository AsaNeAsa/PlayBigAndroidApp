package com.playbig.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.playbig.R;
import com.playbig.fragment.Fragment_quickbetFriendranking;
import com.playbig.fragment.Fragment_quickbetGlobalranking;
import com.playbig.fragment.Fragment_tournamentFriendranking;
import com.playbig.fragment.Fragment_tournamentGlobalranking;
import com.playbig.modal.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/23/2016.
 */
public class Activity_tournamentranking  extends BaseActivity {






    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView txt_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournamentrankig);

        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        txt_title = (TextView)findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //  getSupportActionBar().setTitle("Welcome User");


        txt_title.setText("Ranking") ;



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mActivity, MainActivity.class);
                startActivity(i);

            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_tournamentFriendranking(), "Friend");
        adapter.addFragment(new Fragment_tournamentGlobalranking(), "Global");

        //adapter.addFragment(new ThreeFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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
}
