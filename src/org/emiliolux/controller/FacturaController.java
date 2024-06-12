
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
import org.emiliolux.bean.Clientes;
import org.emiliolux.bean.Empleado;
import org.emiliolux.bean.Factura;
import org.emiliolux.db.Conexion;
import org.emiliolux.report.GenerarReportes;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author emili
 */
public class FacturaController implements Initializable {

    private Principal escenarioPrincipal;
    
    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;
    
    private ObservableList<Factura> listarFacturas;
    private ObservableList<Clientes> listarClientes;
    private ObservableList<Empleado> listarEmpleados;
    
    @FXML
    private Button btnRegresar;
    
    @FXML
    private TextField txtNumeroFactura;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtTotalFactura;

    @FXML
    private TextField txtFechaFactura;

    @FXML
    private ComboBox cmbClienteID;

    @FXML
    private ComboBox cmbCodigoEmpleado;

    @FXML
    private TableView tblFactura;

    @FXML
    private TableColumn colNumeroFactura;

    @FXML
    private TableColumn colEstado;

    @FXML
    private TableColumn colTotalF;

    @FXML
    private TableColumn colFechaF;

    @FXML
    private TableColumn colClienteID;

    @FXML
    private TableColumn colCodigoEmpleado;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbClienteID.setItems(getClientes());
        cmbCodigoEmpleado.setItems(getEmpleado());
        cmbClienteID.setDisable(true);
        cmbCodigoEmpleado.setDisable(true);
    }    
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void cargarDatos() {
        DesactivarControles();
        tblFactura.setItems(getFactura());
        colNumeroFactura.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("numeroDeFactura"));
        colEstado.setCellValueFactory(new PropertyValueFactory<Factura, String>("estado"));
        colTotalF.setCellValueFactory(new PropertyValueFactory<Factura, Double>("totalFactura"));
        colFechaF.setCellValueFactory(new PropertyValueFactory<Factura, String>("fechaFactura"));
        colClienteID.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("clienteID"));
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<Factura, Integer>("codigoEmpleado"));
    }
    
    public void seleccionarElemento() {
        txtNumeroFactura.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getNumeroDeFactura()));
        txtEstado.setText(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getEstado());
        txtTotalFactura.setText(String.valueOf(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getTotalFactura()));
        txtFechaFactura.setText(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getFechaFactura());
        cmbClienteID.getSelectionModel().select(buscarClientes(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getClienteID()));
        cmbCodigoEmpleado.getSelectionModel().select(buscarEmpleados(((Factura) tblFactura.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
    }
    
    public Clientes buscarClientes(int clienteID) {
        Clientes result = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_buscarCliente(?)}");
            procedimiento.setInt(1, clienteID);

            ResultSet registro = procedimiento.executeQuery();

            while (registro.next()) {
                result = new Clientes(registro.getInt("clienteID"),
                        registro.getString("nitCliente"),
                        registro.getString("nombresCliente"),
                        registro.getString("apellidosCliente"),
                        registro.getString("direccionCliente"),
                        registro.getString("telefonoCliente"),
                        registro.getString("correoCliente"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
        public Empleado buscarEmpleados(int codigoEmpleado) {
        Empleado result = null;
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_buscarEmpleado(?)}");
            procedimiento.setInt(1, codigoEmpleado);

            ResultSet registro = procedimiento.executeQuery();

            while (registro.next()) {
                result = new Empleado(registro.getInt("codigoEmpleado"),
                        registro.getString("nombresEmpleado"),
                        registro.getString("apellidosEmpleado"),
                        registro.getDouble("sueldo"),
                        registro.getString("direccion"),
                        registro.getString("turno"),
                        registro.getInt("codigoCargoEmpleado"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    
    public ObservableList<Factura> getFactura() {
        ArrayList<Factura> lista = new ArrayList<Factura>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarFacturas()}");
            ResultSet resultado = procedimiento.executeQuery();

            while (resultado.next()) {
                lista.add(new Factura(resultado.getInt("numeroDeFactura"),
                        resultado.getString("estado"),
                        resultado.getDouble("totalFactura"),
                        resultado.getString("fechaFactura"),
                        resultado.getInt("clienteID"),
                        resultado.getInt("codigoEmpleado")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listarFacturas = FXCollections.observableList(lista);
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
    
    public void reporte() {
        switch (tipoDeOperaciones) {
            case ACTUALIZAR:
                imgEditar.setImage(new Image("/org/emiliolux/images/Editar.png"));
                imgReporte.setImage(new Image("/org/emiliolux/images/ReportesClientes.png"));
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
        int factID = Integer.valueOf(((Factura)tblFactura.getSelectionModel().getSelectedItem()).getNumeroDeFactura());
        parametros.put("factID",factID);
        GenerarReportes.mostrarReportes("ReporteFactura.jasper", "Reporte de Factura", parametros);
    }
    
    
    
    public void DesactivarControles() {
        txtNumeroFactura.setEditable(false);
        txtEstado.setEditable(false);
        txtTotalFactura.setEditable(false);
        txtFechaFactura.setEditable(false);
        cmbClienteID.setDisable(true);
        cmbCodigoEmpleado.setDisable(true);
    }

    public void ActivarControles() {
        txtNumeroFactura.setEditable(true);
        txtEstado.setEditable(true);
        txtTotalFactura.setEditable(true);
        txtFechaFactura.setEditable(true);
        cmbClienteID.setDisable(false);
        cmbCodigoEmpleado.setDisable(false);
    }

    public void LimpiarControles() {
        txtNumeroFactura.clear();
        txtEstado.clear();
        txtTotalFactura.clear();
        txtFechaFactura.clear();
        tblFactura.getSelectionModel().getSelectedItem();
        cmbClienteID.getSelectionModel().clearSelection();
        cmbCodigoEmpleado.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        }
    }
    
}
