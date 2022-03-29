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

<!-- Recuperamos la lista de peliculas de la variable de sesion -->
<%
ArrayList<PeliculaBean> peliculas = (ArrayList<PeliculaBean>)session.getAttribute("listaPeliculas");

int pag = 1;
float peliculasTotales = peliculas.size();
int paginasTotales = 0;
int indice = 0;

// Si las peliculas entre 5 dan un numero con decimales las paginas tienen que ser ese numero mas uno para mostrar todas
if ((peliculasTotales/5) % 1 == 0) 
	paginasTotales = Math.round(peliculasTotales/5);
else
	paginasTotales = Math.round(peliculasTotales/5)+1;

// Obtenemos el numero de pagina
if (request.getParameter("pag") != null) {
	pag = Integer.valueOf(request.getParameter("pag"));
}
%>

<body>

<div style="font-family:Arial">
	<h1>Peliculas del Videoclub</h1>
	
	<table border=2>
	
		<tr>
			<td>Nº Pelicula</td>
			<td>ID</td>
			<td>Nombre</td>
			<td>Año</td>
		</tr>
		
	<!-- Mostramos las peliculas almacenadas en la variable de sesion -->
	<%for(int i = 0; i<5; i++){ %>
		<%
		
		// Obtenemos el numero de pelicula a mostrar de la lista
		if(pag==1)
			indice = i;
		else
			indice = i+(pag-1)*5;
		
		// Si mostramos la ultima pelicula salimos del bucle
		if(indice == peliculas.size())
			break;
		
		%>
		
		<tr>
			<td><%=indice+1%></td>
			<td><%=peliculas.get(indice).getId()%></td>
			<td><%=peliculas.get(indice).getNombre()%></td>
			<td><%=peliculas.get(indice).getAno()%></td>
		</tr>
		
	<%} %>
	
	</table>
		
	<p>Mostrando página <%=pag%> de <%=paginasTotales%></p>
	
	<!-- Si la pagina es mayor que uno mostramos el boton de anterior -->
	<%if(pag>1){%>
		<a href="peliculas?pag=<%=pag - 1%>">&lt; Anterior</a>
	<%}%>
	
	<!-- Si la pagina actual es menor a las paginas totales mostramos el boton de siguiente -->
	<%if(pag<paginasTotales){%>
		<a href="peliculas?pag=<%=pag + 1%>">Siguiente &gt;</a> 
	<%}%>
	
	
</div>

</body>
</html>