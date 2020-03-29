package com.example.manifesto.ui

import android.content.Context
import android.widget.Toast

//Extension function to generate toast easier
fun Context.toast(message:String)=
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()