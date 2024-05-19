package com.example.taskapp.controller

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.networks.response.DetailsResponse
import kotlinx.coroutines.Delay

class ProfileAdapter(
    private val pData:MutableList<DetailsResponse.Data>
):RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {
    class ViewHolder(var bind:FragmentProfileBinding):RecyclerView.ViewHolder(bind.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}