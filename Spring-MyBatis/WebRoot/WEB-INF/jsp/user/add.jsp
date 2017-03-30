<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户注册</title>
  </head>
  
  <body>
    <form method = "post" action="<c:url value="/user.html"/>">
    	<table>
    		<tr>
    			<td>姓名：</td>
    			<td><input type="text" name="name"/> </td>
    		</tr>
    		<tr>
    			<td>地址：</td>
    			<td><input type="text" name="location"/></td>
    		</tr>
    		<tr>
    			<td colspan="2"><input type="submit" name="提交"/></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
