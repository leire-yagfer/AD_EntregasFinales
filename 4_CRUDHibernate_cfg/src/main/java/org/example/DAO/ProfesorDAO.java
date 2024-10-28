package org.example.DAO;

import org.example.Model.Profesor;
import org.hibernate.Session;

import java.util.List;

public class ProfesorDAO {
    //todas las operaciones que hago sobre la tabla profesores --> CRUD

    public static void insertarProfesor(Session sesion, Profesor nuevoProfesor){
        //insertar en la base de datos --> cuando se ponía "begin nombre ...(desarrollo)... end nombre;)
        //se inserta con el .save(lo que quiero insertar)
        sesion.beginTransaction();
        sesion.save(nuevoProfesor);
        sesion.getTransaction().commit(); //commit --> confirmo la transacción
    }//nuevoProfesor


    public static void modificarProfesor(Session sesion, Profesor modificacionProfesor){
        sesion.beginTransaction();
        sesion.update(modificacionProfesor);
        sesion.getTransaction().commit();
    }//modificarProfesor


    public static void eliminarProfesor(Session sesion, Profesor eliminarProfesor){
        sesion.beginTransaction();
        sesion.delete(eliminarProfesor);
        sesion.getTransaction().commit();
    }//eliminarProfesor


    public static void listarProfesores(Session sesion){ //solo necesito la sesión
        //consulta
        List<Profesor> listaProfesores = sesion.createQuery("from Profesor").getResultList();

        for (Profesor p : listaProfesores) {
            //formato clasico
            System.out.println(p.toString());
        }//for

        //el for equivale a: list.forEach(System.out::println);
    }//listarProfesores


    //método que me devuelve un profesor
    public static Profesor buscarProfesor(Session sesion, int idProfesor){
        Profesor p;

        p = (Profesor)sesion.get(Profesor.class,102); //casteo a tipo profesor
        System.out.println("Datos del profesor buscado --> ID: " + p.getId() + " - NOMBRE: " + p.getNombre() + " - APPELLIDO1: " + p.getApe1() + " - APPELLIDO2: " + p.getApe2());

        return p;
    }//buscarProfesor
}//class