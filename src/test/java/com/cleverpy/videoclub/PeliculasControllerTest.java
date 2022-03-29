package com.cleverpy.videoclub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cleverpy.beans.PeliculaBean;

class PeliculasControllerTest {

	@Test
	void testInsertarPeliculaSinNombre() {
		PeliculaBean pelicula = new PeliculaBean();
		pelicula.setAno("1999");
		assertEquals(PeliculasController.insertarPelicula(pelicula), "La pelicula a introducir debe tener un nombre", "Se ha intentado introducir una pelicula sin nombre y no se ha devuelto un mensaje de error.");
	}
	
	@Test
	void testInsertarPeliculaSinAno() {
		PeliculaBean pelicula = new PeliculaBean();
		pelicula.setNombre("La Jungla de Cristal");
		assertEquals(PeliculasController.insertarPelicula(pelicula), "La pelicula debe tener un ano valido", "Se ha introducido una pelicula sin ano y no se ha devuelto un mensaje de error.");
	}
	
	@Test
	void testInsertarPeliculaConAnoString() {
		PeliculaBean pelicula = new PeliculaBean();
		pelicula.setNombre("La Jungla de Cristal");
		pelicula.setAno("HOLA");
		assertEquals(PeliculasController.insertarPelicula(pelicula), "La pelicula debe tener un ano valido", "Se ha introducido una pelicula con ano que contenia letras y no se ha devuelto un mensaje de error.");
	}

}
