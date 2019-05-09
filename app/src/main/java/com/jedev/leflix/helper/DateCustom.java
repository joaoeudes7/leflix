package com.jedev.leflix.helper;

import android.util.Base64;

import java.text.SimpleDateFormat;

public class DateCustom {


    public static String dataAtual() {

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;

    }

    public static class Base64Custom {


        public static String codificarBase64(String texto){
            return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT ).replaceAll("(\\n|\\r)","");
        }

        public static String decodificarBase64(String textoCodificado){
            return new String( Base64.decode(textoCodificado, Base64.DEFAULT) );
        }

    }
}
