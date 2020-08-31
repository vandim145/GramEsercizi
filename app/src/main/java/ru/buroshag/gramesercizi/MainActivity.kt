package ru.buroshag.gramesercizi

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val arrItems=resources.getStringArray(R.array.gramA1Items)
        val adapter = ArrayAdapter < String>(this,android.R.layout.simple_list_item_1,arrItems)
        listViewItems.adapter=adapter
        listViewItems.setOnItemClickListener{parent,view,position,id->
            Toast.makeText(this,"position $position  id $id", Toast.LENGTH_LONG).show()
            val intent=Intent(this, GramExBtn::class.java)
            intent.putExtra(GramExBtn.NUM_EX,position)
            startActivity(intent)}
    }
}
