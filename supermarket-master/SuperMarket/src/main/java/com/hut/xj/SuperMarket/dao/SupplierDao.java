package com.hut.xj.SuperMarket.dao;

import com.hut.xj.SuperMarket.domain.Supplier;
import com.hut.xj.SuperMarket.domain.User;
import com.hut.xj.SuperMarket.mapper.SupplierMapper;

import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupplierDao extends BaseDao {
    public List<Supplier> selectAll(){
        SqlSession sqlSession = BaseDao.open();
        SupplierMapper supplierMapper= sqlSession.getMapper(SupplierMapper.class);
        List<Supplier> lists = supplierMapper.selectAll();
        BaseDao.close();
        return lists;
    }
    public void addSupplier(Supplier supplier){
        SqlSession sqlSession = BaseDao.open();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        supplierMapper.supplierlAdd(supplier.getSupplier_num(),supplier.getSupplier_name(),supplier.getSupplier_contacts(),supplier.getSupplier_c_phone(),supplier.getSupplier_address(),supplier.getSupplier_fax(),supplier.getSupplier_des(),supplier.getSupplier_time());
        sqlSession.commit();
        BaseDao.close();
    }
    public Supplier findById(String supplier_num){
        SqlSession sqlSession = BaseDao.open();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        Supplier supplier = supplierMapper.findById(supplier_num);
        BaseDao.close();
        return supplier;
    }
    public void deleteById(String supplier_num){
        SqlSession sqlSession = BaseDao.open();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        supplierMapper.deleteById(supplier_num);
        sqlSession.commit();
        BaseDao.close();
    }
    public void updateById(Supplier supplier){
        SqlSession sqlSession = BaseDao.open();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        supplierMapper.update(supplier.getSupplier_name(),supplier.getSupplier_contacts(),supplier.getSupplier_c_phone(),supplier.getSupplier_address(),supplier.getSupplier_fax(),supplier.getSupplier_des(),supplier.getSupplier_time(),supplier.getSupplier_num());
        sqlSession.commit();
        BaseDao.close();
    }
    public List<Supplier> searchByName(String supplier_name){
        SqlSession sqlSession = BaseDao.open();
        SupplierMapper supplierMapper = sqlSession.getMapper(SupplierMapper.class);
        List<Supplier> suppliers = supplierMapper.searchByName(supplier_name);
        BaseDao.close();
        return suppliers;
    }
}
