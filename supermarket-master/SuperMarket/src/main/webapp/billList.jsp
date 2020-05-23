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
            <p><span id="hours"></span><span style="color: #fff21b"> ${user.username }</span> , 欢迎你！</p>
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
                <li id="active"><a href="/billIndex?action=list">账单管理</a></li>
                <li><a href="/supplierIndex?action=list">供应商管理</a></li>
                <li><a href="/userIndex?action=list">用户管理</a></li>
                <li><a href="password.jsp">密码修改</a></li>
                <li><a href="/SuperMarket?action=out">退出系统</a></li>
            </ul>
            </nav>
        </div>
        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>账单管理页面</span>
            </div>
            <div class="search">
                <form action="billIndex?action=search" method="post">
                    <span>商品名称：</span>
                    <input type="text" name="bill_name" placeholder="请输入商品的名称"/>
                    <span>供应商：</span>
                    <select name="tigong" >
                        <option value="">--请选择--</option>
                        <option value="北京市粮油总公司">北京市粮油总公司</option>
                        <option value="邯郸市五得利面粉厂">邯郸市五得利面粉厂</option>
                    </select>
                    <span>是否付款：</span>
                    <select name="fukuan">
                        <option value="">--请选择--</option>
                        <option value="已付款">已付款</option>
                        <option value="未付款">未付款</option>
                    </select>
                    <input type="submit" value="查询"/>
                    <a href="/billIndex?action=add">添加订单</a>
                </form>
            </div>
            <!--账单表格 样式和供应商公用-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">账单编码</th>
                    <th width="20%">商品名称</th>
                    <th width="10%">供应商</th>
                    <th width="10%">账单金额</th>
                    <th width="10%">是否付款</th>
                    <th width="10%">创建时间</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach items="${bills }" var="bill">
                <tr>
                    <td>${bill.bill_id }</td>
                    <td>${bill.bill_name }</td>
                    <td>${bill.bill_supplier }</td>
                    <td>${bill.bill_money }</td>
                    <td>${bill.bill_pay }</td>
                    <td>${bill.bill_time }</td>
                    <td>
                        <a href="/billIndex?action=view&bill_id=${bill.bill_id }"><img src="img/read.png" alt="查看" title="查看"/></a>
                        <a href="/billIndex?action=update&bill_id=${bill.bill_id }"><img src="img/xiugai.png" alt="修改" title="修改"/></a>
                        <a href="#" class="removeBill" mid="${bill.bill_id }"><img src="img/schu.png" alt="删除" title="删除"/></a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yesBill">确定</a>
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