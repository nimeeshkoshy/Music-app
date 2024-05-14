package com.example.musicapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.String

import kotlin.Throwable

class MainActivity : AppCompatActivity() {
    lateinit var myRecyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        myRecyclerView=findViewById(R.id.recyclerView)

       val apiInterface = ApiClient().getRetrofit().create(ApiInterface::class.java)

        apiInterface.getData("eminem").enqueue(object : Callback<Root?>{
            override fun onResponse(call: Call<Root?>, response: Response<Root?>) {
              // if api call is success then this method is executed

                val dataList=response.body()?.data!!
            /*    val textView=findViewById<TextView>(R.id.text2)
                textView.text=dataList.toString()*/

                myAdapter=MyAdapter(this@MainActivity,dataList)
                myRecyclerView.adapter=myAdapter
                myRecyclerView.layoutManager=LinearLayoutManager(this@MainActivity)


                Log.d("onResponce","onResponce"+response.body())
            }

            override fun onFailure(call: Call<Root?>, t: Throwable) {
              //  TODO("Not yet implemented")

                Log.d("onFailure","onFailure"+t.message)


            }


        })
    }
}