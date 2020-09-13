<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<h2>
	<span class="more"><a href="#" target="_self">更多&gt;&gt;</a> </span>热销图书
</h2>
<div class="book_c_04">

	<c:forEach var="hot" items="${hots}">
	<!--热销图书A开始-->
	<div class="second_d_wai">
		<div class="img">
			<a href="${pageContext.request.contextPath}/book/bookDetails?bookName=${hot.bookName}" target='_self'>
				<img src="${path}/back/img/${hot.image}" border=0 /> 
			</a>
		</div>
		<div class="shuming">
			<a href="${pageContext.request.contextPath}/book/bookDetails?bookName=${hot.bookName}" target="_blank">&nbsp;&nbsp;&nbsp;${hot.bookName}</a><a href="#" target="_blank"></a>
		</div>
		<div class="price">
			定价：￥${hot.price}
		</div>
		<div class="price">
			当当价：￥${hot.ddPrice}
		</div>
		<div class="price">
			销量：<font color="red">${hot.saleCount}</font>
		</div>
	</div>
	<div class="book_c_xy_long"></div>
	<!--热销图书A结束-->
	</c:forEach>
</div>

<div class="clear"></div>