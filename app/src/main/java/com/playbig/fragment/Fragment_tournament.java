package com.playbig.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.playbig.Activity.Activity_myprofile;
import com.playbig.Activity.Activity_tournament;
import com.playbig.Activity.MainActivity;
import com.playbig.R;

import com.playbig.adapter.Adapter_tournament;
import com.playbig.customview.SimpledividerItemdecoration;
import com.playbig.modal.BaseFragment;
import com.playbig.modal.ItemData;

import java.util.ArrayList;

/**
 * Created by User on 2/8/2016.
 */
public class Fragment_tournament extends BaseFragment {


    ArrayAdapter<String> adapter_tournament;
    ArrayList  post_list;
   Adapter Adapter_tournament;
   // Adapter_tournament adp_tournament;
    public Fragment_tournament() {



    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tournament, container, false);
        init(view);
        return view;
    }



    protected void init(View view) {


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.tournament_recycle_view);


        final ItemData itemsData[] = { new ItemData("ICC WORLDCUP",R.drawable.ic_launcher),
                new ItemData("IPL",R.drawable.gold_coin),
                new ItemData("CHAMPIONS TROPHY",R.drawable.ic_launcher),
                new ItemData("BIGBASH",R.drawable.ic_launcher),
                new ItemData("BPL",R.drawable.ic_launcher),
                new ItemData("COUNTY",R.drawable.ic_launcher)};


        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));


        recyclerView.addItemDecoration(new SimpledividerItemdecoration(mActivity));



        Adapter_tournament adp_tournament = new Adapter_tournament(itemsData,mActivity);
        recyclerView.setAdapter(adp_tournament);
        recyclerView.setItemAnimator(new DefaultItemAnimator());






        adp_tournament.setOnItemClickListner(new Adapter_tournament.OnItemClick() {
            @Override
            public void ItemClick(int position) {
               // Log.v("editImageClick", "" + ItemData.(position)));
                Log.e("@@@@@", "" + position);
                Toast.makeText(mActivity, ""+position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mActivity, Activity_tournament.class);
                startActivity(i);
                //**/



            }


        });















      /*  recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new   RecyclerItemClickListener.OnItemClickListener() {

                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Log.e("@@@@@", "" + position);
                    }
                })
        );

*/



            recyclerView.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent i = new Intent(mActivity, Activity_tournament.class);
                startActivity(i);
            }


            }

            );



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

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tournament, container, false);



        RecyclerView recyclerView = (RecyclerView)container.findViewById(R.id.tournament_recycle_view);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        // this is data fro recycler view
        post_list = new ArrayList<PostList>(Arrays.asList((PostList)

              Adapter_tournament  adp_tournament = new Adapter_tournament(post_list);
        // 4. set adapter
        recyclerView.setAdapter(adp_tournament);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());




        );

*/

}



