package com.playbig.network;


import org.json.JSONException;

public interface NetworkMethods {
    //	public MRequest requestService(ContentValues values, final WebService webService);
    public void successWithString(Object values, WebServiceConfigs.WebService webService) throws JSONException;

    public void failedWithMessage(Object values, WebServiceConfigs.WebService webService);

    public void failedForNetwork(Object values, WebServiceConfigs.WebService webService);
}
