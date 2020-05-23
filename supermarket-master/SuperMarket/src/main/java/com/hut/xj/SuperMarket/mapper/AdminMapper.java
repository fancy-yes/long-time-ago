package com.hut.xj.SuperMarket.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hut.xj.SuperMarket.domain.Admin;

public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE username=#{username} AND passwd=#{passwd}")
    public Admin check(@Param("username") String username,@Param("passwd") String passwd);

    @Update("UPDATE admin SET passwd=#{passwd}")
    public void updatePasswd(@Param("passwd") String passwd);
}
