function check(){
    var passwd1=document.getElementById("userpassword").value;
    var passwd2=document.getElementById("userRemi").value;
    if(passwd1 != passwd2){
        alert("两次密码不一致");
        document.getElementById("submit").disabled = true;
    }
}
function check1(){
    var passwd1=document.getElementById("userpassword").value;
    var passwd2=document.getElementById("userRemi").value;
    if(passwd1 != passwd2){
        alert("两次密码不一致");
        return false;
    }else{
        return true;
    }

}
