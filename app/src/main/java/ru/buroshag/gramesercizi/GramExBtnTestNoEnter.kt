package ru.buroshag.gramesercizi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gram_ex_btn_test.*

class GramExBtnTestNoEnter : AppCompatActivity() {
        var iquestArr = 0                    // общий индекс в массиве вопросов и ответов
        var nAns=""                           // выбранный пользователем ответ
        lateinit var questArr: Array<String>  // массив вопросов и ответов
        lateinit var ans: EditText
        var items = (2..6).toMutableList() // --- создаем массив 2 - 6 пяти  чисел (в 0 - сам вопрос, в 1 позиция ответа в строке вопроса
        var position=""
        internal var iTrueAnswers: Int = 0  // количество правильных ответов
        internal var questtxt: String = ""  // Вопрос
        internal var posQuestArr = 0        // позиция пропущенных симбволов в строке вопроса
        companion object {  const val Q_ARR = "q_arr"
            const val IQ_ARR = "iq_arr"
            const val ITRUE = "itrue"
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_gram_ex_btn_test_no_enter)

            questArr=intent.getStringArrayExtra(Q_ARR)
            iquestArr=intent.getIntExtra(IQ_ARR,0)
            iTrueAnswers=intent.getIntExtra(ITRUE,0)
            position=intent.getStringExtra(GramEx.POS)
            println("position= $position")
            position_txt_btn.setText(position)

            val stringText1 = "$iTrueAnswers из ${questArr.size / 7}"
            numbOfRightAns.text = stringText1 // меняем кол-во правильных вопросов в строке на экране
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
        fun onClickBack(view: View) { //  Идем назад
            startActivity(Intent(this, MainActivity::class.java))
        }

        fun answBtn(view: View) {  // ответ проверяем и идем дальше
            when (view.id)
            {   R.id.answ1 -> nAns=questArr[iquestArr+items[0]]
                R.id.answ2 -> nAns=questArr[iquestArr+items[1]]
                R.id.answ3 -> nAns=questArr[iquestArr+items[2]]
                R.id.answ4 -> nAns=questArr[iquestArr+items[3]]
                R.id.answ5 -> nAns=questArr[iquestArr+items[4]]  }
            if (nAns.equals(questArr[iquestArr + 2], ignoreCase = true)){
                val toast = Toast.makeText(applicationContext, "Верно", Toast.LENGTH_SHORT)
                toast.show()
                iTrueAnswers++
                val stringText1 = "$iTrueAnswers из ${questArr.size / 7}"
                numbOfRightAns.text = stringText1 // меняем кол-во правильных вопросов в строке на экране
            }else {
                // неправильный ответ
                val toast = Toast.makeText(applicationContext, "Ошибка", Toast.LENGTH_SHORT)
                toast.show()
            }
            iquestArr += 7
            //println(" Btn iquestArr = $iquestArr size ${questArr.size}")
            if (iquestArr >= questArr.size) { // конец массива вопросов
                //println("Зашел btn")
                val toast = Toast.makeText(applicationContext, "Конец упражнения. Правильных ответов $iTrueAnswers", Toast.LENGTH_LONG)
                toast.show()
                val intent1= Intent(this, EndOfEx::class.java)
                intent1.putExtra(Q_ARR,questArr)
                intent1.putExtra(IQ_ARR,iquestArr)
                intent1.putExtra(ITRUE,iTrueAnswers)
                intent1.putExtra(GramEx.POS,position)
                startActivity(intent1)
                return
            }
            var randomInt =  (Math.random()*2).toInt()   //случайно переходим в GramEx или нет
            println("btn randomInt = $randomInt")
            if (randomInt == 0){                        // Переход имесли совпало и 2й столбец не отрицательный
                val intent= Intent(this, GramExTest::class.java)
                intent.putExtra(Q_ARR,questArr)
                intent.putExtra(IQ_ARR,iquestArr)
                intent.putExtra(ITRUE,iTrueAnswers)
                intent.putExtra(GramEx.POS,position)
                //println("Переходим в Ex")
                startActivity(intent)
                //println("не перешли в Ex")
                return
            }
            //println("Я здесь btn")
            res.text = null
            //println("Я здесь btn1")

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
            //println("Я здесь btn3")
        }
}