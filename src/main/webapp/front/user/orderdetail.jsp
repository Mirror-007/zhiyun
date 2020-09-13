<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>order</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/btn.css" />
    <meta name="keywords" content="keyword1,keyword2,keyword3">
    <meta name="description" content="this is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
    
  </head>
  <body style="background-color: #f0f9fd;text-align: center">
  <div align=center style="font-size:25px">订单详细信息</div><hr/>
  	 <table bordercolor='#898E90' align='center' border='3px' cellpadding='5px' cellspacing="0px">
          <tr bgcolor="lightblue" align="center"> 
             <td>订单号：${OrderItemss.orderNumber}</td>
             <td colspan="2">订单金额：¥：${OrderItemss.total}</td>
             <td colspan="2">订单状态：${OrderItemss.status}</td>
          </tr>
          <c:forEach var="oi" items="${OrderItemss.orderItems}">
			<tr align='center'> 
				 <td>${oi.book.bookName}</td>
				 <td style="width: 100px;"><img style="width:32px;height: 20px" src="${pageContext.request.contextPath}/back/img/${oi.book.image}"></td>
				 <td style="width: 180px;">单价：¥${oi.book.price}</td>
				 <td style="width: 200px;">当当价：¥${oi.book.ddPrice}</td>
				 <td style="width: 100px;">${oi.count}本</td>
			</tr>
			</c:forEach>
			 <tr bgcolor="lightblue">
          		<td colspan="3"><div class="button btn-p" onclick="history.go(-1);" style="margin-top: 10px">返回上级&raquo;</div></td>
          		<td colspan="3"><div class="button btn-p" onclick="location.href='${pageContext.request.contextPath}/cate/showFrontCate'">返回首页&raquo;</div></td>
          </tr>
     </table> 
  </body>
</html>
