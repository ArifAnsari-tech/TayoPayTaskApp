package com.example.taskapp.auth

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.taskapp.R
import com.example.taskapp.dash.MainActivity
import com.example.taskapp.databinding.ActivityLoginBinding
import com.example.taskapp.networks.ApiInterface
import com.example.taskapp.networks.RetrofitService
import com.example.taskapp.networks.response.LoginResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var loader: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.secondary)



        loader = ProgressDialog(this)
        loader.setTitle("Please Wait ..")
        loader.setMessage("Getting details")
        loader.setCancelable(false)

        binding.sgTv.setOnClickListener {
            val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(intent)
            finish() }
        binding.fgPass.setOnClickListener {
            Toast.makeText(this@LoginActivity, "Not Working", Toast.LENGTH_SHORT).show() }

        binding.logButton.setOnClickListener {
            if (validateInputs()) { loader.show(); login() } }
    }

    private fun validateInputs(): Boolean {
        return when{
            binding.lgMobileNo.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show(); false }
            binding.lgMobileNo.text.toString().length < 9 -> { Toast.makeText(this, "phone number must be 9 digits", Toast.LENGTH_SHORT).show(); false }
            binding.lgPass.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show(); false }
            binding.lgPass.text.toString().length < 8 -> { Toast.makeText(this, "Password must be 8 digits", Toast.LENGTH_SHORT).show(); false }

            else -> true
        }

    }

    private fun login() {
        val retrofitClient = RetrofitService().getClient()?.create(ApiInterface::class.java)

        val mobilePhone = "27"+binding.lgMobileNo.text.toString()
        val password = binding.lgPass.text.toString()

        val jOb=JsonObject().apply {
            addProperty("username",mobilePhone)
            addProperty("password",password)
        }

        val login = retrofitClient?.login(
            jOb
        )

        login?.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loader.dismiss()
                if (response.isSuccessful){
                    val responseBody = response.body()
                    val token = response.body()?.data?.authToken

                    Log.d(javaClass.simpleName, "token $token ")

                    val sharedPreferences: SharedPreferences
                            =getSharedPreferences("LogPref", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()

                    editor.putString("token" ,token)
                    editor.apply()

                    Log.d("TAG", "TOKEN: $token" +"\n${sharedPreferences.getString("token", "")}")

                    Log.d("LoginActivity", "onResponse: Success ${responseBody?.data}")

                    if (responseBody?.status != null) {
                        Toast.makeText(this@LoginActivity, "Login Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        loader.dismiss()
                        Toast.makeText(this@LoginActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    val errorBody = response.errorBody()?.string()
                    loader.dismiss()
                    Toast.makeText(this@LoginActivity, "SignUp Failed: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loader.dismiss()
                Toast.makeText(this@LoginActivity, "Server error", Toast.LENGTH_SHORT).show()
            }

        })




    }
}