package com.hut.xj.SuperMarket.service;

import com.hut.xj.SuperMarket.dao.UserDao;
import com.hut.xj.SuperMarket.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();

    public List<HashMap<String,String >> findAll(){
        List<User> lists = userDao.findAll();
        List<HashMap<String,String>> maps = new ArrayList<HashMap<String, String>>();
        for (User list : lists){
            HashMap<String,String> map=new HashMap<String,String>();
            map.put("user_number",list.getUser_number());
            map.put("user_name",list.getUser_name());
            map.put("user_passwd",list.getUser_passwd());
            map.put("user_sex",list.getUser_sex());
            map.put("user_age",String.valueOf(list.getUser_age()));
            map.put("user_birth",list.getUser_birth());
            map.put("user_phone",list.getUser_phone());
            map.put("user_address",list.getUser_address());
            map.put("user_type",list.getUser_type());
            maps.add(map);
        }
        return maps;
    }

    public void addUser(User user){
        userDao.add(user);
    }

    public User findById(String user_number){
        User user = userDao.findById(user_number);
        return user;
    }

    public void deleteById(String user_number){
        userDao.deleteById(user_number);
    }

    public void updateById(User user){
        userDao.updateById(user);
    }

    public User selectByName(String username,String passwd){
        User user = userDao.selectByName(username,passwd);
        return user;
    }

    public  List<User> searchByName(String user_name){
        List<User> users = userDao.searchByName(user_name);
        return users;

    }
}
