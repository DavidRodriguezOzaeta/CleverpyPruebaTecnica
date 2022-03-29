package com.cleverpy.hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    
    static {
        try {
            // Creamos la SessionFactory desde el hibernate.cfg.xml
            sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
            
        } catch (Throwable ex) {
            // En caso de error lo mostramos
            System.err.println("La creacion de SessionFactory ha fallado" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	
}
