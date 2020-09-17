package ru.buroshag.gramesercizi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_end_of_ex.*

class EndOfEx : AppCompatActivity() {
    companion object {
        const val Q_ARR = "q_arr"
        const val IQ_ARR = "iq_arr"
        const val ITRUE = "itrue"
        const val IQUEST = "iquest"
    }
    lateinit var questArr: Array<String>  // массив вопросов и ответов
    var iquestArr = 0                    // общий индекс в массиве вопросов и ответов
    var iTrueAnswers: Int = 0  // количество правильных ответов
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_of_ex)

        println("Вошел 3")
        questArr=intent.getStringArrayExtra(Q_ARR)
        iquestArr=intent.getIntExtra(IQ_ARR,0)
        iTrueAnswers=intent.getIntExtra(ITRUE,0)
        val stringText1 = "$iTrueAnswers из ${questArr.size / 7}"
        numbOfRightAns.text=stringText1
    }

    fun onClickBack(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }
}
