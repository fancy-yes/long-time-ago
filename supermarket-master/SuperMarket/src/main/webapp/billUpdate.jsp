<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <p><span id="hours"> </span><span style="color: #fff21b"> ${sessionScope.user.username }</span> , 欢迎你！</p>
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
            </ul>>
        </nav>
    </div>
    <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>账单管理页面 >> 订单添加页面</span>
        </div>
        <div class="providerAdd">
            <form action="/billIndex?action=update" method="post">
                <!--div的class 为error是验证错误，ok是验证成功-->
                <div class="">
                    <label for="billId">订单编码：</label>
                    <input type="hidden" name="billId" id="billId" value="${bill.bill_id }"/>
                    <span>${bill.bill_id }</span>
                </div>
                <div>
                    <label for="billName">商品名称：</label>
                    <input type="text" name="billName" id="billName" placeholder="${bill.bill_name }"/>
                    <span >*</span>
                </div>
                <div>
                    <label for="billCom">商品单位：</label>
                    <input type="text" name="billCom" id="billCom" placeholder="${bill.bill_com }"/>
                    <span>*</span>

                </div>
                <div>
                    <label for="billNum">商品数量：</label>
                    <input type="text" name="billNum" id="billNum" placeholder="${bill.bill_num }"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="money">总金额：</label>
                    <input type="text" name="money" id="money" placeholder="${bill.bill_money }"/>
                    <span>*</span>
                </div>
                <div>
                    <label for="supplier">供应商：</label>
                        <select name="supplier" >
                            <option value="">--请选择相应的提供商--</option>
                            <option value="北京市粮油总公司">北京市粮油总公司</option>
                            <option value="邯郸市五得利面粉厂">邯郸市五得利面粉厂</option>
                        </select>
                    <span>*</span>
                </div>
                <div>
                    <label >是否付款：</label>
                    <input type="radio" name="zhifu" value="未付款"/>未付款
                    <input type="radio" name="zhifu" value="已付款"/>已付款
                </div>
                <div class="providerAddBtn">
                    <!--<a href="#">保存</a>-->
                    <!--<a href="billList.html">返回</a>-->
                    <input type="submit" value="提交"/>
                    <input type="button" value="返回" onclick="history.back(-1)"/>
                </div>
            </form>
        </div>

    </div>
</section>
<footer class="footer">
</footer>
<script src="js/time.js"></script>

</body>
</html>