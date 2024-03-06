package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)
        val nextPage = findViewById<Button>(R.id.btnDetails)

        calcButton.setOnClickListener{
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if(validateInput(weight, height)) {
                //operations
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * height.toFloat() / 100)

                //get result in two decimal places
                val bmiAns = String.format("%.2f", bmi).toFloat()
                displayResult(bmiAns)
            }
        }

        //move user to another activity page
        nextPage.setOnClickListener{
            val intent = Intent(this,displayPage::class.java)

            //retrieve the code
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            //putExtra function is to retrieve back the passed variable
            intent.putExtra("WEIGHT", weight)
            intent.putExtra("HEIGHT", height)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else->{
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDesc = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal Range is 18.5 - 24.9"

        var resultText = ""
        var color = 0

        when{
            bmi < 18.50 -> {
                resultText = "Underweight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.overweight
            }
            bmi > 29.99 -> {
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDesc.setTextColor(ContextCompat.getColor(this,color))
        resultDesc.text = resultText
    }
}