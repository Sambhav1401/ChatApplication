package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth=FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
        btnlogin=findViewById(R.id.btnlogin)
        btnsignup=findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
       val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            val email=edtEmail.text.toString()
            val Password=edtPassword.text.toString()

            login(email,Password)
        }
    }
    private fun login(email:String,Password:String)
    {
        mAuth.signInWithEmailAndPassword(email, Password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent (this@Login, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
              Toast.makeText(this@Login, " User does not exist , sign up new users",Toast.LENGTH_SHORT).show()

                }
            }
    }
}