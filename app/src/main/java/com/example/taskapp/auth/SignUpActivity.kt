package com.example.taskapp.auth

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.taskapp.R
import com.example.taskapp.dash.SuccessActivity
import com.example.taskapp.databinding.ActivitySignUpBinding
import com.example.taskapp.networks.ApiInterface
import com.example.taskapp.networks.RetrofitService
import com.example.taskapp.networks.response.RegisterResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val genders = arrayOf("Male", "Female", "Others")
    private lateinit var loader: ProgressDialog
    private var genderId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.secondary)

        loader = ProgressDialog(this)
        loader.setTitle("Please Wait ..")
        loader.setMessage("Account is Creating")
        loader.setCancelable(false)

        binding.genderFiled.setOnClickListener { showGenderPopup(it) }
        binding.dobFiled.setOnClickListener { openCalender(it) }
        binding.sgBtn.setOnClickListener { if (validateInputs()) { loader.show(); register() } }
    }

    private fun validateInputs(): Boolean {
        return when {
            binding.fName.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter first name", Toast.LENGTH_SHORT).show(); false }
            binding.lName.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter last name", Toast.LENGTH_SHORT).show(); false }
            binding.sEmail.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show(); false }
            !Patterns.EMAIL_ADDRESS.matcher(binding.sEmail.text.toString()).matches() -> { Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show(); false }
            binding.mobileNo.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show(); false }
            binding.mobileNo.text.toString().length < 9 -> { Toast.makeText(this, "Phone number must be 9 digits", Toast.LENGTH_SHORT).show(); false }
            binding.genderTv.text.toString().isEmpty() -> { Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show(); false }
            binding.dobTv.text.toString().isEmpty() -> { Toast.makeText(this, "Please select your date of birth", Toast.LENGTH_SHORT).show(); false }
            binding.sgPass.text.toString().isEmpty() -> { Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show(); false }
            binding.sgPass.text.toString().length < 8 -> { Toast.makeText(this, "Password must be 8 digits", Toast.LENGTH_SHORT).show(); false }
            else -> true
        }
    }

    private fun register() {
       val retrofitClient = RetrofitService().getClient()?.create(ApiInterface::class.java)
        val firstName = binding.fName.text.toString()
        val middleName = binding.mName.text.toString()
        val lastName = binding.lName.text.toString()
        val email = binding.sEmail.text.toString()
        val mobilePhone = "27" + binding.mobileNo.text.toString()
        val password = binding.sgPass.text.toString()
        val dateOfBirth = binding.dobTv.text.toString()
        val genderId = (this.genderId?.toString() ?: "")
        val j=JsonObject().apply {
            addProperty("firstName",firstName)
            addProperty("middleName",middleName)
            addProperty("lastName",lastName)
            addProperty("email",email)
            addProperty("mobilePhone",mobilePhone)
            addProperty("password",password)
            addProperty("genderId",genderId)
            addProperty("dateOfBirth",dateOfBirth)
        }
        val signUp = retrofitClient?.register(
          j
        )


        signUp?.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                loader.dismiss()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("SignUpActivity", "onResponse: Success ${responseBody?.data}")
                    if (responseBody?.status != null) {
                        Toast.makeText(this@SignUpActivity, "Account Created", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@SignUpActivity, SuccessActivity::class.java))
                        finish()
                    } else {
                        loader.dismiss()
                        Toast.makeText(this@SignUpActivity, response.body()?.message, Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    val errorBody = response.errorBody()?.string()
                    loader.dismiss()
                    Toast.makeText(this@SignUpActivity, "SignUp Failed: $errorBody", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                loader.dismiss()
                Log.e("SignUpActivity", "onFailure: ${t.message}", t)
                Toast.makeText(this@SignUpActivity, "Server offline: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun openCalender(it: View?) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
            val formattedDate = String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
            binding.dobTv.setText(formattedDate)
        }, year, month, day)

        dpd.show()
    }


    private fun showGenderPopup(view: View) {
        val popup = PopupMenu(view.context, view)
        popup.menu.apply {
            genders.forEachIndexed { index, gender -> add(0, index, index, gender) }
        }
        popup.setOnMenuItemClickListener { menuItem ->
            val selectedGender = genders[menuItem.itemId]
            binding.genderTv.setText(selectedGender)
            genderId = menuItem.itemId + 1 // Assuming Male = 1, Female = 2, Others = 3
            true
        }
        popup.show()
    }
}
