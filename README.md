# CleverpyPruebaTecnica
Prueba tecnica para Cleverpy de una API Rest que trabaja con una base de datos de peliculas

Este archivo sirve de documentación para trabajar y utilizar esta API Rest.

Antes de nada mencionar los siguientes aspectos:
- El gestor de dependencias utilizado es Maven.
- Se han cumplido todas las funcionalidades solicitadas incluidas la de permitir llamadas XHR ya que se ha configurado el CORS para ello.
- En la implementación se han cumplido todos los objetivos solicitados.
- Objetivos opcionales: Se ha cumplido el de "Testing unitario" con una serie de tests que cubren los metodos de las clases que pueden llevar a fallo. 
- Objetivos opcionales: Se ha cumplido el de "Uso de Lombok" utilizando sus anotaciones para beans.

Para que el proyecto pueda funcionar en local se ha de crear una base de datos SQL Server en la máquina en la que se ejecuta con las siguientes propiedades:
- Usuario de SQL Server Authentication llamado "usuarioSQL" con contraseña "1234".
- Una base de datos llamada "Videoclub" que tenga una tabla llamada "Peliculas" con los siguientes campos:
  ID (PRIMARY KEY NOT NULL) int
  Nombre varchar(255)
  Ano nvarchar(50)

A continuación se adjuntan las propiedades de conexión de Hibernate que apuntan a dicha base de datos:
  
<property name="connection.username">usuarioSQL</property> 
<property name="connection.password">1234</property>
<property name="hibernate.connection.driver_class">com.microsoft.sqlserver.jdbc.SQLServerDriver</property>
<property name="connection.url">jdbc:sqlserver://localhost:1433;databaseName=Videoclub</property> 
<property name="dialect">org.hibernate.dialect.SQLServerDialect</property>

Pasamos a mencionar los diferentes ENDPOINTS que pueden ser llamados desde una aplicación de llamadas HTTP como Postman, 
su método de uso y la funcionalidad que desempeñan.

- localhost:8080/listarPeliculas GET
  Se devuelve una lista en formato JSON de las películas de la tabla "Peliculas".
  
- localhost:8080/api/listarPeliculasConcretas GET
  Se devuelve una lista en formato JSON de las películas filtradas por año y titulo añadiendo al endpoint uno o varios de estos parámetros:
  &nombre=El Club de la Lucha
  &ano=1999
  Un ejemplo de la llamada con ambos parámetros sería:
  localhost:8080/api/listarPeliculasConcretas?nombre=El Club de la Lucha&ano=1995
  
- localhost:8080/api/insertarPelicula POST
  Se inserta en la tabla "Peliculas" una pelicula con los datos que se envíen por POST, un ejemplo sería enviar una petición POST con el siguiente texto en el body:
  {
    "nombre": "Willow",
    "ano": "1988"
  }
  Se introduciría la película "Willow" del año 1988 en la tabla "Peliculas" con un ID auto incrementativo y se mostraría un mensaje de que todo ha ido bien, 
  en caso de no proporcionar un nombre o proporcionar un año no válido se mostraría un error como respuesta.
  
A continuación se detallan los diferentes ENDPOINTS a los que se puede acceder a través de un navegador web para visualizar en archivos JSP la información 
solicitada por la prueba:

- http://localhost:8080/peliculas
  Se muestra una página en la que se listan todas las películas con paginación.
  
- http://localhost:8080/peliculasFiltradas
  Se muestra una página con un buscador para buscar películas por año y título.
