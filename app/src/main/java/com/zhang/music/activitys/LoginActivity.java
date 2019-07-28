package com.zhang.music.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.zhang.music.R;
import com.zhang.music.utils.UserUtils;
import com.zhang.music.views.InputView;

public class LoginActivity extends BaseActivity {
    private InputView mInputPhone,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

//    初始化View
    private void initView(){
        initNavBar(false,"登陆",false);

        mInputPhone=findViewById(R.id.input_phone);
        mInputPassword=findViewById(R.id.input_password);
    }

//    跳转注册点击事件
    public void onRegisterClick(View view){
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

//    登陆
    public void onCommitClick(View view){

        String phone=mInputPhone.getInputStr();
        String password=mInputPassword.getInputStr();

//        验证用户输入是否合法
        if (!UserUtils.validateLogin(this,phone,password)){
            return;
        }

//        跳转到应用主页
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
