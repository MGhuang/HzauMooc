package com.feidian.george.hzaumooc.Tool;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by lenovo on 2016/4/10.
 */
public class Download {
    private static final File sdcard = Environment.getExternalStorageDirectory();
    private static final String path = sdcard.getPath() + "/课程中心";
    private static long downloadId;
    public static void startDownload(Context activity,String url,String name) {
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String serviceString = Context.DOWNLOAD_SERVICE;
        if(DownloadUtils.downloadManager==null) {
            DownloadUtils.downloadManager = (DownloadManager) activity.getSystemService(serviceString);
        }
        Uri uri=Uri.parse(url);
        //创建下载文件
        File fileName = new File(path, name);
        //建立下载请求
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //设置下载位置
        request.setDestinationUri(Uri.fromFile(fileName));
        //设置下载是的标题栏
        request.setTitle(name);
        //下载提示信息
        request.setDescription("现在我的文件正在下载.....");
        //下载完成后给予提示
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //加入下载队列
        downloadId = DownloadUtils.downloadManager.enqueue(request);
        //对下载文件的ID与文件名予以存储
        DownloadUtils.downloadId.add(downloadId);
        DownloadUtils.downloadName.add(name);
    }
}
