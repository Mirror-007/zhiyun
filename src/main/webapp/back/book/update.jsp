<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="${path}/back/css/btn.css" />
	
<style type="text/css">
td{
	padding-top: 8px;
}
#file_upload1 {
	display: none;
}
#file_upload1_label {
	display: inline-block;
	border: 1px solid #aaa;
	width: 120px;
	height: 145px;
	margin-left: 20px;
	text-align: center;
	line-height: 145px;
	cursor: pointer;
}
</style>


</head>

<body style="background-color: #f0f9fd;text-align: center">
	<div style="font-size:25px">修改图书信息</div>
	<hr />
	<div align="center">
	<form action="${path}/book/updateBook" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					
					<td>名称：</td>
					<td><input type="text" name="bookName" class="el-input__inner" value="${shu.bookName}" required="required"></td>
					<%-- <span id="message" style="color:red;font-size: 15px;margin-left:10px">${msg}</span> --%>
					<td rowspan="14" style="width: 300px">
					<td>页数：</td>
					<td><input type="number" class="el-input__inner" value="500" readonly="readonly"></td>
				</tr>
				<tr>
					<td>所属分类：</td>
					<td>
						<select name="parentCateName" class="el-select__inner inner2">
							<c:forEach var="cate" items="${twos}">
								<c:if test="${cate.cateName==erjileibie}">
									<option value="${cate.cateName}" selected="selected">${cate.cateName}</option>
								</c:if>
								<%--<c:if test="${cate.cateName!=erjileibie}">
									<option value="${cate.cateName}">${cate.cateName}</option>
								</c:if>--%>
								<option value="${cate.cateName}">${cate.cateName}</option>
							</c:forEach>
						</select>
					</td>
					<td>字数：</td>
					<td><input type="number" class="el-input__inner" value="500000" readonly="readonly"></td>
				</tr>
				
				<tr>
					<td>原价：</td>
					<td><input type="number" name="price" class="el-input__inner" value="${shu.price}" required="required"></td>
					<td>封面：</td>
					<td rowspan="3">
						<label id="file_upload1_label" for="file_upload1">
							<img id="uploadimg" src="${path}/back/img/${shu.image}" alt="" width="120" height="145"/>
						</label> 
						<input type="file" name="cover" class="" id="file_upload1"	onchange="upload_review()">
					</td>
				</tr>
				
				<tr>
					<td>当当价：</td>
					<td><input type=number name="ddPrice" class="el-input__inner" value="${shu.ddPrice}" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td>库存：</td>
					<td><input type="number" name="storages" class="el-input__inner" value="${shu.storages}" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" name="writer" class="el-input__inner" value="${shu.writer}" required="required"></td>
					<td>编辑推荐：</td>
					<td rowspan="2"><textarea class="el-textarea__inner" name="" readonly="readonly">这里是编辑推荐</textarea></td>
				</tr>
				<tr>
					<td>出版社：</td>
					<td><input type="text" name="press" class="el-input__inner" value="${shu.press}" required="required"></td>
					<td></td>
				</tr>
				<tr>
					<td>出版时间：</td>
					<td><input type="date" name="publishDate" class="el-input__inner" value="${publishDate}"  required="required"></td>
					<td>内容简介：</td>
					<td rowspan="2"><textarea class="el-textarea__inner" name="" readonly="readonly">这里是内容简介</textarea></td>
				</tr>
				<tr>
					<td>版次：</td>
					<td><input type="text" name="" class="el-input__inner" value="第五版" readonly="readonly"></td>
					<td></td>
				</tr>
				<tr>
					<td>印刷时间：</td>
					<td><input type="date" name="" class="el-input__inner" readonly="readonly" value="2012-12-12"/></td>
					<td>作者简介：</td>
					<td rowspan="2"><textarea class="el-textarea__inner" name="" readonly="readonly">这里是作者简介</textarea></td>
				</tr>
				<tr>
					<td>印次：</td>
					<td><input type="text" name="" class="el-input__inner" value="第三次印刷" readonly="readonly"></td>
					<td></td>
				</tr>
				<tr>
					<td>ISBN：</td>
					<td><input type="text" name="=" class="el-input__inner" value="9876543210" readonly="readonly"></td>
					<td>基本目录：</td>
					<td rowspan="2"><textarea class="el-textarea__inner" name="" readonly="readonly">这里是基本目录</textarea></td>
				</tr>
				<tr>
					<td>开本：</td>
					<td><input type="text" name="" class="el-input__inner" value="16开" readonly="readonly"></td>
					<td></td>
				</tr>
				<tr>
					<td>纸张：</td>
					<td><input type="text" name="" class="el-input__inner" value="A4纸" readonly="readonly"></td>
					<td>媒体评论：</td>
					<td rowspan="2"><textarea class="el-textarea__inner" name="" readonly="readonly">这里是媒体评论</textarea></td>
				</tr>
				<tr>
					<td>包装：</td>
					<td><input type="text" name="" class="el-input__inner" value="精装" readonly="readonly"></td>
					<td></td>
				</tr>
			</table>
			<input type="submit" class="button btn-p" value="提交" />&emsp; 
			<input type="button" class="button btn-p" value="返回上级" onclick="history.go(-1);" />
		</form>
	</div>
	<script>
		function upload_review() {
			var img = document.getElementById("uploadimg");
			var input = document.getElementById("file_upload1");
			var tip = document.getElementById("uploadtip");			

			var file = input.files.item(0);
			var freader = new FileReader();
			freader.readAsDataURL(file);
			freader.onload = function(e) {
				img.src = e.target.result;
				tip.style.display = "none";
			};
		}
	</script>
</body>
</html>

