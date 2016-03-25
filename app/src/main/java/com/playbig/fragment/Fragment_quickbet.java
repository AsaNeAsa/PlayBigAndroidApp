package com.playbig.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.playbig.Activity.Activity_Login;
import com.playbig.Activity.Activity_quickplaceabet;
import com.playbig.Activity.Activity_quickranking;
import com.playbig.R;
import com.playbig.modal.BaseFragment;
import com.playbig.util.ConstantCodes;
import com.playbig.util.PreferenceUtility;


/**
 * Created by User on 2/8/2016.
 */
public class Fragment_quickbet extends BaseFragment {



    LinearLayout ly_placeabat,ly_ranking;
    PreferenceUtility preferenceUtility;

    public Fragment_quickbet() {


        setContentView(R.layout.fragment_quickbet, true);

        //Required empty public constructor
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
                    startActivity(new Intent(mActivity, Activity_quickplaceabet.class));
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
                Intent i = new Intent(mActivity, Activity_quickranking.class);
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