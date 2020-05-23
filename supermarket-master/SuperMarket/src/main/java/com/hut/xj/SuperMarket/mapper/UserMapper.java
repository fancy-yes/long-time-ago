package com.hut.xj.SuperMarket.mapper;

import org.apache.ibatis.annotations.*;

import com.hut.xj.SuperMarket.domain.Bill;
import com.hut.xj.SuperMarket.domain.User;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    @Insert
            ("INSERT INTO user VALUES " +
            "(#{user_number},#{user_name},#{user_passwd}," +
                    "#{user_sex},#{user_age},#{user_birth},#{user_phone},#{user_address},#{user_type})"
    )
    public void userAdd(@Param("user_number") String user_number,@Param("user_name") String user_name,
                        @Param("user_passwd") String user_passwd,@Param("user_sex") String user_sex,
                        @Param("user_age") int user_age,@Param("user_birth") String user_birth,
                        @Param("user_phone") String user_phone,@Param("user_address") String user_address,
                        @Param("user_type") String user_type);


    @Select("SELECT * FROM user")
    public List<User> selectAllUser();

    @Select("SELECT * FROM user WHERE user_number=#{user_number}")
    public User findById(@Param("user_number") String user_number);

    @Delete("DELETE FROM user WHERE user_number=#{user_number}")
    public void deleteById(@Param("user_number") String user_number);

    @Update("UPDATE user SET user_name=#{user_name},user_sex=#{user_sex}," +
            "user_birth=#{user_birth},user_age=#{user_age},user_phone=#{user_phone}" +
            ",user_address=#{user_address},user_type=#{user_type} WHERE user_number=#{user_number}")
    public void update(@Param("user_name") String user_name,@Param("user_sex") String user_sex,
                       @Param("user_birth") String user_birth,@Param("user_age") int user_age,
                       @Param("user_phone") String user_phone,@Param("user_address") String  user_address,
                       @Param("user_type") String user_type,@Param("user_number") String user_number);
    @Select("SELECT * FROM user WHERE user_name=#{user_name} AND user_passwd=#{user_passwd}")
    public User selectByName(@Param("user_name") String user_name, @Param("user_passwd") String user_passwd);

    @Select("SELECT * FROM user WHERE user_name=#{user_name }")
    public List<User> searchBydName(@Param("user_name") String user_name);
}
