package com.svalero.peliculas.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Directores {
    private int idDirector;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    private boolean retirado;
    private String imagen;

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isRetirado() {
        return retirado;
    }

    public void setRetirado(boolean retirado) {
        this.retirado = retirado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
