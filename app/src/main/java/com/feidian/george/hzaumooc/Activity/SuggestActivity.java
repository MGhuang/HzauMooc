package com.feidian.george.hzaumooc.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.feidian.george.hzaumooc.Adapter.Suggestion.Talk;
import com.feidian.george.hzaumooc.Adapter.Suggestion.TalkAdapter;
import com.feidian.george.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;


public class SuggestActivity extends Activity {
    private List<Talk> msgList=new ArrayList<Talk>();
    private ListView list_view;
    private EditText input_text;
    private Button send;
    private TalkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        initData();
        list_view = (ListView) findViewById(R.id.list_view);
        input_text = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        adapter = new TalkAdapter(this,R.layout.a_suggest_l_item, msgList);
        list_view.setAdapter(adapter);

        //发送消息
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text = input_text.getText().toString();
                if(!"".equals(text)){
                    Talk msg = new Talk(text, Talk.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，更新ListView
                    adapter.notifyDataSetChanged();
                    //将ListView定位到最后一行
                    list_view.setSelection(msgList.size());
                    input_text.setText("");//情况输入框的内容
                }
            }
        });
    }
    private void initData() {
        // TODO Auto-generated method stub
        Talk msg1 = new Talk("您好，请问有什么可以帮您的么？", Talk.TYPE_RECEIVED);
        msgList.add(msg1);
    }
}
