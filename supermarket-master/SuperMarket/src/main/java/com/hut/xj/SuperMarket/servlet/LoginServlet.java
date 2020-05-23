package com.hut.xj.SuperMarket.servlet;

import com.hut.xj.SuperMarket.domain.Admin;
import com.hut.xj.SuperMarket.domain.User;
import com.hut.xj.SuperMarket.service.AdminService;
import com.hut.xj.SuperMarket.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/SuperMarket")
public class LoginServlet extends HttpServlet {
    AdminService adminService= new AdminService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("pragma","no-cache");
        resp.setHeader("expires","-1");

        String action = req.getParameter("action");
        if("out".equalsIgnoreCase(action)){
            req.getSession().removeAttribute("user");
            resp.sendRedirect("/login.jsp");
            return;
        }

        if(req.getSession().getAttribute("user") != null){
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
            return;
        }else{
            String username = req.getParameter("username");
            String passwd=req.getParameter("password");
            Admin user = adminService.check(username,passwd);
            if(user != null){
                req.getSession().setAttribute("user",user);
                resp.sendRedirect("/index.jsp");
                return;
            }
        }
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
        return ;
    }
}
