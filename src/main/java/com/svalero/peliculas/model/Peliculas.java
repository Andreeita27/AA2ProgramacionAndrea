package com.svalero.peliculas.model;

import lombok.Data;

@Data
public class Peliculas {
    private int idPelicula;
    private String titulo;
    private String sinopsis;
    private int duracion;
    private String fechaEstreno;
    private boolean disponibleStreaming;
    private String imagen;
    private int idDirector;

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public boolean isDisponibleStreaming() {
        return disponibleStreaming;
    }

    public void setDisponibleStreaming(boolean disponibleStreaming) {
        this.disponibleStreaming = disponibleStreaming;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }
}
