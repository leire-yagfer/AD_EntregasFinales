package org.example.Util;

/*
 * En cualquier libro o tutorial que hable sobre Hibernate acabará hablándose de la clase HibernateUtil.
 * Esta clase que debemos crearnos nosotros y que no está incluida en Hibernate,
 * contiene código estático que inicializa Hibernate y crea el objeto SessionFactory.
 * Se incluye además un método estático que da acceso al objeto SessionFactory que se ha creado.
 */

//HibernateUtil, que nos de las conexiones con la base de datos.

//De esta forma, la SessionFactory será única y compartida por todos

import org.example.Model.Profesor;
import org.example.Model.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    static SessionFactory factory = null; //factory: variable estática que contiene la instancia única de SessionFactory que se compartirá entre las diferentes partes de la aplicación.
    static {
        Configuration cfg = new Configuration(); //se crea un objeto de configuración de Hibernate.
        cfg.configure("hibernate.cfg.xml"); //carga el archivo de configuración hibernate.cfg.xml, que contiene los detalles de conexión a la base de datos, dialecto, entre otras configuraciones.
        cfg.addAnnotatedClass(Profesor.class); //se añade la clase Profesor como una entidad para ser mapeada a la base de datos.
        cfg.addAnnotatedClass(Usuario.class); //añado la clase usuario
        factory = cfg.buildSessionFactory(); //se construye la instancia de SessionFactory con la configuración provista.
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

    public static Session getSession() {
        return factory.openSession();
    }

}