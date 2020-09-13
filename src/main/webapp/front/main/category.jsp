<%@page contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="book_l_border1" id="__FenLeiLiuLan">
	<div class="book_sort_tushu">
		<h2>
			分类浏览
		</h2>
		
		<!--1级分类开始-->
			<c:forEach var="grade1" items="${LIST}">
			<div class="bg_old" onmouseover="this.className = 'bg_white';"
				onmouseout="this.className = 'bg_old';">
			<c:if test="${grade1.grade==1}">
				<h3>
					[<a href='${pageContext.request.contextPath}/book/bookList?grade1=${grade1.cateName}'>${grade1.cateName}</a>]
				</h3>
			</c:if>
				<ul class="ul_left_list">
					<c:forEach var="grade2" items="${grade1.categories}">
						<!--2级分类开始-->
						<li>
							<a href='${pageContext.request.contextPath}/book/bookList?grade2=${grade2.cateName}'>${grade2.cateName}</a>
						</li>
						<!--2级分类结束-->
					</c:forEach>
				</ul>
				<div class="empty_left">
				</div>
			</div>

			<div class="more2">
			</div>
			</c:forEach>
		<div class="bg_old">
			<h3>
				&nbsp;
			</h3>
		</div>
	</div>
</div>
