<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	
	<script type="text/javascript" src="${path}/front/js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript">
		$(function(){
			
			//验证码放大镜
			$(".yzm_img").mouseover(function(e){
				var bigImag = $("<img id='bimg' src='"+$(this).attr("src")+"'/>");
				$(bigImag).css({
					"top":e.pageY+5,
					"left":e.pageX+10,
					"height":"100px",
					"width":"200px",
					"position":"absolute",
					"border":"5px solid #cad",
					"border-radius":"30px 10px",
				});
				$("body").append(bigImag);
			}).mouseout(function(){
				$("#bimg").remove();
			}).mousemove(function(e){
				$("#bimg").css({
					"top":e.pageY+5,
					"left":e.pageX+10,
					"height":"250px",
					"width":"200px",
					"position":"absolute",
					"border":"5px solid #cad",
					"border-radius":"30px 10px",
				});
			});
	});
	</script>
</head>
<body>
<h2>
	编辑推荐
</h2>
<div id=__bianjituijian/danpin>
	<div class=second_c_02>
		<c:forEach var="res" items="${recommends}">
		<!--编辑推荐图书开始-->
		<div class=second_c_02_b1>
			<div class=second_c_02_b1_1>
				<a href='${pageContext.request.contextPath}/book/bookDetails?bookName=${res.bookName}' target='_self'>
					<img class="yzm_img" src="${pageContext.request.contextPath}/back/img/${res.image}" width="70">
				</a>
			</div>
			<div class=second_c_02_b1_2>
				<h3>
					<a href='${pageContext.request.contextPath}/book/bookDetails?bookName=${res.bookName}' target='_blank' title='输赢'>${res.bookName}</a>
				</h3>
				<h4>
					作者：${res.writer} 著
					<br />
					出版社：${res.press}&nbsp;&nbsp;&nbsp;&nbsp;
					出版时间：<fmt:formatDate value="${res.publishDate}"/>
				</h4>
				<h5>
					简介：挺好的一本书     
				</h5>
				<h6>
					定价：￥${res.price}&nbsp;&nbsp;
					当当价：￥${res.ddPrice}
					销量：<font color="red">${res.saleCount}</font>
				</h6>
				<div class=line_xx></div>
			</div>
		</div>
		</c:forEach>
		
		<!--编辑推荐图书结束-->
	</div>
</div>
</body>
</html>