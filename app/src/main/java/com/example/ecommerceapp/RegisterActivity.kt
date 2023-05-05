package com.example.ecommerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ecommerceapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.button4.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            finish()
        }

        binding.button3.setOnClickListener {
            validateUser()
        }
    }

    private fun validateUser() {
        if (binding.email.text!!.isEmpty() || binding.password.text!!.isEmpty()){
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }else{
            storeData()
        }
    }

    private fun storeData() {
        val builder = AlertDialog.Builder(this)
            .setTitle("Loading...")
            .setMessage("Please wait")
            .setCancelable(false)
            .create()
        builder.show()

        val data = hashMapOf<String, Any>()
        data["name"] = binding.email.text.toString()
        data["password"] = binding.password.text.toString()

        Firebase.firestore.collection("users").document(binding.email.text.toString())
            .set(data).addOnSuccessListener {
                Toast.makeText(this, "UserRegistered", Toast.LENGTH_SHORT).show()
                openLogin()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null){
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}