package com.hut.xj.SuperMarket.service;

import com.hut.xj.SuperMarket.dao.SupplierDao;
import com.hut.xj.SuperMarket.domain.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupplierService {
    SupplierDao supplierDao = new SupplierDao();
    public List<HashMap<String,String>> fingAll(){
        List<Supplier> lists = supplierDao.selectAll();
        List<HashMap<String,String>> maps = new ArrayList<HashMap<String, String>>();
        for(Supplier supplier: lists){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("supplier_num",supplier.getSupplier_num());
            map.put("supplier_name",supplier.getSupplier_name());
            map.put("supplier_contacts",supplier.getSupplier_contacts());
            map.put("supplier_c_phone",supplier.getSupplier_c_phone());
            map.put("supplier_fax",supplier.getSupplier_fax());
            map.put("supplier_time",supplier.getSupplier_time());
            maps.add(map);
        }
        return maps;
    }
    public void addSupplier(Supplier supplier){
        supplierDao.addSupplier(supplier);
    }
    public Supplier findById(String supplier_num){
        Supplier supplier = supplierDao.findById(supplier_num);
        return supplier;
    }
    public void deleteById(String supplier_num){
        supplierDao.deleteById(supplier_num);
    }

    public void updateById(Supplier supplier){
        supplierDao.updateById(supplier);
    }

    public List<Supplier> searchByName(String supplier_name){
        List<Supplier> suppliers = supplierDao.searchByName(supplier_name);
        return suppliers;
    }
}
