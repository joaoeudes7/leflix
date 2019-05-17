package com.jedev.leflix.utils

import android.util.Base64

import java.text.SimpleDateFormat

object DateCustom {

    object Base64Custom {

        fun codificarBase64(texto: String): String {
            return Base64.encodeToString(texto.toByteArray(), Base64.DEFAULT).replace("([\\n\\r])".toRegex(), "")
        }

        fun decodificarBase64(textoCodificado: String): String {
            return String(Base64.decode(textoCodificado, Base64.DEFAULT))
        }

        fun dataAtual(): String {
            val data = System.currentTimeMillis()
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            return simpleDateFormat.format(data)
        }
    }
}
