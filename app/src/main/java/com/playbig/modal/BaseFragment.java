package com.playbig.modal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment implements Act_ImpMethods {

    private View view;
    private int res;
    public Activity mActivity;

    public boolean isReloadFromBackStack, isAlreadyLoaded;

    public void setContentView(int res) {
        this.res = res;
    }

    public void setContentView(int res, boolean isReloadFromBackStack) {
        this.res = res;
        this.isReloadFromBackStack = isReloadFromBackStack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setHasOptionsMenu(true);
        if (isReloadFromBackStack) {
            if (view == null) {
                isAlreadyLoaded = false;
                view = inflater.inflate(res, container, false);
            } else {
                isAlreadyLoaded = true;
                //	((ViewGroup) view.getParent()).removeView(view);
            }
        } else {
            isAlreadyLoaded = false;
            view = inflater.inflate(res, container, false);
        }
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        try {
            super.onActivityCreated(savedInstanceState);
            mActivity = getActivity();


            initVariable();
            initView();
            postInitView();
            addAdapter();
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
            showToast(e.toString());
//			Utility.showLogE(BaseFragment.class, e.toString());
        }
    }

    public View links(int res) {
        return view.findViewById(res);
    }

    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void postInitView() {
        // TODO Auto-generated method stub

    }

    public final void showToast(String msg) {
//		if (ConstantCode.SHOW_TOAST)
//			Toast.makeText(mActivity, msg, ConstantCode.TOAST_DURATION).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (Activity) context;
        }
    }
}
