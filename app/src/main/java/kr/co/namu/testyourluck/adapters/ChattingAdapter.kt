package kr.co.namu.testyourluck.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import kr.co.namu.testyourluck.R
import kr.co.namu.testyourluck.datas.ChattingMessage

class ChattingAdapter(val mContext:Context, val resId : Int, val mList:List<ChattingMessage>)
    : ArrayAdapter<ChattingMessage>(mContext, resId, mList) {

    val inf = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

//        tempRow가 비어있는지? null인지? => 돌려막기 (재사용성 활용) 할 재료가 없다.
        if (tempRow == null) {
//            써먹을 재료가 없으면 새로 xml을 그려줘야함
            tempRow = inf.inflate(R.layout.chatting_message_list_item, null)

        }

//        tempRow는 더이상 null일 가능성이 없다. => row에게 전달.
        val row = tempRow!!

//       bring identified items from xml
        val cpuLayout = row.findViewById<LinearLayout>(R.id.cpuLayout)
        val cpuTxt = row.findViewById<TextView>(R.id.cpuTxt)
        val userLayout = row.findViewById<LinearLayout>(R.id.userLayout)
        val userTxt = row.findViewById<TextView>(R.id.userTxt)

//        bringing chatting data
        val data = mList[position]

//        실제 데이터 뿌려주기
//        사람이 말한건지 아닌지로 구별
        if(data.who == "USER"){
//            userLayout은 보여주고 cpuLayout은 숨긴다
            userLayout.visibility = View.VISIBLE
            cpuLayout.visibility = View.GONE

//            사용자 텍스트뷰에 내용 반영
            userTxt.text = data.content
        }
        else{

            userLayout.visibility = View.GONE
            cpuLayout.visibility = View.VISIBLE

//            cpu 텍스트뷰에 내용 반영
            cpuTxt.text = data.content

        }


//        최종 완성된 row를 리스트에 뿌리자.
        return row
    }

}