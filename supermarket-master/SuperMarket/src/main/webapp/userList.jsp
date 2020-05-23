<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>超市账单管理系统</title>
    <link rel="stylesheet" href="css/public.css"/>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<!--头部-->
    <header class="publicHeader">
        <h1>超市账单管理系统</h1>
        <div class="publicHeaderR">
            <p><span id="hours"></span><span style="color: #fff21b"> ${sessionScope.user.username }</span> , 欢迎你！</p>
            <a href="/SuperMarket?action=out">退出</a>
        </div>
    </header>
<!--时间-->
    <section class="publicTime">
        <span id="time">2015年1月1日 11:11  星期一</span>
        <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
    </section>
<!--主体内容-->
    <section class="publicMian ">
        <div class="left">
            <h2 class="leftH2"><span class="span1"></span>功能列表 <span></span></h2>
            <nav>
            <ul class="list">
                <li><a href="/billIndex?action=list">账单管理</a></li>
                <li><a href="/supplierIndex?action=list">供应商管理</a></li>
                <li id="active"><a href="/userIndex?action=list">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="/SuperMarket?action=out">退出系统</a></li>
            </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
                <form action="/userIndex?action=search" method="post">
                    <span>用户名：</span>
                    <input name="user_name" type="text" placeholder="请输入用户名"/>
                    <input type="submit" value="查询"/>
                    <a href="/userAdd.jsp">添加用户</a>
                </form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户类型</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${users }" var="user">
                <tr>
                    <td>${user.user_number }</td>
                    <td>${user.user_name }</td>
                    <td>${user.user_sex }</td>
                    <td>${user.user_age }</td>
                    <td>${user.user_phone }</td>
                    <td>${user.user_type }</td>
                    <td>
                        <a href="/userIndex?action=view&user_number=${user.user_number }"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="/userIndex?action=update&user_number=${user.user_number }"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="#" class="removeUser" mid="${user.user_number }"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>

        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yesUser">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>

    <footer class="footer">
    </footer>

<script src="js/jquery.js"></script>
<script src="js/js.js"></script>
<script src="js/time.js"></script>

</body>
</html>