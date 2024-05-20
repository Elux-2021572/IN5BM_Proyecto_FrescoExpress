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
import javax.swing.JOptionPane;
import org.emiliolux.bean.EmailProveedor;
import org.emiliolux.bean.Proveedores;
import org.emiliolux.db.Conexion;
import org.emiliolux.system.Principal;

public class EmailProveedorController implements Initializable {

    private Principal escenarioPrincipal;

    private enum operaciones { AGREGAR, ELIMINAR, EDITAR, ACTUALIZAR, CANCELAR, NINGUNO }
    private operaciones tipoDeOperaciones = operaciones.NINGUNO;

    private ObservableList<EmailProveedor> listaEmailProveedor;
    private ObservableList<Proveedores> listaProveedores;

  @FXML
    private Button btnRegresar;

    @FXML
    private TableView tblEmailPro;

    @FXML
    private TableColumn colCodigoEmailP;

    @FXML
    private TableColumn colEmailP;

    @FXML
    private TableColumn colDescripcion;

    @FXML
    private TableColumn colCodigoP;

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
    private TextField txtCodigoEmailP;

    @FXML
    private TextField txtEmailP;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private ComboBox cmbCodigoP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
        cmbCodigoP.setItems(getProveedores());
        cmbCodigoP.setDisable(true);
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void cargarDatos() {
        DesactivarControles();
        tblEmailPro.setItems(getEmailProveedor());
        colCodigoEmailP.setCellValueFactory(new PropertyValueFactory<EmailProveedor , Integer>("codigoEmailProveedor"));
        colEmailP.setCellValueFactory(new PropertyValueFactory<EmailProveedor , String>("emailproveedor"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<EmailProveedor , String>("descripcion"));
        colCodigoP.setCellValueFactory(new PropertyValueFactory<EmailProveedor , Integer>("codigoProveedor"));
    }
    
    public void seleccionarElemento() {
        txtCodigoEmailP.setText(String.valueOf(((EmailProveedor) tblEmailPro.getSelectionModel().getSelectedItem()).getCodigoEmailProveedor()));
        txtEmailP.setText(((EmailProveedor) tblEmailPro.getSelectionModel().getSelectedItem()).getEmailproveedor());
        txtDescripcion.setText(((EmailProveedor) tblEmailPro.getSelectionModel().getSelectedItem()).getDescripcion());
        cmbCodigoP.getSelectionModel().select(buscarProveedor(((EmailProveedor)tblEmailPro.getSelectionModel().getSelectedItem()).getCodigoProveedor()));
    }


    public Proveedores buscarProveedor(int codigoProveedor){
        Proveedores result=null;
        try{
           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_buscarProveedor(?)}");
           procedimiento.setInt(1, codigoProveedor);
           ResultSet registro = procedimiento.executeQuery();
           while(registro.next()){
               result= new Proveedores(registro.getInt("codigoProveedor"),
               registro.getString("codigoProveedor"),
               registro.getString("nombresProveedor"),
               registro.getString("apellidosProveedor"),
               registro.getString("direccionProveedor"),
               registro.getString("razonSocial"),
               registro.getString("contactoPrincipal"),
               registro.getString("paginaWeb"));
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public ObservableList<EmailProveedor> getEmailProveedor() {
        ArrayList<EmailProveedor> lista = new ArrayList<EmailProveedor>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_listarEmailProveedores()}");
            ResultSet resultado = procedimiento.executeQuery();
            while (resultado.next()) {
                lista.add(new EmailProveedor(
                    resultado.getInt("codigoEmailProveedor"),
                    resultado.getString("emailProveedor"),
                    resultado.getString("descripcion"),
                    resultado.getInt("codigoProveedor")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmailProveedor = FXCollections.observableList(lista);
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
    

    public void agregar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                ActivarControles();
                btnEliminar.setText("Cancelar");
                btnAgregar.setText("Guardar");
                imgAgregar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                txtCodigoEmailP.setDisable(true);
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
                imgAgregar.setImage(new Image("/org/emiliolux/images/Agregar.png"));
                imgEliminar.setImage(new Image("/org/emiliolux/images/Eliminar.png"));
                btnReportes.setDisable(false);
                btnEditar.setDisable(false);
                btnRegresar.setDisable(false);
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
        }
    }

    public void guardar() {
        EmailProveedor registro = new EmailProveedor();
        registro.setEmailproveedor(txtEmailP.getText());
        registro.setDescripcion(txtDescripcion.getText());
        registro.setCodigoProveedor(((Proveedores) cmbCodigoP.getSelectionModel().getSelectedItem()).getCodigoProveedor());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_agregarEmailProveedor(?, ?, ?)}");
            procedimiento.setString(1, registro.getEmailproveedor());
            procedimiento.setString(2, registro.getDescripcion());
            procedimiento.setInt(3, registro.getCodigoProveedor());
            procedimiento.execute();
            listaEmailProveedor.add(registro);
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
                if (tblEmailPro.getSelectionModel().getSelectedItem() != null) {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?", "Eliminar EmailProveedor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resp == JOptionPane.YES_OPTION) {
                        try {
                            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_eliminarEmailProveedor(?)}");
                            procedimiento.setInt(1, ((EmailProveedor) tblEmailPro.getSelectionModel().getSelectedItem()).getCodigoEmailProveedor());
                            boolean execute = procedimiento.execute();
                            listaEmailProveedor.remove(tblEmailPro.getSelectionModel().getSelectedItem());
                            LimpiarControles();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un EmailProveedor para eliminar");
                }
                break;
        }
    }

    public void editar() {
        switch (tipoDeOperaciones) {
            case NINGUNO:
                if (tblEmailPro.getSelectionModel().getSelectedItem() != null) {
                    imgReportes.setImage(new Image("/org/emiliolux/images/Cancelar.png"));
                    imgEditar.setImage(new Image("/org/emiliolux/images/Guardar.png"));
                    btnReportes.setText("Cancelar");
                    btnEditar.setText("Actualizar");
                    btnAgregar.setDisable(true);
                    btnEliminar.setDisable(true);
                    btnRegresar.setDisable(true);
                    ActivarControles();
                    tipoDeOperaciones = operaciones.ACTUALIZAR;
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un EmailProveedor para poder editar");
                }
                break;
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
                cargarDatos();
                tipoDeOperaciones = operaciones.NINGUNO;
                break;
        }
    }

    public void actualizar() {
            EmailProveedor registro = (EmailProveedor)tblEmailPro.getSelectionModel().getSelectedItem();
            registro.setEmailproveedor(txtEmailP.getText());
            registro.setDescripcion(txtDescripcion.getText());
            registro.setCodigoProveedor(((Proveedores) cmbCodigoP.getSelectionModel().getSelectedItem()).getCodigoProveedor());
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_actualizarEmailProveedor(?, ?, ?, ?)}");
            procedimiento.setInt(1, registro.getCodigoEmailProveedor());
            procedimiento.setString(2, registro.getEmailproveedor());
            procedimiento.setString(3, registro.getDescripcion());
            procedimiento.setInt(4, registro.getCodigoProveedor());
            procedimiento.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DesactivarControles() {
        txtCodigoEmailP.setEditable(false);
        txtEmailP.setEditable(false);
        txtDescripcion.setEditable(false);
        cmbCodigoP.setDisable(true);
    }

    public void ActivarControles() {
        txtCodigoEmailP.setEditable(false);
        txtEmailP.setEditable(true);
        txtDescripcion.setEditable(true);
        cmbCodigoP.setDisable(false);
    }

    public void LimpiarControles() {
        txtCodigoEmailP.clear();
        txtEmailP.clear();
        txtDescripcion.clear();
        cmbCodigoP.getSelectionModel().clearSelection();
    }


    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            escenarioPrincipal.menuPrincipalView();
        } 
    }
}
