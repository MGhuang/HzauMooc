package com.feidian.george.hzaumooc.Fragment.Person;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.feidian.george.hzaumooc.Adapter.Person.Admin;
import com.feidian.george.hzaumooc.R;
import com.feidian.george.hzaumooc.Tool.LoginUtil;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lenovo on 2016/5/20.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{
    private Button login = null;
    private EditText admin = null;
    private EditText password = null;
    private TextView regist = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_person_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        login = (Button)getView().findViewById(R.id.f_login_xlogin);
        admin = (EditText)getView().findViewById(R.id.f_login_admin);
        password = (EditText)getView().findViewById(R.id.f_login_password);
        regist = (TextView)getView().findViewById(R.id.f_login_regist);
        login.setOnClickListener(this);
        regist.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.f_login_xlogin:
                final String xadmin = admin.getText().toString();
                final String xpassword = password.getText().toString();
                if(xadmin.equals("")||xpassword.equals("")){
                    Toast.makeText(getActivity(), "账号密码不能为空!", Toast.LENGTH_SHORT).show();
                }else {
                    BmobQuery<Admin> query = new BmobQuery<>();
                    query.setLimit(100);
                    query.findObjects(getActivity(), new FindListener<Admin>() {
                        @Override
                        public void onSuccess(List<Admin> list) {
                            for (Admin l : list) {
                                if (xadmin.equals(l.getAdmin()) && xpassword.equals(l.getPassword())) {
                                    getActivity().getIntent().putExtra("admin", xadmin);
                                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                                    LoginUtil.admin = xadmin;
                                    LoginUtil.password = xpassword;
                                    getActivity().setResult(getActivity().RESULT_OK, getActivity().getIntent());
                                    getActivity().finish();
                                }
                            }
                        }

                        @Override
                        public void onError(int i, String s) {
                            Toast.makeText(getActivity(), "登陆失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.f_login_regist:
                Message message = new Message();
                message.what = 1;
                LoginUtil.handler.sendMessage(message);
                break;
            default:break;
        }
    }
}
