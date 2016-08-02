<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="material.min.css" />
<link rel="stylesheet" href="styles.css" />

<script type="text/javascript" charset="utf8" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
var alist;
var path;

$(document).ready(function() {
	$("#user-account").change(function() {
		var val = document.getElementById("user-account").value;
		//var text = document.getElementById("user-account").text;
		//alert("val:"+val+"|"+text);
		path = "";
	});
	//alert("loginInfo:"+loginInfo);
});


function init() {
	var loginInfo = "userName="+"<c:out value="${loginInfo}" />";
	//alert(loginInfo);
	$.ajax({
		type:"GET",
		url:"getUserAccounts",
		data:loginInfo,
		dataType:"json",
		success:function(data) {
			if(data.status==true) {
				//alert("success1");
				alist = data.result;
				alert("alist"+alist.length);
				var selObj = document.getElementById("user-account");
				AddAccountSelect(selObj, alist);
			} else
				alert("status=false");
		},
		error:function() {
			alert("ajax error");
		}
	});
}

function AddAccountSelect(selObj, data) {
	for(var i=0;i<data.length;i++) {
		selObj.options.add(new Option(data[i].accountNumber,data[i].id))
	}
}
</script>

</head>
<body onload="init();">
	<%@include file="/menu.jsp"%>
	<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
		<%@include file="/header.jsp"%>
		<main class="mdl-layout__content">
			<div>
				<h1>账户挂失</h1>
				
				<select id="user-account"></select>
				<button ></button>
			
			</div>
			<%@include file="/footer.jsp"%>
		</main>
	</div>
	<script src="material.min.js"></script>
</body>
</html>