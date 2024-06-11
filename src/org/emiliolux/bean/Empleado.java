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
public class Empleado {

    private int codigoEmpleado;
    private String nombresEmpleado;
    private String apellidosEmpleado;
    private double sueldo;
    private String direccion;
    private String turno;
    private int codigoCargoEmpleado;

    public Empleado() {
    }

    public Empleado(int codigoEmpleado, String nombresEmpleado, String apellidosEmpleado, double sueldo, String direccion, String turno, int codigoCargoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
        this.nombresEmpleado = nombresEmpleado;
        this.apellidosEmpleado = apellidosEmpleado;
        this.sueldo = sueldo;
        this.direccion = direccion;
        this.turno = turno;
        this.codigoCargoEmpleado = codigoCargoEmpleado;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombresEmpleado() {
        return nombresEmpleado;
    }

    public void setNombresEmpleado(String nombresEmpleado) {
        this.nombresEmpleado = nombresEmpleado;
    }

    public String getApellidosEmpleado() {
        return apellidosEmpleado;
    }

    public void setApellidosEmpleado(String apellidosEmpleado) {
        this.apellidosEmpleado = apellidosEmpleado;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getCodigoCargoEmpleado() {
        return codigoCargoEmpleado;
    }

    public void setCodigoCargoEmpleado(int codigoCargoEmpleado) {
        this.codigoCargoEmpleado = codigoCargoEmpleado;
    }
    
    @Override 
    public String toString(){
        return getNombresEmpleado() + "  |  " + getDireccion();
    }

}
