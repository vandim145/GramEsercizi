package ru.buroshag.gramesercizi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gram_ex_list.*

class GramExList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gram_ex_list)

        val arrItems=resources.getStringArray(R.array.gramA1Items)
        val adapter = ArrayAdapter < String>(this,android.R.layout.simple_list_item_1,arrItems)
        listViewItems.adapter=adapter
        listViewItems.setOnItemClickListener{parent,view,position,id->
            Toast.makeText(this,"position $position  id $id", Toast.LENGTH_LONG).show()
            val intent= Intent(this, GramExBtn::class.java)
            intent.putExtra(GramExBtn.Q_ARR,position)
            startActivity(intent)}
    }

    /*  fun onClickGramExListBack(view: View) {
          //  Идем назад
          startActivity(Intent(this, MainActivity::class.java))
      }*/
}
