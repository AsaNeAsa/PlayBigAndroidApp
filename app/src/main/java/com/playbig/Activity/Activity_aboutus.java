package com.playbig.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.playbig.R;
import com.playbig.modal.BaseActivity;
/**
 * Created by HP on 10-02-2016.
 */






public class Activity_aboutus extends BaseActivity {

       private  Toolbar toolbar;
    private  TextView txt_title;
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);



           toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
           setSupportActionBar(toolbar);

           txt_title = (TextView)findViewById(R.id.toolbar_title);
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           getSupportActionBar().setDisplayShowHomeEnabled(true);
           //  getSupportActionBar().setTitle("Welcome User");


           txt_title.setText("About Us") ;


           toolbar.setNavigationOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent i = new Intent(mActivity,MainActivity.class);
                   startActivity(i);

               }
           });




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
