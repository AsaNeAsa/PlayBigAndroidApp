package com.playbig.network;


import org.json.JSONException;

public class NetworkCallbacks implements NetworkMethods {

    @Override
    public void successWithString(Object values, WebServiceConfigs.WebService webService) throws JSONException {

    }

    @Override
    public void failedWithMessage(Object values, WebServiceConfigs.WebService webService) {

    }

    @Override
    public void failedForNetwork(Object values, WebServiceConfigs.WebService webService) {

    }
}
