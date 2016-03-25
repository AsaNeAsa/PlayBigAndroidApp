package com.playbig.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.playbig.R;
import com.playbig.modal.ItemData;

/**
 * Created by User on 2/17/2016.
 */
 public class Adapter_tournamenttoday extends RecyclerView.Adapter<Adapter_tournamenttoday.ViewHolder> {

    private static Context ctxt;
    private ItemData[] itemsData;



    public Adapter_tournamenttoday(ItemData[] itemsData, Context ctxt) {
        this.itemsData = itemsData;
        this.ctxt = ctxt;

    }


    public Adapter_tournamenttoday.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournamenttoday, null);


        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {






    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_tournamentname,txt_team1,txt_team2,txt_rateteam1,txt_rateteam2,txt_ratedraw,txt_time;
        public ImageView img_team1,img_team2;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txt_tournamentname = (TextView) itemLayoutView.findViewById(R.id.txt_tournamentname);
            txt_team1 = (TextView) itemLayoutView.findViewById(R.id.txt_team1);
            txt_team2 = (TextView) itemLayoutView.findViewById(R.id.txt_team2);

            txt_rateteam1 = (TextView) itemLayoutView.findViewById(R.id.txt_rateteam1);

            txt_rateteam2 = (TextView) itemLayoutView.findViewById(R.id.txt_rateteam2);
            txt_ratedraw = (TextView) itemLayoutView.findViewById(R.id.txt_ratedraw);
            txt_time = (TextView) itemLayoutView.findViewById(R.id.txt_time);
            img_team1 = (ImageView) itemLayoutView.findViewById(R.id.img_team1);
            img_team2 = (ImageView) itemLayoutView.findViewById(R.id.img_team2);












        }
    }



    public void setOnImageClickListner(OnImageClick onImageClickListner) {
        this.onImageClickListner = onImageClickListner;
    }

    OnImageClick onImageClickListner;


    public interface OnImageClick {
        public void editImageClick(int position);

        public void deleteImageClick(int position);

        public void rowItemClick(int position);
    }





    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    }

