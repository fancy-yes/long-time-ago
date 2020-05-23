package com.hut.xj.SuperMarket.service;

import com.hut.xj.SuperMarket.dao.AdminDao;
import com.hut.xj.SuperMarket.domain.Admin;

public class AdminService {
    AdminDao adminDao = new AdminDao();
    public Admin check(String username,String passwd){
        Admin admin = adminDao.checkAdmin(username,passwd);
        return admin;
    }
    public void updatePasswd(String passwd){
        adminDao.updatePasswd(passwd);
    }
}
