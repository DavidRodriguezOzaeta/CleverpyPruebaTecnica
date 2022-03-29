package com.cleverpy.videoclub;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleverpy.beans.PeliculaBean;
import com.cleverpy.hibernate.HibernateUtils;

@RestController
// Con esta anotacion habilitamos CORS para las llamadas XHR
@CrossOrigin
@RequestMapping("/api")
public class PeliculasController {

	static LogApi logApi = new LogApi(PeliculasController.class);
	
	/*
	 * Este metodo se encarga de devolver una lista con todas las peliculas de la tabla 
	 * Peliculas  
	 */
	@GetMapping("/listarPeliculas")
	public static List<PeliculaBean> listarPeliculas(){
		
		// Creamos una sesion de Hibernate y realizamos el select de las peliculas
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PeliculaBean> peliculas = (List<PeliculaBean>)session.createQuery("from PeliculaBean").list();
        session.getTransaction().commit();
        // Una vez finalizada la transaccion cerramos la sesion y devolvemos las peliculas
        HibernateUtils.getSessionFactory().close();
        logApi.log.info("Peliculas listadas");
        return peliculas;
		
	}
	
	/*
	 * Este metodo se encarga de devolver una lista con peliculas filtradas por anos
	 * o por el titulo
	 */
	@GetMapping("/listarPeliculasConcretas")
	public static List<PeliculaBean> listarPeliculasConcretas(String nombre, String ano){
		
		List<PeliculaBean> peliculas = null;
		String hql;
		Query query = null;
		
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		query = configurarQuery(nombre, ano, query, session);
		
		// Si no se ha pasado ningun parametro para filtrar las peliculas devolvemos lista vacia
		if (query == null) {
			logApi.log.info("Realizada llamada a peliculas concretas sin parametros");
			HibernateUtils.getSessionFactory().close();
			return peliculas;
		}
		
		peliculas = query.list();
		session.getTransaction().commit();
		HibernateUtils.getSessionFactory().close();
		logApi.log.info("Peliculas concretas listadas");
		return peliculas;
		
	}

	/*
	 * Este metodo se encarga de configurar la query de peliculas filtrando por 
	 * los parametros nombre y ano
	 */
	private static Query configurarQuery(String nombre, String ano, Query query,
			Session session) {
		
		String hql;
		
		// Si recibimos titulo y ano buscamos con esos parametros, si solo recibimos uno de los dos
		// buscamos por ese parametro y si no recibimos ninguno no buscamos
		if (nombre != null && ano != null) {
			hql = "FROM PeliculaBean WHERE nombre = :nombre AND ano = :ano";
			query = session.createQuery(hql);
			query.setParameter("nombre", nombre);
			query.setParameter("ano", ano);
		}
		else if (nombre != null) {
			hql = "FROM PeliculaBean WHERE nombre = :nombre";
			query = session.createQuery(hql);
			query.setParameter("nombre", nombre);
		}
		else if (ano != null) {
			hql = "FROM PeliculaBean  WHERE ano = :ano";
			query = session.createQuery(hql);
			query.setParameter("ano", ano);
		}
		
		return query;
		
	}
	
	/*
	 * Este metodo se encarga de insertar una pelicula en la tabla Peliculas
	 */
	@PostMapping("/insertarPelicula")
	public static String insertarPelicula(@RequestBody PeliculaBean pelicula) {
		
		// Comprobamos que han metido un nombre para la pelicula
		if(pelicula.getNombre() == null || pelicula.getNombre().isEmpty())
			return "La pelicula a introducir debe tener un nombre";
		
		// Comprobamos que introducen un ano valido
		if(!Utils.isAnoPeliculaValido(pelicula))
			return "La pelicula debe tener un ano valido";
		
		// Insertamos la pelicula
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(pelicula);
        session.getTransaction().commit();
        String respuesta = "Insertada pelicula con ID "+pelicula.getId()+" y nombre "+pelicula.getNombre();
        HibernateUtils.getSessionFactory().close();
        logApi.log.info(respuesta);
        return respuesta;
        
	}
	
	
}
