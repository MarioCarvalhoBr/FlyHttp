/*
 * Copyright 2017 Mário de Araújo Carvalho
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package com.synckware.flyhttp.library.core;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.synckware.flyhttp.library.custom.CustomJsonRequest;
import com.synckware.flyhttp.library.controller.VolleyControllerSingleton;
import com.synckware.flyhttp.library.interfaces.OnCallbackResponseJson;
import com.synckware.flyhttp.library.interfaces.OnCallbackResponseString;
import com.synckware.flyhttp.library.utils.InternetUtil;
import com.synckware.flyhttp.library.utils.Metode;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Classe responsável por fazer as resquisições HTTP
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */

public class FlyHttp {
    //Atributos
    private Context mContext;// Contexto para a requisição
    private String URL = null; //String com a URL desejado
    private int metode = 0; // Metodo escolhido
    private boolean withProgress = false; //Atributo para saber se foi escolhido o progress
    private ProgressDialog progressDialog = null; // Progress de carregamento
    private Metode mMetode = null; // Objeto da classe Metode para requisicao

    Map<String, String> mParams = new HashMap<String, String>(); // Parametros do formulário

    /**
     * @Description: Construtor simples, exige os métodos: setURL e setMedote
     * @param mContext
     */
    public FlyHttp(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * @Description: Construtor carregado sem progress
     * @param metodo
     * @param url
     * @param context
     */
    public FlyHttp(int metodo, String url, Context context) {
        this.metode = metodo;
        this.mContext = context;
        this.URL = url;
        //Temos de criar o novo método com base no método que veio para poder concluir a requisição do Volley
        mMetode = new Metode(metode);
    }

    /**
     * @Description: Construtor carregado com progress
     * @param metodo
     * @param url
     * @param context
     * @param withProgress
     */
    public FlyHttp(int metodo, String url, Context context, boolean withProgress) {
        this.metode = metodo;
        this.mContext = context;
        this.URL = url;
        this.withProgress = withProgress;
        //Temos de criar o novo método com base no método que veio para poder concluir a requisição do Volley
        mMetode = new Metode(metode);
    }

    /**
     * @Descripition: Iniciar uma requisição web com resposta em formato {@link String}
     * @param callback
     * @throws Exception
     */
    public void build(final OnCallbackResponseString callback) {
        if (withProgress){
            showProgrees();
        }
        Log.e("FlyHttp","Requisição iniciada...");
        if(InternetUtil.checkConnection(mContext)){
            createdRequisition(callback);
        }else{
            Toast.makeText(mContext, ""+InternetUtil.NO_CONNECTED, Toast.LENGTH_SHORT).show();
            Log.e("FlyHttp","Requisição finalizada...");
            finishProgrees();
        }

    }

    /**
     * @Descripition: Iniciar uma requisição web com resposta em formato {@link JSONObject}
     * @param callback
     * @throws Exception
     */
    public void build(final OnCallbackResponseJson callback){
        Log.e("FlyHttp","Requisição iniciada...");
        if(InternetUtil.checkConnection(mContext)){
            createdRequisition(callback);
        }else{
            Toast.makeText(mContext, ""+InternetUtil.NO_CONNECTED, Toast.LENGTH_SHORT).show();
            Log.e("FlyHttp","Requisição finalizada...");
            finishProgrees();
        }

    }

    /**
     * @Description: Criar um requisição web com retorno em formato {@link String}
     * @param callback
     */
    private void createdRequisition(final OnCallbackResponseString callback) {

        StringRequest mRequest = new StringRequest(mMetode.getMetode(),
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finishProgrees();
                        Log.e("onResponseString: ", response);
                        String result = response;
                        try {
                            callback.onSuccessString(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("FlyHttp","Requisição finalizada...");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finishProgrees();
                        Log.v("onErrorResponseString: ", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError) {
                            err = "A conexão com a internet caiu ou o Host não foi encontrado!";
                        }
                        try {
                            if (err != "null") {
                                callback.onError(err);
                            } else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("FlyHttp","Requisição finalizada...");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = FlyHttp.this.getParams();
                return params;
            }
        };
        mRequest.setRetryPolicy(new DefaultRetryPolicy(2000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyControllerSingleton.getInstance(mContext).addToRequestQueue(mRequest);

    }

    /**
     * @Description: Criar um requisição web com retorno em formato {@link JSONObject}
     * @param callback
     */
    private void createdRequisition(final OnCallbackResponseJson callback) {

        CustomJsonRequest rq = new CustomJsonRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        finishProgrees();
                        Log.v("Response", response.toString());
                        try {
                            if (!response.toString().isEmpty()) {
                                callback.onSuccessJSONObject(response);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        Log.e("FlyHttp","Requisição finalizada...");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finishProgrees();
                        Log.v("Response:", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError) {
                            err = "A conexão com a internet caiu ou o Host não foi encontrado!";
                        }
                        try {
                            if (err != "null") {
                                callback.onError(err);
                            } else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("FlyHttp","Requisição finalizada...");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        rq.setPriority(Request.Priority.HIGH);
        VolleyControllerSingleton.getInstance(mContext).addToRequestQueue(rq);

    }

    //Metodo responsável por criar o progress
    private void showProgrees(){
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
    }

    //Método responsavel por destruir o progress
    private void finishProgrees(){
        if(withProgress){
            progressDialog.dismiss();
        }
    }
    //Get's e Set's

    /**
     * @Description: Metodo para receber o formulario passado
     * @param params
     */
    public void setParams(Map<String, String> params) {
        this.mParams = params;
    }

    /**
     * @Description: Metodo para pegar o formulario passado
     * @return mParams
     */
    private Map<String, String> getParams() {
        return mParams;
    }

    public boolean isWithProgress() {
        return withProgress;
    }

    public void setWithProgress(boolean withProgress) {
        this.withProgress = withProgress;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getMetode() {
        return metode;
    }

    public void setMetode(int metode) {
        this.metode = metode;
        //Temos de criar o novo método com base no método que veio para poder concluir a requisição do Volley
        mMetode = new Metode(metode);
    }
}