<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="com.cleverpy.beans.PeliculaBean"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de peliculas</title>
</head>

<%
ArrayList<PeliculaBean> peliculas = (ArrayList<PeliculaBean>)session.getAttribute("listaPeliculasFiltradas");
int contador = 1;
%>

<script type="text/javascript">

	function buscarPeliculas(){
		// Llamamos al metodo que busca las peliculas con los parametros de los input
		var url = "buscarPeliculas";
		// Si se han metido datos de nombre y ano
		if(document.getElementById('ano_pelicula').value != "" && document.getElementById('titulo_pelicula').value != "")
			url += "?nombre="+document.getElementById('titulo_pelicula').value+"&?ano="+document.getElementById('ano_pelicula').value;
		// Si se han metido datos de nombre
		else if(document.getElementById('titulo_pelicula').value != "")
			url += "?nombre="+document.getElementById('titulo_pelicula').value;
		// Si se han metido datos de ano
		else if(document.getElementById('ano_pelicula').value != "")
			url += "?ano="+document.getElementById('ano_pelicula').value;
		window.location.href = url;
		
	}

</script>

<body>

<div style="font-family:Arial">
	<h1>Buscador de peliculas del Videoclub</h1>
	
	Titulo: <input type="text" id="titulo_pelicula" name="titulo_pelicula">
	Año: <input type="text" id="ano_pelicula" name="ano_pelicula">
	<br><br>
	<input type="button" value="Buscar" onclick="buscarPeliculas()">
	<br><br>
	<table border=2>
	
		<tr>
			<td>Nº Pelicula</td>
			<td>ID</td>
			<td>Nombre</td>
			<td>Año</td>
		</tr>
		
	<!-- Mostramos las peliculas almacenadas en la variable de sesion -->
	<%
	if(peliculas != null && !peliculas.isEmpty()){
		for(PeliculaBean p : peliculas){ 
			contador++;
		%>
			
			<tr>
				<td><%=contador%></td>
				<td><%=p.getId()%></td>
				<td><%=p.getNombre()%></td>
				<td><%=p.getAno()%></td>
			</tr>
			
		<%
		}
	}
	%>
	
	</table>
	
</div>

</body>
</html>