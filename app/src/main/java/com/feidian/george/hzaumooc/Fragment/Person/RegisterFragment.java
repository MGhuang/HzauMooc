package com.feidian.george.hzaumooc.Fragment.Person;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.feidian.george.hzaumooc.Adapter.Person.Admin;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.LoginUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by lenovo on 2016/5/20.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {
    private Button regist = null;
    private EditText admin = null;
    private EditText password = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_person_regist, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        regist = (Button) getView().findViewById(R.id.register);
        admin = (EditText) getView().findViewById(R.id.admin);
        password = (EditText) getView().findViewById(R.id.password);
        regist.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                final String xadmin = admin.getText().toString();
                final String xpassword = password.getText().toString();
                if (xadmin.equals("") || xpassword.equals("")) {
                    Toast.makeText(getActivity(), "账号密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    BmobQuery<Admin> query = new BmobQuery<>();
                    query.setLimit(100);
                    query.findObjects(getActivity(), new FindListener<Admin>() {
                        @Override
                        public void onSuccess(List<Admin> list) {
                            boolean isFind = false;
                            for (Admin l : list) {
                                if (xadmin.equals(l.getAdmin())) {
                                    Toast.makeText(getActivity(), "该账号已被注册!", Toast.LENGTH_SHORT).show();
                                    isFind = true;
                                    break;
                                }
                            }
                            if (isFind == false) {
                                final Admin xxadmin = new Admin(null, xadmin, xpassword);
                                xxadmin.save(getActivity(), new SaveListener() {
                                    @Override
                                    public void onSuccess() {
                                        Toast.makeText(getActivity(), "注册成功！", Toast.LENGTH_SHORT).show();
                                        Message message = new Message();
                                        admin.setText("");
                                        password.setText("");
                                        message.what = 0;
                                        LoginUtil.handler.sendMessage(message);
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        Toast.makeText(getActivity(), "注册失败！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onError(int i, String s) {
                            Toast.makeText(getActivity(), "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
