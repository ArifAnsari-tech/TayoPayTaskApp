package com.example.taskapp.dash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.taskapp.R
import com.example.taskapp.auth.LoginActivity
import com.example.taskapp.auth.SignUpActivity
import com.example.taskapp.databinding.ActivityGetStartedBinding
import com.example.taskapp.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityGetStartedBinding.inflate(layoutInflater)
        openBottomSheet()
        setContentView(binding.root)

        window.statusBarColor= ContextCompat.getColor(this, R.color.white)

    }
    private fun openBottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val sheetBinding: BottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(sheetBinding.root)
        bottomSheetDialog.show()

        sheetBinding.srtBtn.setOnClickListener {
            val intent=Intent(this@GetStartedActivity,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        sheetBinding.logBtn.setOnClickListener {
            val intent=Intent(this@GetStartedActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}