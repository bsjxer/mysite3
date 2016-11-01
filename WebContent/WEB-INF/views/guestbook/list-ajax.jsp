<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var isEnd = false;
var page = 0;
var render = function( vo, bo ){
	//
	// 현업에서는 이부분을 template library ex) ejs
	//
	if( bo ) {
		var htmls = 
			"<li id=" + vo.no + ">" +
			"<table class='bottom' ><tr>" +
			"<td>" + vo.no + "</td>" +
			"<td>" + vo.name + "</td>" +
			"<td>" + vo.reg_date + "</td>" +
			"<td><a data-id="+vo.no+">삭제</a></td></tr>" +
			"<tr><td colspan='4'>" + vo.content.replace( /\n/gi, "<br>") + "</td></tr></table>" +
			"</li>";
			
		$( "#list-guestbook" ).prepend( htmls );
	} else {
		var htmls = 
			"<li id=" + vo.no + ">" +
			"<table class='bottom' ><tr>" +
			"<td>" + vo.no + "</td>" +
			"<td>" + vo.name + "</td>" +
			"<td>" + vo.reg_date + "</td>" +
			"<td><a data-id="+vo.no+">삭제</a></td></tr>" +
			"<tr><td colspan='4'>" + vo.content.replace( /\n/gi, "<br>") + "</td></tr></table>"
			"</li>";
			
		$( "#list-guestbook" ).append( htmls );
	}
};



var fetchList = function() {
	if ( isEnd == true ) {
		return;
	}
	++page;
	$.ajax({
		url: "${pageContext.request.contextPath }/api/guestbook",
		type: "post",
		dataType: "json",
		data:"a=ajax-list&p=" + page,
		success: function( response ){
			if( response.result != "success" ){
				console.error( response.message );
				isEnd = true;
				return;
			}
			
			// rendering
			$( response.data ).each( function(index, vo) {
				render( vo, false );
			});
			
			if( response.data.length < 5 ){
				isEnd = true;
				$( "#btn-fetch" ).prop( "disabled", true );
			}
		},
		error : function( jqXHR, status, e ){
			console.error( status + ":" + e );
		}
	});
}

$( function (){
	// 삭제 버튼 click event (live event)
	$( document ).on( "click", "#list-guestbook li a", function( event ){
		event.preventDefault();
		console.log( $(this).data("id"));
		
		$("#m_no").val($(this).data("id"));
		
		$("#dialog").dialog({
			autoOpen: false,
			show: {
				effect: "blind",
				duration: 1000
			},
			hide: {
				effect: "fade",
				duration: 1000
			},
			
			buttons: {
				"정말 삭제?": function() {
					var a = $("#m_a").val();
					var no = $("#m_no").val();
					var password = $("#m_password").val();
					console.log( a + ":" + no + ":" + password);
					
					$.ajax({
						url: "${pageContext.request.contextPath }/api/guestbook",
						type: "post",
						dataType: "json",
						data:"?a=ajax-delete&no=" + no + "&password=" + password,
						success: function( response ){
							if( response.result != "success" ){
								console.error( response.message );
								return;
							}
							console.log( response );
							$("#"+response.data).remove();
						},
						error : function( jqXHR, status, e ){
							console.error( status + ":" + e );
						}
					});
					
					$( "#add-form")[0].reset();
					
					$( this ).dialog( "close" );
					
				},
				"취소": function() {
					$( this ).dialog( "close" );
				}
			}
			
		}).dialog( "open" );
	});
	
	
	$( "#add-form").submit( function(event) {
		event.preventDefault();
		
		var name = $("input[name]").val();
		var content = $("textarea[name='content']").val();
		var pass = $("input[type='password']").val();
		// ajax insert
		
		$.ajax({
			url: "${pageContext.request.contextPath }/api/guestbook",
			type: "post",
			dataType: "json",
			data:"?a=ajax-add&name=" + name + "&content=" + content + "&password=" + pass,
			success: function( response ){
				if( response.result != "success" ){
					console.error( response.message );
					return;
				}
				
				// rendering
				$( response.data ).each( function(index, vo) {
					render( vo, true );
				});
				
			},
			error : function( jqXHR, status, e ){
				console.error( status + ":" + e );
			}
		});
		
	});
	
	$(window).scroll( function() {
		var $window = $(this);
		var scrollTop = $window.scrollTop();
		var windowHeight = $window.height();
		var documentHeight = $( document ).height();
		
		// 스크롤 바가 바닥까지 왔을 때( 20px  덜 왔을 때)
		if( scrollTop + windowHeight  + 20 > documentHeight ){
			// console.log( "call fetchList" );
			fetchList();
		}
	});
	
	$( "#btn-fetch").click(function(){
		fetchList();
	});
	
	// 1번째 가져오기.
	fetchList();
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form id="add-form" action="" method="post">
					<table class="main">
						<tr class="ai">
							<td id="b1">이름</td><td><input type="text" name="name"></td>
							<td id="b2">비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td id="b3" colspan=4><textarea name="content" ></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul id="list-guestbook">
				</ul>
				<button style="margin-top:20px" id="btn-fetch">가져오기</button>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
	
	<div id="dialog" title="Basic dialog">
  		<p>비밀번호를 삭제?</p>
  		<form id="add-form" method="post" action="/mysite3/gb">
			<input id="m_a" type="hidden" name="a" value="ajax-delete">
			<input id="m_no" type='hidden' name="no" >
			<label>비밀번호</label>
			<input id="m_password" type="password" name="password">
			<input type="submit" value="확인">
		</form>
	</div>
</body>
</html>