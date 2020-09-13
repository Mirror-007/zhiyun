<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<html>
	<head>
		<link rel="stylesheet" href="${path}/back/css/btn.css" />
	</head>
	<body style="background-color: #f0f9fd;text-align: center;">
		<div style="font-size:25px">地址信息管理</div><hr/>
		<form action="${path}/addr/addAddress" method="post">
		<table bordercolor='#898E90' align='center' border='3px' cellpadding='10px' cellspacing="0px" >
			<tr bgcolor='lightblue'>
				<td>收件人</td>
				<td>地址</td>
				<td>邮编</td>
				<td>电话</td>
				<td>默认地址</td>
				<td>操作</td>
			</tr>
			<c:forEach var="ad" items="${ads}">
			<tr>
				<td>${ad.addressee}</td>
				<td>${ad.address}</td>
				<td>${ad.postcode}</td>
				<td>${ad.phone}</td>
				<td>${ad.defaultAddr}</td>
				<td>
					<c:if test="${ad.defaultAddr==0}">
						<a href="${path}/addr/deleteAddr?id=${ad.id}">删除</a>
						<a href="${path}/addr/setDefault?id=${ad.id}">设为默认地址</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td><input name="addressee" required="required"/></td>
				<td><input name="address" required="required"/></td>
				<td><input name="postcode" required="required"/></td>
				<td><input name="phone" required="required"/></td>
				<td></td>
				<td><input type="submit" value="创建一个新地址"/></td>
			</tr>
			<tr bgcolor="lightblue" align="center">
          	<td colspan="3"><div class="button btn-p" onclick="history.go(-1);" style="margin-top: 10px">返回上级&raquo;</div></td>
          	<td colspan="3"><div class="button btn-p" onclick="location.href='${pageContext.request.contextPath}/cate/showFrontCate'">返回首页&raquo;</div></td>
          </tr>
		</table><br/>
		</form>
		<span style="color:red"><h3>${mm}</h3></span>
	</body>  
</html>



