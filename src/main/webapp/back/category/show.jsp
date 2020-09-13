<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<html>
	<head>
		<link rel="stylesheet" href="${path}/back/css/btn.css" />
	</head>
	<body style="background-color: #f0f9fd;text-align: center;">
	
		<div style="font-size:25px">类别管理</div><hr/>
		<div style="margin-bottom: 10px;">
			<div class="button btn-p" onclick="location.href='${path}/back/category/add-first.jsp'">添加一级类别&raquo;</div>
			<div class="button btn-p" onclick="location.href='${path}/cate/toAddSecondGrade'">添加二级类别&raquo;</div>
		</div>
		<table bordercolor='#898E90' align='center' border='3px' cellpadding='10px' cellspacing="0px" >
			<tr bgcolor='lightblue'>
				<td>Id</td>
				<td>类别名</td>
				<td>所属类别</td>
				<td>级别</td>
				<td>操作</td>
			</tr>
		<c:forEach var="grade1" items="${LIST}">	
			<c:if test="${grade1.grade==1}">
			<tr>
				<td>${grade1.id}</td>
				<td>${grade1.cateName}</td>
				<td>${grade1.parentCateName}</td>
				<td>${grade1.grade}</td>
				<td>
					<!-- 判断一级类别下有没有二级类别 -->
					<c:if test="${grade1.categories.isEmpty()==true}">
						<div class="button" onclick="location.href='${path}/cate/deleteOne?aid=${grade1.id}'">删除 &raquo;</div>
					</c:if>
				</td>
			</tr>
			</c:if>
			<c:forEach var="grade2" items="${grade1.categories}">
				<tr>
					<td>${grade2.id}</td>
					<td>${grade2.cateName}</td>
					<td>${grade2.parentCateName}</td>
					<td>${grade2.grade}</td>
					<td>
						<c:if test="${grade2.books.isEmpty()==true}">
							<div class="button" onclick="location.href='${path}/cate/deleteOne?aid=${grade2.id}'">删除 &raquo;</div>	
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
		</table><br/>
		
	</body>  
</html>



