<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8" import="java.util.*" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录 - 当当网</title>
<link href="${path}/back/css/login.css" rel="stylesheet" type="text/css" />
<link href="${path}/back/css/pop_cheat.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/back/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
		$(function(){
		var usn = false;
		var pass = false;
		var vc = false;
		//验证账号名
		$("#txtUsername").blur(function(){
			var username = $(this).val();
			if(username==null || username ==""){
				$("#usernameMsg").html("<b><font style='color:red'>账号不能为空</font></b>");
			}else{
				$("#usernameMsg").html("<b><font style='color:green'>√</font></b>");
				usn = true;
			}
		});
		
		//验证密码
		$("#txtPassword").blur(function(){
			var password = $(this).val();
			if(password==null || password ==""){
				$("#passwordMsg").html("<b><font style='color:red'>密码不能为空</font></b>");
			}else{
				$("#passwordMsg").html("<b><font style='color:green'>√</font></b>");
				pass = true;
			}
		});
		
		//验证验证码
		$("#txtVcode").blur(function(){
			var vcode = $(this).val();
			if(vcode == null || vcode ==""){
				$("#vcodeMsg").html("<b><font style='color:red'>验证码不能为空</font></b>");
			}else{
				$("#vcodeMsg").html("<b><font style='color:green'>√</font></b>");
				vc = true;
			}
		});
		
		//验证from表单提交
		$("form").submit(function(){
			//将所有input框中的blur事件都执行一遍
			$("input").trigger("blur");
			
			if(!usn || !pass || !vc){
				return false;
			}
		 });
		 
		 //验证码放大镜
			$("#imgVcode").mouseover(function(e){
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
					"height":"100px",
					"width":"200px",
					"position":"absolute",
					"border":"5px solid #cad",
					"border-radius":"30px 10px",
				});
			});
	});
		//点击更换验证码
		function changeImage(){
			$("#imgVcode").attr("src","${path}/admin/capture?a="+new Date().getTime());
		};
		
		
			
	</script>
</head>
<body>
	<div class="head">
		<a href="http://www.dangdang.com"> <img src="${path}/back/images/signin_logo.png" /></a>
		<div class="improve">
			<img src="${path}/back/images/bz.jpg" width="178" height="61" />
		</div>
	</div>

	<div class="login_bg" style="width:960px; margin:0 auto;">
		<img src="http://a.dangdang.com/api/data/cpx/img/38930001/1" style="display: none;">
		<div id="J_cheatProofTop" class="new_tip">
			<div id="component_2747856"></div>
			<div>为保障账户安全，请勿设置与邮箱及其他网站相同的账户登录密码或支付密码，<a href="javascript:;">谨防诈骗</a>！</div>
		</div>
		<div class="set_area clearfix">
			<div class="wrap clearfix">
				<div id="J_loginDiv">
					<form action="${pageContext.request.contextPath}/admin/login" method="post">
						<span id="message" style="color:red;font-size: 15px;margin-left:10px">${msg}</span>
						<div id="J_loginCoreWrap" class="infro">
							<div class="username" id="username_div">
								<span></span> <input class="user" id="txtUsername" name="admin.adminName" type="text" />
							</div>
							<p id="usernameMsg"></p>
							<br /><br />
							<div class="password" id="password_div">
								<span></span> <input class="pass" id="txtPassword" name="admin.adminPass" type="password" />
							</div>
							<p id="passwordMsg"></p>
							<br /> <br />
							<div id="J_captchVcodeWrap" style="" class="Captcha">
								<div class="code" style="width:100px;">
									<input type="text" name="vcode" id="txtVcode"/>
								</div>
								<div class="Captcha-operate">
									<div class="Captcha-imageConatiner">
										<a class="code_pic" id="vcodeImgWrap" name="change_code_img" href="javascript:void(0);">
											<img id="imgVcode" src="${path}/admin/capture" class="Ucc_captcha Captcha-image" onClick="changeImage()">
										</a>
										<a id="vcodeImgBtn" name="change_code_link" class="code_picww" href="javascript:changeImage()">换张图</a> 
										<span id="spn_vcode_ok" class="icon_yes pin_i" style="display: none;"></span> 
										<span id="J_tipVcode" class="cue warn"></span>
									</div>
								</div>
							</div>
							<br /> <br />
							<p id="vcodeMsg"></p>
							<p class="btn">
								<input id="submitLoginBtn" type="submit" value="登&nbsp;录" />
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
</body>
</html>





