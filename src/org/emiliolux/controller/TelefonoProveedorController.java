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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.emiliolux.bean.Proveedores;
import org.emiliolux.bean.TelefonoProveedor;
import org.emiliolux.db.Conexion;
import org.emiliolux.system.Principal;

/**
 * FXML Controller class
 *
 * @author emilio
 */
public class TelefonoProveedorController implements Initializable {

    private Principal escenarioPrincipal;

    private enum operaciones {
        AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO
    }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;

    private ObservableList<TelefonoProveedor> listaTelefonoProveedor;
    private ObservableList<Proveedores> listaProveedores;

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
        cargarDatos();
        cmbCodigoProveedor.setDisable(true);
        cmbCodigoProveedor.setItems(getProveedores());

    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cargarDatos() {
        DesactivarControles();
        tblTelefonoPro.setItems(getTelefonoProveedor());
        colcodigoTelefonoP.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, Integer>("codigoTelefonoProveedor"));
        colnumeroPr.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, String>("numeroPrincipal"));
        colnumeroS.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, String>("numeroSecundario"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, String>("observaciones"));
        colcodigoProveedor.setCellValueFactory(new PropertyValueFactory<TelefonoProveedor, Integer>("codigoProveedor"));
    }

    public void seleccionarElemento() {
        txtCodigoTelefonoP.setText(String.valueOf(((TelefonoProveedor) tblTelefonoPro.getSelectionModel().getSelectedItem()).getCodigoTelefonoProveedor()));
        txtNumeroP.setText(((TelefonoProveedor) tblTelefonoPro.getSelectionModel().getSelectedItem()).getNumeroPrincipal());
        txtNumeroS.setText(((TelefonoProveedor) tblTelefonoPro.getSelectionModel().getSelectedItem()).getNumeroSecundario());
        txtObservaciones.setText(((TelefonoProveedor) tblTelefonoPro.getSelectionModel().getSelectedItem()).getObservaciones());
        cmbCodigoProveedor.getSelectionModel().select(buscarProveedor(((TelefonoProveedor) tblTelefonoPro.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
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

    public ObservableList<TelefonoProveedor> getTelefonoProveedor() {
        ArrayList<TelefonoProveedor> lista = new ArrayList<TelefonoProveedor>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarTelefonoProveedores()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new TelefonoProveedor(resultado.getInt("codigoTelefonoProveedor"),
                        resultado.getString("numeroPrincipal"),
                        resultado.getString("numeroSecundario"),
                        resultado.getString("observaciones"),
                        resultado.getInt("codigoProveedor")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTelefonoProveedor = FXCollections.observableList(lista);
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
            //cargarDatos();
            case NINGUNO:
                break;
        }
    }

    public void DesactivarControles() {
        txtCodigoTelefonoP.setEditable(false);
        txtNumeroP.setEditable(false);
        txtNumeroS.setEditable(false);
        txtObservaciones.setEditable(false);
        cmbCodigoProveedor.setDisable(true);
    }

    public void ActivarControles() {
        txtCodigoTelefonoP.setEditable(true);
        txtNumeroP.setEditable(true);
        txtNumeroS.setEditable(true);
        txtObservaciones.setEditable(true);
        cmbCodigoProveedor.setDisable(false);
    }

    public void LimpiarControles() {
        txtCodigoTelefonoP.clear();
        txtNumeroP.clear();
        txtNumeroS.clear();
        txtObservaciones.clear();
        tblTelefonoPro.getSelectionModel().getSelectedItem();
        cmbCodigoProveedor.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        }
    }

}
