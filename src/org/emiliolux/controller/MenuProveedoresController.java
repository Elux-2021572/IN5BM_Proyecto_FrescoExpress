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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.emiliolux.bean.Proveedores;
import org.emiliolux.db.Conexion;
import org.emiliolux.report.GenerarReportes;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuProveedoresController implements Initializable {

    private Principal escenarioPrincipal;
    private ObservableList<Proveedores> listaProveedores;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;

    @FXML
    private Button btnRegresar;

    @FXML
    private TableView tblProveedores;

    @FXML
    private TableColumn colCodigo;

    @FXML
    private TableColumn colNit;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableColumn colApellido;

    @FXML
    private TableColumn colDireccion;

    @FXML
    private TableColumn colRazon;

    @FXML
    private TableColumn colContacto;

    @FXML
    private TableColumn colPagina;

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
    private TextField txtCodigo;

    @FXML
    private TextField txtNit;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtRazon;

    @FXML
    private TextField txtContacto;

    @FXML
    private TextField txtPagina;

    public ObservableList<Proveedores> getProveedores() {
        ArrayList<Proveedores> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarProveedores ()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new Proveedores(resultado.getInt("codigoProveedor"),
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
        return listaProveedores = FXCollections.observableList(lista);
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
        tblProveedores.setItems(getProveedores());
        colCodigo.setCellValueFactory(new PropertyValueFactory<Proveedores, Integer>("codigoProveedor"));
        colNit.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nitProveedor"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("nombresProveedor"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("apellidosProveedor"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("direccionProveedor"));
        colRazon.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("razonSocial"));
        colContacto.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("contactoPrincipal"));
        colPagina.setCellValueFactory(new PropertyValueFactory<Proveedores, String>("paginaWeb"));
    }

    public void seleccionar() {
        txtCodigo.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
        txtNit.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getNitProveedor()));
        txtNombre.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getNombresProveedor()));
        txtApellido.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getApellidosProveedor()));
        txtDireccion.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getDireccionProveedor()));
        txtRazon.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getRazonSocial()));
        txtContacto.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getContactoPrincipal()));
        txtPagina.setText(String.valueOf(((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getPaginaWeb()));
    }

    public void activarControles() {
        txtCodigo.setEditable(true);
        txtNit.setEditable(true);
        txtNombre.setEditable(true);
        txtApellido.setEditable(true);
        txtDireccion.setEditable(true);
        txtRazon.setEditable(true);
        txtContacto.setEditable(true);
        txtPagina.setEditable(true);
    }

    public void desactivarControles() {
        txtCodigo.setEditable(false);
        txtNit.setEditable(false);
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtDireccion.setEditable(false);
        txtRazon.setEditable(false);
        txtContacto.setEditable(false);
        txtPagina.setEditable(false);
    }

    public void limpiarControles() {
        txtCodigo.clear();
        txtNit.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtDireccion.clear();
        txtRazon.clear();
        txtContacto.clear();
        txtPagina.clear();
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
                tipoDeOperaciones = MenuProveedoresController.operaciones.ACTUALIZAR;
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
                tipoDeOperaciones = MenuProveedoresController.operaciones.NINGUNO;
                break;

        }
    }

    public void guardar() {
        Proveedores registro = new Proveedores();
        //registro.setCodigoProveedor(Integer.parseInt(txtCodigo.getText()));
        registro.setNitProveedor(txtNit.getText());
        registro.setNombresProveedor(txtNombre.getText());
        registro.setApellidosProveedor(txtApellido.getText());
        registro.setDireccionProveedor(txtDireccion.getText());
        registro.setRazonSocial(txtRazon.getText());
        registro.setContactoPrincipal(txtContacto.getText());
        registro.setPaginaWeb(txtPagina.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_agregarProveedor(?, ?, ?, ?, ?, ?, ?)}");
            //procedimiento.setInt(1, registro.getCodigoProveedor());
            procedimiento.setString(1, registro.getNitProveedor());
            procedimiento.setString(2, registro.getNombresProveedor());
            procedimiento.setString(3, registro.getApellidosProveedor());
            procedimiento.setString(4, registro.getDireccionProveedor());
            procedimiento.setString(5, registro.getRazonSocial());
            procedimiento.setString(6, registro.getContactoPrincipal());
            procedimiento.setString(7, registro.getPaginaWeb());
            procedimiento.execute();
            listaProveedores.add(registro);
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
                tipoDeOperaciones = MenuProveedoresController.operaciones.NINGUNO;
                break;
            default:
                if (tblProveedores.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro", "Eliminar Proveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_eliminarProveedor(?)}");
                            procedimiento.setInt(1, ((Proveedores) tblProveedores.getSelectionModel().getSelectedItem()).getCodigoProveedor());
                            procedimiento.execute();
                            listaProveedores.remove(tblProveedores.getSelectionModel().getSelectedItem());
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
                if (tblProveedores.getSelectionModel().getSelectedItem() != null) {
                    btnEditar.setText("Actualizar");
                    btnReportes.setText("Cancelar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    imgEditar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                    imgReportes.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                    activarControles();
                    txtCodigo.setEditable(false);
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
                imgReportes.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                desactivarControles();
                limpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_actualizarProveedor (?, ?, ?, ?, ?, ?, ?, ?)}");
            Proveedores registro = (Proveedores)tblProveedores.getSelectionModel().getSelectedItem();
            //registro.setCodigoProveedor(txtCodigo.getText);
            registro.setNitProveedor(txtNit.getText());
            registro.setNombresProveedor(txtNombre.getText());
            registro.setApellidosProveedor(txtApellido.getText());
            registro.setDireccionProveedor(txtDireccion.getText());
            registro.setRazonSocial(txtRazon.getText());
            registro.setContactoPrincipal(txtContacto.getText());
            registro.setPaginaWeb(txtPagina.getText());
            procedimiento.setInt(1, registro.getCodigoProveedor());
            procedimiento.setString(2, registro.getNitProveedor());
            procedimiento.setString(3, registro.getNombresProveedor());
            procedimiento.setString(4, registro.getApellidosProveedor());
            procedimiento.setString(5, registro.getDireccionProveedor());
            procedimiento.setString(6, registro.getRazonSocial());
            procedimiento.setString(7, registro.getContactoPrincipal());
            procedimiento.setString(8, registro.getPaginaWeb());
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
                imgReportes.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
            case NINGUNO:
                imprimirReporte();
                break;
        }
    }
    
    public void imprimirReporte(){
        Map parametros = new HashMap();
        parametros.hashCode();
        parametros.put("codigoProveedor", null);
        GenerarReportes.mostrarReportes("ReporteProveedores.jasper", "Reporte de Proveedores", parametros);
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
}
