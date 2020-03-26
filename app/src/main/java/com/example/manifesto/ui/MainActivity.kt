package com.example.manifesto.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.manifesto.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //To setup navigation controller
        val navController=Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController)

    }
//To deal with navigation button pressed
    override fun onSupportNavigateUp(): Boolean {
      return NavigationUI.navigateUp(
          Navigation.findNavController(this,R.id.fragment),
          null
      )
    }
}
