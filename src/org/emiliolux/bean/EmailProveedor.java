/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emiliolux.bean;

/**
 *
 * @author emili
 */
public class EmailProveedor {

    private int codigoEmailProveedor;
    private String emailproveedor;
    private String descripcion;
    private int codigoProveedor;

    public EmailProveedor() {
    }

    public EmailProveedor(int codigoEmailProveedor, String emailproveedor, String descripcion, int codigoProveedor) {
        this.codigoEmailProveedor = codigoEmailProveedor;
        this.emailproveedor = emailproveedor;
        this.descripcion = descripcion;
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoEmailProveedor() {
        return codigoEmailProveedor;
    }

    public void setCodigoEmailProveedor(int codigoEmailProveedor) {
        this.codigoEmailProveedor = codigoEmailProveedor;
    }

    public String getEmailproveedor() {
        return emailproveedor;
    }

    public void setEmailproveedor(String emailproveedor) {
        this.emailproveedor = emailproveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

}
