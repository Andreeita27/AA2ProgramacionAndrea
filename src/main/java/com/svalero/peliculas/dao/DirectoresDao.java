package com.svalero.peliculas.dao;

import com.svalero.peliculas.exception.DirectorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Directores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.svalero.peliculas.util.Constant.PAGE_SIZE;

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

    public List<Directores> getAll(int page) throws SQLException {
        List<Directores> directores = new ArrayList<>();
        String sql = "SELECT * FROM directores ORDER BY nombre LIMIT ?, ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, page * PAGE_SIZE);
        statement.setInt(2, PAGE_SIZE);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Directores director = new Directores();
            director.setIdDirector(resultSet.getInt("id_director"));
            director.setNombre(resultSet.getString("nombre"));
            director.setNacionalidad(resultSet.getString("nacionalidad"));
            director.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            director.setRetirado(resultSet.getBoolean("retirado"));
            director.setImagen(resultSet.getString("imagen"));
            directores.add(director);
        }

        resultSet.close();
        statement.close();
        return directores;
    }

    public Directores get(int id) throws SQLException, DirectorNoEncontradoExcepcion {
        String sql = "SELECT * FROM directores WHERE id_director = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            throw new DirectorNoEncontradoExcepcion();
        }

        Directores director = new Directores();
        director.setIdDirector(result.getInt("id_director"));
        director.setNombre(result.getString("nombre"));
        director.setNacionalidad(result.getString("nacionalidad"));
        director.setFechaNacimiento(result.getDate("fecha_nacimiento"));
        director.setRetirado(result.getBoolean("retirado"));
        director.setImagen(result.getString("imagen"));

        return director;
    }

}
