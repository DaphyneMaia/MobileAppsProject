package com.example.mobileapps1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mobileapps1.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccount : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.registered.setOnClickListener {
            val intent = Intent (this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener{
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()
            val confirmPass = binding.confirmPassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent (this, SignInActivity::class.java)
                            startActivity(intent)
                        } else{ Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_LONG).show()
                }
            } else{
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_LONG).show()
            }
        }
    }
}