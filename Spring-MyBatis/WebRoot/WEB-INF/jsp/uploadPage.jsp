<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>请上传用户头像</title>
</head>
<body>
	<h1>
		请选择上传的头像
	</h1>
	<form action='<c:url value="/user/upload.html"></c:url>' method="post" enctype="multipart/form-data">
		<input type="text" name="name"/>
		<input type="file" name="file"/>
		<input type="submit">
	</form>
</body>
</html>