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
import org.emiliolux.bean.Clientes;
import org.emiliolux.db.Conexion;
import org.emiliolux.system.Principal;

/**
 *
 * @author Emilio Lux
 */
public class MenuClientesController implements Initializable {

    private Principal escenarioPrincipal;
    private ObservableList<Clientes> listarClientes;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;

    @FXML
    private Button btnRegresar;
    @FXML
    private TableView tblCliente;
    @FXML
    private TableColumn colclienteID;
    @FXML
    private TableColumn colnitCliente;
    @FXML
    private TableColumn colnombresCliente;
    @FXML
    private TableColumn colapellidosCliente;
    @FXML
    private TableColumn coldireccionCliente;
    @FXML
    private TableColumn coltelefonoCliente;
    @FXML
    private TableColumn colcorreoCliente;
    @FXML
    private TextField txtdireccionCliente;
    @FXML
    private TextField txtclienteID;
    @FXML
    private TextField txttelefonoCliente;
    @FXML
    private TextField txtnitCliente;
    @FXML
    private TextField txtcorreoCliente;
    @FXML
    private TextField txtnombresCliente;
    @FXML
    private TextField txtapellidosCliente;
    @FXML
    private Button btnAgregarCliente;
    @FXML
    private Button btnEliminarCliente;
    @FXML
    private Button btnEditarCliente;
    @FXML
    private Button btnReporte;
    @FXML
    private ImageView imgAgregar;
    @FXML
    private ImageView imgEliminar;
    @FXML
    private ImageView imgEditar;
    @FXML
    private ImageView imgReporte;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cargarDatos() {
        desactivarControles();
        tblCliente.setItems(getClientes());
        colclienteID.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("clienteID"));
        colnitCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nitCliente"));
        colnombresCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nombresCliente"));
        colapellidosCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("apellidosCliente"));
        coldireccionCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("direccionCliente"));
        coltelefonoCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("telefonoCliente"));
        colcorreoCliente.setCellValueFactory(new PropertyValueFactory<Clientes, String>("correoCliente"));
    }

    public void seleccionarElemento() {
        txtclienteID.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getClienteID()));
        txtnitCliente.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getNitCliente()));
        txtnombresCliente.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getNombresCliente()));
        txtapellidosCliente.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getApellidosCliente()));
        txtdireccionCliente.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getDireccionCliente()));
        txttelefonoCliente.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getTelefonoCliente()));
        txtcorreoCliente.setText(String.valueOf(((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getCorreoCliente()));
    }

    public ObservableList<Clientes> getClientes() {
        ArrayList<Clientes> lista = new ArrayList<>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarClientes()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Clientes(resultado.getInt("clienteID"),
                        resultado.getString("nitCliente"),
                        resultado.getString("nombresCliente"),
                        resultado.getString("apellidosCliente"),
                        resultado.getString("direccionCliente"),
                        resultado.getString("telefonoCliente"),
                        resultado.getString("correoCliente")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarClientes = FXCollections.observableList(lista);
    }
    
    public void activarControles() {
        txtclienteID.setEditable(true);
        txtnitCliente.setEditable(true);
        txtnombresCliente.setEditable(true);
        txtapellidosCliente.setEditable(true);
        txtdireccionCliente.setEditable(true);
        txttelefonoCliente.setEditable(true);
        txtcorreoCliente.setEditable(true);
    }

    public void desactivarControles() {
        txtclienteID.setEditable(false);
        txtnitCliente.setEditable(false);
        txtnombresCliente.setEditable(false);
        txtapellidosCliente.setEditable(false);
        txtdireccionCliente.setEditable(false);
        txttelefonoCliente.setEditable(false);
        txtcorreoCliente.setEditable(false);
    }


    public void limpiarControles() {
        txtclienteID.clear();
        txtnitCliente.clear();
        txtnombresCliente.clear();
        txtapellidosCliente.clear();
        txtdireccionCliente.clear();
        txttelefonoCliente.clear();
        txtcorreoCliente.clear();
    }

    public void AgregarClientes() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                activarControles();
                btnAgregarCliente.setText("Guardar");
                btnEliminarCliente.setText("Cancelar");
                imgEliminar.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                imgAgregar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                txtclienteID.setEditable(false);
                btnEditarCliente.setDisable(true);
                btnReporte.setDisable(true);

                tipoDeOperaciones = operaciones.ACTUALIZAR;
                break;
            case ACTUALIZAR:
                GuardarClientes();
                cargarDatos();
                desactivarControles();
                limpiarControles();
                btnAgregarCliente.setText("Agregar");
                btnEliminarCliente.setText("Eliminar");
                imgAgregar.setImage(new Image("/org/emiliolux/images/Agregar.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/Eliminar.png"));
                txtclienteID.setEditable(true);
                btnEditarCliente.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
        }
    }

    public void GuardarClientes() {
        Clientes registro = new Clientes();
        registro.setNitCliente(txtnitCliente.getText());
        registro.setNombresCliente(txtnombresCliente.getText());
        registro.setApellidosCliente(txtapellidosCliente.getText());
        registro.setDireccionCliente(txtdireccionCliente.getText());
        registro.setTelefonoCliente(txttelefonoCliente.getText());
        registro.setCorreoCliente(txtcorreoCliente.getText());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_agregarCliente(?, ?, ?, ?, ?, ?)}");
            procedimiento.setString(1, registro.getNitCliente());
            procedimiento.setString(2, registro.getNombresCliente());
            procedimiento.setString(3, registro.getApellidosCliente());
            procedimiento.setString(4, registro.getDireccionCliente());
            procedimiento.setString(5, registro.getTelefonoCliente());
            procedimiento.setString(6, registro.getCorreoCliente());
            procedimiento.execute();
            listarClientes.add(registro);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void eliminarClientes() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnAgregarCliente.setText("Agregar");
                btnEliminarCliente.setText("Eliminar");
                btnEditarCliente.setDisable(false);
                btnReporte.setDisable(false);
                imgAgregar.setImage((new Image("/org/emiliolux/images/Agregar.png")));
                imgEliminar.setImage((new Image("/org/emiliolux/images/Eliminar.png")));
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
            default:
                if (tblCliente.getSelectionModel().getSelectedItem() != null) {
                    int respuesta = JOptionPane.showConfirmDialog(null, "Confirmar la eliminacion del registro", "Eliminar Clientes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_NO_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_eliminarCliente(?)}");
                            procedimiento.setInt(1, ((Clientes) tblCliente.getSelectionModel().getSelectedItem()).getClienteID());
                            procedimiento.execute();
                            listarClientes.remove(tblCliente.getSelectionModel().getSelectedItem());
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

    public void editarClientes() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                if (tblCliente.getSelectionModel().getSelectedItem() != null) {
                    btnEditarCliente.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnAgregarCliente.setDisable(true);
                    btnEliminarCliente.setDisable(true);
                    imgEditar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                    imgReporte.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                    activarControles();
                    txtclienteID.setEditable(false);
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para editar");
                    break;
                }
            case ACTUALIZAR:
                actualizarCliente();
                btnEditarCliente.setText("Editar");
                btnReporte.setText("Reporte");
                btnAgregarCliente.setDisable(false);
                btnEliminarCliente.setDisable(false);
                imgEditar.setImage(new Image("/org/emiliolux/images/Actualizar.png"));
                imgReporte.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                desactivarControles();
                limpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;

        }
    }

    public void actualizarCliente() {
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_actualizarCliente(?,?,?,?,?,?,?)}");
            Clientes registro = (Clientes) tblCliente.getSelectionModel().getSelectedItem();
            registro.setNitCliente(txtnitCliente.getText());
            registro.setNombresCliente(txtnombresCliente.getText());
            registro.setApellidosCliente(txtapellidosCliente.getText());
            registro.setDireccionCliente(txtdireccionCliente.getText());
            registro.setTelefonoCliente(txttelefonoCliente.getText());
            registro.setCorreoCliente(txtcorreoCliente.getText());
            procedimiento.setInt(1, registro.getClienteID());
            procedimiento.setString(2, registro.getNitCliente());
            procedimiento.setString(3, registro.getNombresCliente());
            procedimiento.setString(4, registro.getApellidosCliente());
            procedimiento.setString(5, registro.getDireccionCliente());
            procedimiento.setString(6, registro.getTelefonoCliente());
            procedimiento.setString(7, registro.getCorreoCliente());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportesClientes() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                imgEditar.setImage(new Image("/org/emiliolux/images/Editar.png"));
                imgReporte.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
                btnEditarCliente.setText("Editar");
                btnReporte.setText("Reporte");
                btnAgregarCliente.setDisable(false);
                btnEliminarCliente.setDisable(false);
                desactivarControles();
                limpiarControles();
                tipoDeOperaciones = operaciones.NINGUNO;
                cargarDatos();
                break;
            case NINGUNO:
                break;
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        } else if (event.getSource() == btnAgregarCliente) {
            activarControles();
        }
    }

}
