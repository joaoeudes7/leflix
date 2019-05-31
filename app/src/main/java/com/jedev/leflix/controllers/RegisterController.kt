package com.jedev.leflix.controllers

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.jedev.leflix.R
import com.jedev.leflix.model.User
import com.jedev.leflix.service.firebase.UserService
import com.jedev.leflix.utils.DateCustom
import com.jedev.leflix.utils.Extract
import com.jedev.leflix.view.HomeActivity
import com.jedev.leflix.view.LoginActivity

class RegisterController(private val context: Activity) {

    fun onRegister(edtName: EditText, edtEmail: EditText, edtPassword: EditText) {
        val fireAuth = FirebaseAuth.getInstance()

        val name = Extract.valueOfEditText(edtName)
        val email = Extract.valueOfEditText(edtEmail)
        val password = Extract.valueOfEditText(edtPassword)

        if (this.validateLogin(name, email, password)) {
            fireAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    task.result?.user?.let {
                        val user = User(it.uid, name, email)

                        UserService().save(user)

                        context.startActivity(Intent(context, HomeActivity::class.java))
                        context.finish()
                    }
                } else {
                    val msgError: String

                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        msgError = context.getString(R.string.senhaFraca)
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        msgError = context.getString(R.string.emailInvalido)
                    } catch (e: FirebaseAuthUserCollisionException) {
                        msgError = context.getString(R.string.emailDuplicado)
                    } catch (e: Exception) {
                        msgError = context.getString(R.string.erroCadastrousuario) + e.message
                        e.printStackTrace()
                    }

                    Toast.makeText(context, msgError, Toast.LENGTH_LONG).show()
                }
            }
        } else {
            val msg = "Preencha todos os campos!"
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }

    private fun validateLogin(name: String, email: String, password: String): Boolean {
        val isValidName = name.length > 5
        val isValidEmail = email.length > 6 && email.contains("@")
        val isValidPassword = password.length > 4

        return isValidName && isValidEmail && isValidPassword
    }

    fun openLogin() {
        val intentLogin = Intent(context, LoginActivity::class.java)
        context.startActivity(intentLogin)
        context.finish()
    }
}