package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_baseball.*
import kr.co.namu.testyourluck.adapters.ChattingAdapter
import kr.co.namu.testyourluck.datas.ChattingMessage

class BaseballActivity : AppCompatActivity() {


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



    }
}