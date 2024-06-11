/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emiliolux.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.emiliolux.bean.CargoEmpleado;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author emili
 */
public class CargoEmpleadoController implements Initializable {
    
    private Principal escenarioPrincipal;
    private ObservableList<CargoEmpleado> listarCargoEmpleado;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    
    @FXML
    private TableView tblCargoEMpleado;    
    @FXML
    private TextField txtCodigoC;

    @FXML
    private TextField txtDescripcionC;

    @FXML
    private TextField txtNombreC;

    @FXML
    private TableColumn colCodigoCargoE;

    @FXML
    private TableColumn colNombreC;

    @FXML
    private TableColumn colDescripcionC;

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
    private ImageView imgReporte;

    @FXML
    private Button btnRegresar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        } 
    }

    
}
