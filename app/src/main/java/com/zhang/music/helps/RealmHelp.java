package com.zhang.music.helps;

import com.zhang.music.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Author by Zhang on 2019/7/24 22:17
 */
public class RealmHelp {

    private Realm mRealm;

    public RealmHelp(){
        mRealm=Realm.getDefaultInstance();
    }

//    关闭数据库
    public void close(){
        if (mRealm != null && !mRealm.isClosed()){
            mRealm.close();
        }
    }

//    保存用户信息
    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.insertOrUpdate(userModel);
        mRealm.commitTransaction();
    }

//    返回所有用户
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query=mRealm.where(UserModel.class);
        RealmResults<UserModel> results=query.findAll();
        return results;
    }

//    验证用户信息
    public boolean validateUser(String phone,String password){
        boolean result=false;
        RealmQuery<UserModel> query=mRealm.where( UserModel.class );
        query=query.equalTo("phone",phone).equalTo("password",password);
        UserModel userModel=query.findFirst();
        if (userModel!=null){
            result=true;
        }

        return result;
    }
}
