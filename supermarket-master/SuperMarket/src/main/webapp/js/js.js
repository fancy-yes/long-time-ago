/**
 * Created by yaling.he on 2015/11/17.
 */

//供应商管理页面上点击删除按钮弹出删除框(providerList.html)
$(function () {
    $('.removeProvider').click(function () {
        $('.zhezhao').css('display', 'block');
        var id = this.getAttribute('mid');
        $('#yes').attr('mid',id);
        $('#removeProv').fadeIn();
    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
    });
});
$(function () {
    $('#yes').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeProv').fadeOut();
        var Id = $("#yes").attr('mid');
        $(location).attr('href','http://localhost:8080/supplierIndex?action=delete&supplier_num='+Id);
    });
});


//订单管理页面上点击删除按钮弹出删除框(billList.html)
$(function () {
    $('.removeBill').click(function () {
        $('.zhezhao').css('display', 'block');
        var id = this.getAttribute('mid');
        $('#yesBill').attr('mid',id)
        $('#removeBi').fadeIn();
    });
});

$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
    });
});
$(function () {
    $('#yesBill').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeBi').fadeOut();
        var Id = $("#yesBill").attr('mid');
        $(location).attr('href','http://localhost:8080/billIndex?action=delete&bill_id='+Id);
    });
});

//用户管理页面上点击删除按钮弹出删除框(userList.html)
$(function () {
    $('.removeUser').click(function () {
        $('.zhezhao').css('display', 'block');
        var id = this.getAttribute('mid');
        $('#yesUser').attr('mid',id);
        $('#removeUse').fadeIn();

    });
});
$(function () {
    $('#no').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeUse').fadeOut();
    });
});
$(function () {
    $('#yesUser').click(function () {
        $('.zhezhao').css('display', 'none');
        $('#removeUse').fadeOut();
        var Id = $("#yesUser").attr('mid');
        $(location).attr('href','http://localhost:8080/userIndex?action=delete&user_number='+Id);
    });
});
