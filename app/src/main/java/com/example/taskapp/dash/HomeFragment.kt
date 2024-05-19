package com.example.taskapp.dash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp.R
import com.example.taskapp.controller.DetailsAdapter
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.model.HistoryModel


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var detailsAdapter: DetailsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uList = ArrayList<HistoryModel>()
        uList.add(HistoryModel(R.drawable.soma,"+252-235-48-899","Abdullahi Ahmed","18-04-2024","09:30","Cash Collection","1001.00 USD","PAID"))
        uList.add(HistoryModel(R.drawable.uae,"+971-235-40-000","Mohammed Irfan","18-04-2024","15:45","Cash Collection","500.00 USD","PENDING"))
        uList.add(HistoryModel(R.drawable.soma,"+252-235-48-899","Abdullahi Ahmed","18-04-2024","09:30","Mobile Money","1001.00 USD","PAID"))
        uList.add(HistoryModel(R.drawable.uae,"+971-235-40-000","Mohammed Irfan","18-04-2024","15:45","Cash Collection","500.00 USD","PENDING"))
        uList.add(HistoryModel(R.drawable.soma,"+252-235-48-899","Abdullahi Ahmed","18-04-2024","09:30","Cash Collection","1001.00 USD","PAID"))

        detailsAdapter=DetailsAdapter(uList)
        binding.listRcl.adapter=detailsAdapter



    }

}