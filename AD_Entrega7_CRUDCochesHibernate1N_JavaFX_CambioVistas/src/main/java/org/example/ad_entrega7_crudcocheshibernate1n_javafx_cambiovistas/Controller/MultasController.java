package org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.DAO.MultaDAOImpl;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Coche;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Model.Multa;
import org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas.Util.ComprobacionesAlertasCambioEscena;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class MultasController implements Initializable {


    //ATRIBUTOS
    @FXML
    private Button atrasBoton;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colIdMulta;

    @FXML
    private TableColumn<?, ?> colPrecio;

    @FXML
    private DatePicker fechaDatePicker;

    @FXML
    private TextField idMultaTF;

    @FXML
    private TextField precioTF;

    @FXML
    private TextField matriculaTF;

    @FXML
    private TableView<Multa> tableViewMultas;


    Coche cocheSelected;

    MultaDAOImpl multasDAO = new MultaDAOImpl();

    ObservableList<Multa> listadoMultas;


    //MÉTODOS
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TABLEVIEW --> inicializo las columnas
        colIdMulta.setCellValueFactory(new PropertyValueFactory<>("id_multa"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }//initialize



    @FXML
    void onElegirMultaClick(MouseEvent event) {
        Multa multaSeleccionada = tableViewMultas.getSelectionModel().getSelectedItem(); //obtengo la multa seleccionada en el tableView y la guardo en la variable multaSeleccionada de tipo Multa
        if (multaSeleccionada != null) { //si hay una multa seleccionada
            //pongo los datos en los TextFields
            idMultaTF.setText(String.valueOf(multaSeleccionada.getId_multa())); //cargo el id de la multa en el textField
            precioTF.setText(String.valueOf(multaSeleccionada.getPrecio())); //cargo el precio de la multa en el textField
            fechaDatePicker.setValue(multaSeleccionada.getFecha()); //cargo la fecha de la multa en el datePicker
        }//if
    }//onElegirCocheClick



    @FXML
    void onInsertarClick(ActionEvent event) {
        String matricula = matriculaTF.getText();
        String precio = precioTF.getText();
        LocalDate fecha = fechaDatePicker.getValue();

        if (precio.isEmpty() || fecha == null) {
            ComprobacionesAlertasCambioEscena.mostrarAlerta("Error. Rellene todos los campos");
            return;
        } else if (!ComprobacionesAlertasCambioEscena.esPrecioValido(precio)) {
            ComprobacionesAlertasCambioEscena.mostrarAlerta("Error. El precio introducido no es válido (separado por un '.' solo puede tener dos decimales).");
            return;
        } else {
            Multa multa = new Multa(matricula, precio, fecha);
            if (multasDAO.insertarMulta(multa) > 0) {
                actualizarTabla(); //llamo al método que actualiza la tabla después de haber realizado la inserción
                onLimpiarClick(event); //limpio los campos
            } else {
                ComprobacionesAlertasCambioEscena.mostrarAlerta("No se ha podido agregar el coche. Inténtelo de nuevo.");
            }//if-else
        }//if-elseif-else
    } //onInsertarClick



    @FXML
    void onActualizarClick(ActionEvent event) {
        Multa multaSeleccionada = tableViewMultas.getSelectionModel().getSelectedItem(); //obtengo la multa seleccionada en el tableView y la guardo en la variable multaSeleccionada de tipo Multa

        //compruebo si hay alguna multa seleccionada
        if (multaSeleccionada == null) {
            ComprobacionesAlertasCambioEscena.mostrarAlerta("No se ha seleccionado ninguna multa.");
            return; //si no hay multa seleccionada, salgo del método
        }//if

        String precio = precioTF.getText();
        LocalDate fecha = fechaDatePicker.getValue();

        //compruebo el precio
        if (!ComprobacionesAlertasCambioEscena.esPrecioValido(precio)) {
            ComprobacionesAlertasCambioEscena.mostrarAlerta("Debe introducir un precio valido.");
            return;
        } else{ //se ha introducido un precio válido
            //actualizo los campos del coche seleccionado
            multaSeleccionada.setPrecio(precio);
            multaSeleccionada.setFecha(fecha);

            //compruebo que se ha podido actualizar
            if (multasDAO.actualizarMulta(multaSeleccionada) > 0) {
                actualizarTabla(); //actualizo la tabla
                onLimpiarClick(event); //limpio los campos
            } else {
                ComprobacionesAlertasCambioEscena.mostrarAlerta("Error al actualizar los datos de la multa.");
            }//if-else
        }//if-else
    }//onActualizarClick



    @FXML
    void onBorrarClick(ActionEvent event) {
        Multa multaSeleccionada = tableViewMultas.getSelectionModel().getSelectedItem(); //obtengo la multa seleccionada en el tableView y la guardo en la variable multaSeleccionada de tipo Multa

        if (multaSeleccionada != null && multasDAO.eliminarMulta(multaSeleccionada) > 0) {
            actualizarTabla(); //actualizo la tabla
            onLimpiarClick(event); //limpio todos los campos
        } else ComprobacionesAlertasCambioEscena.mostrarAlerta("Debe seleccionar una multa.");
    }//onBorrarClick



    @FXML
    void onLimpiarClick(ActionEvent event) {
        idMultaTF.clear();
        precioTF.clear();
        fechaDatePicker.setValue(null);

        //si presiono sobre el boton de limpiar que se deseleccione la multa que estaba seleccionada porque si posteriormente le doy a borrar, por ejemplo, se me borra aunque no estés los datos puestos en los TF
        tableViewMultas.getSelectionModel().clearSelection();
    }//onLimpiarClick



    @FXML
    void onVoloverAtrasClick(ActionEvent event) {
        ComprobacionesAlertasCambioEscena.cambiarEscena(atrasBoton, "main.fxml");
    }//onVoloverAtrasClick



    //método para actualizar la tabla después de realizar cambios
    private void actualizarTabla() {
        List<Multa> listarCoches = multasDAO.mostrarMultasCocheSeleccionado(cocheSelected); //obtengo la lista de multas del coche seleccionado
        listadoMultas = FXCollections.observableArrayList(listarCoches); //convierto a ObservableList
        tableViewMultas.setItems(listadoMultas); //actualizo el TableView con la nueva lista
    }//actualizarTabla



    public void datosCocheMulta(Coche coche) {
        cocheSelected = coche;
        matriculaTF.setText(coche.getMatricula()); //pongo la matricula del coche seleccionado en la clase MainController pasado a través del método del cambio de escena

        //muestro las listaMultas del coche seleccionado en la tabla
        List<Multa> listaMultas = multasDAO.mostrarMultasCocheSeleccionado(cocheSelected);
        listadoMultas = FXCollections.observableArrayList(listaMultas);
        tableViewMultas.setItems(listadoMultas);
    }//datosCocheMulta
}//class