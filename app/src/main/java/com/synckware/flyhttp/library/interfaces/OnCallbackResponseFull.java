package com.synckware.flyhttp.library.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by carvalho on 05/07/17.
 */
@Deprecated
public interface OnCallbackResponseFull {
    void onSuccessJSONObject(JSONObject result) throws JSONException;
    void onSuccessString(String result) throws Exception;
    void onError(String result) throws Exception;
}