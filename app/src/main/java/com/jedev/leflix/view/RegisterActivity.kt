package com.jedev.leflix.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import com.jedev.leflix.R
import com.jedev.leflix.controllers.RegisterController
import kotlinx.android.synthetic.main.register_main.*

class RegisterActivity : AppCompatActivity() {

    val controller = RegisterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_main)

        btn_login.setOnClickListener {
            controller.openLogin()
        }

        btn_register.setOnClickListener {
            controller.onRegister(edt_name, edt_email, edt_password)
        }
    }


}
