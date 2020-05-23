package com.hut.xj.SuperMarket.dao;

import com.hut.xj.SuperMarket.domain.Admin;
import com.hut.xj.SuperMarket.mapper.AdminMapper;

import org.apache.ibatis.session.SqlSession;

public class AdminDao extends BaseDao {
    public Admin checkAdmin(String username,String passwd){
        SqlSession sqlSession = BaseDao.open();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        Admin admin = adminMapper.check(username,passwd);
        BaseDao.close();
        return admin;
    }
    public void updatePasswd(String passwd){
        SqlSession sqlSession = BaseDao.open();
        AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
        adminMapper.updatePasswd(passwd);
        sqlSession.commit();
        BaseDao.close();
    }
}
