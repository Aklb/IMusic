package com.zhang.music.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.zhang.music.R;
import com.zhang.music.activitys.LoginActivity;
import com.zhang.music.helps.RealmHelp;
import com.zhang.music.models.UserModel;

import java.util.List;

/**
 * Author by Zhang on 2019/7/16 22:44
 */
public class UserUtils {

//    验证登陆用户输入合法性
    public static boolean validateLogin(Context context,String phone,String password){
//       验证手机号（简单）
//        RegexUtils.isMobileSimple(phone);
//       验证手机号（精确）
//        RegexUtils.isMobileExact(phone);
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_LONG).show();
            return false;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码",Toast.LENGTH_LONG).show();
            return false;
        }

        /*
        * 1.用户当前手机号是否已经被注册
        * 2.用户输入的手机号和密码是否匹配
        * */
        if (UserUtils.userExistFromPhone( phone )){
            Toast.makeText(context,"当前手机号未注册",Toast.LENGTH_LONG).show();
            return false;
        }

        RealmHelp realmHelp=new RealmHelp();
        boolean result=realmHelp.validateUser(phone,EncryptUtils.encryptMD5ToString(password));
//        realmHelp.close();
        if (!result){
            Toast.makeText(context,"手机号或密码不正确",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

//   退出登录
    public static void logout(Context context){
        Intent intent=new Intent(context, LoginActivity.class);
//        添加intent标志符，清理task栈，并生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//        定义Activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    /**
     * @description 注册用户
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */

    public static boolean registerUser(Context context,String phone,String password,String passwordConfirm){
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_LONG).show();
            return false;
        }

        if (StringUtils.isEmpty(password) || !password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_LONG).show();
            return false;
        }

//        用户当前输入的手机号是否已经被注册
        /*
        * 1.通过Realm获取到当前已经注册的所有用户
        * 2.根据用户输入的手机号匹配查询所有用户，如果可以匹配则证明该手机号已经被注册了，否则就表示手机号还未注册
        * */
        if (UserUtils.userExistFromPhone(phone)){
            Toast.makeText(context,"该手机号已存在",Toast.LENGTH_LONG).show();
            return false;
        }

        UserModel userModel=new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);

        return true;
    }

    /**
     * @description 保存用户的数据库
     * @param userModel
     */

    public static void saveUser(UserModel userModel){
        RealmHelp realmHelp=new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }

//    根据手机号判断用户是否存在
    public static boolean userExistFromPhone(String phnoe){
        boolean result=false;

        RealmHelp realmHelp=new RealmHelp();
        List<UserModel> allUser=realmHelp.getAllUser();

        for (UserModel userModel : allUser){
            if (userModel.getPhone().equals(phnoe)){
//                当前用户已经存在数据库中了
                result=true;
                break;
            }
        }

        realmHelp.close();

        return result;
    }
}
