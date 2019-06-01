package com.jedev.leflix.controllers

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.*
import com.jedev.leflix.model.User
import com.jedev.leflix.service.firebase.UserService
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
                        val userfire = FirebaseAuth.getInstance().currentUser
                        val user = User(it.uid, name, email)

                        UserService().save(user)

                        val profileUpdates = UserProfileChangeRequest.Builder()
                                .setDisplayName(name).build()
                        userfire?.updateProfile(profileUpdates)
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d("olamundo", "User  updated.")
                                    }
                                }


                        context.startActivity(Intent(context, HomeActivity::class.java))
                        context.finish()
                    }
                } else {
                    val msgError: String

                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        msgError = context.getString(com.jedev.leflix.R.string.senhaFraca)
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        msgError = context.getString(com.jedev.leflix.R.string.emailInvalido)
                    } catch (e: FirebaseAuthUserCollisionException) {
                        msgError = context.getString(com.jedev.leflix.R.string.emailDuplicado)
                    } catch (e: Exception) {
                        msgError = context.getString(com.jedev.leflix.R.string.erroCadastrousuario) + e.message
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