<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>当当图书 – 全球最大的中文网上书店</title>
		<link href="${path}/front/css/book.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/second.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/secBook_Show.css" rel="stylesheet" type="text/css" />
		<link href="${path}/front/css/list.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/front/js/prototype-1.6.0.3.js"></script>
	</head>
	<body>
		&nbsp;

		<!-- 头部开始 -->
		<%@include file="../common/head.jsp"%>
		<!-- 头部结束 -->

		<div style="width: 962px; margin: auto;">
			<a href="#"><img src="${path}/front/images/default/book_banner_081203.jpg" border="0" /> </a>
		</div>
		<div class='your_position'>
			您现在的位置:&nbsp;
			<a href='${path}/cate/showFrontCate'>当当图书</a> &gt;&gt;
			<c:if test="${requestScope.category2 == null}">
				<font style='color: #cc3300'><strong>${grade1Name}</strong> </font>
			</c:if>
			<c:if test="${requestScope.category2!=null}">
				<a href="${pageContext.request.contextPath}/book/bookList?grade1=${grade1Name}">${grade1Name}</a>&gt;&gt;
				<font style='color: #cc3300'><strong>${category2.cateName}</strong> </font>
			</c:if>
			<!-- if -->
		</div>

		<div class="book">

			<!--左栏开始-->
			<div id="left" class="book_left">
				<div id="__fenleiliulan">
					<div class=second_l_border2>
						<h2>
							分类浏览
						</h2>
						<ul>
							<li>
								<div>
									<div class=second_fenlei>
										<a href="${pageContext.request.contextPath}/book/bookList?grade1=${grade1Name}">&middot;
											<c:if test="${grade==null}">
												<font style='color: #cc3300'>全部&nbsp;</font>
											</c:if>
											<c:if test="${grade!=null}">
												全部&nbsp;
											</c:if>
										</a>
									</div>
								</div>
							</li>
							<div class="clear"></div>
							<c:forEach var="grade2" items="${ctg}">
							<!--2级分类开始-->
							<li>
								<div>
									<div class=second_fenlei>
										&middot;
									</div>
									<div class=second_fenlei>
										<a href="${pageContext.request.contextPath}/book/bookList?grade2=${grade2.cateName}">
											<c:if test="${gr==grade2.cateName}">
												<font style='color: #cc3300'>${grade2.cateName}&nbsp;</font>
											</c:if>
											<c:if test="${gr!=grade2.cateName}">
												${grade2.cateName}&nbsp;
											</c:if>
										</a>
									</div>
								</div>
							</li>
						   <div class="clear"></div>
							</c:forEach>
							<div class="clear"></div>
							<!--2级分类结束-->
							
							<li>
								<div></div>
							</li>
						</ul>
					</div>
				</div>
			</div>

			<!--左栏结束-->

			<!--中栏开始-->
			<div class="book_center">

				<!--图书列表开始-->
				<div id="divRight" class="list_right">

					<div id="book_list" class="list_r_title">
						<div class="list_r_title_text">
							排序方式
						</div>
						<select onchange='' name='select_order' size='1'
							class='list_r_title_ml'>
							<option value="">
								按上架时间 降序
							</option>
						</select>
						<div id="divTopPageNavi" class="list_r_title_text3">

							<!--分页导航开始-->
							<c:if test="${pageIndex>1}">
							<div class='list_r_title_text3a'>
								<a name=link_page_next href="${pageContext.request.contextPath}/book/bookList?pageIndex=${pageIndex-1}&grade1=${gN}&grade2=${category2.cateName}">
									<img src='${path}/front/images/page_up.gif' /> 
								</a>
							</div>
							</c:if>
							
							<c:if test="${pageIndex<=1}">
							<div class='list_r_title_text3a'>
								<img src='${path}/front/images/page_up_gray.gif' />
							</div>
							</c:if>
							<div class='list_r_title_text3b'>
								第${pageIndex}页/共${endPageIndex}页
							</div>
							
							<c:if test="${pageIndex < endPageIndex}">
							<div class='list_r_title_text3a'>
								<a name=link_page_next href="${pageContext.request.contextPath}/book/bookList?pageIndex=${pageIndex+1}&grade1=${gN}&grade2=${category2.cateName}">
									<img src='${path}/front/images/page_down.gif' /> 
								</a>
							</div>
							</c:if>
							
							<c:if test="${pageIndex==endPageIndex}">
							<div class='list_r_title_text3a'>
								<img src='${path}/front/images/page_down_gray.gif' />
							</div>
							</c:if>
							<!--分页导航结束-->
						</div>
					</div>
					
					<!--商品条目开始-->
						
						<div class="list_r_line"></div>
						<div class="clear"></div>
							
						<c:forEach var="bos" items="${bos1[0].categories}">
							<c:forEach var="bos2" items="${bos.books}">
						<div class="clear"></div>

						<div class="clear"></div>
							<div class="list_r_list">
								<span class="list_r_list_book">
									<a name="link_prd_img" href='${path}/book/bookDetails?book.bookName=${bos2.bookName}'>
										<img src="${path}/back/img/${bos2.image}" /> 
									</a> 
								</span>
								<h2>
									<a name="link_prd_name" href='${path}/book/bookDetails?book.bookName=${bos2.bookName}'>${bos2.bookName}</a>
								</h2>
								<h3>
									顾客评分：100
								</h3>
								<h4 class="list_r_list_h4">
									作 者:
									<a href='#' name='作者'>佚名</a>
								</h4>
								<h4>
									出版社：
									<a href='#' name='出版社'>${bos2.press}</a>
								</h4>
								<h4>
									出版时间：<fmt:formatDate value="${bos2.publishDate}" pattern="yyyy-MM-dd"/>
								</h4>
								<h5>
									这是一本好书，描述了Struts、Hibernate和Spring等框架的整合应用！
								</h5>
								<div class="clear"></div>
								<h6>
									<span class="del">￥${bos2.price}</span>
									<span class="red">￥${bos2.ddPrice}</span>
									节省：￥${bos2.price-bos2.ddPrice}
								</h6>
								<span class="list_r_list_button"> 
								<a href="${path}/cart/addCart?id=${bos2.id}"> 
									<img src='${path}/front/images/buttom_goumai.gif' /> 
								</a>
<%--								<span id="cartinfo"></span>--%>
							</div>
						<div class="clear"></div>
						</c:forEach>
						</c:forEach>	
						
						
						
						<c:forEach var="bos2" items="${bos2}">
						<div class="clear"></div>

						<div class="clear"></div>
							<div class="list_r_list">
								<span class="list_r_list_book">
									<a name="link_prd_img" href='#'>
										<img src="${path}/back/img/${bos2.image}" /> 
									</a> 
								</span>
								<h2>
									<a name="link_prd_name" href='#'>${bos2.bookName}</a>
								</h2>
								<h3>
									顾客评分：100
								</h3>
								<h4 class="list_r_list_h4">
									作 者:
									<a href='#' name='作者'>佚名</a>
								</h4>
								<h4>
									出版社：
									<a href='#' name='出版社'>${bos2.press}</a>
								</h4>
								<h4>
									出版时间：<fmt:formatDate value="${bos2.publishDate}" pattern="yyyy-MM-dd"/>
								</h4>
								<h5>
									这是一本好书，描述了Struts、Hibernate和Spring等框架的整合应用！
								</h5>
								<div class="clear"></div>
								<h6>
									<span class="del">￥${bos2.price}</span>
									<span class="red">￥${bos2.ddPrice}</span>
									节省：￥${bos2.price-bos2.ddPrice}
								</h6>
								<span class="list_r_list_button"> 
								<a href="${path}/cart/addCart?id=${bos2.id}"> 
									<img src='${path}/front/images/buttom_goumai.gif' /> 
								</a>
<%--								<span id="cartinfo"></span>--%>
							</div>
						<div class="clear"></div>
						</c:forEach>
						
					
						<!--商品条目结束-->

					<div class="clear"></div>
					<div id="divBottomPageNavi" class="fanye_bottom">
					</div>

				</div>
				<!--图书列表结束-->

			</div>
			<!--中栏结束-->
			<div class="clear"></div>
		</div>

		<!--页尾开始 -->
		<%@include file="../common/foot.jsp"%>
		<!--页尾结束 -->
	</body>
</html>
