package com.jedev.leflix.utils

import android.widget.EditText

class Extract {
    companion object {
        fun valueOfEditText(edt: EditText): String {
            return edt.text.toString().trim()
        }
    }
}