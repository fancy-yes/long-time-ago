package com.hut.xj.SuperMarket.mapper;

import org.apache.ibatis.annotations.*;

import com.hut.xj.SuperMarket.domain.Supplier;

import java.util.List;

public interface SupplierMapper {
    @Insert
            ("INSERT INTO supplier VALUES " +
                    "(#{supplier_num},#{supplier_name},#{supplier_contacts},#{supplier_c_phone}," +
                    "#{supplier_address},#{supplier_fax},#{supplier_des},#{supplier_time})"
            )
    public void supplierlAdd(@Param("supplier_num") String supplier_num, @Param("supplier_name") String supplier_name,
                             @Param("supplier_contacts") String supplier_contacts,
                             @Param("supplier_c_phone") String supplier_c_phone, @Param("supplier_address") String supplier_address,
                             @Param("supplier_fax") String supplier_fax,
                             @Param("supplier_des") String supplier_des, @Param("supplier_time") String supplier_time);

    @Select("SELECT * FROM supplier")
    public List<Supplier> selectAll();

    @Select("SELECT * FROM supplier WHERE supplier_num=#{supplier_num}")
    public Supplier findById(@Param("supplier_num") String supplier_num);

    @Delete("DELETE FROM supplier WHERE supplier_num=#{supplier_num}")
    public void deleteById(@Param("supplier_num") String supplier_num);

    @Update("UPDATE supplier SET supplier_name=#{supplier_name},supplier_contacts=#{supplier_contacts}," +
            "supplier_c_phone=#{supplier_c_phone},supplier_address=#{supplier_address},supplier_fax=#{supplier_fax}" +
            ",supplier_des=#{supplier_des},supplier_time=#{supplier_time} WHERE supplier_num=#{supplier_num}")
    public void update(@Param("supplier_name") String supplier_name,@Param("supplier_contacts") String supplier_contacts,
                       @Param("supplier_c_phone") String supplier_c_phone, @Param("supplier_address") String supplier_address,
                       @Param("supplier_fax") String supplier_fax, @Param("supplier_des") String supplier_des,
                       @Param("supplier_time") String supplier_time,@Param("supplier_num") String supplier_num);
    @Select("SELECT * FROM supplier WHERE supplier_name=#{supplier_name}")
    public  List<Supplier> searchByName(@Param("supplier_name") String supplier_name);
}
