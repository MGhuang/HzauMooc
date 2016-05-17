package com.feidian.george.hzaumooc.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
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
import com.feidian.george.hzaumooc.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
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
public class EvaluteActivity extends Activity implements View.OnClickListener{
    private RelativeLayout relativeLayout = null;
    private FrameLayout frameLayout;
    private ImageView button = null;
    private VideoView videoView;
    private Button button1 = null;
    private EditText edit = null;
    private List<Evalute> data = new ArrayList<>();
    private EvaluteAdapter adapter;
    private ListView listView = null;
    private LinearLayout linearLayout = null;
    private ImageView back = null;
    private TextView videoName = null;
    private static long position = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalute);
        //跳转时获取各种网址及课程名，实现视频的播放与评论
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String courseName = intent.getStringExtra("courseName");
        Bmob.initialize(this, "bdc6b1e1572437dda9938ac54b702a5b");
        BmobQuery<Evalute> query = new BmobQuery<>();
        query.addWhereEqualTo("courseName",courseName);
        query.setLimit(50);
        query.findObjects(this, new FindListener<Evalute>() {
            @Override
            public void onSuccess(List<Evalute> list) {
                for (Evalute e : list) {
                    data.add(e);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
        relativeLayout = (RelativeLayout)super.findViewById(R.id.top);
        button = (ImageView)super.findViewById(R.id.button);
        button1 = (Button)super.findViewById(R.id.button1);
        edit = (EditText)super.findViewById(R.id.edit);
        listView = (ListView)super.findViewById(R.id.evalute);
        linearLayout = (LinearLayout)super.findViewById(R.id.under);
        frameLayout = (FrameLayout)super.findViewById(R.id.frame);
        back = (ImageView)super.findViewById(R.id.back);
        videoName = (TextView)super.findViewById(R.id.videoName);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        back.setOnClickListener(this);
        adapter = new EvaluteAdapter(data,this,R.layout.a_evalute_l_item);
        listView.setAdapter(adapter);
        if(Vitamio.initialize(this)){
            videoView = (VideoView)super.findViewById(R.id.vedio);
            videoView.setVideoURI(Uri.parse(url));
            MediaController mediaController = new MediaController(this,true,frameLayout);
            videoView.setMediaController(mediaController);
            //videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ORIGIN,0);
            videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                }
            });
            videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
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
                videoView.start();
                videoView.seekTo(position);
            }
        }
  //按钮的处理
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //将屏幕设置为横屏
            case R.id.button:
                if(this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
                    position = videoView.getCurrentPosition();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    linearLayout.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.GONE);
                    videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM, 0);
                    videoView.seekTo(position);
                }else{
                    long x = videoView.getCurrentPosition();
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    linearLayout.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    videoView.seekTo(x);
                }
                break;
            //设置评论的监听，并上传数据库
            case R.id.button1:
                String words = edit.getText().toString();
                if(words.equals("")){
                    Toast.makeText(this,"评论不能为空！",Toast.LENGTH_SHORT).show();
                }else{
                    Evalute evalute = new Evalute(words,"魅力汉语");
                    evalute.save(this, new SaveListener() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(EvaluteActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int i, String s) {

                        }
                    });
                    data.add(evalute);
                    adapter.notifyDataSetChanged();
                    edit.setText("");
                }
                break;
            //
            case R.id.back:
                finish();
            default:break;
        }
    }
    //手机返回按钮处理事件
    @Override
    public void onBackPressed(){
        if(this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
            position = videoView.getCurrentPosition();
            Toast.makeText(this,String.valueOf(position),Toast.LENGTH_SHORT).show();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            linearLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.VISIBLE);
            videoView.seekTo(position);
        }else{
            finish();
        }
    }
}
