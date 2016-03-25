package com.playbig.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;


import com.playbig.adapter.PagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;
import com.playbig.R;
import com.playbig.fragment.Fragment_quickbet;
import com.playbig.fragment.Fragment_tournament;

import java.util.List;
import java.util.Vector;

public class MainActivityold extends AppCompatActivity implements OnTabChangeListener, OnPageChangeListener {




    private TabHost host;
    public static ViewPager pager;
    public static TabHost tabs;

    PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainwithtab);
       /* Toolbar custom_toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(custom_toolbar);




       //setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("LOG IN");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
*/


        tabs = (TabHost) findViewById(android.R.id.tabhost);
        pager = (ViewPager) findViewById(R.id.pager);

        tabs.setup();




        setUpTab(savedInstanceState);
        tabs.getTabWidget().setDividerDrawable(null);

        tabs.setOnTabChangedListener((OnTabChangeListener) this);


       // Intialise ViewPager
        intialiseViewPager();
        tabs.getTabWidget().setDividerDrawable(null);
        pager.setOffscreenPageLimit(2);


    }

    private void intialiseViewPager() {

        List<Fragment> fragments = new Vector<Fragment>();
        fragments.add(Fragment.instantiate(this, Fragment_quickbet.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment_tournament.class.getName()));

        this.mPagerAdapter = new PagerAdapter(
                super.getSupportFragmentManager(), fragments);
        //

        this.pager.setAdapter(this.mPagerAdapter);
        this.pager.setOnPageChangeListener((OnPageChangeListener) this);
    }






    public void setUpTab(Bundle args) {

        //		TabInfo tabInfo = null;

        View view_BookNow = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.tab_layout, null);
        ImageView tab_icon = (ImageView) view_BookNow
                .findViewById(R.id.img_tab_icon);
        TextView txt_tab_name = (TextView) view_BookNow
                .findViewById(R.id.txt_tab_name);
      //  tab_icon.setBackgroundResource(R.drawable.profile_icon);
        txt_tab_name.setText("Quick Bet");
        view_BookNow.setBackgroundResource(R.drawable.tab_bg);
        TabSpec spec = tabs.newTabSpec("Quick Bet");
        spec.setContent(R.id.tab1);
        spec.setIndicator(view_BookNow);
        tabs.addTab(spec);




        View view_history = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.tab_layout, null);
        ImageView tab_icon1 = (ImageView) view_history
                .findViewById(R.id.img_tab_icon);
        TextView txt_tab_name1 = (TextView) view_history
                .findViewById(R.id.txt_tab_name);
      //  tab_icon1.setBackgroundResource(R.drawable.connection);
        txt_tab_name1.setText("Tournament");
        view_history.setBackgroundResource(R.drawable.tab_bg);
        spec = tabs.newTabSpec("Tournament");
        spec.setContent(R.id.tab2);
        spec.setIndicator(view_history);
        tabs.addTab(spec);


        tabs.setOnTabChangedListener((OnTabChangeListener) this);


    }




    public void onTabChanged(String tabId) {


        int pageNumber;
        if (tabId.equals("Quick Bet")) {


            pageNumber = 0;
        } else if (tabId.equals("Tournament")) {

            pageNumber = 1;
        }
         else {


            pageNumber = 2;
        }

        pager.setCurrentItem(pageNumber);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int pageNumber) {



        if (pageNumber==0) {
            pager.getAdapter().notifyDataSetChanged();
          //  onTaskComplete = (OnTaskComplete) this.mPagerAdapter.getFragment(0);
          //  ((Fragment_quickbet) mPagerAdapter.getFragment(0)).getdata();
           // txt_title_service.setVisibility(View.GONE);

            pageNumber = 0;
        } else if (pageNumber==1) {
          //  onTaskComplete = (OnTaskComplete) this.mPagerAdapter.getFragment(1);
         //   ((Fragment_tournament) mPagerAdapter.getFragment(1)).getdata();
         //   txt_title_service.setVisibility(View.GONE);
            pageNumber = 1;
        }
       else {

           // onTaskComplete = (OnTaskComplete) this.mPagerAdapter.getFragment(3);
           // ((Tab2Fragment) mPagerAdapter.getFragment(3)).getdata();
            pageNumber = 3;
        }
        tabs.setCurrentTab(pageNumber);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
