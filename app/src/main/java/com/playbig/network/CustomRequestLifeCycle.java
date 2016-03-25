package com.playbig.network;

import android.content.Context;

import com.playbig.R;
import com.playbig.util.ConstantCodes;
import com.playbig.util.Utility;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

/**
 * Created by pankaj on 7/8/15.
 */
public class CustomRequestLifeCycle implements WebUtils.RequestLifeCycle {

    private Context context;
    private static CustomRequestLifeCycle request;

    private CustomRequestLifeCycle(Context context) {
        this.context = context;
    }

    public static CustomRequestLifeCycle getInstance(Context context) {
        if (request == null) {
            request = new CustomRequestLifeCycle(context);
        }
        return request;
    }

    //=========================================================================
    //=================== U S E R   S P E C I F I C   C O D E S ===============
    //=========================================================================

    @Override
    public boolean validateResponse(JSONObject json, StringBuilder failMessage) {
        /*if (json.has(ConstantCode.IN_STATUS) && json.optString(ConstantCode.IN_STATUS).equals(ConstantCode.IN_RESULT_OK)) {
            return true;
        } else {
            return false;
        }*/
        if (json.has("Status") && json.optString("Status").equals("Success")) {
            return true;
        } else {
            return false;
        }

        //Send Failure Message
        //failMessage.append(context.getString(R.string.error_general));
        //return false;
    }

    @Override
    public HashMap<String, String> getHeaders(WebServiceModel webService) {
//        HashMap<String, String> headers = new HashMap<String, String>();
//
//        headers.put(ConstantCode.HEADER_KEY, App.getAppInstance().getSecurityKey());
//        headers.put(ConstantCode.F_ID_OPERATOR, ConstantCode.ID_OPERATOR);
//        headers.put(ConstantCode.F_ID_OPERATOR_TYPE, ConstantCode.ID_OPERATOR_TYPE);
//        headers.put(ConstantCode.F_ID_COMPANY, ConstantCode.ID_COMPANY);
//
//        if (!TextUtils.isEmpty(App.getAppInstance().getAccesToken())) {
//            headers.put(ConstantCode.HEADER__ACCESS_TOKEN, App.getAppInstance().getAccesToken());
//        }
//        if (!TextUtils.isEmpty(App.getAppInstance().getPassengerId())) {
//            headers.put(ConstantCode.F_PASSENGER_ID, App.getAppInstance().getPassengerId());
//
//        }
        return null;
    }

    public String getNoInternetMessage() {
        return context.getString(R.string.error_internet);
    }

    @Override
    public boolean isInternetConnected() {
        return Utility.isInternetConnected(context);
    }

    @Override
    public File createNewVideoFile() {
        return null;
    }

    @Override
    public File createNewImageFile() {
        return null;
    }
}
