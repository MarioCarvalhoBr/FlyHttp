package com.synckware.flyhttp;

/**
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
import org.json.JSONException;
import org.json.JSONObject;

public interface RequestCallback {
    void onSuccessJSONObject(JSONObject result) throws JSONException;
    void onSuccessString(String result) throws Exception;
    void onError(String result) throws Exception;
}