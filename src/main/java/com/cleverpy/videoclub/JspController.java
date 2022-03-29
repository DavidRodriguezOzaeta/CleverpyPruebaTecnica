package com.cleverpy.videoclub;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cleverpy.beans.PeliculaBean;

@Controller
public class JspController {

	LogApi logApi = new LogApi(JspController.class);
	
	/*
	 * Este endpoint se encarga de mostrar las peliculas de la base de datos con paginacion
	 */
	@RequestMapping("/peliculas")
    public String peliculas(HttpSession session) {
		
		// Obtenemos la lista de peliculas
		List<PeliculaBean> peliculas = PeliculasController.listarPeliculas();
		
		// Seteamos la lista en sesion para recuperarla en el front
		session.setAttribute("listaPeliculas", peliculas);
		logApi.log.info("Se entra en /peliculas");
        return "paginacion";
    }
	
	/*
	 * Este endpoint se encarga de mostrar un buscador de peliculas por titulo y ano
	 */
	@RequestMapping("/peliculasFiltradas")
    public String peliculasFiltradas() {
		
		logApi.log.info("Se entra en /peliculasFiltradas");
        return "peliculasFiltradas";
    }
	
	@GetMapping("/buscarPeliculas")
	public String buscarPeliculas(HttpSession session, String nombre, String ano) {
		
		List<PeliculaBean> peliculas = PeliculasController.listarPeliculasConcretas(nombre, ano);
		
		session.setAttribute("listaPeliculasFiltradas", peliculas);
		
		logApi.log.info("Se han buscado peliculas con los parametros: Nombre "+nombre+", Ano: "+ano);
        return "peliculasFiltradas";
    }
	
}
