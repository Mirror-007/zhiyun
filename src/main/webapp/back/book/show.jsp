<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
  <head>
    <title>index.html</title>
	<link rel="stylesheet" href="${path}/back/css/btn.css" />
    <meta charset="utf-8">
    
	<style type="text/css">
		table {
			border-right: 1px solid black;
			border-bottom: 1px solid black;
		}
		
		table td {
			padding: 10px;
			text-align:center;
			border-left: 1px solid black;
			border-top: 1px solid black;
		}
</style>
  </head>
  
  <body style="background-color: #f0f9fd;">
  	
  	<div align=center style="font-size:25px">图书管理</div><hr/>  	
  	<div style="margin: 0 auto;text-align:center;">
  		<form action="${path}/book/likeQuery" method="post">
  			<input onclick="location.href='${path}/book/toAddBook'" class="button btn-p" value="添加图书&raquo;" />&emsp;&emsp;
		  	<select name="key" class="el-select__inner inner2">
		  		<option value="bookName">书名</option>
		  		<option value="writer">作者</option>
		  		<option value="press">出版社</option>
		  	</select>
		  	<input class="el-input__inner" type="text" placeholder="请输入查询条件" name="content" required="required"/>
		  	<input class="el-search-content" type="submit" value="搜索"/>
	  	</form>
  	</div>
  	<div style="margin-top:20px">
  	<table bordercolor='#898E90' align='center' border='2px' cellpadding='5px' cellspacing="0px" width="100%">
  		<tr bgcolor='lightblue' align='center'>
			<td style="width: 100px">id</td>
			<td style="width: 100px">名称</td>
			<td>所属分类</td>
			<td>原价</td>
			<td>当当价</td>
			<td>作者</td>
			<td>出版社</td>
			<td>出版时间</td>
			<td>封面</td>
			<td>销量</td>
			<td>库存</td>
			<td>操作</td>
  		</tr>
  	<c:forEach var="book" items="${books}">
  		<tr align='center'>
			<td>${book.id}</td>
			<td>${book.bookName}</td>
			<td>${book.parentCateName}</td>
			<td>${book.price}</td>
			<td>${book.ddPrice}</td>
			<td>${book.writer}</td>
			<td>${book.press}</td>
			<td>
				<fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				<img width="32px" height="20px" src="${path}/back/img/${book.image}">
			</td>
			<td>${book.saleCount}</td>
			<td>${book.storages}</td>
			<td>
				<input  class="button"  onclick="location.href='${path}/book/toUpdateBook?id=${book.id}'" value="修改 " />
				<input class="button-del" onclick="location.href='${path}/book/deleteBook?id=${book.id}'" value="删除 " />
			</td>
		</tr>
	</c:forEach>
  	</table>
  	</div><br>
  </body>
</html>
