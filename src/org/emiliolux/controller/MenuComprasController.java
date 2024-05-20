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
import org.emiliolux.bean.Compras;
import org.emiliolux.db.Conexion;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuComprasController implements Initializable {

    private Principal escenarioPrincipal;

    private ObservableList<Compras> listaCompras;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    @FXML
    private Button btnRegresar;

    @FXML
    private TableView tblCompras;

    @FXML
    private ImageView imgAgregarCompra;

    @FXML
    private ImageView imgEditarCompra;

    @FXML
    private ImageView imgEliminarCompra;

    @FXML
    private ImageView imgReportes;

    @FXML
    private Button btnAgregarCompra;

    @FXML
    private Button btnEditarCompra;

    @FXML
    private Button btnEliminarCompra;

    @FXML
    private Button btnReportesCompra;

    @FXML
    private TableColumn colNumDoc;

    @FXML
    private TableColumn colFechaDocumento;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colTotDocumento;

    @FXML
    private TextField txtNumDocumento;

    @FXML
    private TextField txtFechaDocumento;

    @FXML
    private TextField txtTotDocumento;

    @FXML
    private TextField txtDescripcion;

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

    public ObservableList<Compras> getCompras() {
        ArrayList<Compras> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarCompras ()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Compras(resultado.getInt("numeroDocumento"),
                        resultado.getString("fechaDocumento"),
                        resultado.getString("descripcion"),
                        resultado.getInt("totalDocumento")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCompras = FXCollections.observableList(lista);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }

    public void cargarDatos() {
        tblCompras.setItems(getCompras());
        colNumDoc.setCellValueFactory(new PropertyValueFactory<Compras, Integer>("numeroDocumento"));
        colFechaDocumento.setCellValueFactory(new PropertyValueFactory<Compras, String>("fechaDocumento"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Compras, String>("descripcion"));
        colTotDocumento.setCellValueFactory(new PropertyValueFactory<Compras, String>("totalDocumento"));
    }

    public void seleccionar() {
        txtNumDocumento.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getNumeroDocumento()));
        txtFechaDocumento.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getFechaDocumento()));
        txtDescripcion.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getDescripcion()));
        txtTotDocumento.setText(String.valueOf(((Compras) tblCompras.getSelectionModel().getSelectedItem()).getTotalDocumento()));
    }

    public void activarControles() {
        txtNumDocumento.setEditable(true);
        txtFechaDocumento.setEditable(true);
        txtDescripcion.setEditable(true);
        txtTotDocumento.setEditable(true);
    }

    public void desactivarControles() {
        txtNumDocumento.setEditable(false);
        txtFechaDocumento.setEditable(false);
        txtDescripcion.setEditable(false);
        txtTotDocumento.setEditable(false);
    }

    public void limpiarControles() {
        txtNumDocumento.clear();
        txtFechaDocumento.clear();
        txtDescripcion.clear();
        txtTotDocumento.clear();
    }

    public void AgregarCompra() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                activarControles();
                btnAgregarCompra.setText("Guardar");
                btnEliminarCompra.setText("Cancelar");
                btnEditarCompra.setDisable(true);
                btnReportesCompra.setDisable(true);
                imgAgregarCompra.setImage((new Image("/org/emiliolux/images/Guardar.png")));
                imgEliminarCompra.setImage((new Image("/org/emiliolux/images/Cancelar.png")));

                tipoDeOperaciones = MenuComprasController.operaciones.ACTUALIZAR;
                break;

            case ACTUALIZAR:
                guardarCompra();
                desactivarControles();
                limpiarControles();
                btnAgregarCompra.setText("Agregar");
                btnEliminarCompra.setText("Eliminar");
                btnEditarCompra.setDisable(false);
                btnReportesCompra.setDisable(false);
                imgAgregarCompra.setImage(new Image("/org/emiliolux/images/Agregar.png"));
                imgEliminarCompra.setImage(new Image("/org/emiliolux/images/Eliminar.png"));
                cargarDatos();
                tipoDeOperaciones = MenuComprasController.operaciones.NINGUNO;
                break;

        }
    }

    public void guardarCompra() {
        Compras registro = new Compras();
        //registro.setNumeroDocumento(Integer.parseInt(txtNumDocumento.getText()));
        txtNumDocumento.setDisable(true);
        registro.setFechaDocumento(txtFechaDocumento.getText());
        registro.setDescripcion(txtDescripcion.getText());
        registro.setTotalDocumento(Integer.parseInt(txtTotDocumento.getText()));
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_agregarCompra( ?, ?, ?)}");
            txtNumDocumento.setDisable(true);
            //procedimiento.setInt(1, registro.getNumeroDocumento());
            procedimiento.setString(1, registro.getFechaDocumento());
            procedimiento.setString(2, registro.getDescripcion());
            procedimiento.setDouble(3, registro.getTotalDocumento());
            procedimiento.execute();
            listaCompras.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarCompra() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarCompra.setText("Agregar");
                btnEliminarCompra.setText("Eliminar");
                btnEditarCompra.setDisable(false);
                btnReportesCompra.setDisable(false);
                imgAgregarCompra.setImage((new Image("/org/emiliolux/images/Agregar.png")));
                imgEliminarCompra.setImage((new Image("/org/emiliolux/images/Eliminar.png")));
                tipoDeOperaciones = MenuComprasController.operaciones.NINGUNO;
                break;
            default:
                if (tblCompras.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro", "Eliminar Compra", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_eliminarCompra(?)}");
                            procedimiento.setInt(1, ((Compras) tblCompras.getSelectionModel().getSelectedItem()).getNumeroDocumento());
                            procedimiento.execute();
                            listaCompras.remove(tblCompras.getSelectionModel().getSelectedItem());
                            limpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe de seleccionar una compra para eliminar");
                }
                break;
        }
    }

    public void editarCompra() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                if (tblCompras.getSelectionModel().getSelectedItem() != null) {
                    btnEditarCompra.setText("Actualizar");
                    btnReportesCompra.setText("Cancelar");
                    btnAgregarCompra.setDisable(true);
                    btnEliminarCompra.setDisable(true);
                    imgEditarCompra.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                    imgReportes.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                    activarControles();
                    txtNumDocumento.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para editar");
                }
                break;
            case ACTUALIZAR:
                actualizarCompra();
                btnEditarCompra.setText("Editar");
                btnReportesCompra.setText("Reporte");
                btnAgregarCompra.setDisable(false);
                btnEliminarCompra.setDisable(false);
                imgEditarCompra.setImage(new Image("/org/emiliolux/images/Actualizar.png"));
                imgReportes.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                desactivarControles();
                limpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }

    public void actualizarCompra() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_actualizarCompra (?, ?, ?, ?)}");
            Compras registro = (Compras) tblCompras.getSelectionModel().getSelectedItem();
            registro.setFechaDocumento(txtFechaDocumento.getText());
            registro.setDescripcion(txtDescripcion.getText());
            registro.setTotalDocumento(Integer.parseInt(txtTotDocumento.getText()));
            procedimiento.setInt(1, registro.getNumeroDocumento());
            procedimiento.setString(2, registro.getFechaDocumento());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setInt(4, registro.getTotalDocumento());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reporte() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnEditarCompra.setText("Editar");
                btnReportesCompra.setText("Reporte");
                btnAgregarCompra.setDisable(false);
                btnEliminarCompra.setDisable(false);
                imgEditarCompra.setImage(new Image("/org/emiliolux/images/Actualizar.png"));
                imgReportes.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
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
