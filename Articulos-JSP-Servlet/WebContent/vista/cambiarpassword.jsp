<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cambiar Password</title>

</head>
<body>

<c:out value = "${ad}"/>


<form action="adminArticulo?action=change_password"  method="post">
  Inserte nueva contraseña <input type="password" name="fname"><br>
  Insertela de nuevo su nueva contraseña <input type="password" name="fname2">
  <input type="submit" value="Submit">
</form>
	
	
</body>
</html>