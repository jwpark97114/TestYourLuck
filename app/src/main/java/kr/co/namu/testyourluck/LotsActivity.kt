package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lots.*

class LotsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lots)

        title = "제비 뽑기"

        peopleCountMinusBtn.setOnClickListener {
//            현재 몇명인지 받아오기
            var peopleCount = peopleCountTxt.text.toString().toInt()

//            사람 줄이기
            peopleCount--

//            인원수를 문구 변경
            peopleCountTxt.text = peopleCount.toString()
        }

        peopleCountPlusBtn.setOnClickListener {
            var peopleCount = peopleCountTxt.text.toString().toInt()
            peopleCount ++
            peopleCountTxt.text=peopleCount.toString()
        }
    }
}