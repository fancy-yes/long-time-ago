package com.hut.xj.SuperMarket.dao;

import org.apache.ibatis.session.SqlSession;

import com.hut.xj.SuperMarket.util.MybatisUtils;

public class BaseDao {
     public static SqlSession open(){
          SqlSession sqlSession=MybatisUtils.getSqlSession();
          return sqlSession;
     }
     public static void close(){
          MybatisUtils.closeSqlSesssion();
     }
}
