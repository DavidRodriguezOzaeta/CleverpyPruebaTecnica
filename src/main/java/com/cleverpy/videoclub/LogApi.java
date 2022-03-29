package com.cleverpy.videoclub;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogApi {

	Logger log;
	
	/*
	 * Este objeto se utiliza para crear un log personalizado de la clase que lo llame
	 * y asi saber que clase esta sacando los logs
	 */
	public LogApi(Class clase) {
		log = Logger.getLogger(clase);
		BasicConfigurator.configure();
		// Con esta linea evitamos que salgan muchos de los molestos logs de Hibernate
	    Logger.getLogger("org.hibernate").setLevel(Level.WARN);
	}
	
}
