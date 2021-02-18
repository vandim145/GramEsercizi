package ru.buroshag.gramesercizi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class GramExBtnNoEnter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gram_ex_btn_no_enter)

        val toast = Toast.makeText(applicationContext, "GramExBtnNoEnter ", Toast.LENGTH_LONG)
        toast.show()
    }

}