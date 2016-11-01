<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="${pageContext.request.contextPath }/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#join-form").submit(function() {
			// 1. 이름 체크
			if ($("#name").val() == "") {
				alert("이름은 필수 입력 항목입니다.");
				return false;
			}
			
			// 2-1. 이메일 비어있는 지 체크
			if ( $( "#eamil" ).val() == "" ){
				alert( "이메일은 필수 입력 항목입니다." );
				$( "#email" ).focus();
				return false;
			}
			
			// 2-2. 이메일 중복 체크 유무
			if( $( "#img-chkemail" ).is( ":visible" ) == false ){
				alert( "이메일 중복 체크를 해야 합니다." );
				return false;
			}
			
			// 3. 비밀번호 체크
			if( $("input[type='password']").val == "" ){
				alert( "비밀번호는 필수 입력 항목입니다.");
				$("input[type='password']").focus();
				return false;
			}
			
			// 4. 약관동의 체크
			if( $( "#agree-prov" ).is( ":checked" ) == false ) {
				alert( "약관동의를 체크 해야 합니다." );
				return false;
			}

			return false;
		});
		$("#email").change(function() {
			$("#img-chkemail").hide();
			$("#btn-chkemail").show();
		});
		$("#btn-chkemail").click(function() {
			var email = $("#email").val();
			if (email == "") {
				return;
			}

			$.ajax({
				url : "/mysite3/api/user?a=chkemail&email=" + email,
				type : "get",
				dataType : "json",
				data : "",
				//contentType: "application/json",
				success : function(response) {
					if (response.result == "fail") {
						console.log(response.message);
						return;
					}

					// success
					if (response.data == "exist") {
						alert("이미 존재하는 이메일입니다. 다른 이메일을 사용해 주세요.");
						$("#email").val("").focus();
						return;
					}

					// 존재하지 않는 이메일
					$("#img-chkemail").show();
					$("#btn-chkemail").hide();

				},
				error : function(jqXHR, status, e) {
					console.error(status + ":" + e);
				}
			});

		});
	});
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinForm" method="post"
					action="/mysite3/user">
					<input type="hidden" name="a" value="join" /> <label
						class="block-label" for="name">이름</label> <input id="name"
						name="name" type="text" value=""> <label
						class="block-label" for="email">이메일</label> <input id="email"
						name="email" type="text" value=""> <img id="img-chkemail"
						style="width: 16px; display: none"
						src="${pageContext.request.contextPath }/assets/images/check.png" />
					<input id="btn-chkemail" type="button" value="중복체크"> <label
						class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
	<div id="dialog" title="가입폼 체크" style="display:none">
   		<p></p>
 	</div>	
</body>
</html>