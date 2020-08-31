package ru.buroshag.gramesercizi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gram_ex_btn.*
import kotlinx.android.synthetic.main.activity_gram_ex_btn.numbOfRightAns
import ru.buroshag.gramesercizi.R


class GramExBtn : AppCompatActivity() {

    var iquestArr = 0                    // общий индекс в массиве вопросов и ответов
    var nAns=""                           // выбранный пользователем ответ
    lateinit var questArr: Array<String>  // массив вопросов и ответов
    lateinit var ans: EditText
    var items = (2..6).toMutableList() // --- создаем массив 2 - 6 пяти  чисел (в 0 - сам вопрос, в 1 позиция ответа в строке вопроса
    internal var wasRight: Boolean = false // был ли уже правильный ответ на текущий вопрос, чтоб не накручивать счетчик правильных  ответов
    internal var iTrueAnswers: Int = 0  // количество правильных ответов
    internal var questtxt: String = ""  // Вопрос
    internal var posQuestArr = 0        // позиция пропущенных симбволов в строке вопроса
    companion object {  const val NUM_EX = "num_ex" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gram_ex_btn)

        val numEx=intent.getIntExtra(NUM_EX,1)
        when (numEx){
            0 -> questArr = resources.getStringArray(R.array.ex1)   // массив вопросов и ответов
            1 -> questArr = resources.getStringArray(R.array.ex2)
            2 -> questArr = resources.getStringArray(R.array.ex3)
            3 -> questArr = resources.getStringArray(R.array.ex4)   // массив вопросов и ответов
            4 -> questArr = resources.getStringArray(R.array.ex5)
            5 -> questArr = resources.getStringArray(R.array.ex6)
            6 -> questArr = resources.getStringArray(R.array.ex7)
            7 -> questArr = resources.getStringArray(R.array.ex8)
            8 -> questArr = resources.getStringArray(R.array.ex9)   // массив вопросов и ответов
            9 -> questArr = resources.getStringArray(R.array.ex10)
            10 -> questArr = resources.getStringArray(R.array.ex11)
            11 -> questArr = resources.getStringArray(R.array.ex12)

            else -> Toast.makeText(applicationContext, "чото не так", Toast.LENGTH_LONG).show()
        }

        iTrueAnswers = 0                                // количество правильных ответов

        // --- формируем вопрос
        posQuestArr = Integer.parseInt(questArr[iquestArr+1]) // второй столбец - где в строке находится вставляемый ответ, заменяется ?? с пробелом
        questtxt = questArr[iquestArr].substring(0, posQuestArr) + "?? " + questArr[iquestArr].substring(posQuestArr) //формируем строку вопроса
        quest.text = questtxt // и на экран
        //  случайно сортируем
        items.shuffle()
        // --- выводим ответы
        answ1.text=questArr[iquestArr+items[0]]
        answ2.text=questArr[iquestArr+items[1]]
        answ3.text=questArr[iquestArr+items[2]]
        answ4.text=questArr[iquestArr+items[3]]
        answ5.text=questArr[iquestArr+items[4]]


    }

    fun onClickA1nextBtn(view: View) {
        // println(" Вошли  1")
        res.text = null
        //println(" Вошли 2")
        //  ans.setText("")
        // println(" Вошли  3")
        iquestArr += 7
        wasRight = false
        //  println(" Вошли  iquestArr = $iquestArr")
        if (iquestArr >= questArr.size) { // конец массива вопросов
            val toast = Toast.makeText(applicationContext, "Конец упражнения. Правильных ответов $iTrueAnswers", Toast.LENGTH_LONG)
            toast.show()
            iquestArr -= 7
        }
        val iQuestArr = Integer.parseInt(questArr[iquestArr + 1])
        questtxt = questArr[iquestArr].substring(0, iQuestArr) + " ?? " + questArr[iquestArr].substring(iQuestArr)
        quest.text = questtxt
        items.shuffle()
        // --- выводим ответы
        answ1.text=questArr[iquestArr+items[0]]
        answ2.text=questArr[iquestArr+items[1]]
        answ3.text=questArr[iquestArr+items[2]]
        answ4.text=questArr[iquestArr+items[3]]
        answ5.text=questArr[iquestArr+items[4]]
    }

    fun onClickBack(view: View) { //  Идем назад
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun answBtn(view: View) {
        when (view.id)
        {
            R.id.answ1 -> nAns=questArr[iquestArr+items[0]]
            R.id.answ2 -> nAns=questArr[iquestArr+items[1]]
            R.id.answ3 -> nAns=questArr[iquestArr+items[2]]
            R.id.answ4 -> nAns=questArr[iquestArr+items[3]]
            R.id.answ5 -> nAns=questArr[iquestArr+items[4]]
        }
        if (nAns.equals(questArr[iquestArr + 2], ignoreCase = true) && wasRight == false){
            res.text = "Верно"
            wasRight = true
            iTrueAnswers++
            val stringText1 = "$iTrueAnswers из ${questArr.size / 7}"
            numbOfRightAns.text = stringText1 // меняем кол-во правильных вопросов в строке на экране
        }else {
            if (nAns.equals(questArr[iquestArr + 2], ignoreCase = true))
                res.text = "Верно" // ответ верен но уже отвечали
            else
                res.text = "Ошибка" // неправильный ответ
        }
    }
}