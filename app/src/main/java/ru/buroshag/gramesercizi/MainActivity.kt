package ru.buroshag.gramesercizi

import android.content.Intent
import android.graphics.Color
import android.graphics.Color.green
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    var isTest = false                     //  если режим теста, то true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var iquestArr = 0                      // общий индекс в массиве вопросов и ответов
        var iTrueAnswers: Int = 0              // количество правильных ответов
        lateinit var questArr1: Array<String>  // массив вопросов и ответов неперемешанный
        val arrItems=resources.getStringArray(R.array.gramA1Items)  // список тем заданий
        val adapter = ArrayAdapter < String>(this,android.R.layout.simple_list_item_1,arrItems)
        listViewItems.adapter=adapter
        listViewItems.setOnItemClickListener{parent,view,position,id->
            var intent=Intent(this, GramEx::class.java)
            if (isTest) intent=Intent(this, GramExTest::class.java)
            when (position){ // Выбор темы задачи и загрузка массива с задачами
                0 -> questArr1 = resources.getStringArray(R.array.ex1)   // массив вопросов и ответов неперемешанный
                1 -> questArr1 = resources.getStringArray(R.array.ex2)
                2 -> questArr1 = resources.getStringArray(R.array.ex3)
                3 -> questArr1 = resources.getStringArray(R.array.ex4)
                4 -> questArr1 = resources.getStringArray(R.array.ex5)
                else -> Toast.makeText(applicationContext, "чото не так", Toast.LENGTH_LONG).show()}
            println(" тема = ${arrItems[position]}")
            var nquestArr = questArr1.size/7                        // Количество задач
            println(" nquestArr = $nquestArr")
            var nquestArr1 = questArr1.size                         // Количество элементов всего
            println(" nquestArr1 = $nquestArr1")
            var questArr = Array<String>(nquestArr1) { i->i.toString()}  // массив вопросов и ответов для перемешевания
            println("размер ${questArr.size}")
            questArr.forEach { println( " $it ") }
            var itemsQuestArrList = (0..nquestArr-1).toMutableList() // создаем для с числами
            itemsQuestArrList.shuffle()                            // случайно сортируем
        //    itemsQuestArrList.forEach{ println( "itemsQuestArrList $it ")}
        // -------------- заполняем перемешанный массив вопросов и ответов ----------------
            var j=0
            itemsQuestArrList.forEach{
         //       println( " цикл it= $it j= $j")
                for (i in 0 .. 6){
         //           println( " i $i")
         //           println( " индексы ${j*7+i}")
         //           println( " индексы ${it*7+i}")
         //           println( " questArr[j*7+i] ${questArr[j*7+i]}")
         //           println( " questArr1[it+i] ${questArr1[it*7+i]}")
                    questArr[j*7+i]=questArr1[it*7+i]
                    println( " questArr[j*7+i] ${questArr[j*7+i]}")
                }
                j+=1
            }          // Создаем отсортированный массив

            intent.putExtra(GramEx.Q_ARR,questArr)
            intent.putExtra(GramEx.IQ_ARR,iquestArr)
            intent.putExtra(GramEx.ITRUE,iTrueAnswers)
            intent.putExtra(GramEx.POS,arrItems[position])
            intent.putExtra(GramEx.IS_TEST,isTest)
            questArr.forEach{ println( " $it ")}
            startActivity(intent)}
    }

    fun onClickFinish(view: View) {
        this@MainActivity.finish()
        exitProcess(0)
    }

    fun onClickLearn(view: View) {
        isTest=false
        butTest.setBackgroundColor(Color.RED)
        butLearn.setBackgroundColor(Color.GREEN)

    }
    fun onClickTest(view: View) {
        isTest=true
        butTest.setBackgroundColor(Color.GREEN)
        butLearn.setBackgroundColor(Color.RED)
    }
}
