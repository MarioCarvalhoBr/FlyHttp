package com.synckware.flyhttp.library.interfaces;
/**
 * @Description: Interface que fornece os metodos para uma requisição HTTP e permite retorno via String
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
public interface OnCallbackResponseString{
    void onSuccessString(String result) throws Exception;
    void onError(String result) throws Exception;
}