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
public class Factura {

    private int numeroDeFactura;
    private String estado;
    private double totalFactura;
    private double fechaFactura;
    private int clienteID;
    private int codigoEmpleado;

    public Factura() {
    }

    public Factura(int numeroDeFactura, String estado, double totalFactura, double fechaFactura, int clienteID, int codigoEmpleado) {
        this.numeroDeFactura = numeroDeFactura;
        this.estado = estado;
        this.totalFactura = totalFactura;
        this.fechaFactura = fechaFactura;
        this.clienteID = clienteID;
        this.codigoEmpleado = codigoEmpleado;
    }

    public int getNumeroDeFactura() {
        return numeroDeFactura;
    }

    public void setNumeroDeFactura(int numeroDeFactura) {
        this.numeroDeFactura = numeroDeFactura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public double getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(double fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

}
