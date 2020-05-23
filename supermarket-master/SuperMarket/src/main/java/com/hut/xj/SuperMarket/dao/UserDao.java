package com.hut.xj.SuperMarket.dao;

import com.hut.xj.SuperMarket.domain.User;
import com.hut.xj.SuperMarket.mapper.UserMapper;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDao extends BaseDao {

    public void add(User user) {
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.userAdd(user.getUser_number(), user.getUser_name(), user.getUser_passwd(), user.getUser_sex(), user.getUser_age(), user.getUser_birth(), user.getUser_phone(), user.getUser_address(), user.getUser_type());
        sqlSession.commit();
        BaseDao.close();
    }
    public  List<User> findAll(){
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> lists = userMapper.selectAllUser();
        BaseDao.close();
    return lists;
    }

    public User findById(String user_number){
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findById(user_number);
        BaseDao.close();
        return user;
    }
    public void deleteById(String user_number){
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteById(user_number);
        sqlSession.commit();
        BaseDao.close();
    }
    public void updateById(User user){
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.update(user.getUser_name(),user.getUser_sex(),user.getUser_birth(),user.getUser_age(),user.getUser_phone(),user.getUser_address(),user.getUser_type(),user.getUser_number());
        sqlSession.commit();
        BaseDao.close();
    }
    public User selectByName(String username,String passwd){
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByName(username,passwd);
        BaseDao.close();
        return user;
    }
    public List<User> searchByName(String user_name){
        SqlSession sqlSession = BaseDao.open();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.searchBydName(user_name);
        BaseDao.close();
        return users;
    }
}
