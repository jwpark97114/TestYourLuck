package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_baseball.*
import kr.co.namu.testyourluck.adapters.ChattingAdapter
import kr.co.namu.testyourluck.datas.ChattingMessage

class BaseballActivity : AppCompatActivity() {

//    몇번만에 문제를 맞췄는지 기록하기 위한 변수
    var tryCount = 0

//    컴퓨터가 문제로 낸 세자리 숫자 저장할 배열
    val cpuNumbers = ArrayList<Int>()


//    Chatting log between a cpu and a user.
    val chattingMessageList = ArrayList<ChattingMessage>()

    lateinit var mChatAdapter : ChattingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseball)

        //        print with listView (connect to adapter)

        mChatAdapter = ChattingAdapter(this,R.layout.chatting_message_list_item,chattingMessageList)
        chattingListView.adapter = mChatAdapter


//        Welcome message is shown by the cpu

        Handler().postDelayed({
//            일정 시간 이후에 실행할 내용
            chattingMessageList.add(ChattingMessage("CPU","숫자야구 게임에 오신것을 환영합니다."))
            mChatAdapter.notifyDataSetChanged()
        }, 1000)

        Handler().postDelayed({
        chattingMessageList.add(ChattingMessage("CPU","제가 생각하는 세자리 숫자를 맞춰주세요."))
        mChatAdapter.notifyDataSetChanged()
        },2000)

        Handler().postDelayed({
        chattingMessageList.add(ChattingMessage("CPU","0은 없고, 중복된 숫자도 없습니다."))
        mChatAdapter.notifyDataSetChanged()
        },3000)



//        문제 출제 기능 실행
        makeCpuNumbers()

//        입력버튼 이벤트 처리
        okBtn.setOnClickListener {
//            입력내용 확인
            val input = inputNumEdt.text.toString()

//            3자리 아니면 거부
            if(input.length !=3){
                Toast.makeText(this,"세자리 숫자로 입력하세요",Toast.LENGTH_SHORT).show()
//                실행못하게 강제종료
                return@setOnClickListener
            }

//            같은 값을 두번이상 넣을 일은 없다
//            입력되어 있던 내용을 빈칸으로 변경
            inputNumEdt.setText("")

//            위 조건문 이후로는 무조건 숫자가 세자리임이 확인됨
//            채팅 내용으로 띄워주기

            chattingMessageList.add(ChattingMessage("USER",input))

//            어댑터가 새로고침 해야 내용 반영
            mChatAdapter.notifyDataSetChanged()

//            리스트뷰 화면 바닥으로 끌어내리기 => 새로 입력된 숫자가 보이게
            chattingListView.smoothScrollToPosition(chattingMessageList.size-1)

//            몇 스트라이크 몇 볼인지 답장하기
            checkStrikeAndBall(input)



        }

    }

//          컴퓨터가 답장해주는 기능

    fun checkStrikeAndBall(inputString: String){

//        한번  시도 했으므로 시도카운트 증가
        tryCount++

//        정답이 741 => 입력 145 인 경우 1스트라이크 1볼
//        입력값을 숫자로 바꿔야

        val inputNum = inputString.toInt()

//        숫자를 다시 배열로 쪼개자

        val userNumbers = ArrayList<Int>()

        userNumbers.add(inputNum/100)
        userNumbers.add((inputNum/10)%10)
        userNumbers.add(inputNum%10)

//        스트라이크 & 볼 갯수 파악
        var strikeCount = 0;
        var ballCount = 0;

//        i가 내 숫자를 확인하는 역할
        for(i in 0..2){

//            j는 문제 숫자를 확인하는 역할
            for(j in 0..2){

//                두 칸에 적힌 숫자가 같은가?
                if(userNumbers[i] == cpuNumbers[j]){
//                    같다면 스크라이크인지 볼인지도 검사
                    if(i==j){
                        strikeCount++
                    }
                    else{
                        ballCount++
                    }
                }
            }
        }

//        구해낸 스트라이크 갯수와 볼 갯수로 컴퓨터 답장 출력하기
        val cpuMessage = "s:${strikeCount}, b:${ballCount} 입니다"

//        컴퓨터의 답장을 0.7초 딜레이 넣기
        Handler().postDelayed({

        chattingMessageList.add(ChattingMessage("CPU",cpuMessage))

        mChatAdapter.notifyDataSetChanged()

        chattingListView.smoothScrollToPosition(chattingMessageList.size-1)}
            ,700)


//        정답 체크를 1.4초 후에 해 채팅이 이어지듯 보이게 하기

        Handler().postDelayed({

//        만약 3 스트라이크 일 경우 "정답" 출력후 종료시키기

        if(strikeCount==3){

            chattingMessageList.add(ChattingMessage("CPU","정답입니다."))

            chattingMessageList.add(ChattingMessage("CPU","${tryCount}회 만에 맞췄습니다."))
            mChatAdapter.notifyDataSetChanged()
            chattingListView.smoothScrollToPosition(chattingMessageList.size-1)

//            정답을 맞출시 입력 못하게 막기 => enabled false 로 비활성화

            inputNumEdt.isEnabled = false
            okBtn.isEnabled = false

//            게임 종료 안내메세지 토스트로 띄우기
            Toast.makeText(this,"이용해 주셔서 감사합니다.",Toast.LENGTH_SHORT).show()


        }},1400)



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