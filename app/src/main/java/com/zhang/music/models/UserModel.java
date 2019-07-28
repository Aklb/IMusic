package com.zhang.music.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Author by Zhang on 2019/7/24 22:20
 */
public class UserModel extends RealmObject {

    @PrimaryKey//主键
    private String phone;
    @Required//不可或缺的值
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
