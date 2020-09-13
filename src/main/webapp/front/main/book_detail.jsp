<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<html>
	<head>
		<title>图书详情</title>
		<link rel="stylesheet" type="text/css" href="${path}/front/css/book_detail.css"/>
		<link href="${path}/front/css/public_footer.css" rel="stylesheet" type="text/css" />
		<%--<meta http-equiv="refresh" content="2" />--%>
		<script type="text/javascript" src="${path}/front/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
            $(function () {
				$('#im').click(function fun1(){
					//1.创建一个xhr对象
					var xhr;
					if(window.ActiveXObject){
						xhr = new ActiveXObject('Microsoft.xmlhttp');
					}else{
						xhr = new XMLHttpRequest();
					}
					//2.明确发送方式和发送目标
					xhr.open('get','${path}/cart/addCart?id=${bookDetail.id}');
					//3.设置头文件
					/*xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');*/
					//4.发送请求
					xhr.send();
					//5.处理响应--设置监听函数
					xhr.onreadystatechange = function(){
						if(xhr.status == 200&&xhr.readyState==4){
							//获取响应内容
							var txt = xhr.responseText;
							console.log(txt);

							$('#sp2').html(txt);
							timeOut();

						}

					}
				});

				//实现内容展示时间
                function timeOut() {
                    setTimeout(function() {
                        $('#sp2').hide();
                    }, 1500);
                }
			});
		</script>
	</head>
	<body>
		<br/>
		<div>
			<a href="${path}/cate/showFrontCate">回到首页</a>
			<h1>
				丛书名：${bookDetail.bookName}
			</h1>
			<hr/>
		</div>
		<table width="80%" border="0" align="center" cellspacing="0" cellpadding="5">
			<tr>
				<td rowspan="9" width="20%" valign="top"><img src="${path}/back/img/${bookDetail.image}" width="240px" height="340px" /></td>
				<td colspan="2">作者：${bookDetail.writer}</td>
			</tr>
			<tr>
				<td colspan="2">出版社：${bookDetail.press}</td>
			</tr>
			<tr>
				<td>出版时间：<fmt:formatDate pattern="yyyy-MM-dd" value="${bookDetail.publishDate}"/></td>
				<td>字数：1,000,000</td>
			</tr>
			<tr>
				<td>版次： 4</td>
				<td>页数： 200</td>
			</tr>
			<tr>
				<td>印刷时间： 2016/10/10</td>
				<td>开本： 16</td>
			</tr>
			<tr>
				<td>印次： 6</td>
				<td>纸张：A4</td>
			</tr>
			<tr>
				<td>ISBN： 996699123</td>
				<td>销量：<font color="red">${bookDetail.saleCount}</font></td>
			</tr>
			<tr>
				<td>所属类别：${yiJi}&gt;&gt;<font style='color: #cc3300'><strong>${bookDetail.parentCateName}</strong></font>
			</tr>
			<tr>
				<td colspan="2">定价：￥${bookDetail.price}&nbsp;&nbsp;&nbsp;&nbsp;
				<font color="red">智云价：￥${bookDetail.ddPrice}</font>&nbsp;&nbsp;&nbsp;&nbsp;
				节省：￥${bookDetail.price-bookDetail.ddPrice}</td>
			</tr>
			<tr>
			<td></td>
			<td></td>
				<td colspan="2">
					<%--<a href="${path}/cart/addCart?id=${bookDetail.id}">
						<img src='${path}/front/images/buttom_goumai.gif' on/>
					</a>--%>
						<img id="im" src='${path}/front/images/buttom_goumai.gif'/>

						<br><span id="sp2" style="color: #00be00"></span>
				</td>
			</tr>
		</table>
		<form enctype="application/x-www-form-urlencoded"></form>
		<hr style="border:1px dotted #666"/>
		<h2>编辑推荐</h2>
		<p>非常好的一本书！&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>内容简介</h2>
		<p>朴实无华，贴近自然！&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>作者简介</h2>
		<p>大家风范！&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>目录</h2>
		<p>请自行查找&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>媒体评论</h2>
		<p>EXCELLENT&nbsp;&nbsp;</p>
		<hr style="border:1px dotted #666"/>
		<h2>书摘插图</h2>
		<p>&nbsp;&nbsp;</p>
		<!--页尾开始 -->
		<div class="public_footer">
			<div class="public_footer_bottom">
				<div class="public_footer_icp" style="line-height: 48px;">
					Copyright (C) 智云服饰 2019-2020, All Rights Reserved
					<a href="#" target="_self"><img src="${path}/front/images/bottom/validate.gif" border="0" align="middle" /> </a>
					<a href="#" target="_self" style="color: #666;">京ICP证041189号</a>
				</div>
			</div>
		</div>
		<!--页尾结束 -->
	</body>
</html>
