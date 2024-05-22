<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>대시보드</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
	<style>
		.container{
			max-width : 434px;
		}
	</style>
</head>

<body>
<div class="container" align="center">
    <h3 class="form-signin-heading text-center mb-5">💚빅데이터반 대시보드💚</h3>

	<h4 class="overview-normalize">접속 아이디</h4>
	<p id='loginid'>
	${userId}
	</p>
	<hr/>
    <h4 class="overview-normalize">역할</h4>
	<p id='pw'>
	${role}
	</p>
    <hr/>
    <h4 class="overview-normalize">이동 권한 확인</h4>
    <p>
        <button onclick="adminPage('${cpath}/admin')" class="btn btn-sm btn-success">관리자페이지(관리자만)</button>
        <button onclick="userPage('${cpath}/user')" class="btn btn-sm btn-info">유저페이지(유저만)</button>
    </p>
    <hr/>
    <form method="post" action="${cpath}/logout">
        <button class="btn btn-sm btn-danger btn-block" type="submit" id="logoutbtn">로그아웃</button>
    </form>

</div>
</body>
<script>
function adminPage(url) {
    // myData.role 값을 가져옴
    var role = "${myData.role}";

    // 만약 역할이 관리자인 경우
    if (role === "admin") {
        // 관리자 페이지로 이동
        location.href = url;
    } else {
        // 아닌 경우 경고창 표시
        alert("관리자만 들어갈 수 있는 페이지입니다.");
    }
}

function userPage(url) {
    // myData.role 값을 가져옴
    var role = "${myData.role}";

    // 만약 역할이 일반인 경우
    if (role === "user") {
        // 일반 페이지로 이동
        location.href = url;
    } else {
        // 아닌 경우 경고창 표시
        alert("유저만 들어갈 수 있는 페이지입니다.");
    }
}
</script>
</html>