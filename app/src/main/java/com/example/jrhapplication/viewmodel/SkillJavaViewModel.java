package com.example.jrhapplication.viewmodel;

import com.example.jrhapplication.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * **************************************
 * 项目名称:jrhApplication
 *
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/24     10:25 AM
 * 用途:
 * **************************************
 */
class SkillJavaViewModel {

    private List<User> userList = new ArrayList<>(1000);

    private List<User> createUserList(){
        for (int i = 0; i < userList.size(); i++) {
            userList.add(new User(i,"name"+i,i,"address"+i));
        }
        return userList;
    }
}
