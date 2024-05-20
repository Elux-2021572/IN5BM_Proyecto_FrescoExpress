/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.emiliolux.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author emili
 */
public class TelefonoProveedorController implements Initializable {

    private Principal escenarioPrincipal;
    @FXML
    private Button btnRegresar;
    
    @FXML
    private TableView tblTelefonoPro;

    @FXML
    private TableColumn colcodigoTelefonoP;

    @FXML
    private TableColumn colnumeroPr;

    @FXML
    private TableColumn colnumeroS;

    @FXML
    private TableColumn colObservaciones;

    @FXML
    private TableColumn colcodigoProveedor;

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

    @FXML
    private TextField txtCodigoTelefonoP;

    @FXML
    private TextField txtNumeroP;

    @FXML
    private TextField txtNumeroS;

    @FXML
    private TextField txtObservaciones;

    @FXML
    private ComboBox cmbCodigoProveedor;
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
