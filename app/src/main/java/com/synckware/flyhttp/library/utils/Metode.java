package com.synckware.flyhttp.library.utils;

import com.android.volley.Request;
/**
 * @Description: Classe por fornecesser os parâmetros do metodo da requisição
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
public class Metode{

    public static final int POST = Request.Method.POST; //POST = 1
    public static final int GET = Request.Method.GET; //GET = 0
    public int metode = 0;

    //Recebe o método como inteiro
    public Metode(int metode) {
        this.metode = metode;
    }
    //Retorna o metodo como inteiro do tipo Request
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