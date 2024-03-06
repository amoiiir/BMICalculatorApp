package com.example.bmicalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.bmicalculator.databinding.ActivityDisplayPageBinding

class displayPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_display_page)
        val userWeight = intent.getStringExtra("WEIGHT")
        val textView = findViewById<TextView>(R.id.tvUserWeight)
        val userHeight = intent.getStringExtra("HEIGHT")
        val heightView = findViewById<TextView>(R.id.tvUserHeight)

        val message = "$userWeight, is your current weight"
        textView.text = message
        val message1 = "$userHeight, is your current height"
        heightView.text = message1

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}