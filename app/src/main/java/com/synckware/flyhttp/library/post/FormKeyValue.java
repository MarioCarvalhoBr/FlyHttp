package com.synckware.flyhttp.library.post;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
/**
 * @Description: Classe utilitária que ajuda a passar um formulário pra web do tipo chave e valor
 * Created by Mário de Araújo Carvalho on 09/05/2017.
 */
public class FormKeyValue<Key, Value> extends HashMap<String, String> {
    @Override
    public int size() {
        return super.size();
    }

    //Criptografia MD5 caso necessário passar os dados criptografados
    public String getMD5(String input) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
