package com.example.todo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.Data.db_Helper
import com.example.todo.Data.personal
import com.example.todo.adapter.CustomAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_load.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //ANIMATION TAKES PLACE HERE
    internal lateinit var animation: Animation
    internal lateinit var animation2: Animation

    internal lateinit var animation3: Animation
    internal lateinit var animation4: Animation

    lateinit var cat_text: EditText
    lateinit var place_text: EditText
    lateinit var time_text: EditText
    lateinit var about_text: EditText


    //ANIMATION TAKES PLACE HERE

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_load)


        //ANIMATION TAKES PLACE HERE OF MOVING SIDEWAYS
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.plus)
        animation2 = AnimationUtils.loadAnimation(applicationContext, R.anim.plus_second_page)
        //ANIMATION TAKES PLACE HERE OF MOVING SIDEWAYS

        //ANIMATION TAKES PLACE HERE OF MOVING UPWARDS
        animation3 = AnimationUtils.loadAnimation(applicationContext, R.anim.upwards_personal)
        animation4 = AnimationUtils.loadAnimation(applicationContext, R.anim.upwards_personal_2)
        //ANIMATION TAKES PLACE HERE OF MOVING UPWARDS

        //calling the layout here
        val fisrt_page = findViewById<RelativeLayout>(R.id.fisrt_page) as RelativeLayout
        val second_page = findViewById<RelativeLayout>(R.id.second_page) as RelativeLayout

        val personal = findViewById<ConstraintLayout>(R.id.personal) as ConstraintLayout
        //calling the layout here

        //calling the plus_todo imageView and giving it click listener
        plus_todo.setOnClickListener {
            fisrt_page.startAnimation(animation)
            fisrt_page.visibility = View.GONE
            second_page.startAnimation(animation2)
            second_page.visibility = View.VISIBLE

        }
        //calling the plus_todo imageView and giving it click listener

        //calling the imageView_back imageView and giving it click listener
        imageView_back.setOnClickListener {
            second_page.startAnimation(animation)
            second_page.visibility = View.GONE
            fisrt_page.startAnimation(animation2)
            fisrt_page.visibility = View.VISIBLE

        }
        //calling the imageView_back imageView and giving it click listener

        //calling the per_but button and giving it click listener
        per_but.setOnClickListener {
            fisrt_page.startAnimation(animation3)
            fisrt_page.visibility = View.GONE
            personal.startAnimation(animation4)
            personal.visibility = View.VISIBLE

            //RecyclerView
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)

            //crating an arraylist to store users using the data class user
            val person = ArrayList<personal>()

            //adding some dummy data to the list
            val db_Helper_per = db_Helper(this, null)
            var cursor = db_Helper_per.getAllName()

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        var re_place = cursor.getString(cursor.getColumnIndex(db_Helper.COLUMN_PLACE))
                        var re_time = cursor.getString(cursor.getColumnIndex(db_Helper.COLUMN_TIME))
                        var re_about = cursor.getString(cursor.getColumnIndex(db_Helper.COLUMN_ABOUT))
                        println(re_about)
                        println(re_place)
                        println(re_time)
                        person.add(personal(re_place, re_time, re_about))
                    } while (cursor.moveToNext())
                }
            }


            //creating our adapter
            val adapter = CustomAdapter(person)

            //now adding the adapter to recyclerview
            recyclerView.adapter = adapter
            //RecyclerView
        }
        //calling the Personal button and giving it click listener

        //calling the bus_but button and giving it click listener
        bus_but.setOnClickListener {
            fisrt_page.startAnimation(animation3)
            fisrt_page.visibility = View.GONE
            personal.startAnimation(animation4)
            personal.visibility = View.VISIBLE
        }
        //calling the bus_but button and giving it click listener

        //calling the floatingActionButton2 floatingActionButton and giving it click listener
        return_back.setOnClickListener {
            personal.startAnimation(animation3)
            personal.visibility = View.GONE
            fisrt_page.startAnimation(animation4)
            fisrt_page.visibility = View.VISIBLE
        }
        //calling the floatingActionButton2 floatingActionButton and giving it click listener


        //this where my database action takes place


        save_button.setOnClickListener {
            cat_text = findViewById<EditText>(R.id.cat_text) as EditText
            place_text = findViewById<EditText>(R.id.place_text) as EditText
            time_text = findViewById<EditText>(R.id.time_text) as EditText
            about_text = findViewById<EditText>(R.id.about_text) as EditText

            var getcat: String = cat_text.text.toString().trim().toLowerCase()
            var getplace: String = place_text.text.toString().trim().toUpperCase()
            var gettime: String = time_text.text.toString().trim()
            var getabout: String = about_text.text.toString().trim()


            cat_text.setText("")
            place_text.setText("")
            time_text.setText("")
            about_text.setText("")

            val db_Helper_new = db_Helper(this, null)
            db_Helper_new.addItem(getcat, getplace, gettime, getabout)

            second_page.startAnimation(animation)
            second_page.visibility = View.GONE
            fisrt_page.startAnimation(animation2)
            fisrt_page.visibility = View.VISIBLE
        }
        //this where my database action takes place

    }
}
