package com.hut.xj.SuperMarket.dao;

import com.hut.xj.SuperMarket.domain.Bill;
import com.hut.xj.SuperMarket.mapper.BillMapper;
import com.hut.xj.SuperMarket.mapper.UserMapper;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillDao extends BaseDao {
    public List<Bill> selectAll(){
        SqlSession sqlSession = BaseDao.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        List<Bill> lists = billMapper.selectAll();
        BaseDao.close();
        return lists;
    }
    public void addBilll(Bill bill){
        SqlSession sqlSession = BaseDao.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        billMapper.billAdd(bill.getBill_id(),bill.getBill_name(),bill.getBill_com(),bill.getBill_supplier(),bill.getBill_num(),bill.getBill_money(),bill.getBill_pay(),bill.getBill_time());
        sqlSession.commit();
        BaseDao.close();
    }
    public Bill findByid(String bill_id){
        SqlSession sqlSession = BaseDao.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        Bill bill = billMapper.findById(bill_id);
        BaseDao.close();
        return bill;
    }
    public void deleteById(String bill_id){
        SqlSession sqlSession = BaseDao.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        billMapper.deleteById(bill_id);
        sqlSession.commit();
        BaseDao.close();
    }
    public void updateById(Bill bill){
        SqlSession sqlSession = BaseDao.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        billMapper.update(bill.getBill_name(),bill.getBill_com(),bill.getBill_supplier(),bill.getBill_num(),bill.getBill_money(),bill.getBill_pay(),bill.getBill_time(),bill.getBill_id());
        BaseDao.close();
    }
    public List<Bill> search(String bill_name,String bill_supplier,String bill_pay){
        SqlSession sqlSession = BaseDao.open();
        BillMapper billMapper = sqlSession.getMapper(BillMapper.class);
        List<Bill> bills = billMapper.search(bill_name,bill_supplier,bill_pay);
        BaseDao.close();
        return bills;
    }
}
