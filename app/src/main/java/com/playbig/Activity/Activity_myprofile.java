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
import android.widget.Toast;

import com.playbig.R;
import com.playbig.customview.CircularImageView;
import com.playbig.fragment.Fragment_quickbet;
import com.playbig.fragment.Fragment_tournament;
import com.playbig.modal.BaseActivity;
import com.playbig.util.ConstantCodes;
import com.playbig.util.PreferenceUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * Created by HP on 10-02-2016.
 */
public class Activity_myprofile extends BaseActivity   {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView txt_title;
    private PreferenceUtility preference_obj;

private CircularImageView account_pic;


    String profilepic;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        txt_title = (TextView)findViewById(R.id.toolbar_title);
        account_pic = (CircularImageView)findViewById(R.id.Profile_picture);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //  getSupportActionBar().setTitle("Welcome User");

        preference_obj = PreferenceUtility.getSession(getApplicationContext());
        txt_title.setText("My Profile") ;


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mActivity, MainActivity.class);
                startActivity(i);


            }
        });


        preference_obj = PreferenceUtility.getSession(mActivity);
        if (preference_obj != null) {
            profilepic = preference_obj.getString((ConstantCodes.PREFERENCE_KEY.USER_PROFILE_PIC));
            String User_id = preference_obj.getString((ConstantCodes.PREFERENCE_KEY.USER_ID));
            Toast.makeText(getApplicationContext(), "Welcome, " + User_id, Toast.LENGTH_SHORT).show();

           Picasso.with(mActivity).load(profilepic).into(account_pic);





           // customer_id = preference_obj.getString(ConstantCodes.PREFERENCE_KEY_CUSTOMER_ID);
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_tournament(), "Overall");
        adapter.addFragment(new Fragment_tournament(), "Tournaments");
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
