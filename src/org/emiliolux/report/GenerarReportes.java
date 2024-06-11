package org.emiliolux.report;

import java.io.InputStream;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.emiliolux.db.Conexion;

/**
 *
 * @author Emilio Lux
 */
public class GenerarReportes {
    public static void mostrarReportes(String nombreReporte, String titulo, Map parametros){
        InputStream reporte = GenerarReportes.class.getResourceAsStream(nombreReporte);
        try{
            JasperReport ReporteCliente = (JasperReport)JRLoader.loadObject(reporte);
            JasperPrint reporteImpreso = JasperFillManager.fillReport(ReporteCliente, parametros, Conexion.getInstance().getConexion());
            JasperViewer visor = new JasperViewer(reporteImpreso, false);
            visor.setTitle(titulo);
            visor.setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

/*
Inteface map 

    HashMap es uno de los objetos que implementa un conjunto key-value.
    Tiene un constructor sin parametros, 
    New HashMap()
    Su finalidad suele referirse para agrupar información en un unico objeto

    Tiene cierta similitud con la colección de objetos arraylist pero con la diferencia que estos no tienen orden.
    Los agrupa a su conveniencia. 

Hash
    Hace referencia a una tecnica de organización de archivos, hashing en la cual se almacena registrros 
    en una dirección que es generada por una función que generalmente se aplica a la llave del registro

JAVA 
    El hashmap posee un espacio de memoria cuando se aguarda un objeto en dicho espacio se determina
    su direccion aplicando una función a la llave que le indiquemos nosotro. Cada objeto se identifica mediante algun 
    identificador apropiado. 
*/