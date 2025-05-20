package com.svalero.peliculas.dao;

import com.svalero.peliculas.model.Peliculas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PeliculasDao {
    private Connection connection;

    public PeliculasDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Peliculas pelicula) throws SQLException {
        String sql = "INSERT INTO Peliculas (titulo, sinopsis, duracion, fecha_estreno, disponible_streaming, imagen, id_director) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, pelicula.getTitulo());
        statement.setString(2, pelicula.getSinopsis());
        statement.setInt(3, pelicula.getDuracion());
        statement.setDate(4, pelicula.getFechaEstreno());
        statement.setBoolean(5, pelicula.isDisponibleStreaming());
        statement.setString(6, pelicula.getImagen());
        statement.setInt(7, pelicula.getIdDirector());

        int affectedRows = statement.executeUpdate();
        statement.close();

        return affectedRows != 0;
    }


}
