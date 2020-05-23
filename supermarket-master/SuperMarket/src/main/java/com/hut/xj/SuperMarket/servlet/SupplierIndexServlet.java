package com.hut.xj.SuperMarket.servlet;

import com.hut.xj.SuperMarket.dao.SupplierDao;
import com.hut.xj.SuperMarket.domain.Supplier;
import com.hut.xj.SuperMarket.service.SupplierService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "supplierIndex")
public class SupplierIndexServlet extends HttpServlet {
    SupplierService supplierService = new SupplierService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("user") == null){
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //解决缓存
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("pragma","no-cache");
        resp.setHeader("expires","-1");

        String str = req.getParameter("action");
        if("list".equalsIgnoreCase(str)){
            list(req,resp);
        }else if("add".equalsIgnoreCase(str)){
            supplierAdd(req,resp);
        }else if("view".equalsIgnoreCase(str)){
            supplieView(req,resp);
        }else if("delete".equalsIgnoreCase(str)){
            supplierDelete(req,resp);
        }else if("update".equalsIgnoreCase(str)){
            supplierUpdate(req,resp);
        }else if("search".equalsIgnoreCase(str)){
            supplierSearch(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    public void list(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        List<HashMap<String,String>> suppliers  = supplierService.fingAll();
        req.setAttribute("suppliers",suppliers);
        req.getRequestDispatcher("/providerList.jsp").forward(req,resp);
    }
    public void supplierAdd (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if("get".equalsIgnoreCase(req.getMethod())){
            req.getRequestDispatcher("/providerAdd.jsp").forward(req,resp);
        }else{
            String supplier_num = req.getParameter("providerId");
            if(supplierService.findById(supplier_num) != null){
                req.setAttribute("error","编号已存在");
                req.getRequestDispatcher("/providerAdd.jsp").forward(req,resp);
                return;
            }else {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(new Date());
                Supplier supplier=new Supplier();
                supplier.setSupplier_num(supplier_num);
                supplier.setSupplier_name(req.getParameter("providerName"));
                supplier.setSupplier_contacts(req.getParameter("people"));
                supplier.setSupplier_c_phone(req.getParameter("phone"));
                supplier.setSupplier_address(req.getParameter("address"));
                supplier.setSupplier_fax(req.getParameter("fax"));
                supplier.setSupplier_des(req.getParameter("describe"));
                supplier.setSupplier_time(date);
                supplierService.addSupplier(supplier);
                resp.sendRedirect("/supplierIndex?action=list");
            }

        }

    }
    public void supplieView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String str = req.getParameter("supplier_num");
        Supplier supplier = supplierService.findById(str);
        req.setAttribute("supplier",supplier);
        req.getRequestDispatcher("/providerView.jsp").forward(req,resp);
    }
    public void supplierDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String supplier_num = req.getParameter("supplier_num");
        supplierService.deleteById(supplier_num);
        resp.sendRedirect("/supplierIndex?action=list");
    }
    public void supplierUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if("get".equalsIgnoreCase(req.getMethod())){
            String str = req.getParameter("supplier_num");
            Supplier supplier = supplierService.findById(str);
            req.setAttribute("supplier",supplier);
            req.getRequestDispatcher("/providerUpdate.jsp").forward(req,resp);
        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(new Date());
            Supplier supplier=new Supplier();
            supplier.setSupplier_num(req.getParameter("providerId"));
            supplier.setSupplier_name(req.getParameter("providerName"));
            supplier.setSupplier_contacts(req.getParameter("people"));
            supplier.setSupplier_c_phone(req.getParameter("phone"));
            supplier.setSupplier_address(req.getParameter("address"));
            supplier.setSupplier_fax(req.getParameter("fax"));
            supplier.setSupplier_des(req.getParameter("describe"));
            supplier.setSupplier_time(date);
            supplierService.updateById(supplier);
            resp.sendRedirect("/supplierIndex?action=list");
        }
    }
    public void supplierSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
           String supplier_name = req.getParameter("supplier_name");
           List<Supplier> suppliers = supplierService.searchByName(supplier_name);
           req.setAttribute("suppliers",suppliers);
           req.getRequestDispatcher("/providerList.jsp").forward(req,resp);
            return;
    }
}
