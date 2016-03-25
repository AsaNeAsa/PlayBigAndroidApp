package com.playbig.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.playbig.Activity.MainActivity;
import com.playbig.modal.ItemData;
import com.playbig.R;

public class Adapter_tournament extends RecyclerView.Adapter<Adapter_tournament.ViewHolder>  {
    private ItemData[] itemsData;
     private static Context ctxt;

    public Adapter_tournament(ItemData[] itemsData , Context ctxt) {
        this.itemsData = itemsData;
                this.ctxt = ctxt;
            }

    public Adapter_tournament.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tournament, null);


        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
        }


    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // - get data from your itemsData at this position
        // - replace the contents of the view with that itemsDataviewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageUrl());






        viewHolder.txtViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onItemClickListner.ItemClick(position);


/*



                Toast.makeText(ctxt, "Click", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctxt, MainActivity.class);
                ctxt.startActivity(i);
*//**/
            }
        });





        }

            public  class ViewHolder extends RecyclerView.ViewHolder {
                public TextView txtViewTitle;
                public ImageView imgViewIcon;

                public ViewHolder(View itemLayoutView) {
                    super(itemLayoutView);
                    txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.item_title);
                    imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.item_icon);





                    txtViewTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListner.ItemClick(getPosition());
                        }
                    });








                    }


            }
            @Override
    public int getItemCount() {
        return itemsData.length;
        }




    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }








    public void setitembuttonclick(ItemButtonClick itembuttonclick) {
        this.itembuttonclick = itembuttonclick;
    }

    ItemButtonClick itembuttonclick;

    public interface ItemButtonClick  {
        public void setOnclick(int position);


    }

    public void setOnItemClickListner(OnItemClick onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    OnItemClick onItemClickListner;


    public interface OnItemClick {
        public void ItemClick(int position);

        /*public void deleteImageClick(int position);

        public void rowItemClick(int position);*/
    }

}


