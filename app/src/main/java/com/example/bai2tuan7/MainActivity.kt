package com.example.bai2tuan7

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var numberInput: EditText
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberInput = findViewById(R.id.numberInput)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioSquare = findViewById(R.id.radioSquare)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        errorTextView = findViewById(R.id.errorTextView)

        buttonShow.setOnClickListener {
            val n = numberInput.text.toString().toIntOrNull()
            if (n == null || n < 0) {
                errorTextView.text = "Vui lòng nhập một số nguyên dương."
                errorTextView.visibility = View.VISIBLE
                listView.adapter = null
            } else {
                errorTextView.visibility = View.GONE
                val results = when {
                    radioEven.isChecked -> getEvenNumbers(n)
                    radioOdd.isChecked -> getOddNumbers(n)
                    radioSquare.isChecked -> getSquareNumbers(n)
                    else -> emptyList()
                }
                listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val squares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squares.add(i * i)
            i++
        }
        return squares
    }
}
