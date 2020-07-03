package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rock_paper_scissors.*

class RockPaperScissorsActivity : AppCompatActivity() {

//    그림파일 id값 저장할 목록
    val imgList = ArrayList<Int>()

//    컴퓨터가 무엇을 냈는지 저장
    var cpuPick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)

//        목록에 그림 세장 추가
        imgList.add(R.drawable.scissors)
        imgList.add(R.drawable.rock)
        imgList.add(R.drawable.paper)


        scissorBtn.setOnClickListener {

//            0부터 2까지 하나를 랜덤으로 추출 ()
            val randomNum = (Math.random() * 3).toInt()

            val thisTimePick = imgList[randomNum]

//            랜덤으로 뽑힌 그림 띄워주기
            cpuImg.setImageResource(thisTimePick)

            cpuPick = randomNum
            getResultOfRPS(myPick = 0)


        }

        rockBtn.setOnClickListener {

//            0부터 2까지 하나를 랜덤으로 추출 ()
            val randomNum = (Math.random() * 3).toInt()

            val thisTimePick = imgList[randomNum]

//            랜덤으로 뽑힌 그림 띄워주기
            cpuImg.setImageResource(thisTimePick)


            cpuPick = randomNum
            getResultOfRPS(myPick = 1)

        }

        paperBtn.setOnClickListener {

//            0부터 2까지 하나를 랜덤으로 추출 ()
            val randomNum = (Math.random() * 3).toInt()

            val thisTimePick = imgList[randomNum]

//            랜덤으로 뽑힌 그림 띄워주기
            cpuImg.setImageResource(thisTimePick)


            cpuPick = randomNum
            getResultOfRPS(myPick = 2)

        }

    }

//    승패판정
    fun getResultOfRPS(myPick:Int){

//    사용자가 가위를 냈다면
        if(myPick == 0){
            if(cpuPick==0){
                Toast.makeText(this,"무승부",Toast.LENGTH_SHORT).show()
            }
            else if(cpuPick==1){
                Toast.makeText(this,"패배",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"승리",Toast.LENGTH_SHORT).show()
            }
        }
        else if(myPick == 1){
            if(cpuPick==0){
                Toast.makeText(this,"승리",Toast.LENGTH_SHORT).show()
            }
            else if(cpuPick==1){
                Toast.makeText(this,"무승부",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"패배",Toast.LENGTH_SHORT).show()
            }

        }
        else{
            if(cpuPick==0){
                Toast.makeText(this,"패배",Toast.LENGTH_SHORT).show()
            }
            else if(cpuPick==1){
                Toast.makeText(this,"승리",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"무승부",Toast.LENGTH_SHORT).show()
            }

        }
    }
}