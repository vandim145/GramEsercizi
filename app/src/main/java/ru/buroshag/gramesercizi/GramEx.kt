package ru.buroshag.gramesercizi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gram_ex.*



class GramEx : AppCompatActivity() {
    var iquestArr = 0                    // общий индекс в массиве вопросов и ответов
    lateinit var questArr: Array<String>  // массив вопросов и ответов

    lateinit var ans: EditText
    internal var wasRight: Boolean = false // был ли уже правильный ответ на текущий вопрос, чтоб не накручивать счетчик правильных  ответов
    var iTrueAnswers: Int = 0  // количество правильных ответов
    internal var questtxt: String = ""
    var position=""
    companion object {
        const val Q_ARR = "q_arr"
        const val IQ_ARR = "iq_arr"
        const val ITRUE = "itrue"
        const val POS="pos"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gram_ex)

        ans = findViewById(R.id.answer)
        questArr=intent.getStringArrayExtra(Q_ARR)
        //println("questArr= ${questArr.size}")
        iquestArr=intent.getIntExtra(IQ_ARR,0)
        //println("iquestArr= $iquestArr")
        iTrueAnswers=intent.getIntExtra(ITRUE,0)
        //println("iTrueAnswers= $iTrueAnswers")
        position=intent.getStringExtra(POS)
        //println("position= $position")
        position_txt.setText(position)


        val stringText1 = "$iTrueAnswers из ${questArr.size / 7}"
        numbOfRightAns.text = stringText1 // меняем кол-во правильных вопросов в строке на экране
        // --------------------- обработка нажатия Enter
        ans.setOnEditorActionListener(TextView.OnEditorActionListener { _, _, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                // обработка нажатия Enter
                val answerText1 = ans.text.toString()
                val answerText = convertString(answerText1)
                if (answerText.equals(questArr[iquestArr + 2], ignoreCase = true) && wasRight == false) { // если верно и не было верного ответа на этот вопрос
                    res.text = "Верно"
                    wasRight = true
                    iTrueAnswers++
                    val stringText1 = "$iTrueAnswers из ${questArr.size / 7}"
                    numbOfRightAns.text = stringText1 // меняем кол-во правильных вопросов в строке на экране
                } else {
                    if (answerText.equals(questArr[iquestArr + 2], ignoreCase = true))
                        res.text = "Верно" // ответ верен но уже отвечали
                    else
                        res.text = "Ошибка" // неправильный ответ
                }
                return@OnEditorActionListener true //это если не хотим, чтобы нажатая кнопка обрабатывалась дальше видом, иначе нужно оставить false
            }
            false
        })
        //---------------------------
        val iQuestArr = Integer.parseInt(questArr[iquestArr+1]) // второй столбец - где в строке находится вставляемый ответ, заменяется ?? с пробелом
        questtxt = questArr[iquestArr].substring(0, iQuestArr) + "?? " + questArr[iquestArr].substring(iQuestArr) //формируем строку вопроса
        quest.text = questtxt // и на экран
    }

    fun onClickA1next(view: View) {
        iquestArr += 7
        if (iquestArr >= questArr.size) { // конец массива вопросов
            println("Вошел 1")
            val toast = Toast.makeText(applicationContext, " 4 Конец упражнения. Правильных ответов $iTrueAnswers", Toast.LENGTH_LONG)
            toast.show()
            val intent1=Intent(this, EndOfEx::class.java)
            intent1.putExtra(Q_ARR,questArr)
            intent1.putExtra(IQ_ARR,iquestArr)
            intent1.putExtra(ITRUE,iTrueAnswers)
            intent1.putExtra(POS,position)


            println("Вошел 2")
            startActivity(intent1)
            return
        }
        var randomInt =  (Math.random()*2).toInt()   //случайно переходим в GramEx или нет
        println("ex randomInt = $randomInt")
        if (randomInt == 0){                        // Переходим
            println(" Ex1 iquestArr = $iquestArr size ${questArr.size}")
            val intent=Intent(this, GramExBtn::class.java)
            intent.putExtra(GramEx.Q_ARR,questArr)
            intent.putExtra(GramEx.IQ_ARR,iquestArr)
            intent.putExtra(GramEx.ITRUE,iTrueAnswers)
            intent.putExtra(POS,position)

            startActivity(intent)
            return
        }
        res.text = null
        ans.setText(null)
        wasRight = false
        val iQuestArr = Integer.parseInt(questArr[iquestArr + 1])
        questtxt = questArr[iquestArr].substring(0, iQuestArr) + " ?? " + questArr[iquestArr].substring(iQuestArr)
        quest.text = questtxt
    }
    fun onClickControl(view: View) {
        val answerText1 = ans.text.toString()
        val answerText = convertString(answerText1)
        println("answerText =$answerText ")
        if (answerText.equals(questArr[iquestArr + 2], ignoreCase = true) && wasRight == false) {
            res.text = "Верно"
            wasRight = true
            iTrueAnswers++
            val stringText ="$iTrueAnswers из ${questArr.size/ 7}"
            numbOfRightAns.text = stringText
        } else {
            if (answerText.equals(questArr[iquestArr + 2], ignoreCase = true))
                res.text = "Верно"
            else
                res.text = "Ошибка"
        }
    }

    fun onClickBack(view: View) { //  Идем назад
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun convertString( strIn : String) : String {
        val str1 = strIn.replace("\'","`")
        val str2 = str1.replace("’’","`")
        return str2
    }
}

