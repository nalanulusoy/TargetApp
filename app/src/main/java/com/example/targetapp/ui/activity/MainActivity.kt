package com.example.targetapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.targetapp.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.mainNavFragment)

        // Set up ActionBar
        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        (this as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
        // Set up navigation menu
        navigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.mainNavFragment), drawerLayout)
    }

}
