package com.cleverpy.videoclub;

import com.cleverpy.beans.PeliculaBean;

public class Utils {

	/*
	 * Este metodo se encarga de devolver true o false si el ano de la pelicula recibida 
	 * es valido, es decir, esta formado por numeros y consta de 4 digitos
	 */
	public static boolean isAnoPeliculaValido(PeliculaBean pelicula) {
		
		String anoPelicula = pelicula.getAno();
		
		if(anoPelicula == null)
			return false;
		
		// Si el ano no tiene 4 digitos
		if(anoPelicula.length() != 4)
			return false;
		
		// Si el ano introducido no esta formado por numeros
        for (int i = 0; i < anoPelicula.length(); i++) {
        	// Si no es un digito
            if (!Character.isDigit(anoPelicula.charAt(i))) {
                return false;
            }
        }
		
		return true;
	}
	
}
