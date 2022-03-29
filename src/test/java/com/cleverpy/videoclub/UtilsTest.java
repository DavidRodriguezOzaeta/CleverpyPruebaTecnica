package com.cleverpy.videoclub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cleverpy.beans.PeliculaBean;

class UtilsTest {

	@Test
	void testIsAnoPeliculaValidoAnoCorrecto() {
		PeliculaBean pelicula = new PeliculaBean();
		pelicula.setAno("1992");
		assertTrue(Utils.isAnoPeliculaValido(pelicula), "El metodo isAnoPeliculaValido no ha validado bien un ano correcto");
	}
	
	@Test
	void testIsAnoPeliculaValidoAnoInvalido() {
		PeliculaBean pelicula = new PeliculaBean();
		pelicula.setAno("12");
		assertFalse(Utils.isAnoPeliculaValido(pelicula), "El metodo isAnoPeliculaValido ha validado bien un ano invalido");
	}
	
	@Test
	void testIsAnoPeliculaValidoAnoNull() {
		PeliculaBean pelicula = new PeliculaBean();
		assertFalse(Utils.isAnoPeliculaValido(pelicula), "El metodo isAnoPeliculaValido ha validado bien un ano nulo");
	}
	
	@Test
	void testIsAnoPeliculaValidoAnoString() {
		PeliculaBean pelicula = new PeliculaBean();
		pelicula.setNombre("La Jungla de Cristal");
		pelicula.setAno("HOLA");
		assertFalse(Utils.isAnoPeliculaValido(pelicula), "El metodo isAnoPeliculaValido ha validado bien un ano que contenia letras");
	}

}
