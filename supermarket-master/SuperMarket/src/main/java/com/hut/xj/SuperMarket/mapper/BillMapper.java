package com.hut.xj.SuperMarket.mapper;

import org.apache.ibatis.annotations.*;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import com.hut.xj.SuperMarket.domain.Bill;

import java.util.List;

public interface BillMapper {
    @Select("SELECT * FROM bill")
    public List<Bill> selectAll();
    @Insert
            ("INSERT INTO bill VALUES " +
                    "(#{bill_id},#{bill_name},#{bill_com},#{bill_supplier}," +
                    "#{bill_num},#{bill_money},#{bill_pay},#{bill_time})"
            )
    public void billAdd(@Param("bill_id") String bill_id,@Param("bill_name") String bill_name,@Param("bill_com") String bill_com,
                        @Param("bill_supplier") String bill_supplier, @Param("bill_num") String bill_num, @Param("bill_money") String bill_money,
                        @Param("bill_pay") String bill_pay, @Param("bill_time") String bill_time);
    @Select("SELECT * FROM bill WHERE bill_id=#{bill_id}")
    public Bill findById(@Param("bill_id") String bill_id);

    @Delete("DELETE FROM bill WHERE bill_id=#{bill_id}")
    public void deleteById(@Param("bill_id") String bill_id);

    @Update("UPDATE bill SET bill_name=#{bill_name},bill_com=#{bill_com}," +
            "bill_supplier=#{bill_supplier},bill_num=#{bill_num},bill_money=#{bill_money}" +
            ",bill_pay=#{bill_pay},bill_time=#{bill_time} WHERE bill_id=#{bill_id}")
    public void update(@Param("bill_name") String bill_name,@Param("bill_com") String bill_com,
                       @Param("bill_supplier") String bill_supplier, @Param("bill_num") String bill_num,
                       @Param("bill_money") String bill_money, @Param("bill_pay") String bill_pay,
                       @Param("bill_time") String bill_time,@Param("bill_id") String bill_id);
    @Select("SELECT * FROM bill WHERE bill_name=#{bill_name} OR bill_supplier=#{bill_supplier } OR bill_pay=#{bill_pay }")
    public List<Bill> search(@Param("bill_name") String bill_name,@Param("bill_supplier") String bill_supplier,@Param("bill_pay") String bill_pay );
}
