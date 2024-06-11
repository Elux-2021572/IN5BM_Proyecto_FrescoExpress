package org.emiliolux.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.emiliolux.system.Principal;

/**
 *
 * @author Emilio Lux
 */
public class MenuPrincipalController implements Initializable {

    private Principal escenarioPrincipal;

    @FXML
    MenuItem btnMenuClientes;
    @FXML
    MenuItem btnMenuProgramador;
    @FXML
    MenuItem btnMenuCompras;
    @FXML
    MenuItem btnMenuProveedores;
    @FXML
    MenuItem btnMenuTipoProducto;
    @FXML
    MenuItem btnMenuProducto;
    @FXML
    MenuItem btnMenuDetalleC;
    @FXML
    MenuItem btnMenuDetalleF;
    @FXML
    MenuItem btnMenuFactura;
    @FXML
    MenuItem btnMenuEmpleado;
    @FXML
    MenuItem btnMenuTelefonoP;
    @FXML
    MenuItem btnMenuEmailP;
    @FXML
    MenuItem btnCargoEmpleado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnMenuClientes) {
            escenarioPrincipal.menuClientesView();
        }
        if (event.getSource() == btnMenuProgramador) {
            escenarioPrincipal.menuProgramadorView();
        }
        if (event.getSource() == btnMenuCompras) {
            escenarioPrincipal.menuComprasView();
        }
        if (event.getSource() == btnMenuProveedores) {
            escenarioPrincipal.menuProveedoresView();
        }
        if (event.getSource() == btnMenuTipoProducto) {
            escenarioPrincipal.menuTipoProductoView();
        }
        if(event.getSource() == btnMenuProducto){
            escenarioPrincipal.menuProductoView();
        }
        if(event.getSource() == btnMenuDetalleC){
            escenarioPrincipal.menuDetalleCompraView();
        }
        if(event.getSource() == btnMenuDetalleF ){
            escenarioPrincipal.menuDetalleFacturaView();
        }
        if(event.getSource() == btnMenuFactura){
            escenarioPrincipal.menuFacturaView();
        }
        if(event.getSource() == btnMenuEmpleado){
            escenarioPrincipal.menuEmpleadoView();
        }
        if(event.getSource() == btnMenuTelefonoP){
            escenarioPrincipal.menuTelefonoProView();
        }
        if(event.getSource() == btnMenuEmailP){
            escenarioPrincipal.menuEmailProView();
        }
        if(event.getSource() == btnCargoEmpleado){
            escenarioPrincipal.menuCargoEmpleadoView();
        }
    }

}
