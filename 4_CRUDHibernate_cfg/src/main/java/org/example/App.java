package org.example;


import org.example.DAO.ProfesorDAO;
import org.example.DAO.UsuarioDAO;
import org.example.Model.Profesor;
import org.example.Model.Usuario;
import org.example.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.time.LocalDate;

public class App {

    static SessionFactory factory = null;

    public static void main(String[] args) {
        //SessionFactoryes una instancia que creará objetos de tipo Sessiono(fabrica sessiones).

        SessionFactory factory = HibernateUtil.getSessionFactory();

        //Ahora que ya tenemos el objeto SessionFactory podemos obtener la Session
        //para trabajar con Hibernate.

        //Una sesión se utiliza para obtener una conexión física con una base de datos.
        //El objeto Session es liviano y está diseñado para ser instanciado
        //cada vez que se necesita una interacción con la base de datos.
        //Los objetos persistentes se guardan y recuperan a través de un objeto de sesión.

        //Los objetos de la sesión no deben mantenerse abiertos durante mucho tiempo
        //porque generalmente no son seguros para subprocesos y deben crearse y destruirse
        //según sea necesario. La función principal de la sesión
        //es ofrecer, crear, leer y eliminar operaciones para instancias
        //de clases de entidades asignadas.

        Session session = HibernateUtil.getSession(); //conecto mi aplicacion con la base de datos

        //PROFESOR
        System.out.println("----- PROFESORES -----");
        //me creo 3 objetos de la clase Profesor para posteriormente insertarlo en la BD
        Profesor profesor1 = new Profesor(102, "A", "González", "Oltra");
        Profesor profesor2 = new Profesor(103, "B", "Sanchez", "Velasco");
        Profesor profesor3 = new Profesor(104, "C", "Colinas", "Alonso");


            //INSERTAR
        //llamo al método para insertar profesores en la BD
        System.out.println("--- Inserción: ---");
        ProfesorDAO.insertarProfesor(session, profesor1);
        ProfesorDAO.insertarProfesor(session, profesor2);
        ProfesorDAO.insertarProfesor(session, profesor3);


            //LEER = CONSULTAR --> veo los profesores añadidos
        System.out.println("--- Listado de profesores: ---");
        ProfesorDAO.listarProfesores(session);


            //BUSCAR POR ID
        System.out.println("--- Búsqueda: ---");
        Profesor profesor_aux = ProfesorDAO.buscarProfesor(session, 102); //me creo un Profesor llamado profesor_aux en el que almaceno los datos del Profesor que busco por ID


            //MODIFICAR
        System.out.println("--- Modificación: ---");
        System.out.println("-> Datos del profesor antes de modificarse: \n     " + profesor_aux.toString());

        //cambio el nombre
        profesor_aux.setNombre("Alfredo");

        //ejecuto el cambio
        ProfesorDAO.modificarProfesor(session, profesor_aux);

        System.out.println("-> Datos del profesor después de modificarse: \n     " + profesor_aux.toString());


            //LEER = CONSULTAR --> veo las modificaciones
        System.out.println("--- Listado de profesores: ---");
        ProfesorDAO.listarProfesores(session);


            //BORRAR
        System.out.println("--- Eliminado: ---");
        System.out.println("-> Datos del profesor a eliminar: \n    " + profesor2.toString());
        //llamo al método para borrar profesores en la BD
        ProfesorDAO.eliminarProfesor(session, profesor2);


            //LEER = CONSULTAR --> veo el listado final
        System.out.println("--- Listado de profesores: ---");
        ProfesorDAO.listarProfesores(session);
        System.out.println("\n\n");





        //USUARIO
        System.out.println("----- USUARIOS -----");
        //me creo 3 objetos de la clase Usuario para posteriormente insertarlo en la BD
        //para poder usar el tipo de dato Date tengo que castear la fecha
        Usuario usuario1 = new Usuario(202, "A", "A", LocalDate.parse("2000-01-01"));
        Usuario usuario2 = new Usuario(203, "B", "B", LocalDate.parse("2000-02-02"));
        Usuario usuario3 = new Usuario(204, "C", "C", LocalDate.parse("2000-03-03"));


            //INSERTAR
        System.out.println("--- Inserción: ---");
        //llamo al método para insertar profesores en la BD
        UsuarioDAO.insertarUsuario(session, usuario1);
        UsuarioDAO.insertarUsuario(session, usuario2);
        UsuarioDAO.insertarUsuario(session, usuario3);


            //LEER = CONSULTAR --> veo los uduarios insertados
        System.out.println("--- Listado de usuarios: ---");
        UsuarioDAO.listarUsuarios(session);


            //BUSCAR POR ID
        System.out.println("--- Búsqueda: ---");
        Usuario usuario_aux = UsuarioDAO.buscarUsuario(session, 202); //me creo un Usuario llamado usuario_aux en el que almaceno los datos del Usuario que busco por ID


            //MODIFICAR
        System.out.println("--- Modificación: ---");
        System.out.println("-> Datos del profesor antes de modificarse: \n     " + usuario_aux.toString());
        //cambio el nombre
        usuario_aux.setFirstname("NombreCambiado");

        //ejecuto el cambio
        UsuarioDAO.modificarUsuario(session, usuario_aux);
        System.out.println("-> Datos del usuario después de modificarse: \n     " + usuario_aux.toString());


            //LEER = CONSULTAR --> veo las modificaciones
        System.out.println("--- Listado de usuarios: ---");
        UsuarioDAO.listarUsuarios(session);


            //BORRAR
        System.out.println("--- Eliminado: ---");
        System.out.println("-> Datos del usuario a eliminar: \n    " + usuario2.toString());
        //llamo al método para borrar profesores en la BD
        UsuarioDAO.eliminarUsuario(session, usuario2);


            //LEER = CONSULTAR --> veo el resultado final
        System.out.println("--- Listado de usuarios: ---");
        UsuarioDAO.listarUsuarios(session);


        session.close();
        factory.close();
    }//main
}//class