/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emiliolux.bean;

/**
 *
 * @author informatica
 */
public class TipoProducto {

    private int codigoTipoProducto;
    private String descripcion;

    public TipoProducto() {
    }

    public TipoProducto(int codigoTipoProducto, String descripcion) {
        this.codigoTipoProducto = codigoTipoProducto;
        this.descripcion = descripcion;
    }

    public int getCodigoTipoProducto() {
        return codigoTipoProducto;
    }

    public void setCodigoTipoProducto(int codigoTipoProducto) {
        this.codigoTipoProducto = codigoTipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override 
    public String toString(){
        return getCodigoTipoProducto() + "  |  " + getDescripcion();
    }

}
