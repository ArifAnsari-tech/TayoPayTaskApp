package com.example.taskapp.dash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding:ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor= ContextCompat.getColor(this, R.color.secondary)

        Handler(Looper.getMainLooper()).postDelayed({

            val sharedPreferences : SharedPreferences = getSharedPreferences("LogPref", MODE_PRIVATE)
            val token = sharedPreferences.getString("token","")

            if (token?.isNotEmpty() == true) {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this@SplashScreen, "Session Expired", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SplashScreen, GetStartedActivity::class.java)
                startActivity(intent)
            }
            finish()

        },2000)

    }
}