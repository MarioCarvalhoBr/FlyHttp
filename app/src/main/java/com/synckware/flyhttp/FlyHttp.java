package com.synckware.flyhttp;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */

public class FlyHttp {

    public Context mContext;
    private String URL = null;
    private int metodo = 0;

    private Metode mMetode = null;
    Map<String, String> mParams = new HashMap<String, String>();

    public FlyHttp(int metode, String url, Context context) {
        this.metodo = metode;
        this.mContext = context;
        this.URL = url;

        mMetode = new Metode(metode);

    }

    public void setParametros(Map<String, String> params){
        this.mParams = params;
    }

    private Map<String, String> getParametros(){
        return mParams;
    }

    public void buildStringObject(final VolleyCallback callback) {

        StringRequest mRequest = new StringRequest(mMetode.getMetode(),
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("onResponseString: ", response);
                        String result = response;
                        try {
                            callback.onSuccessString(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("onErrorResponseString: ", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError){
                            err = "No Internet Access!";
                        }
                        try {
                            if(err != "null") {
                                callback.onError(err);
                            }
                            else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = getParametros();
                        return params;
                    }

        };
        mRequest.setRetryPolicy(new DefaultRetryPolicy(2000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyControllerSingleton.getInstance(mContext).addToRequestQueue(mRequest);

    }

    public void buildJSONObject(final VolleyCallback callback) {
        CustomJSONObjectRequest rq = new CustomJSONObjectRequest(Request.Method.GET,
                URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Response", response.toString());
                        try {
                            if (!response.toString().isEmpty()) {
                                callback.onSuccessJSONObject(response);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Response", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError){
                            err = "No internet Access!";
                        }
                        try {
                            if(err != "null") {
                                callback.onError(err);
                            }
                            else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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

    /*Classe para pegar o método usuado para fazer as requisições*/
    public static class Metode{
        public static final int POST = Request.Method.POST; //POST = 1
        public static final int GET = Request.Method.GET; //GET = 0
        public int metode = 0;

        public Metode(int metode) {
            this.metode = metode;
        }

        public int getMetode() {
            if(metode == POST){
                return POST;
            }else if(metode == GET){
                return GET;
            }else{
                return metode;
            }
        }
    }
}
