package com.synckware.flyhttp.library.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Description: Interface que fornece os metodos para uma requisição HTTP e permite retorno via JSON
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
public interface OnCallbackResponseJson{
    void onSuccessJSONObject(JSONObject result) throws JSONException;
    void onError(String result) throws Exception;
}
