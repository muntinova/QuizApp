package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var total:Int = 0
    var times:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val clicked = radioGroup.findViewById(checkedId) as RadioButton
            val checked = clicked.isChecked
            if (checked) {
                if (clicked.text.toString().equals("AVD Manager")) {
                    total = total + 50
                } else {
                    total = 0
                }
            }
        }
    }

    fun onSubmit(view : View) {
        if (times==false){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Submit")
            builder.setMessage("Do you want to submit?")
            builder.setIcon(R.drawable.submit)

            builder.setPositiveButton("Yes") { dialogInterface, which ->

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)+1
                val day = c.get(Calendar.DAY_OF_MONTH)

                val hour = c.get(Calendar.HOUR_OF_DAY)
                val minutes = c.get(Calendar.MINUTE)

                if (checkBox.isChecked &&  checkBox3.isChecked) {
                    total += 50
                }
                textView2.setTypeface(null, Typeface.BOLD_ITALIC)
                textView2.setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
                textView2.setTextColor(Color.parseColor("#6200EE"))
                textView2.text =
                    "You submitted on $month/$day/$year at $hour:$minutes " +
                    "\nYour score: " + total.toString()+"%"
                times = true

                Toast.makeText(applicationContext, "Submit done!", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }

            builder.setNegativeButton("No") { dialogInterface, which ->
                Toast.makeText(applicationContext, "Submit canceled!", Toast.LENGTH_SHORT).show()
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        }


    }

    fun onReset(view : View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Reset")
        builder.setMessage("Do you want to restart?")
        builder.setIcon(R.drawable.reset)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            radioButton3.setChecked(false)
            radioButton4.setChecked(false)
            radioButton5.setChecked(false)
            checkBox.setChecked(false)
            checkBox2.setChecked(false)
            checkBox3.setChecked(false)
            textView2.text = ""
            times=false
            total = 0

            Toast.makeText(applicationContext, "Reset done!", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface, which ->
            Toast.makeText(applicationContext, "Reset canceled!", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Exit"){ dialogInterface, which ->
            finish()
        }

        val dialog: AlertDialog = builder.create()

        dialog.show()

    }
}