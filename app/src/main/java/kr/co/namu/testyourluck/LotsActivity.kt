package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lots.*

class LotsActivity : AppCompatActivity() {

//    제비 텍스트뷰 담을 목록
    val luckList = ArrayList<TextView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lots)

        title = "제비 뽑기"

//        목록에 제비를 담기

        luckList.add(luckTxt01)
        luckList.add(luckTxt02)
        luckList.add(luckTxt03)
        luckList.add(luckTxt04)
        luckList.add(luckTxt05)

        peopleCountMinusBtn.setOnClickListener {
//            현재 몇명인지 받아오기
            var peopleCount = peopleCountTxt.text.toString().toInt()

//            만약 이미 5명이면 강제종료

            if(peopleCount == 5){
                Toast.makeText(this,"제비는 5개 까지만 가능합니다.",Toast.LENGTH_SHORT).show()
            }

//            지금 인원수가 2개면 강제종료
            if(peopleCount == 2) {
                Toast.makeText(this,"제비는 최소 2개까지 가능합니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            사람 줄이기
            peopleCount--

//            인원수를 문구 변경
            peopleCountTxt.text = peopleCount.toString()

//            제비를 일단 다 숨겼다가 필요한 만큼 보여주기
            for(luck in luckList){
                luck.visibility = View.GONE
            }

//            필요한 만큼 보여주기
            for (i in 0..peopleCount-1){
                luckList[i].visibility = View.VISIBLE
            }

        }

        peopleCountPlusBtn.setOnClickListener {
            var peopleCount = peopleCountTxt.text.toString().toInt()
            peopleCount ++
            peopleCountTxt.text=peopleCount.toString()
        }

        unluckyMinusBtn.setOnClickListener {
            var unluckyCount = unluckyCountTxt.text.toString().toInt()
            unluckyCount --
            unluckyCountTxt.text=unluckyCount.toString()
        }

        unluckyPlusBtn.setOnClickListener {
            var unluckyCount = unluckyCountTxt.text.toString().toInt()
            unluckyCount ++
            unluckyCountTxt.text=unluckyCount.toString()
        }
    }
}