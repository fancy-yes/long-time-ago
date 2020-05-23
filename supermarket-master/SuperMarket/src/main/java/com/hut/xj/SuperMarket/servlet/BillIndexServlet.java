package com.hut.xj.SuperMarket.servlet;

import com.hut.xj.SuperMarket.dao.BillDao;
import com.hut.xj.SuperMarket.domain.Bill;
import com.hut.xj.SuperMarket.service.BillService;

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

@WebServlet(urlPatterns = "/billIndex")
public class BillIndexServlet extends HttpServlet {
    BillService billService = new BillService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("pragma","no-cache");
        resp.setHeader("expires","-1");

        if(req.getSession().getAttribute("user") == null){
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        String str = req.getParameter("action");
        if("list".equalsIgnoreCase(str)){
            billList(req,resp);
        }else if("add".equalsIgnoreCase(str)){
            billAdd(req,resp);
        }else if("view".equalsIgnoreCase(str)){
            billView(req,resp);
        }else if("delete".equalsIgnoreCase(str)){
            billdelete(req,resp);
        }else if("update".equalsIgnoreCase(str)){
            billUpdate(req,resp);
        }else if("search".equalsIgnoreCase(str)){
            billSearchByName(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
    public void billList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HashMap<String,String>>  bills = billService.findAll();
        req.setAttribute("bills",bills);
        req.getRequestDispatcher("/billList.jsp").forward(req,resp);
    }
    public void billAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        if("get".equalsIgnoreCase(req.getMethod())){
            req.getRequestDispatcher("/billAdd.jsp").forward(req,resp);
        }else {
            String bill_num = req.getParameter("billId");
            if(billService.findByid(bill_num) != null){
                req.setAttribute("error","编号已存在");
                req.getRequestDispatcher("/billAdd.jsp").forward(req,resp);
                return;
            }else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateFormat.format(new Date());
                Bill bill = new Bill();
                bill.setBill_id(bill_num);
                bill.setBill_name(req.getParameter("billName"));
                bill.setBill_com(req.getParameter("billCom"));
                bill.setBill_num(req.getParameter("billNum"));
                bill.setBill_money(req.getParameter("money"));
                bill.setBill_supplier(req.getParameter("supplier"));
                bill.setBill_pay(req.getParameter("zhifu"));
                bill.setBill_time(date);

                billService.addBill(bill);
                resp.sendRedirect("/billIndex?action=list");
            }

        }
    }
    public void billView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String str = req.getParameter("bill_id");
        Bill bill = billService.findByid(str);
        req.setAttribute("bill",bill);
        req.getRequestDispatcher("/billView.jsp").forward(req,resp);

    }
    public void billdelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String bill_id=req.getParameter("bill_id");
        billService.deleteById(bill_id);
        resp.sendRedirect("/billIndex?action=list");
    }
    public void billUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if ("get".equalsIgnoreCase(req.getMethod())){
            String str = req.getParameter("bill_id");
            Bill bill = billService.findByid(str);
            req.setAttribute("bill",bill);
            req.getRequestDispatcher("/billUpdate.jsp").forward(req,resp);
        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = dateFormat.format(new Date());
            Bill bill = new Bill();
            bill.setBill_id(req.getParameter("billId"));
            bill.setBill_name(req.getParameter("billName"));
            bill.setBill_com(req.getParameter("billCom"));
            bill.setBill_num(req.getParameter("billNum"));
            bill.setBill_money(req.getParameter("money"));
            bill.setBill_supplier(req.getParameter("supplier"));
            bill.setBill_pay(req.getParameter("zhifu"));
            bill.setBill_time(date);
            billService.updateById(bill);
            resp.sendRedirect("/billIndex?action=list");
        }
    }
    public void billSearchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String bill_name = req.getParameter("bill_name");
        String bill_supplier = req.getParameter("tigong");
        String bill_pay = req.getParameter("fukuan");
        List<Bill> bills = billService.search(bill_name,bill_supplier,bill_pay);
        req.setAttribute("bills",bills);
        req.getRequestDispatcher("/billList.jsp").forward(req,resp);
        return;

    }
}
