package com.example.taskapp.dash

import android.app.ProgressDialog
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taskapp.auth.LoginActivity
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.networks.ApiInterface
import com.example.taskapp.networks.RetrofitService
import com.example.taskapp.networks.response.DetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sharedPreferences = requireActivity().getSharedPreferences("LogPref", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "") ?: ""

        val retrofitClient = RetrofitService().getClient(token)?.create(ApiInterface::class.java)
        val detail = retrofitClient?.getDetails()

        detail?.enqueue(object : Callback<DetailsResponse> {
            override fun onResponse(
                call: Call<DetailsResponse>,
                response: Response<DetailsResponse>
            ) {
                if (response.isSuccessful) {
                    val profileData = response.body()?.data
                    profileData?.let {
                        binding.fsName.text = it.firstName
                        binding.ltName.text = it.lastName
                        binding.pEmail.text = it.email
                        binding.pMobile.text = it.mobilePhone

                        binding.mlName.text = ""

                    }
                    Log.d("ProfileFragment", "onResponse: $profileData")
                } else {
                    val errorBody = response.errorBody()?.string()
                    if (errorBody?.contains("sessionExpired") == true) {
                        sessionExpiry()
                    } else {
                        Log.d("ProfileFragment", "onResponse: $errorBody")
                    }
                    Log.d("ProfileFragment", "onResponse: ${response.errorBody()?.string()}")
                }
            }


            override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {
                Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show()
                Log.e("ProfileFragment", "onFailure: ${t.message}", t)
            }
        })
    }
    private fun sessionExpiry() {
        Toast.makeText(context, "Session expired, please login again.", Toast.LENGTH_SHORT).show()

        val sharedPreferences = requireActivity().getSharedPreferences("LogPref", MODE_PRIVATE)
        sharedPreferences.edit().remove("token").apply()

        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
