<%@page contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="${path}/front/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/shopping_vehicle.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="${path}/front/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		
		function change(id){
			var rule = /^[0-9]*[1-9][0-9]*$/;
			var count = $("#count"+id).val();
			var msgg = $("#msgg").val();
			if(rule.test(count-0)){
				location.href="${path}/cart/updateCartItem?id="+id+"&count="+count;
				if(msgg==null){
					alert("修改正确，已修改为"+count);
				}
				if(msgg!=null){
					alert("提示："+msgg);
				}
			}
			if(rule.test(count-0)==false){
				alert("请输入正整数！");
			}
		}
		
	</script>
	</head>
	<body>
		<br />
		<br />
		<div class="my_shopping">
			<a href='${path}/cate/showFrontCate'>
				<img class="pic_shop" src="../images/pic_myshopping.gif" />
			</a>
		</div>
		
		<c:if test="${cart==null||total==0}">
			<div id="div_no_choice">
				<div class="choice_title"></div>
				<div class="no_select">
					您还没有挑选商品[<a href='${path}/cate/showFrontCate'> 继续挑选商品&gt;&gt;</a>]
				</div>
			</div>
		</c:if>
		<c:if test="${cart!=null}">
			<c:if test="${total>0}">
		<div id="div_choice" class="choice_merch">
			<h2 id="cart_tips">
				您已选购以下商品
			</h2>
			<div class="choice_bord">
				<table class="tabl_buy" id="tbCartItemsNormal">
					<tr class="tabl_buy_title">
						<td class="buy_td_6">
							<span>图片</span>
						</td>
						<td>
							<span class="span_w1">商品名</span>
						</td>
						<td class="buy_td_5">
							<span class="span_w2">市场价</span>
						</td>
						<td class="buy_td_4">
							<span class="span_w3">智云价</span>
						</td>
						<td class="buy_td_1">
							<span class="span_w2">数量</span>
						</td>
						<td class="buy_td_2">
							<span>变更数量</span>
						</td>
						<td class="buy_td_1">
							<span>删除</span>
						</td>
					</tr>
					<tr class='objhide' over="no">
						<td colspan="8">
							&nbsp;
						</td>
					</tr>
					
                      <!-- 购物列表开始 -->
					<c:forEach var="ci" items="${cart}">                    
						<c:if test="${ci.value.status==true}">
						<tr class='td_no_bord'>
							<td style='display: none'>
								9317290
							</td>
							<td class="buy_td_6">
								    <img src="${path}/back/img/${ci.value.book.image}" border=0 width="60px" height="80px"/>
							</td>
							<td>
								<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${ci.value.book.bookName}</a>
							</td>
							<td class="buy_td_5">
								<span class="c_gray">￥${ci.value.book.price}</span>
							</td>
							<td class="buy_td_4">
								&nbsp;&nbsp;
								<span>￥${ci.value.book.ddPrice}</span>
							</td>
							<td class="buy_td_1">
								&nbsp;&nbsp;${ci.value.count}
							</td>

							<td >
								<input id="count${ci.value.book.id}" class="del_num" type="text" size="3" maxlength="4" value=""/>
								<input id="msgg" value="${msgg}" type="hidden"/>
								<a id="update${ci.value.book.id}" href="javascript:void(0)" onclick="change('${ci.value.book.id}')">变更</a>
							</td>
							<td>
								<a href="${path}/cart/deleteCartItem?id=${ci.value.book.id}">删除</a>
							</td>
						</tr>
							</c:if>
						</c:forEach>
						
					<!-- 购物列表结束 -->
				</table>
				<div class="choice_balance">
					<div class="select_merch">
						<a href='${path}/cate/showFrontCate'> 继续挑选商品>></a>
					</div>
					<div class="total_balance">
						<div class="save_total">
							您共节省：
							<span class="c_red"> ￥<span id="total_economy">${save}</span> </span>
							<span id='total_vip_economy' class='objhide'> 
								( 其中享有优惠： 
								<span class="c_red"> ￥<span id='span_vip_economy'>0.00</span> </span>
								) 
							</span>
							<span style="font-size: 14px">|</span>
							<span class="t_add">商品金额总计：</span>
							<span class="c_red_b"> ￥<span id='total_account'>${total}</span>
							</span>
						</div>
						<div id="balance" class="balance">
							<a name='checkout' href='${path}/front/order/order_info.jsp' > 
								<img src="${path}/front/images/butt_balance.gif" alt="结算" border="0" title="结算" />
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		<!-- 用户删除恢复区 -->
		<div id="divCartItemsRemoved" class="del_merch">
			<div class="del_title">
				您已删除以下商品，如果想重新购买，请点击“恢复”
			</div>
	<c:forEach var="ci" items="${cart}">                   
		<c:if test="${ci.value.status==false}">
			<table class=tabl_del id=del_table>
				<tbody>
					<tr>
						<td width="58" class=buy_td_6>
							&nbsp;
						</td>
						<td width="365" class=t2>
							<a href="#">${ci.value.book.bookName}</a>
						</td>
						<td width="106" class=buy_td_5>
							￥${ci.value.book.price}
						</td>
						<td width="134" class=buy_td_4>
							<span>￥${ci.value.book.ddPrice}</span>
						</td>
						<td width="56" class=buy_td_1>
							<a href="${path}/cart/recoverCartItem?id=${ci.value.book.id}">恢复</a>
						</td>
						<td width="16" class=objhide>
							&nbsp;
						</td>
					</tr>
					<tr class=td_add_bord>
						<td colspan=8>
							&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
	</c:if>
	</c:forEach>
		</div>
</c:if>
		<!-- 用户删除恢复区结束 -->
		<br />
		<br />
		<br />
		<br />
		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>



