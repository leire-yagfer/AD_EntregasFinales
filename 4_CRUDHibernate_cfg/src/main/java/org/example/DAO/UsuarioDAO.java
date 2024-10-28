package org.example.DAO;

import org.example.Model.Profesor;
import org.example.Model.Usuario;
import org.hibernate.Session;

import java.util.List;

public class UsuarioDAO {
    //todas las operaciones que hago sobre la tabla profesores --> CRUD

    public static void insertarUsuario(Session sesion, Usuario nuevoUsuario){
        //insertar en la base de datos --> cuando se ponía "begin nombre ...(desarrollo)... end nombre;)
        //se inserta con el .save(lo que quiero insertar)
        sesion.beginTransaction();
        sesion.save(nuevoUsuario);
        sesion.getTransaction().commit(); //commit --> confirmo la transacción
    }//insertarUsuario


    public static void modificarUsuario(Session sesion, Usuario modificacionUsuario){
        sesion.beginTransaction();
        sesion.update(modificacionUsuario);
        sesion.getTransaction().commit();
    }//modificarUsuario


    public static void eliminarUsuario(Session sesion, Usuario eliminarUsuario){
        sesion.beginTransaction();
        sesion.delete(eliminarUsuario);
        sesion.getTransaction().commit();
    }//eliminarUsuario


    public static void listarUsuarios(Session sesion){ //solo necesito la sesión
        //consulta
        List<Usuario> listaUsuarios = sesion.createQuery("from Usuario").getResultList();

        for (Usuario u : listaUsuarios) {
            //formato clasico
            System.out.println(u.toString());
        }//for

        //el for equivale a: list.forEach(System.out::println);
    }//listarUsuarios


    //método que me devuelve un profesor
    public static Usuario buscarUsuario(Session sesion, int idUsuario){
        Usuario u;

        u = (Usuario)sesion.get(Usuario.class,idUsuario); //casteo a tipo profesor
        System.out.println("Datos del usuario buscado --> ID: " + u.getId() + " - Fisrtname: " + u.getFirstname() + " - Lastname: " + u.getLastname() + " - Date: " +u.getDob());

        return u;
    }//buscarProfesor
}
