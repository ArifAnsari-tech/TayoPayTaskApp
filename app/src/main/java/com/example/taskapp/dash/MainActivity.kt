package com.example.taskapp.dash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.NavHostFragment
import com.example.taskapp.R
import com.example.taskapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor= ContextCompat.getColor(this, R.color.secondary)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeContainer) as NavHostFragment?
        val navController = navHostFragment!!.navController

        binding.bottomNv.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.btHome -> {
                    navController.popBackStack()
                    navController.navigate(R.id.homeFragment)
                }

                R.id.btProfile -> {
                    navController.popBackStack()
                    navController.navigate(R.id.accountFragment)

                }
            }
           true
        }

    }
}

