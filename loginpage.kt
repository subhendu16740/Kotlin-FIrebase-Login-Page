package com.hussein.startup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
    }

    fun buLoginEvent(view: View) {
        LoginToFireBase(etEmail.text.toString(), etPassword.text.toString())
    }

    fun LoginToFireBase(email: String, password: String) {
        mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Successful Login", Toast.LENGTH_LONG).show()
                        LoadMain()

                    } else {
                        Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_LONG).show()
                    }
                }

    }

    override fun onStart() {
        super.onStart()
        LoadMain()
    }

    fun LoadMain() {
        var currentUser = mAuth!!.currentUser

        if (currentUser != null) {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("uid", currentUser.uid)
            startActivity(intent)
        }
    }
}
