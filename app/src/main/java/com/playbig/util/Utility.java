package com.playbig.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.google.gson.Gson;
import com.playbig.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * Created by android on 10/8/15.
 */
public class Utility {

    public static void hideKeyboard(Activity activity) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

// check if no view has focus:
            View view = activity.getCurrentFocus();
            if (view != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public static void hideKeyboard(Activity activity, EditText edt) {
        if (activity != null) {
            InputMethodManager inputManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

// check if no view has focus:
            if (edt != null) {
                inputManager.hideSoftInputFromWindow(edt.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public final static String trim(EditText edt) {
        if (!isBlank(edt)) {
            return edt.getText().toString().trim();
        }
        return "";
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public final static String trim(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    public final static boolean isBlank(EditText edt) {
        if (edt == null || (edt != null && TextUtils.isEmpty(edt.getText().toString().trim()))) {
            return true;
        }
        return false;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                    .matches();
        }
    }

    public static ProgressDialog progressDialog;


    public static void showProgressDialog(Activity mActivity,
                                          DialogInterface.OnCancelListener cancelListener) {
// TODO Auto-generated method stub

        progressDialog = ProgressDialog.show(mActivity, null, mActivity.getResources().getString(R.string.txt_wait_line), true,
                true, cancelListener);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setOnCancelListener(cancelListener);

        Log.v("ProgressDialog", "show ");
    }

    public static void dismissProgressDialog() {
        Log.v("ProgressDialog", "dismiss");
        if (progressDialog != null)
            progressDialog.dismiss();
    }


    public static int dpToPx(int dp, Context ctxt) {
        DisplayMetrics displayMetrics = ctxt.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public static Object parseFromString(String jsonData, Class modelClass) {
        return new Gson().fromJson(jsonData, modelClass);

    }

    public static String getJsonString(Object modelClass) {

        return new Gson().toJson(modelClass);
    }


    public static String roundToTwoDecimal(String value) {
        try {
            Double double_value = Double.parseDouble(value);
            DecimalFormat df = new DecimalFormat("#,###.##");
            return "₹ " + df.format(double_value);

        } catch (Exception e) {
            e.printStackTrace();
            return "00.00";
        }
    }

    public static int roundFigure(String value) {
        try {
            Double double_value = Double.parseDouble(value);
            DecimalFormat df = new DecimalFormat("####.##");
            return Integer.parseInt(df.format(double_value));

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String price_rupee(int value) {

        Double double_value = Double.parseDouble("" + value);
        DecimalFormat df = new DecimalFormat("#,###.##");
        return "₹ " + df.format(double_value);
    }



    /**
     * Check to see if Internet is enabled or not
     */
    public static boolean isInternetConnected(Context activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getDataFromResponse(String response) throws JSONException {
        JSONObject jobj = new JSONObject(response);
        return jobj.getString("DATA");
    }




}
