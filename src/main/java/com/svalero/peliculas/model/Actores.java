package com.svalero.peliculas.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Actores {
    private int idActor;
    private String nombre;
    private String nacionalidad;
    private Date fechaNacimiento;
    private int numeroPeliculas;
    private boolean retirado;
    private String imagen;

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
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

    public int getNumeroPeliculas() {
        return numeroPeliculas;
    }

    public void setNumeroPeliculas(int numeroPeliculas) {
        this.numeroPeliculas = numeroPeliculas;
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
