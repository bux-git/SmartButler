package com.dqr.www.smartbutler.entity;

import cn.bmob.v3.BmobUser;

/**
 * Description：
 * Author：LiuYM
 * Date： 2017-04-11 16:08
 */

public class User extends BmobUser {
    private int age;
    private boolean gender;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
