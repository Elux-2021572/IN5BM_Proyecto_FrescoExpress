package org.emiliolux.system;

import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.emiliolux.controller.CargoEmpleadoController;
import org.emiliolux.controller.DetalleCompraController;
import org.emiliolux.controller.DetalleFacturaController;
import org.emiliolux.controller.EmailProveedorController;
import org.emiliolux.controller.EmpleadoController;
import org.emiliolux.controller.FacturaController;
import org.emiliolux.controller.MenuClientesController;
import org.emiliolux.controller.MenuComprasController;
import org.emiliolux.controller.MenuPrincipalController;
import org.emiliolux.controller.MenuProgramadorController;
import org.emiliolux.controller.MenuProveedoresController;
import org.emiliolux.controller.MenuTipoProductoController;
import org.emiliolux.controller.ProductosController;
import org.emiliolux.controller.TelefonoProveedorController;

/**
 * Documentacion Nombre completo:Emilio José Lux Zapeta
 *
 * @author Emilio Lux
 */
public class Principal extends Application {

    private Stage escenarioPrincipal;
    private Scene escena;
    private final String URLVIEW = "/org/emiliolux/view/";

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("FRESCO EXPRESS");
        escenarioPrincipal.getIcons().add(new Image(Principal.class.getResourceAsStream("/org/emiliolux/images/LogoExpress sin fondo.png")));
        menuPrincipalView();
        escenarioPrincipal.show();
    }

    public Initializable cambiarEscena(String fxmlName, int width, int heigth) throws Exception {
        Initializable resultado;
        FXMLLoader loader = new FXMLLoader();

        InputStream file = Principal.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Principal.class.getResource(URLVIEW + fxmlName));

        escena = new Scene((AnchorPane) loader.load(file), width, heigth);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.sizeToScene();

        resultado = (Initializable) loader.getController();

        return resultado;
    }

    public void menuPrincipalView() {
        try {
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController) cambiarEscena("MenuPrincipalView.fxml", 1072, 617);
            menuPrincipalView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void menuClientesView() {
        try {
            MenuClientesController menuClienteView = (MenuClientesController) cambiarEscena("MenuClientesView.fxml", 1180, 679);
            menuClienteView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }
    
    public void menuCargoEmpleadoView(){
        try{
            CargoEmpleadoController CargoEmpleadoView = (CargoEmpleadoController) cambiarEscena("CargoEmpleadoView.fxml", 1074,618);
            CargoEmpleadoView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    public void menuProgramadorView() {
        try {
            MenuProgramadorController menuProgramadorView = (MenuProgramadorController) cambiarEscena("MenuProgramadorView.fxml", 1080, 623);
            menuProgramadorView.setEscenarioPrincipal(this);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void menuComprasView() {
        try {
            MenuComprasController menuComprasView = (MenuComprasController) cambiarEscena("MenuComprasView.fxml", 1168, 669);
            menuComprasView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void menuProveedoresView() {
        try {
            MenuProveedoresController menuProveedoresView = (MenuProveedoresController) cambiarEscena("MenuProveedoresView.fxml", 1278, 735);
            menuProveedoresView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void menuTipoProductoView() {
        try {
            MenuTipoProductoController menuTipoProductoView = (MenuTipoProductoController) cambiarEscena("MenuTipoProductoView.fxml", 1129, 649);
            menuTipoProductoView.setEscenarioPrincipal(this);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuProductoView(){
        try{
            ProductosController productoView = (ProductosController) cambiarEscena("ProductosView.fxml", 1323, 761);
            productoView.setEscenarioPrincipal(this);
        
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuDetalleCompraView(){
        try{
            DetalleCompraController DetalleCompra = (DetalleCompraController) cambiarEscena("DetalleCompra.fxml",1323,761);
            DetalleCompra.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuDetalleFacturaView(){
        try{
            DetalleFacturaController  menuDetalleFacturaView = (DetalleFacturaController) cambiarEscena("DetalleFacturaView.fxml", 1323, 761);
            menuDetalleFacturaView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuFacturaView(){
        try{
            FacturaController menuFacturaView = (FacturaController) cambiarEscena("FacturaView.fxml" , 1323,761);
            menuFacturaView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuEmpleadoView(){
        try{
            EmpleadoController menuEmpleadoView = (EmpleadoController)cambiarEscena("EmpleadoView.fxml" ,1323, 761);
            menuEmpleadoView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuTelefonoProView(){
        try{
            TelefonoProveedorController menuTelefonoProView =(TelefonoProveedorController)cambiarEscena("TelefonoProveedorView.fxml" , 1323, 761);
            menuTelefonoProView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    public void menuEmailProView(){
        try{
            EmailProveedorController menuEmailProView =(EmailProveedorController)cambiarEscena("EmailProveedorView.fxml" , 1323, 761);
            menuEmailProView.setEscenarioPrincipal(this);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
