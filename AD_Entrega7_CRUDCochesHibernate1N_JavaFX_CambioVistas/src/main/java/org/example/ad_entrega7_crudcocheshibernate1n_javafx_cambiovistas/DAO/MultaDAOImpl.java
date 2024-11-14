package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.DAO;

import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Coche;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Multa;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MultaDAOImpl implements MultaDAO {

    //método para obtener todas las multas almacenados en la base de datos
    @Override
    public List<Multa> mostrarMultasCocheSeleccionado(Coche cocheSeleccionado) {
        Transaction transaction = null; //inicializo la transacción
        List<Multa> multas = new ArrayList<>(); //inicializo la lista de multas
        try (Session session = HibernateUtil.getSession()) { //utilizo HibernateUtil para obtener la sesión
            transaction = session.beginTransaction();
            multas = session.createQuery("from Multa WHERE matricula = :matricula", Multa.class).setParameter("matricula", cocheSeleccionado.getMatricula()).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //si la transacción no es nula, hago rollback
            System.err.println("Error al mostrar multas: " + e.getMessage()); //imprimir mensaje de error
        }
        return multas; //devuelvo la lista de coches
    }//mostrarMultasCocheSeleccionado


    //método para insertar una nueva multa en la base de datos
    @Override
    public int insertarMulta(Multa insertarMulta) {
        int semaforo = 0; //variable para controlar el estado de la operación
        Transaction transaction = null; //inicializo la transacción como nula
        try (Session session = HibernateUtil.getSession()) { //abro la sesión
            transaction = session.beginTransaction();
            session.save(insertarMulta); //guardo el coche en la base de datos
            transaction.commit();
            semaforo = 1; //operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //si la transacción no es nula, hago rollback
            System.err.println("Error al insertar coche: " + e.getMessage());
        }
        return semaforo; //devuelvo el estado de la operación
    }//insertarMulta


    //método para eliminar una multa de la base de datos
    public int eliminarMulta(Multa eliminarMulta) {
        int semaforo = 0; //variable para controlar el estado de la operación
        Transaction transaction = null; //inicializo la transacción como nula
        try (Session session = HibernateUtil.getSession()) { //abro la sesión
            transaction = session.beginTransaction();
            session.delete(eliminarMulta); //elimino la multa
            transaction.commit();
            semaforo = 1; //operación exitosa
        } catch (Exception e) { //capturo excepciones
            if (transaction != null) transaction.rollback(); //si la transacción no es nula, hago rollback
            System.err.println("Error al eliminar la multa: " + e.getMessage());
        }
        return semaforo; //devuelvo el estado de la operación
    }//eliminarMulta


    //método para actualizar una multa en la base de datos
    @Override
    public int actualizarMulta(Multa actualizarMulta) {
        int semaforo = 0; //variable para controlar el estado de la operación
        Transaction transaction = null; //inicializo la transacción como nula
        try (Session session = HibernateUtil.getSession()) { //abro la sesión
            transaction = session.beginTransaction();
            session.update(actualizarMulta); //actualizo la multa en la base de datos
            transaction.commit();
            semaforo = 1; //operación exitosa
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); //si la transacción no es nula, hago rollback
            System.err.println("Error al actualizar la multa: " + e.getMessage());
        }
        return semaforo; //devuelvo el estado de la operación
    }//actualizarMulta
}//class
