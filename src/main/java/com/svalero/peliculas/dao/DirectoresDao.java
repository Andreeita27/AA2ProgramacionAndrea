package com.svalero.peliculas.dao;

import com.svalero.peliculas.model.Directores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DirectoresDao {

    private final Connection connection;

    public DirectoresDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Directores director) throws SQLException {
        String sql = "INSERT INTO directores (nombre, nacionalidad, fecha_nacimiento, retirado, imagen) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, director.getNombre());
        statement.setString(2, director.getNacionalidad());
        statement.setDate(3, director.getFechaNacimiento());
        statement.setBoolean(4, director.isRetirado());
        statement.setString(5, director.getImagen());

        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }
}
