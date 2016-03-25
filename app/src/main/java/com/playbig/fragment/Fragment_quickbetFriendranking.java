package com.playbig.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.playbig.Activity.Activity_tournament;
import com.playbig.R;
import com.playbig.adapter.Adapter_ranking_quickbetfriend;
import com.playbig.adapter.Adapter_tournament;
import com.playbig.customview.SimpledividerItemdecoration;
import com.playbig.modal.BaseFragment;
import com.playbig.modal.ItemData;

/**
 * Created by User on 2/23/2016.
 */
public class Fragment_quickbetFriendranking extends BaseFragment {
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

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quickbetfriendranking, container, false);
        init(view);
        return view;
    }



    protected void init(View view) {


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ranking_quickbetfriend);


        final ItemData itemsData[] = {new ItemData("ICC WORLDCUP", R.drawable.ic_launcher),
                new ItemData("IPL", R.drawable.gold_coin),
                new ItemData("CHAMPIONS TROPHY", R.drawable.ic_launcher),
                new ItemData("BIGBASH", R.drawable.ic_launcher),
                new ItemData("BPL", R.drawable.ic_launcher),
                new ItemData("COUNTY", R.drawable.ic_launcher)};


        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));


        recyclerView.addItemDecoration(new SimpledividerItemdecoration(mActivity));


        Adapter_ranking_quickbetfriend adp_tournament = new Adapter_ranking_quickbetfriend(itemsData, mActivity);
        recyclerView.setAdapter(adp_tournament);
        recyclerView.setItemAnimator(new DefaultItemAnimator());





/*

        adp_tournament.setOnItemClickListner(new Adapter_tournament.OnItemClick() {
            @Override
            public void ItemClick(int position) {
                // Log.v("editImageClick", "" + ItemData.(position)));
                Log.e("@@@@@", "" + position);
                Toast.makeText(mActivity, "" + position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(mActivity, Activity_tournament.class);
                startActivity(i);
                /*/
/**//*




            }


        });
*/















      /*  recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new   RecyclerItemClickListener.OnItemClickListener() {

                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Log.e("@@@@@", "" + position);
                    }
                })
        );

*/





    }

}
