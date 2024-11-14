package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.DAO;

import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Coche;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Multa;

import java.util.List;

public interface MultaDAO {
    List<Multa> mostrarMultasCocheSeleccionado(Coche cocheSeleccionado); //método para mostrar todos los coches almacenados en la base de datos
    int insertarMulta(Multa insertarMulta); //método para insertar un nuevo coche
    int eliminarMulta(Multa eliminarMulta); //método para eliminar un coche
    int actualizarMulta(Multa actualizarMulta); //método para actualizar un coche
}//class