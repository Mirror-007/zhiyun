<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>
	<span class="more"><a href="#" target="_self">更多&gt;&gt;</a> </span>最新上架图书
</h2>
<div class="book_c_04">
<c:forEach var="ns" items="${news}">
	<!--热销图书A开始-->
	<div class="second_d_wai">
		<div class="img">
			<a href="${pageContext.request.contextPath}/book/bookDetails?bookName=${ns.bookName}" target='_self'>
				<img src="${pageContext.request.contextPath}/back/img/${ns.image}" border=0 /> 
			</a>
		</div>
		<div class="shuming">
			<a href="${pageContext.request.contextPath}/book/bookDetails?bookName=${ns.bookName}" target="_blank">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ns.bookName}</a><a href="#" target="_blank"></a>
		</div>
		<div class="price">
			定价：￥${ns.price}
		</div>
		<div class="price">
			当当价：￥${ns.ddPrice}
		</div>
		<div class="price">
			销量：<font color="red">${ns.saleCount}</font>
		</div>
	</div>
	<div class="book_c_xy_long"></div>
	<!--热销图书A结束-->
</c:forEach>
</div>
<div class="clear"></div>
