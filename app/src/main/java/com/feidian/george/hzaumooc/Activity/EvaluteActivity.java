package com.feidian.george.hzaumooc.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feidian.george.hzaumooc.Adapter.Evalute.EvaluteAdapter;
import com.feidian.george.hzaumooc.Bmob.Bean.Evalute;
import com.feidian.george.hzaumooc.Bmob.BmobOperate;
import com.feidian.george.hzaumooc.Bmob.BmobWrite;
import com.feidian.george.hzaumooc.Bmob.Upload.BmobUpload;
import com.feidian.george.hzaumooc.Interface.Class.UpdateListener;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.LoginUtil;
import com.feidian.george.hzaumooc.Tool.Main_StaticValue;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by lenovo on 2016/5/13.
 */
public class EvaluteActivity extends Activity implements View.OnClickListener ,UpdateListener{

    public static final String BUNDLE_CLASSNAME = "courseName";
    public static final String BUNDLE_URL = "url";
    String courseName;

    @Bind(R.id.evalute_topview)
    RelativeLayout evalute_topview;
    @Bind(R.id.evalute_frame)
    FrameLayout evalute_frame;
    @Bind(R.id.evalute_bigscreen)
    ImageView evalute_bigscreen;
    @Bind(R.id.evalute_vedio)
    VideoView evalute_vedio;
    @Bind(R.id.evalute_send)
    Button evalute_send;
    @Bind(R.id.evalute_edit)
    EditText evalute_edit;
    @Bind(R.id.evalute_back)
    ImageView evalute_back;
    @Bind(R.id.evalute_videoName)
    TextView evalute_videoName;
    @Bind(R.id.evalute_listview)
    ListView evalute_listview;
    @Bind(R.id.evalute_under)
    LinearLayout evalute_under;

    private android.os.Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case Main_StaticValue.SUCCESS_GET_DATA:
                    adapter.notifyDataSetChanged();
                    break;
                case Main_StaticValue.WRONG_GET_DATA:
                    Toast.makeText(EvaluteActivity.this,"亲，请检查网络...",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(EvaluteActivity.this,"亲，请检查网络...",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private List<Evalute> data = new ArrayList<>();
    private EvaluteAdapter adapter;


    private static long position = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalute);
        ButterKnife.bind(this);
        Bmob.initialize(this, "bdc6b1e1572437dda9938ac54b702a5b");
        initView();
    }


    //按钮的处理
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //将屏幕设置为横屏
            case R.id.evalute_bigscreen:
                if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    evalute_under.setVisibility(View.GONE);
                    evalute_topview.setVisibility(View.GONE);
                    evalute_vedio.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM, 0);
                    position = evalute_vedio.getCurrentPosition();
                    evalute_vedio.seekTo(position);
                } else {

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    evalute_under.setVisibility(View.VISIBLE);
                    evalute_topview.setVisibility(View.VISIBLE);
                    long x = evalute_vedio.getCurrentPosition();
                    evalute_vedio.seekTo(x);
                }
                break;
            //设置评论的监听，并上传数据库
            case R.id.evalute_send:
                String words = evalute_edit.getText().toString();
                if (words.equals("")) {
                    Toast.makeText(this, "评论不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    Evalute evalute = new Evalute(words, courseName);
                    BmobWrite.Inist().uploadEvaluted(evalute,this);
                    data.add(evalute);
                    adapter.notifyDataSetChanged();
                    evalute_edit.setText("");
                }
                break;
            case R.id.evalute_back:
                finish();
            default:
                break;
        }
    }

    //手机返回按钮处理事件
    @Override
    public void onBackPressed() {
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            position = evalute_vedio.getCurrentPosition();
            Toast.makeText(this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            evalute_under.setVisibility(View.VISIBLE);
            evalute_topview.setVisibility(View.VISIBLE);
            evalute_vedio.seekTo(position);
        } else {
            finish();
        }
    }
    private void initView()
    {
        Intent intent = getIntent();
        String url = intent.getStringExtra(BUNDLE_URL);
        courseName = intent.getStringExtra(BUNDLE_CLASSNAME);
        evalute_bigscreen.setOnClickListener(this);
        evalute_send.setOnClickListener(this);
        evalute_back.setOnClickListener(this);
        adapter = new EvaluteAdapter(data, this, R.layout.a_evalute_l_item);
        BmobOperate.Inist().getEvaluteDate("courseName",courseName,data,this,this);
        evalute_listview.setAdapter(adapter);
        setPlay(url);
    }
    void setPlay(String url) {
        if (Vitamio.initialize(this)) {
            evalute_vedio.setVideoURI(Uri.parse(url));
            MediaController mediaController = new MediaController(this, true, evalute_frame);
            evalute_vedio.setMediaController(mediaController);
            //videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ORIGIN,0);
            evalute_vedio.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                }
            });
            evalute_vedio.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                                                @Override
                                                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                                                    switch (what) {
                                                        //开始缓冲
                                                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                                                            mp.pause();
                                                            break;
                                                        //缓冲结束
                                                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:

                                                            mp.start();
                                                            break;
                                                        //正在缓冲
                                                        case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                                                            break;

                                                    }
                                                    return true;
                                                }
                                            }
            );
            evalute_vedio.start();
            evalute_vedio.seekTo(position);
        }
    }

    @Override
    public void UpdateOperate() {
        handler.sendEmptyMessage(Main_StaticValue.SUCCESS_GET_DATA);
    }

    @Override
    public void errorToast() {
        handler.sendEmptyMessage(Main_StaticValue.WRONG_GET_DATA);
    }
}
