package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_baseball.*
import kr.co.namu.testyourluck.adapters.ChattingAdapter
import kr.co.namu.testyourluck.datas.ChattingMessage

class BaseballActivity : AppCompatActivity() {

//    컴퓨터가 문제로 낸 세자리 숫자 저장할 배열
    val cpuNumbers = ArrayList<Int>()


//    Chatting log between a cpu and a user.
    val chattingMessageList = ArrayList<ChattingMessage>()

    lateinit var mChatAdapter : ChattingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseball)

//        Welcome message is shown by the cpu
        chattingMessageList.add(ChattingMessage("CPU","숫자야구 게임에 오신것을 환영합니다."))
        chattingMessageList.add(ChattingMessage("CPU","제가 생각하는 세자리 숫자를 맞춰주세요."))
        chattingMessageList.add(ChattingMessage("CPU","0은 없고, 중복된 숫자도 없습니다."))

//        print with listView (connect to adapter)

        mChatAdapter = ChattingAdapter(this,R.layout.chatting_message_list_item,chattingMessageList)
        chattingListView.adapter = mChatAdapter

//        문제 출제 기능 실행
        makeCpuNumbers()


    }

//    컴퓨터가 문제를 내주는 기능

    fun makeCpuNumbers(){
//        cpuNumbers에 랜덤한 세자리 숫자 배치
        for (i in 0..2){

//            중복이면 숫자 다시 뽑아야한다 => 중복이 아닐때 까지 계속 뽑는다
            while (true){
//                1~9 정수 랜덤 뽑기
                val randomNum = (Math.random()*9+1).toInt()

//              이미 뽑은 숫자인지 중복검사
                var isDuplicatedNum = false

//                문제로 나온 숫자를 돌려봐서 같은 숫자가 발견되면 중복인걸로 처리

                for(num in cpuNumbers){
                    if(num == randomNum){
                        isDuplicatedNum = true
                    }
                }

//                중복이 아니어야 숫자 사용가능
                if(!isDuplicatedNum){
                    cpuNumbers.add(randomNum)
                    break
                }
            }

        }

//        로그로 문제 숫자 화인
        for(num in cpuNumbers){
            Log.d("문제출제",num.toString())
        }
    }


}