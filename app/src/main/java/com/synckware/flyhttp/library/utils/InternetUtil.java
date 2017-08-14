package com.synckware.flyhttp.library.utils;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * @Description: Classe utilitária com métodos que ajudam a melhorar as requisições HTTP
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
public class InternetUtil {
    private final static boolean DEBUG = true;
    private final static String TAG = "MY_TAG";
    private final static String TEXTO_CONTINUAR_COM = "Continuar com...";
    public static String NO_CONNECTED = "Não está conectado a Internet.";

    /**
     * @Description: Abrir uma URL passada
     * @param context: Context da Intent
     * @param url: URL para abrir
     */
    public static void openURL(Context context, String url)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        try {
            Intent chooser = Intent.createChooser(intent, TEXTO_CONTINUAR_COM);
            context.startActivity(chooser);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "Error: Não foi possível abrir a URL", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * @Description: Verifica a conexão com a internet
     * @param context
     * @return boolean
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static boolean checkConnection(Context context) {
       /*.ConnectivityManager
        *.TYPE_MOBILE 0
        *.TYPE_WIFI 1
        *.TYPE_WIMAX 6
        *.TYPE_ETHERNET 9
        */
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {
                return false;
            }

            int[] p = {0,1,6,9};
            for (int i : p) {
                if (cm.getNetworkInfo(i).isConnected()){
                    return true;//Conexão OK
                }
            }
        } catch (Exception e) {//Erro ao verificar conexão
            return false;
            //e.printStackTrace();
        }
        return false;//Sem Conexão
    }

}
