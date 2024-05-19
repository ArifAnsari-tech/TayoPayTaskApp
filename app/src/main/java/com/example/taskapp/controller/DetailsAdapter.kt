package com.example.taskapp.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.databinding.TransactionItemBinding
import com.example.taskapp.model.HistoryModel
import com.example.taskapp.networks.response.DetailsResponse

class DetailsAdapter(
    private val uDetails:ArrayList<HistoryModel>
) :RecyclerView.Adapter<DetailsAdapter.ViewHolder>(){

    class ViewHolder(var binding:TransactionItemBinding) :RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TransactionItemBinding.bind(LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item,parent,false)))
    }

    override fun getItemCount(): Int {
        return uDetails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.flag.setImageResource(uDetails[position].image)
        holder.binding.mobileNu.text=uDetails[position].mobileNo
        holder.binding.userName.text=uDetails[position].uNAme
        holder.binding.dateTv.text=uDetails[position].date
        holder.binding.timeTv.text=uDetails[position].time
        holder.binding.paymentType.text=uDetails[position].type
        holder.binding.amountTv.text=uDetails[position].money
        holder.binding.statusTv.text=uDetails[position].status

    }
}