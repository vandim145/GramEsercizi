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
        val arrIsKeyboard=resources.getStringArray(R.array.gramA1ItemsIsKeyboard) // будет ввод или нет
        //------------- адаптер для списка разделов   -------------------------
        val adapter = ArrayAdapter < String>(this,android.R.layout.simple_list_item_1,arrItems)
        listViewItems.adapter=adapter
        // ------------ листнер для выбора темы
        listViewItems.setOnItemClickListener{parent,view,position,id->
            var intent=Intent(this, GramExBtn::class.java)
            if (arrIsKeyboard[position] == "0") {
                if (isTest) intent=Intent(this, GramExBtnTestNoEnter::class.java)
                else        intent=Intent(this,GramExBtnNoEnter::class.java)
            }   else
            {
                if (isTest) intent=Intent(this, GramExBtnTest::class.java)
                else        intent=Intent(this,GramExBtn::class.java)
            }
            when (position){                                  // Выбор темы задачи и загрузка массива с задачами
                0 -> questArr1 = resources.getStringArray(R.array.ex1)   // массив вопросов и ответов неперемешанный
                1 -> questArr1 = resources.getStringArray(R.array.ex2)
                2 -> questArr1 = resources.getStringArray(R.array.ex3)
                3 -> questArr1 = resources.getStringArray(R.array.ex4)
                4 -> questArr1 = resources.getStringArray(R.array.ex5)
                else -> Toast.makeText(applicationContext, "чото не так", Toast.LENGTH_LONG).show()}

        var nquestArr = questArr1.size/7                        // Количество задач
        var nquestArr1 = questArr1.size                         // Количество элементов всего
        var questArr = Array<String>(nquestArr1) { i->i.toString()}  // массив вопросов и ответов для перемешевания
        var itemsQuestArrList = (0..nquestArr-1).toMutableList() // создаем для с числами
        itemsQuestArrList.shuffle()                            // случайно сортируем
        // -------------- заполняем перемешанный массив вопросов и ответов ----------------
        var j=0
        itemsQuestArrList.forEach{ // Создаем отсортированный массив
            for (i in 0 .. 6){
                questArr[j*7+i]=questArr1[it*7+i]}
            j+=1 }
        // готовим параметры для передачи и другую активность
        intent.putExtra(GramEx.Q_ARR,questArr)           // массив вопросов и ответов
        intent.putExtra(GramEx.IQ_ARR,iquestArr)         // текущий индекс массив вопросов и ответов
        intent.putExtra(GramEx.ITRUE,iTrueAnswers)       // количество правильных ответов
        intent.putExtra(GramEx.POS,arrItems[position])   // тема задания
        startActivity(intent)}
    }
    fun onClickFinish(view: View) {
        this@MainActivity.finish()
        exitProcess(0)}
    fun onClickLearn(view: View) {
        isTest=false
        butTest.setBackgroundColor(Color.RED)
        butLearn.setBackgroundColor(Color.GREEN)}
    fun onClickTest(view: View) {
        isTest=true
        butTest.setBackgroundColor(Color.GREEN)
        butLearn.setBackgroundColor(Color.RED)}
}
