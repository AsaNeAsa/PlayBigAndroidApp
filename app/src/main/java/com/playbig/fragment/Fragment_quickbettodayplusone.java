package com.playbig.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.playbig.Activity.Activity_myprofile;
import com.playbig.R;
import com.playbig.adapter.Adapter_tournamenttodayplusone;
import com.playbig.customview.SimpledividerItemdecoration;
import com.playbig.modal.BaseFragment;
import com.playbig.modal.ItemData;

/**
 * Created by User on 2/22/2016.
 */
public class Fragment_quickbettodayplusone extends BaseFragment {






    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quickbettodayplusone, container, false);
        init(view);
        return view;
    }



    private void init(View view) {


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.quickbet_todayplusone);
        recyclerView.addItemDecoration(new SimpledividerItemdecoration(mActivity));


        ItemData itemsData[] = { new ItemData("ICC WORLDCUP",R.drawable.ic_launcher),
                new ItemData("IPL",R.drawable.ic_launcher),
                new ItemData("CHAMPIONS TROPHY",R.drawable.ic_launcher),
                new ItemData("BIGBASH",R.drawable.ic_launcher),
                new ItemData("BPL",R.drawable.ic_launcher),
                new ItemData("COUNTY",R.drawable.ic_launcher)};


        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        Adapter_tournamenttodayplusone adp_quickplacebettodayplusone = new Adapter_tournamenttodayplusone(itemsData,mActivity);
        recyclerView.setAdapter(adp_quickplacebettodayplusone);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, Activity_myprofile.class);
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
