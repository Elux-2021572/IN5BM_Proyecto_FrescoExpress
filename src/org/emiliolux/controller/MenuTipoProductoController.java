/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.emiliolux.bean.TipoProducto;
import org.emiliolux.db.Conexion;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuTipoProductoController implements Initializable {

    private Principal escenarioPrincipal;
    private ObservableList<TipoProducto> listaTipoProducto;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;

    @FXML
    private Button btnRegresar;
    
 @FXML
    private TableView tblTipoProducto;

    @FXML
    private TableColumn colTipoProducto;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnReportes;

    @FXML
    private ImageView imgAgregar;

    @FXML
    private ImageView imgEliminar;

    @FXML
    private ImageView imgReporte;

    @FXML
    private ImageView imgEditar;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtCodigoProducto;
    
    public ObservableList<TipoProducto> getTipoProducto() {
        ArrayList<TipoProducto> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarTipoProductos()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new TipoProducto(resultado.getInt("codigoTipoProducto"),
                        resultado.getString("descripcion")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTipoProducto = FXCollections.observableList(lista);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public Button getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(Button btnRegresar) {
        this.btnRegresar = btnRegresar;
    }
    
    public void cargarDatos() {
        tblTipoProducto.setItems(getTipoProducto());
        colTipoProducto.setCellValueFactory(new PropertyValueFactory<TipoProducto, Integer>("codigoTipoProducto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TipoProducto, String>("descripcion"));
    }
    
    
    public void seleccionar() {
        txtCodigoProducto.setText(String.valueOf(((TipoProducto) tblTipoProducto.getSelectionModel().getSelectedItem()).getCodigoTipoProducto()));
        txtDescripcion.setText(String.valueOf(((TipoProducto) tblTipoProducto.getSelectionModel().getSelectedItem()).getDescripcion()));
    }
    
    public void activarControles() {
        txtCodigoProducto.setEditable(true);
        txtDescripcion.setEditable(true);
    }
    
    public void desactivarControles() {
        txtCodigoProducto.setEditable(false);
        txtDescripcion.setEditable(false);
    }
    
    public void limpiarControles() {
        txtCodigoProducto.clear();
        txtDescripcion.clear();
    }
    
    public void Agregar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                activarControles();
                btnAgregar.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReportes.setDisable(true);
                imgAgregar.setImage((new Image("/org/emiliolux/images/Guardar.png")));
                imgEliminar.setImage((new Image("/org/emiliolux/images/Cancelar.png")));
                tipoDeOperaciones = MenuTipoProductoController.operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardar();
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReportes.setDisable(false);
                imgAgregar.setImage(new Image("/org/emiliolux/images/Agregar.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/Eliminar.png"));
                cargarDatos();
                tipoDeOperaciones = MenuTipoProductoController.operaciones.NINGUNO;
                break;

        }
    }
    
    public void guardar() {
        TipoProducto registro = new TipoProducto();
        registro.setDescripcion(txtDescripcion.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_agregarTipoProducto(?)}");
            procedimiento.setString(1, registro.getDescripcion());
            procedimiento.execute();
            listaTipoProducto.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReportes.setDisable(false);
                imgAgregar.setImage((new Image("/org/emiliolux/images/Agregar.png")));
                imgEliminar.setImage((new Image("/org/emiliolux/images/Eliminar.png")));
                tipoDeOperaciones = MenuTipoProductoController.operaciones.NINGUNO;
                break;
            default:
                if (tblTipoProducto.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro", "Eliminar Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_eliminarTipoProducto(?)}");
                            procedimiento.setInt(1, ((TipoProducto) tblTipoProducto.getSelectionModel().getSelectedItem()).getCodigoTipoProducto());
                            procedimiento.execute();
                            listaTipoProducto.remove(tblTipoProducto.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar un cliente para eliminar");
                }
                break;
        }
    }
    
    public void editar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                if (tblTipoProducto.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReportes.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    imgEditar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                    imgReporte.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                    activarControles();
                    txtCodigoProducto.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para editar");
                }
                break;
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                btnReportes.setText("Reporte");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                imgEditar.setImage(new Image("/org/emiliolux/images/Actualizar.png"));
                imgReporte.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                desactivarControles();
                limpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_actualizarTipoProducto (?, ?)}");
            TipoProducto registro = (TipoProducto)tblTipoProducto.getSelectionModel().getSelectedItem();
            //registro.setCodigoTipoProducto(txtCodigoProducto.getText());
            registro.setDescripcion(txtDescripcion.getText());
            procedimiento.setInt(1, registro.getCodigoTipoProducto());
            procedimiento.setString(2, registro.getDescripcion());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void reporte() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnEditar.setText("Editar");
                btnReportes.setText("Reporte");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                imgEditar.setImage(new Image("/org/emiliolux/images/Actualizar.png"));
                imgReporte.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
        }
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        }
    }

}
