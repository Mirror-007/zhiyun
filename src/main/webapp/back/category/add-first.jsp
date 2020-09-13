<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>index.html</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/btn.css" />
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=GBK">
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
    	$(function(){
    		var aa = false;
    		$("#fname").blur(function(){
    			var name = $(this).val();
    			if(name == null || name == ""){
    				$("#haha").html("<font style='color:red'>添加类别不能为空</font>");
    			}else{
    				aa=true;
    			}	
    		});
    	
    		$(".button btn-p").submit(function(){
    			${"input"}.trigger("blur");
    			if(!aa){
    				return false;
    			}
    		});
    	
    	
    	
    	
    	});
    
    </script>
  </head>
  
  <body style="background-color: #f0f9fd;text-align: center">
  	<div style="text-align: center;font-size: 18px">添加商品类别</div><hr/>
  	<form action="${pageContext.request.contextPath}/cate/addFirstGrade" method="post">
  		类别名:<input class="el-input__inner" type="text" name="gradeName" id="fname"/><span id="fnameMsg"></span><br/><br/>
  		<p id="haha"></p>
  		<span id="message" style="color:red;font-size: 15px;margin-left:10px">${requestScope.message}</span><br/>
  		<input class="button btn-p" type="submit" value="提交">&emsp;
		<div class="button btn-p"  onclick="history.go(-1);">返回上级</div>
  	</form>
    	
  </body>
</html>
