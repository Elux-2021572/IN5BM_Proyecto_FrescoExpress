/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.emiliolux.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.emiliolux.bean.CargoEmpleado;
import org.emiliolux.bean.Empleado;
import org.emiliolux.db.Conexion;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author emili
 */
public class EmpleadoController implements Initializable {
    private Principal escenarioPrincipal;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    
    private ObservableList<Empleado> listarEmpleados;
    private ObservableList<CargoEmpleado> listarCargoEmpleado;
    
    @FXML
    private Button btnRegresar;

    @FXML
    private TextField txtCodigoE;

    @FXML
    private TextField txtNombresE;

    @FXML
    private TextField txtApellidosE;

    @FXML
    private TextField txtSueldo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtTurno;

    @FXML
    private ComboBox cmbCodigoCargoE;

    @FXML
    private TableView tblEmpleados;

    @FXML
    private TableColumn colCodigoE;

    @FXML
    private TableColumn colNombreE;

    @FXML
    private TableColumn colApellidosE;

    @FXML
    private TableColumn colSueldo;

    @FXML
    private TableColumn colDireccion;

    @FXML
    private TableColumn colTurno;

    @FXML
    private TableColumn colCodigoCargoE;

    @FXML
    private Button btnAgregar;

    @FXML
    private ImageView imgAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ImageView imgEliminar;

    @FXML
    private Button btnEditar;

    @FXML
    private ImageView imgEditar;

    @FXML
    private Button btnReportes;

    @FXML
    private ImageView imgReportes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbCodigoCargoE.setItems(getEmpleado());
        cmbCodigoCargoE.setDisable(true);
    }    
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void cargarDatos() {
        DesactivarControles();
        tblEmpleados.setItems(getEmpleado());
        colCodigoE.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("codigoEmpleado"));
        colNombreE.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombresEmpleado"));
        colApellidosE.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellidosEmpleado"));
        colSueldo.setCellValueFactory(new PropertyValueFactory<Empleado, Double>("sueldo"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Empleado, String>("direccion"));
        colTurno.setCellValueFactory(new PropertyValueFactory<Empleado, String>("turno"));
        colCodigoCargoE.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("codigoCargoEmpleado"));
    }
    
    public void seleccionarElemento() {
        txtCodigoE.setText(String.valueOf(((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
        txtNombresE.setText(((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getNombresEmpleado());
        txtApellidosE.setText(((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getApellidosEmpleado());
        txtSueldo.setText(String.valueOf(((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getSueldo()));
        txtDireccion.setText(((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getDireccion());
        txtTurno.setText(((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getTurno());
        cmbCodigoCargoE.getSelectionModel().select(buscarCargoEmpleado(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoCargoEmpleado()));
        //cmbcodigoProveedor.getSelectionModel().select(buscarProveedor(((Productos)tblProductos.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
        //txtexistencia.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
    }
    
    
    public CargoEmpleado buscarCargoEmpleado(int codigoEmpleado){
        CargoEmpleado result=null;
        try{
           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_buscarCargoEmpleado(?)}");
           procedimiento.setInt(1, codigoEmpleado);
           
           ResultSet registro = procedimiento.executeQuery();
           
           while(registro.next()){
               result= new CargoEmpleado(registro.getInt("codigoCargoEmpleado"),
               registro.getString("nombreCargo"),
               registro.getString("descripcionCargo"));
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
    
    public ObservableList<Empleado> getEmpleado() {
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarEmpleados()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Empleado(resultado.getInt("codigoEmpleado"),
                        resultado.getString("nombresEmpleado"),
                        resultado.getString("apellidosEmpleado"),
                        resultado.getDouble("sueldo"),
                        resultado.getString("direccion"),
                        resultado.getString("turno"),
                        resultado.getInt("codigoCargoEmpleado")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarEmpleados = FXCollections.observableList(lista);
    }
    
    public ObservableList<CargoEmpleado> getCargoEmpleado() {
        ArrayList<CargoEmpleado> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarCargosEmpleado()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new CargoEmpleado(resultado.getInt("codigoCargoEmpleado"),
                        resultado.getString("nombreCargo"), 
                        resultado.getString("descripcionCargo")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarCargoEmpleado = FXCollections.observableList(lista);
    }
    
    public void DesactivarControles() {
        txtCodigoE.setEditable(false);
        txtNombresE.setEditable(false);
        txtApellidosE.setEditable(false);
        txtDireccion.setEditable(false);
        txtTurno.setEditable(false);
        cmbCodigoCargoE.setDisable(true);
    }

    public void ActivarControles() {
        txtCodigoE.setEditable(true);
        txtNombresE.setEditable(true);
        txtApellidosE.setEditable(true);
        txtDireccion.setEditable(true);
        txtTurno.setEditable(true);
        cmbCodigoCargoE.setDisable(false);
    }

    public void LimpiarControles() {
        txtCodigoE.clear();
        txtNombresE.clear();
        txtApellidosE.clear();
        txtDireccion.clear();
        txtTurno.clear();
        tblEmpleados.getSelectionModel().getSelectedItem();
        cmbCodigoCargoE.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
    
}
