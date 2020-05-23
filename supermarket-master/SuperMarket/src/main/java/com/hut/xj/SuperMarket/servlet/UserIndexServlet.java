package com.hut.xj.SuperMarket.servlet;

import com.hut.xj.SuperMarket.dao.UserDao;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/userIndex")
public class UserIndexServlet extends HttpServlet {
    UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //解决缓存
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("pragma","no-cache");
        resp.setHeader("expires","-1");

        if(req.getSession().getAttribute("user") == null){
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            return;
        }
        String str=req.getParameter("action");
        if("add".equalsIgnoreCase(str)){
            this.add(req,resp);
        }else if("list".equalsIgnoreCase(str)){
            this.list(req,resp);
        }else if("view".equalsIgnoreCase(str)){
            this.view(req,resp);
        }else if("delete".equalsIgnoreCase(str)){
            this.delete(req,resp);
        }else  if("update".equalsIgnoreCase(str)){
            this.update(req,resp);
        }else  if("change".equalsIgnoreCase(str)){
            this.changeById(req,resp);
        }else if("search".equalsIgnoreCase(str)){
            this.searchById(req,resp);
        }
        return;
    }

    /**
     * 添加用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        if("get".equalsIgnoreCase(req.getMethod())){
           req.getRequestDispatcher("/userAdd.jsp").forward(req,resp);
           return;
        }else {
            String user_number = req.getParameter("userId");
            if(service.findById(user_number) != null){
                req.setAttribute("error","用户编号已存在");
                req.getRequestDispatcher("/userAdd.jsp").forward(req,resp);
                return;
            }
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String birth = req.getParameter("data").substring(0,4);
            int age = year - Integer.parseInt(birth);
            User user=new User();
            user.setUser_number(user_number);
            user.setUser_name(req.getParameter("userName"));
            user.setUser_passwd(req.getParameter("userpassword"));
            user.setUser_sex(req.getParameter("userSex"));
            user.setUser_birth(req.getParameter("data"));
            user.setUser_age(age);
            user.setUser_phone(req.getParameter("userphone"));
            user.setUser_address(req.getParameter("userAddress"));
            user.setUser_type(req.getParameter("userlei"));
            service.addUser(user);
            resp.sendRedirect("/userIndex?action=list");
        }

    }

    /**
     * 显示所有用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        List<HashMap<String,String>> users = service.findAll();
        req.setAttribute("users",users);
        req.getRequestDispatcher("/userList.jsp").forward(req,resp);
    }

    /**
     * 查看单个用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void view (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String str = req.getParameter("user_number");
        User user = service.findById(str);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/userView.jsp").forward(req,resp);
    }

    /**
     * 删除单个用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String str = req.getParameter("user_number");
        service.deleteById(str);
        resp.sendRedirect("/userIndex?action=list");
    }

    /**
     * 更新单个用户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String user_number = req.getParameter("user_number");
        if("get".equalsIgnoreCase(req.getMethod())){
            User user = service.findById(user_number);
            req.setAttribute("user",user);
            req.getRequestDispatcher("/userUpdate.jsp").forward(req,resp);
        }else{
            User user = new User();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String birth = req.getParameter("data").substring(0,4);
            int age = year - Integer.parseInt(birth);
            user.setUser_number(user_number);
            user.setUser_name(req.getParameter("userName"));
            user.setUser_sex(req.getParameter("userSex"));
            user.setUser_birth(req.getParameter("data"));
            user.setUser_age(age);
            user.setUser_phone(req.getParameter("userphone"));
            user.setUser_address(req.getParameter("userAddress"));
            user.setUser_type(req.getParameter("userlei"));
            service.updateById(user);
            resp.sendRedirect("/userIndex?action=list");
        }
    }

    /**
     * 修改当前用户密码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void changeById(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        AdminService adminService = new AdminService();
        Admin user = (Admin)req.getSession().getAttribute("user");
        if(!req.getParameter("oldPassword").equalsIgnoreCase(user.getPasswd())){
            req.setAttribute("error","密码不正确");
            req.getRequestDispatcher("/password.jsp").forward(req,resp);
            return;
        }else {
            String newpwd = req.getParameter("newPassword");
            adminService.updatePasswd(newpwd);
            resp.sendRedirect("/password.jsp");
            return;
        }

    }

    public void searchById(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        String user_name = req.getParameter("user_name");
        List<User> users = service.searchByName(user_name);
        req.setAttribute("users",users);
        req.getRequestDispatcher("/userList.jsp").forward(req,resp);
    }

}
