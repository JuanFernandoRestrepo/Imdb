package com.example.imdb.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.data.local.AppDatabase
import com.example.myapplication.data.User
import kotlinx.coroutines.launch
import com.example.imdb.R

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        val imageButton = findViewById<ImageButton>(R.id.backButton)
        imageButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val button = findViewById<Button>(R.id.buttonAccept)
        val username = findViewById<TextView>(R.id.editTextName)
        val mail = findViewById<TextView>(R.id.editTextEmail)
        val password = findViewById<TextView>(R.id.editTextPassword)
        button.setOnClickListener {
            val userNameText = username.text.toString()
            val userMail = mail.text.toString()
            val userPass = password.text.toString()
//            Log.d("RegistroActivity", "Nombre: $userNameText")
//            Log.d("RegistroActivity", "Email: $userMail")
//            Log.d("RegistroActivity", "Contraseña: $userPass")

            if (userNameText.isNotEmpty() && userMail.isNotEmpty() && userPass.isNotEmpty()) {
                val db = AppDatabase.getDatabase(this)
                val user = User(0, userNameText, userMail, userPass)

                Log.d("RegistroActivity", "Registrando usuario: $user")

                lifecycleScope.launch {
                    db.userDao().insertUser(user)

                    Toast.makeText(this@RegistroActivity, "Registro exitoso", Toast.LENGTH_SHORT).show()

                    kotlinx.coroutines.delay(1000)

                    startActivity(Intent(this@RegistroActivity, MainActivity::class.java))
                }
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

    }
}