<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 500px;
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: gray;
	font-style-color: black;
	height: 100%;
	border-radius: 5px;
	height: 100%
}

/* Set black background color, white text and some padding */
nav {
	background-color: blue;
	color: white;
	padding: 15px;
}

a { /*사이드 글자 색상*/
	color: black;
}
/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

.navbar {
	padding-top: 150px;
	/* height: 100px; */
}

.container-fluid {
	/* background-color: white; */
	
}
</style>
<script>
$( document ).ready(function() {
	/* $("#btn").bind("click",function(){
		alert('asdf');
	}); */
	function getContextPath() {
		var hostIndex = location.href.indexOf( location.host ) + location.host.length;
		return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	};

	$("#btn").bind("click",function(){
		if($('#bookCode').val()==""){
			alert('값입력해요');
		}
	    $.ajax({
	        url : getContextPath()+"/getRental",
	        type: "POST",
	        data : { "bookCode" : $("#bookCode").val() },
	        success : function(responseData){
	            var data = JSON.parse(responseData);
	            if(!data){
	                alert("실패");
	                return false;
	            }
	            $("#bookName").val(data.bookName);
	            $("#memberName").val(data.memberName);
	            $("#totalPrice").val(data.totalPrice);
	            $("#rentalPayment").val(data.rentalPayment);
	            $("#willPay").val(data.willPay);
	            $("#rentalCode").val(data.rentalCode);
	        }
	    });
	});
});
	
</script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="#">Main</a></li>
					<li><a href="#">도서관리</a></li>
					<li class="active"><a href="#">대여관리</a></li>
					<li><a href="#">회원관리</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
							Login</a></li>
				</ul>
			</div>
		</div>
	</nav>


	<div class="container-fluid text-green">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<ul class="nav nav-pills nav-stacked">
					<li><a href="<c:url value="/bookRent"/>">대여</a></li>
					<li class="active"><a href="<c:url value="/bookReturn"/>">반납</a></li>

				</ul>
				<br>
			</div>

			<div class="col-sm-9">
				<h4>
					<small>도서반납</small>
				</h4>
				<form action="<c:url value="/bookReturn"/>" method="post">
					<input type="hidden" id="rentalCode" name="rentalCode" value="">
					<div>
						도서코드: <input id="bookCode" type="text" name="bookCode" />
						<button id="btn" type="button" class="btn btn-info">조회</button>
					</div>
					<div>
						도서명: <input id="bookName" type="text" name="bookName" />
					</div>
					<div>
						회원이름: <input id="memberName" type="text" name="memberName" />
					</div>
					<div>
						총요금: <input id="totalPrice" type="text" name="totalPrice" />
					</div>
					<div>
						받은금액: <input id="rentalPayment" type="text" name="rentalPayment" />
					</div>
					<div>
						받을금액: <input id="willPay" type="text" name="willPay" />
					</div>
					<!-- <input type="submit" value="대여" />
							<input type="reset"	value="초기화" /> -->
					<input type="submit" class="btn btn-success btn-large" value="반납" />
					<input type="reset" class="btn btn-danger btn-large" value="초기화" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>
