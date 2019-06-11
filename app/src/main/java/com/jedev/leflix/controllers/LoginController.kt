package com.jedev.leflix.controllers

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.jedev.leflix.view.HomeActivity
import com.jedev.leflix.view.RecuperarSenhaActivity
import com.jedev.leflix.view.RegisterActivity

class LoginController(private val context: Activity) {

    fun onLogin(edtEmail: EditText, edtPassword: EditText) {
        val fireAuth = FirebaseAuth.getInstance()

        val email = edtEmail.text.toString().trim()
        val password = edtPassword.text.toString().trim()

        if (!this.validateLogin(email, password)) {
            val msg = "Preencha todos os campos!"
            return Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                this.openHome()
            } else {
                val msgError: String

                try {
                    throw task.exception!!
                } catch (e:FirebaseAuthInvalidUserException) {
                    msgError = "Usuário não está cadastrado."
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    msgError = "E-mail e senha não correspondem a um usuário cadastrado"
                } catch (e: Exception) {
                    msgError = "Erro ao cadastrar usuário: " + e.message
                    e.printStackTrace()
                }

                Toast.makeText(context, msgError, Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun validateLogin(email: String, password: String): Boolean {
        val isValidEmail = email.length > 6 && email.contains("@")
        val isValidPassword = password.length > 4

        return isValidEmail && isValidPassword
    }

    fun openRegister() {
        val intentRegister = Intent(context, RegisterActivity::class.java)
        context.startActivity(intentRegister)
        context.finish()
    }

    fun openRecuperarSenha() {
        val intentRecuperarSenha = Intent(context, RecuperarSenhaActivity::class.java)
        context.startActivity(intentRecuperarSenha)
    }

    private fun openHome() {
        val intentHome = Intent(context, HomeActivity::class.java)
        context.startActivity(intentHome)
        context.finish()
    }
}