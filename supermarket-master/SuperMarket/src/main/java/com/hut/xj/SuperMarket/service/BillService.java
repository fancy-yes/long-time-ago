package com.hut.xj.SuperMarket.service;


import com.hut.xj.SuperMarket.dao.BillDao;
import com.hut.xj.SuperMarket.domain.Bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BillService {
    BillDao billDao = new BillDao();
    public List<HashMap<String,String>> findAll(){
        List<Bill> lists = billDao.selectAll();
        List<HashMap<String,String>> maps = new ArrayList<HashMap<String, String>>();
        for(Bill bill : lists){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("bill_id",bill.getBill_id());
            map.put("bill_name",bill.getBill_name());
            map.put("bill_supplier",bill.getBill_supplier());
            map.put("bill_money",bill.getBill_money());
            map.put("bill_pay",bill.getBill_pay());
            map.put("bill_time",bill.getBill_time());
            maps.add(map);
        }
        return maps;
    }

    public void addBill(Bill bill){
        billDao.addBilll(bill);
    }

    public Bill findByid(String bill_id){
        Bill bill = billDao.findByid(bill_id);
        return bill;
    }
    public void deleteById(String bill_id){
        billDao.deleteById(bill_id);
    }

    public void updateById(Bill bill){
        billDao.updateById(bill);
    }

    public List<Bill> search(String bill_name,String bill_supplier,String bill_pay){
        List<Bill> bills = billDao.search(bill_name,bill_supplier,bill_pay);
        return bills;
    }
}
