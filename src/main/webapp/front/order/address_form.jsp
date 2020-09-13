<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 当当网</title>
		<link href="${path}/front/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/page_bottom.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${path}/front/js/jquery-1.8.3.min.js"></script>
	<script type="text/JavaScript">
		 $(document).ready(function(){
		
		$("#address").bind('change',function(){

           var recieve = $(this).val();
           var addressee=recieve.split("@")[0];
           var address=recieve.split("@")[1];
           var postcode=recieve.split("@")[2];
           var phone=recieve.split("@")[3];
           $("#receiveName").val(addressee);
           $("#fullAddress").val(address);
           $("#postalCode").val(postcode);
           $("#phone").val(phone);
           });
});
	
	</script>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			生成订单骤: 1.确认订单 >
			<span class="red_bold"> 2.填写送货地址</span> > 3.订单成功
		</div>
		<div class="fill_message">
			<form name="ctl00" method="post" action="${path}/addr/toAddAddress?addr.userId=${a.id}" id="f">
			<p>
				选择地址：
				<select id="address" name="sel">
					
					<c:forEach var="dizhi" items="${showAddr}">
							<c:if test="${defaultAddr.id==dizhi.id}">
								<option value="${dizhi.addressee}@${dizhi.address}@${dizhi.postcode}@${dizhi.phone}" selected="selected">${dizhi.addressee}</option>
							</c:if>
							<c:if test="${defaultAddr.id!=dizhi.id}">
								<option value="${dizhi.addressee}@${dizhi.address}@${dizhi.postcode}@${dizhi.phone}">${dizhi.addressee}</option>
						 	</c:if>
					</c:forEach>		
				</select>
			</p>
				<input type="hidden" name="id" id="addressId" />

				<table class="tab_login">
					<tr>
						<td valign="top" class="w1">
							收件人姓名：
						</td>
						<td>
							<input type="text" class="text_input" name="addr.addressee" id="receiveName" value="${defaultAddr.addressee}" readonly="readonly"/>
							<div class="text_left" id="nameValidMsg">
								<p>
									请填写有效的收件人姓名
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							收件人详细地址：
						</td>
						<td>
							<input type="text" name="addr.address" class="text_input" id="fullAddress" value="${defaultAddr.address}" readonly="readonly"/>
							<div class="text_left" id="addressValidMsg">
								<p>
									请填写有效的收件人的详细地址
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							邮政编码
						</td>
						<td>
							<input type="text" class="text_input" name="addr.postcode" id="postalCode" value="${defaultAddr.postcode}" readonly="readonly"/>
							<div class="text_left" id="codeValidMsg">
								<p>
									请填写有效的收件人的邮政编码
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							电话
						</td>
						<td>
							<input type="text" class="text_input" name="addr.phone" id="phone" value="${defaultAddr.phone}" readonly="readonly"/>
							<div class="text_left" id="phoneValidMsg">
								<p>
									请填写有效的收件人的电话
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">
					<a href="order_info.jsp">
						<input id="btnClientRegister" class="button_1" name="submit" type="submit" value="取消" />
					</a>			
				<input id="btnClientRegister" class="button_1" name="submit" type="submit" value="下一步" />
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

