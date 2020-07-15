<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar Artículos</title>
</head>
<body>
	<h1>Lista  Artículos</h1>
	<table>
		<tr>
			<td><a href="adminArticulo?action=index" >Ir al menú</a> </td>
		</tr>
	</table>
	
	<table border="1" width="100%">
		<tr>
		 <td> ID</td>
		 <td> CODIGO</td>
		 <td> NOMBRE</td>
		 <td>DESCRPICION</td>
		 <td>EXISTENCIA</td>
		 <td>PRECIO</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="articulo" items="${lista}">
			<tr>
				<td><c:out value="${articulo.id}"/></td>
				<td><c:out value="${articulo.codigo}"/></td>
				<td><c:out value="${articulo.nombre}"/></td>
				<td><c:out value="${articulo.descripcion}"/></td>
				<td><c:out value="${articulo.existencia}"/></td>
				<td><c:out value="${articulo.precio}"/></td>
				<td><a href="adminArticulo?action=showedit&id=<c:out value="${articulo.id}" />">Editar</a></td>
				<td><a href="adminArticulo?action=eliminar&id=<c:out value="${articulo.id}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
	
	<h1>Establecer fecha de apertura y cierre de matriculas</h1><br>
	<p>Fecha inicio: <fmt:formatDate pattern = "yyyy-MM-dd" value = "${dateOpen}" /></p>
	<p> Fecha limite:<fmt:formatDate pattern = "yyyy-MM-dd" value = "${dateClose}" /></p>
	<h2>Fecha de apertura:</h2><br>
	<form action="adminArticulo?action=set_date" method="post">
 	 <label for="dia">dia</label>
  		<select id="dia" name="dia">
    		<option value="01">1</option>
    		<option value="02">2</option>
    		<option value="03">3</option>
    		<option value="04">4</option>
    		<option value="05">5</option>
    		<option value="06">6</option>
    		<option value="07">7</option>
    		<option value="08">8</option>
    		<option value="09">9</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="12">12</option>
    		<option value="13">13</option>
    		<option value="14">14</option>
    		<option value="15">15</option>
    		<option value="16">16</option>
    		<option value="17">17</option>
    		<option value="18">18</option>
    		<option value="19">19</option>
    		<option value="20">20</option>
    		<option value="21">21</option>
    		<option value="22">22</option>
    		<option value="23">23</option>
    		<option value="24">24</option>
    		<option value="25">25</option>
    		<option value="26">26</option>
    		<option value="27">27</option>
    		<option value="28">28</option>
    		<option value="29">29</option>
    		<option value="30">30</option>
    		<option value="31">31</option>
  		</select>
  	<label for ="mes">mes</label>
  		<select id = "mes" name="mes">
  			<option value = "01">01</option>
  			<option value = "02">02</option>
  			<option value = "03">03</option>
  			<option value = "04">04</option>
  			<option value = "05">05</option>
  			<option value = "06">06</option>
  			<option value = "07">07</option>
  			<option value = "08">08</option>
  			<option value = "09">09</option>
  			<option value = "10">10</option>
  			<option value = "11">11</option>
  			<option value = "12">12</option>
  		</select>
  	year xd:<input type="text" name="year"><br>
  	
  	<label for="dia2">dia</label>
  		<select id="dia2" name="dia2">
    		<option value="01">1</option>
    		<option value="02">2</option>
    		<option value="03">3</option>
    		<option value="04">4</option>
    		<option value="05">5</option>
    		<option value="06">6</option>
    		<option value="07">7</option>
    		<option value="08">8</option>
    		<option value="09">9</option>
    		<option value="10">10</option>
    		<option value="11">11</option>
    		<option value="12">12</option>
    		<option value="13">13</option>
    		<option value="14">14</option>
    		<option value="15">15</option>
    		<option value="16">16</option>
    		<option value="17">17</option>
    		<option value="18">18</option>
    		<option value="19">19</option>
    		<option value="20">20</option>
    		<option value="21">21</option>
    		<option value="22">22</option>
    		<option value="23">23</option>
    		<option value="24">24</option>
    		<option value="25">25</option>
    		<option value="26">26</option>
    		<option value="27">27</option>
    		<option value="28">28</option>
    		<option value="29">29</option>
    		<option value="30">30</option>
    		<option value="31">31</option>
  		</select>
  	<label for ="mes2">mes</label>
  		<select id = "mes2" name="mes2">
  			<option value = "01">01</option>
  			<option value = "02">02</option>
  			<option value = "03">03</option>
  			<option value = "04">04</option>
  			<option value = "05">05</option>
  			<option value = "06">06</option>
  			<option value = "07">07</option>
  			<option value = "08">08</option>
  			<option value = "09">09</option>
  			<option value = "10">10</option>
  			<option value = "11">11</option>
  			<option value = "12">12</option>
  		</select>
  	year xd:<input type="text" name="year2">
  	
  	<input type="submit" value = "Submit">
	</form>

<br>
<a href="adminArticulo?action=page_password" >Cambiar contrasena</a>

</body>
</html>