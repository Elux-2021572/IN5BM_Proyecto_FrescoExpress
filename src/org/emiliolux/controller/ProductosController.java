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
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.emiliolux.bean.Productos;
import org.emiliolux.bean.Proveedores;
import org.emiliolux.bean.TipoProducto;
import org.emiliolux.db.Conexion;
import org.emiliolux.report.GenerarReportes;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author Emilio
 */
public class ProductosController implements Initializable {

    private Principal escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;

    private ObservableList<Productos> listarProductos;
    private ObservableList<Proveedores> listaProveedores;
    private ObservableList<TipoProducto> listaTipoProducto;

    @FXML
    private Button btnRegresar;

    @FXML
    private ComboBox<Proveedores> cmbcodigoProveedor;

    @FXML
    private ComboBox<TipoProducto> cmbcodigoTipoProducto;

    @FXML
    private TextField txtprecioMayor;

    @FXML
    private TextField txtimagenProducto;

    @FXML
    private TextField txtexistencia;

    @FXML
    private TextField txtcodigoProducto;

    @FXML
    private TextField txtdescripcionProducto;

    @FXML
    private TextField txtprecioUnitario;

    @FXML
    private TextField txtprecioDocena;

    @FXML
    private TableView tblProductos;

    @FXML
    private TableColumn colcodigoProducto;

    @FXML
    private TableColumn coldescripcionProducto;

    @FXML
    private TableColumn colprecioUnitario;

    @FXML
    private TableColumn colprecioDocena;

    @FXML
    private TableColumn colprecioMayor;

    @FXML
    private TableColumn colimagenProducto;

    @FXML
    private TableColumn colexistencia;

    @FXML
    private TableColumn codigoTipoProducto;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbcodigoTipoProducto.setItems(getTipoProducto());
        cmbcodigoProveedor.setItems(getProveedores());
        cmbcodigoTipoProducto.setDisable(true);
        cmbcodigoProveedor.setDisable(true);
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cargarDatos() {
        DesactivarControles();
        tblProductos.setItems(getProducto());
        colcodigoProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("codigoProducto"));
        coldescripcionProducto.setCellValueFactory(new PropertyValueFactory<Productos, String>("descripcionProducto"));
        colprecioUnitario.setCellValueFactory(new PropertyValueFactory<Productos, String>("precioUnitario"));
        colprecioDocena.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("precioDocena"));
        colprecioMayor.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("precioMayor"));
        colimagenProducto.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("imagenProducto"));
        colexistencia.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("existencia"));
        codigoTipoProducto.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("codigoTipoProducto"));
        colcodigoProveedor.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("codigoProveedor"));
    }

    public void seleccionarElemento() {
        txtcodigoProducto.setText(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto());
        txtdescripcionProducto.setText(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getDescripcionProducto());
        txtprecioUnitario.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioUnitario()));
        txtprecioDocena.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioDocena()));
        txtprecioMayor.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getPrecioMayor()));
        txtimagenProducto.setText(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getImagenProducto());
        txtexistencia.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getExistencia()));
        cmbcodigoTipoProducto.getSelectionModel().select(buscarTipoProducto(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getCodigoTipoProducto()));
        cmbcodigoProveedor.getSelectionModel().select(buscarProveedor(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
        //txtexistencia.setText(String.valueOf(((Productos) tblProductos.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
    }

    public TipoProducto buscarTipoProducto(int codigoTipoProducto) {
        TipoProducto resultado = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_buscarProducto (?)}");
            procedimiento.setInt(1, codigoTipoProducto);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()) {
                resultado = new TipoProducto(registro.getInt("codigoTipoProducto"), ("descripcionProducto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Proveedores buscarProveedor(int codigoProveedor) {
        Proveedores result = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_buscarProveedor(?)}");
            procedimiento.setInt(1, codigoProveedor);

            ResultSet registro = procedimiento.executeQuery();

            while (registro.next()) {
                result = new Proveedores(registro.getInt("codigoProveedor"),
                        registro.getString("codigoProveedor"),
                        registro.getString("nombresProveedor"),
                        registro.getString("apellidosProveedor"),
                        registro.getString("direccionProveedor"),
                        registro.getString("razonSocial"),
                        registro.getString("contactoPrincipal"),
                        registro.getString("paginaWeb"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public ObservableList<Productos> getProducto() {
        ArrayList<Productos> lista = new ArrayList<Productos>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarProductos()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Productos(resultado.getString("codigoProducto"),
                        resultado.getString("descripcionProducto"),
                        resultado.getInt("precioUnitario"),
                        resultado.getInt("precioDocena"),
                        resultado.getInt("precioMayor"),
                        resultado.getString("imagenProducto"),
                        resultado.getInt("existencia"),
                        resultado.getInt("codigoTipoProducto"),
                        resultado.getInt("codigoProveedor")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarProductos = FXCollections.observableList(lista);
    }

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> listaPro = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarProveedores ()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaPro.add(new Proveedores(resultado.getInt("codigoProveedor"),
                        resultado.getString("nitProveedor"),
                        resultado.getString("nombresProveedor"),
                        resultado.getString("apellidosProveedor"),
                        resultado.getString("direccionProveedor"),
                        resultado.getString("razonSocial"),
                        resultado.getString("contactoPrincipal"),
                        resultado.getString("paginaWeb")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProveedores = FXCollections.observableList(listaPro);
    }

    public ObservableList<TipoProducto> getTipoProducto() {
        ArrayList<TipoProducto> listaTipoPro = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarTipoProductos()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                listaTipoPro.add(new TipoProducto(resultado.getInt("codigoTipoProducto"),
                        resultado.getString("descripcion")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTipoProducto = FXCollections.observableList(listaTipoPro);
    }

    public void agregar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                ActivarControles();
                btnEliminar.setText("Cancelar");
                btnAgregar.setText("Guardar");
                imgAgregar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                txtcodigoProducto.setEditable(true);
                btnReportes.setDisable(true);
                btnEditar.setDisable(true);
                btnRegresar.setDisable(true);
                tipoDeOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                guardar();
                cargarDatos();
                DesactivarControles();
                LimpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                imgAgregar.setImage(new Image("/org/emiliolux/images/AgregarClientes.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/EliminarClientes.png"));
                btnRegresar.setDisable(false);
                btnReportes.setDisable(false);
                btnEditar.setDisable(false);
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
        }
    }

    public void guardar() {
        Productos registro = new Productos();
        registro.setCodigoProducto(txtcodigoProducto.getText());
        registro.setDescripcionProducto(txtdescripcionProducto.getText());
        registro.setPrecioUnitario(Integer.parseInt(txtprecioUnitario.getText()));
        registro.setPrecioDocena(Integer.parseInt(txtprecioDocena.getText()));
        registro.setPrecioMayor(Integer.parseInt(txtprecioMayor.getText()));
        registro.setImagenProducto(txtimagenProducto.getText());
        registro.setExistencia(Integer.parseInt(txtexistencia.getText()));
        registro.setCodigoProveedor(((Proveedores) cmbcodigoProveedor.getSelectionModel().getSelectedItem()).getCodigoProveedor());
        registro.setCodigoTipoProducto(((TipoProducto) cmbcodigoTipoProducto.getSelectionModel().getSelectedItem()).getCodigoTipoProducto());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_agregarProducto(?, ?, ?, ?, ?, ?, ?, ? , ?)}");
            procedimiento.setString(1, registro.getCodigoProducto());
            procedimiento.setString(2, registro.getDescripcionProducto());
            procedimiento.setInt(3, registro.getPrecioUnitario());
            procedimiento.setInt(4, registro.getPrecioDocena());
            procedimiento.setInt(5, registro.getPrecioMayor());
            procedimiento.setString(6, registro.getImagenProducto());
            procedimiento.setInt(7, registro.getExistencia());
            procedimiento.setInt(8, registro.getCodigoProveedor());
            procedimiento.setInt(9, registro.getCodigoTipoProducto());
            procedimiento.execute();
            listarProductos.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                DesactivarControles();
                LimpiarControles();
                btnAgregar.setText("Agregar");
                btnEliminar.setText("Eliminar");
                imgAgregar.setImage(new Image("/org/emiliolux/images/Agregar.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/Eliminar.png"));
                btnReportes.setDisable(false);
                btnEditar.setDisable(false);
                btnRegresar.setDisable(false);
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
            default:
                if (tblProductos.getSelectionModel().getSelectedItem() != null) {

                    int resp = JOptionPane.showConfirmDialog(null, "Confirmar eliminar el registro", "Eliminar Producto", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (resp == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_eliminarProducto(?)}");
                            procedimiento.setString(1, ((Productos) tblProductos.getSelectionModel().getSelectedItem()).getCodigoProducto());
                            boolean execute = procedimiento.execute();
                            listarProductos.remove(tblProductos.getSelectionModel().getSelectedItem());
                            LimpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Seleccione un Producto para eliminar");
                    }
                    cargarDatos();
                    break;
                }
        }
    }

    public void editar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                if (tblProductos.getSelectionModel().getSelectedItem() != null) {
                    imgReportes.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                    imgEditar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                    btnReportes.setText("Cancelar");
                    btnEditar.setText("actualizar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnRegresar.setDisable(true);
                    txtcodigoProducto.setDisable(true);
                    ActivarControles();
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para poder editar");
                    break;
                }
            case ACTUALIZAR:
                actualizar();
                imgReportes.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                imgEditar.setImage(new Image("/org/emiliolux/images/Editar.png"));
                btnReportes.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                btnRegresar.setDisable(false);
                DesactivarControles();
                LimpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }

    public void actualizar() {
        Productos registro = (Productos) tblProductos.getSelectionModel().getSelectedItem();
        registro.setDescripcionProducto(txtdescripcionProducto.getText());
        registro.setPrecioUnitario(Integer.parseInt(txtprecioUnitario.getText()));
        registro.setPrecioDocena(Integer.parseInt(txtprecioDocena.getText()));
        registro.setPrecioMayor(Integer.parseInt(txtprecioMayor.getText()));
        registro.setImagenProducto(txtimagenProducto.getText());
        registro.setExistencia(Integer.parseInt(txtexistencia.getText()));
        registro.setCodigoProveedor(((Proveedores) cmbcodigoProveedor.getSelectionModel().getSelectedItem()).getCodigoProveedor());
        registro.setCodigoTipoProducto(((TipoProducto) cmbcodigoTipoProducto.getSelectionModel().getSelectedItem()).getCodigoTipoProducto());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_actualizarProducto( ?, ?, ?, ?, ?, ?, ?, ? , ?)}");
            procedimiento.setString(1, registro.getCodigoProducto());
            procedimiento.setString(2, registro.getDescripcionProducto());
            procedimiento.setInt(3, registro.getPrecioUnitario());
            procedimiento.setInt(4, registro.getPrecioDocena());
            procedimiento.setInt(5, registro.getPrecioMayor());
            procedimiento.setString(6, registro.getImagenProducto());
            procedimiento.setInt(7, registro.getExistencia());
            procedimiento.setInt(8, registro.getCodigoTipoProducto());
            procedimiento.setInt(9, registro.getCodigoProveedor());
            procedimiento.execute();

            listarProductos.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reporte() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                imgEditar.setImage(new Image("/org/emiliolux/images/Editar.png"));
                imgReportes.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                btnReportes.setText("Reporte");
                btnEditar.setText("Editar");
                btnAgregar.setDisable(false);
                btnEliminar.setDisable(false);
                btnRegresar.setDisable(false);
                DesactivarControles();
                LimpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
            case NINGUNO:
                imprimirReporte();
                break;
        }
    }

    public void imprimirReporte() {
        Map parametros = new HashMap();
        parametros.hashCode();
        parametros.put("codigoProducto", null);
        GenerarReportes.mostrarReportes("ReporteProductos.jasper", "Reporte de Productos", parametros);
    }

    public void DesactivarControles() {
        txtcodigoProducto.setEditable(false);
        txtdescripcionProducto.setEditable(false);
        txtprecioUnitario.setEditable(false);
        txtprecioDocena.setEditable(false);
        txtprecioMayor.setEditable(false);
        txtimagenProducto.setEditable(false);
        txtexistencia.setEditable(false);
        cmbcodigoTipoProducto.setDisable(true);
        cmbcodigoProveedor.setDisable(true);
    }

    public void ActivarControles() {
        txtcodigoProducto.setEditable(true);
        txtdescripcionProducto.setEditable(true);
        txtprecioUnitario.setEditable(true);
        txtprecioDocena.setEditable(true);
        txtprecioMayor.setEditable(true);
        txtimagenProducto.setEditable(true);
        txtexistencia.setEditable(true);
        cmbcodigoTipoProducto.setDisable(false);
        cmbcodigoProveedor.setDisable(false);
    }

    public void LimpiarControles() {
        txtcodigoProducto.clear();
        txtdescripcionProducto.clear();
        txtprecioUnitario.clear();
        txtprecioDocena.clear();
        txtprecioMayor.clear();
        txtimagenProducto.clear();
        txtexistencia.clear();
        tblProductos.getSelectionModel().getSelectedItem();
        cmbcodigoTipoProducto.getSelectionModel().clearSelection();
        cmbcodigoProveedor.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
}
