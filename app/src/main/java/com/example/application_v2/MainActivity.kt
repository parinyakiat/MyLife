package com.example.application_v2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.application_v2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        val navController = this.findNavController(R.id.myNavHostFragment)
//        NavigationUI.setupWithNavController(binding.navView, navController)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        drawerLayout = binding.drawerLayout
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.myNavHostFragment)
//        return NavigationUI.navigateUp(navController, drawerLayout)
//    }
}
