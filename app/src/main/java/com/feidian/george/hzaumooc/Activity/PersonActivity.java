package com.feidian.george.hzaumooc.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.feidian.george.hzaumooc.Adapter.Person.Admin;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.LoginUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class PersonActivity extends Activity implements View.OnClickListener {
    private static final int CHOOSE_PHOTO = 0;
    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;
    private static final int LOGIN = 3;
    private ImageView photo = null;
    private TextView login = null;
    private TextView admin = null;
    private LinearLayout linear_history = null;
    private LinearLayout linear_suggest = null;
    private LinearLayout linear_out = null;
    //设置照片存储的路径
    private File photoFile = new File(Environment.getExternalStorageDirectory(), "课程中心");
    //设置图片文件
    private File file = new File(photoFile, "headImage.jpg");
    private Uri imageUri = null;
    //设置剪裁图片的大小
    private static final int output_X = 480;
    private static final int output_Y = 480;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        photo = (ImageView) super.findViewById(R.id.head);
        login = (TextView) super.findViewById(R.id.name);
        admin = (TextView)super.findViewById(R.id.admin_text);
        linear_history = (LinearLayout)super.findViewById(R.id.history);
        linear_suggest = (LinearLayout)super.findViewById(R.id.suggest);
        linear_out = (LinearLayout)super.findViewById(R.id.out);
        linear_out.setOnClickListener(this);
        linear_history.setOnClickListener(this);
        linear_suggest.setOnClickListener(this);
        login.setOnClickListener(this);
        Bmob.initialize(this, "bdc6b1e1572437dda9938ac54b702a5b");
        this.registerForContextMenu(photo);
    }

    //从手机相册中选择照片
    public void choosePhotoFromGallery() {
        Intent intentFromGallery = new Intent();
        intentFromGallery.setType("image/*");
        intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentFromGallery, CHOOSE_PHOTO);
    }

    //从相机拍摄中选择照片
    public void choosePhotoFromCamera() {
        Intent intentFromCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        intentFromCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intentFromCamera, TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "取消操作", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (requestCode) {
            case TAKE_PHOTO:
                if (hasSdcard()) {
                    cropPhoto(Uri.fromFile(file));
                }
                break;
            case CHOOSE_PHOTO:
                cropPhoto(data.getData());
                break;
            case CROP_PHOTO:
                if (data != null) {
                    setPhoto(data);
                }
                break;
            case LOGIN:
                LoginUtil.IS_LOGIN = true;
                login.setText(data.getStringExtra("admin"));
                admin.setText(LoginUtil.admin);
                BmobQuery<Admin> query = new BmobQuery<>();
                query.addWhereEqualTo("admin", LoginUtil.admin);
                query.findObjects(this, new FindListener<Admin>() {
                    @Override
                    public void onSuccess(List<Admin> list) {
                        for (Admin l : list) {
                            if (l.getIcon() != null) {
                                BmobFile bmobFile = l.getIcon();
                                if (file.exists()) {
                                    file.delete();
                                }
                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                bmobFile.download(PersonActivity.this, file, new DownloadFileListener() {
                                    @Override
                                    public void onSuccess(String s) {
                                        //设置头像
                                        setHead();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Toast.makeText(PersonActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onError(int i, String s) {

                    }
                });
                break;
            default:
                break;
        }
    }

    //对原始的图片进行裁剪
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", true);
        intent.putExtra("scale", true);
        //设置宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置裁剪图片的宽高
        intent.putExtra("outputX", output_X);
        intent.putExtra("outputY", output_Y);
        //设置返回数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_PHOTO);
    }

    //对图片进行保存
    public void setPhoto(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Bitmap image = bundle.getParcelable("data");
            file = new File(photoFile, "head.jpg");
            if (file.exists()) {
                file.delete();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedOutputStream os = null;
            try {
                os = new BufferedOutputStream(new FileOutputStream(file));
                image.compress(Bitmap.CompressFormat.JPEG, 80, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            photo.setImageBitmap(image);
            //发送数据
            send();
        }
    }

    public void send() {
        final BmobFile xfile = new BmobFile(file);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在修改...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        xfile.uploadblock(this, new UploadFileListener() {

            @Override
            public void onSuccess() {
                //bmobFile.getFileUrl(context)--返回的上传文件的完整地址
                //toast("上传文件成功:" + bmobFile.getFileUrl(context));
                //插入数据库
                Toast.makeText(PersonActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                insertObject(new Admin(xfile, LoginUtil.admin, LoginUtil.password));
                progressDialog.dismiss();
            }

            @Override
            public void onProgress(Integer value) {
            }

            @Override
            public void onFailure(int code, String msg) {
                // toast("上传文件失败：" + msg);
                Toast.makeText(PersonActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //检测是否存在sd卡
    public boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(LoginUtil.IS_LOGIN==false){
            Toast.makeText(PersonActivity.this,"您还没有登录！",Toast.LENGTH_SHORT).show();
        }else {
            super.onCreateContextMenu(menu, v, menuInfo);
            menu.setHeaderTitle("修改头像");
            menu.add(Menu.NONE, Menu.FIRST + 1, 1, "拍照");
            menu.add(Menu.NONE, Menu.FIRST + 2, 2, "相册");
            menu.add(Menu.NONE, Menu.FIRST + 3, 3, "取消");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                choosePhotoFromCamera();
                break;
            case Menu.FIRST + 2:
                choosePhotoFromGallery();
                break;
            case Menu.FIRST + 3:
                break;
            default:
                break;
        }
        return true;
    }

    public void insertObject(final Admin a) {
        BmobQuery<Admin> query = new BmobQuery<>();
        query.addWhereEqualTo("admin",LoginUtil.admin);
        query.findObjects(PersonActivity.this, new FindListener<Admin>() {
            @Override
            public void onSuccess(List<Admin> list) {
                a.update(PersonActivity.this, list.get(0).getObjectId(), new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(PersonActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(PersonActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name:
                if (LoginUtil.IS_LOGIN == true) {
                    Toast.makeText(PersonActivity.this, "已经登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(PersonActivity.this, LRActivity.class);
                    startActivityForResult(intent, LOGIN);
                }
                break;
            case R.id.history:
                break;
            case R.id.suggest:
                break;
            case R.id.out:
                LoginUtil.IS_LOGIN = false;
                LoginUtil.admin = null;
                LoginUtil.password = null;
                photo.setImageResource(R.mipmap.head);
                login.setText("登录");
                admin.setText("");
                Toast.makeText(PersonActivity.this,"您已经退出登录！",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //设置头像
    public void setHead() {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        }catch (Exception e){
            e.printStackTrace();
        }
        photo.setImageBitmap(bitmap);
    }
}
