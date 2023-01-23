package com.example.projectexe

import android.app.DatePickerDialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var resultDOB:TextView
    private lateinit var resultAge:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val buttonClickDate: Button = findViewById<Button>(R.id.chooseDateButton)
        var attendedVal = 0

        val resultText = findViewById<TextView>(R.id.resultText)
        resultDOB= findViewById<TextView>(R.id.dateOfBirth)
        resultAge= findViewById<TextView>(R.id.age)

        findViewById<Button>(R.id.main_button).setOnClickListener {
            attendedVal++
            resultText.text = "Registration Number" + UUID.randomUUID()
        }

        val myCalender=Calendar.getInstance()
       val datePicker= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR,year)
            myCalender.set(Calendar.MONTH,month)
            myCalender.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateDateLabel(myCalender)
        }

        buttonClickDate.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalender.get(Calendar.YEAR),
                myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateDateLabel(myCalender:Calendar){
        val format="dd-MM-yyyy"
        
        val dob=LocalDate.of(myCalender.get(Calendar.YEAR),
            myCalender.get(Calendar.MONTH)+1,
            myCalender.get(Calendar.DAY_OF_MONTH)
        )
        resultDOB.text=dob.toString()
       val age=Period.between(dob,LocalDate.now())
        resultAge.text=age.years.toString()+"years "+age.months.toString()+" months "+age.days.toString()+" days"
    }
}
