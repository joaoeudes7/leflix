package com.jedev.leflix.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.jedev.leflix.R
import com.jedev.leflix.controllers.LoginController
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val controller = LoginController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.configureActionBar()

        btn_login.setOnClickListener {
            controller.onLogin(edt_email, edt_password)
        }
        btn_register.setOnClickListener {
            controller.openRegister()
        }
    }

    private fun configureActionBar() {
        val bar = supportActionBar
        bar!!.hide()
        bar.setTitle(R.string.login)
        bar.setSubtitle(R.string.doLogin)
        bar.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.verdePadrao)))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }


}

