<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<html>
	<head>
		<link rel="stylesheet" href="${path}/back/css/btn.css" />
	<script type="text/javascript">
		$(function(){	
			//邮箱验证
			$("#txtEmail").blur(function(){
			//$("#name").val().length
				var Email = $(this).val();
	               if(Email== ""){
	               	 $("#EmailMsg").html("<b><font color='red'>邮箱不能为空~~!</font><b/>");
	               }else{
		               var regEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
		               if(regEmail.test(Email)){
		               		$("#EmailMsg").html("<b><font color='green'>邮箱验证通过~~!</font><b/>"); 
		               		ok1=true;    
		               }else{
					        $("#EmailMsg").html("<b><font color='red'>邮箱格式不正确~~!</font><b/>");
					   }	
	               }
			});
			
			//昵称验证
			$("#txtNickName").blur(function(){
				var NickName = $(this).val();
	               if(NickName== ""){
	               	 $("#NickNameMsg").html("<b><font color='red'>昵称不能为空~~!</font><b/>");
	               }else{
		               if(NickName.length<4){
		               		$("#NickNameMsg").html("<b><font color='red'>昵称格式不正确~~!</font><b/>");     
		               }else{
					        $("#NickNameMsg").html("<b><font color='green'>昵称验证通过~~!</font><b/>");
					        ok2=true;
					   }	
	               }
			});
			
			//密码验证
			$("#txtPassword").blur(function(){
				var Password = $(this).val();
               if(Password.length<6 || Password.length>20){
               		$("#PasswordMsg").html("<b><font color='red'>密码格式不正确~~!</font><b/>");     
               }else{
			        $("#PasswordMsg").html("<b><font color='green'>密码验证通过~~!</font><b/>");
			        ok3=true;
			   }
			});
		});
	</script>
	</head>
	<body style="background-color: #f0f9fd;text-align: center;">
	
		<div style="font-size:25px">个人信息管理</div><hr/>
		
		<form action="${path}/user/updateUser?id=${a.id}" method="post">
		<table bordercolor='#898E90' align='center' border='3px' cellpadding='10px' cellspacing="0px" width="30%">
			<tr bgcolor='lightblue'>
				<td>ID:&nbsp;&nbsp;&nbsp;&nbsp;${a.id}</td>
			</tr>
			<tr>
				<td>邮箱:<input id="txtEmail" name="email" value="${a.email}" required="required"/></td>
			</tr>
			<tr>
				<td>昵称:<input id="txtNickName" name="petName" value="${a.petName}" required="required"/></td>
			</tr>
			<tr>
				<td>原密码:<input name="password" placeholder="请输入原密码" required="required"/></td>
			</tr>
			<tr>
				<td>新密码:<input id="txtPassword" name="pass" value=""/></td>
			</tr>
		</table><br/>
		<div style="margin-bottom: 10px;">
		 	<div class="button btn-p"><input type="submit" value="修改个人信息"/>&raquo;</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="button btn-p" onclick="location.href='${path}/admin/queryOrderById?id=${a.id}'">查看历史订单&raquo;</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="button btn-p" onclick="location.href='${path}/addr/queryAddressById?addr.userId=${a.id}'">查看常用地址&raquo;</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="button btn-p" onclick="location.href='${pageContext.request.contextPath}/cate/showFrontCate'">返回首页&raquo;</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="button btn-p" onclick="history.go(-1);" style="margin-top: 10px">返回上级&raquo;</div>
		</div>
		</form>
		<span style="color:red">${fl}</span>
		
	</body>  
</html>



