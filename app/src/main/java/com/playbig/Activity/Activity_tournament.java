package com.playbig.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.playbig.R;
import com.playbig.modal.BaseActivity;
import com.playbig.util.ConstantCodes;
import com.playbig.util.PreferenceUtility;

/**
 * Created by User on 2/20/2016.
 */
public class Activity_tournament extends BaseActivity {






    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    LinearLayout ly_placeabat,ly_ranking;
    private TextView txt_title;
    PreferenceUtility preferenceUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);

        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);

        txt_title = (TextView)findViewById(R.id.toolbar_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //  getSupportActionBar().setTitle("Welcome User");


        txt_title.setText("ICC World cup") ;



    }









    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {


        preferenceUtility = PreferenceUtility.getSession(mActivity);


        ly_placeabat = (LinearLayout) links(R.id.ly_placeabet);
        ly_ranking = (LinearLayout) links(R.id.ly_ranking);


        ly_placeabat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preferenceUtility.getBoolean(ConstantCodes.PREFERENCE_KEY.IS_USER_LOGGED_IN) == true) {
                    startActivity(new Intent(mActivity, Activity_tournamentplaceabet.class));
                    mActivity.finish();
                }else {
                    Toast.makeText(mActivity, "Kindly Login First", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(mActivity, Activity_Login.class);
                    startActivity(i);
                }
            }
        });


        ly_ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, Activity_tournamentranking.class);
                startActivity(i);
                mActivity.finish();
            }
        });

    }

    @Override
    public void addAdapter() {

    }

    @Override
    public void loadData() {

    }





    }





