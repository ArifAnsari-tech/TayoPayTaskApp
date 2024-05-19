package com.example.taskapp.dash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.taskapp.R
import com.example.taskapp.auth.LoginActivity
import com.example.taskapp.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor= ContextCompat.getColor(this, R.color.white)


        binding.loginBtn.setOnClickListener {

            val intent = Intent(this@SuccessActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}