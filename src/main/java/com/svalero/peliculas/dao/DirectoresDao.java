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
        String sql = "INSERT INTO directores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, director.getNombre());
        statement.setString(2, director.getNacionalidad());
        statement.setDate(3, director.getFechaNacimiento());
        statement.setInt(4, director.getNPeliculas());
        statement.setBoolean(5, director.isRetirado());
        statement.setString(6, director.getImagen());

        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }

    public List<Directores> getAll(int page, String nombre, String retirado) throws SQLException {
        List<Directores> directores = new ArrayList<>();
        String sql = "SELECT * FROM directores WHERE 1=1";

        if (nombre != null && !nombre.isEmpty()) {
            sql += " AND nombre LIKE ?";
        }
        if (retirado != null && !retirado.isEmpty()) {
            sql += " AND retirado = ?";
        }

        sql += " ORDER BY nombre LIMIT ?, ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        int paramIndex = 1;
        if (nombre != null && !nombre.isEmpty()) {
            statement.setString(paramIndex++, "%" + nombre + "%");
        }
        if (retirado != null && !retirado.isEmpty()) {
            statement.setBoolean(paramIndex++, Boolean.parseBoolean(retirado));
        }

        statement.setInt(paramIndex++, (page - 1) * PAGE_SIZE);
        statement.setInt(paramIndex, PAGE_SIZE);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Directores director = new Directores();
                director.setIdDirector(resultSet.getInt("id_director"));
                director.setNombre(resultSet.getString("nombre"));
                director.setNacionalidad(resultSet.getString("nacionalidad"));
                director.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                director.setNPeliculas(resultSet.getInt("numero_peliculas"));
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
        director.setNPeliculas(result.getInt("numero_peliculas"));
        director.setRetirado(result.getBoolean("retirado"));
        director.setImagen(result.getString("imagen"));

        return director;
    }

    public boolean modify(Directores director) throws SQLException {
        String sql = "UPDATE directores SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ?, numero_peliculas = ?, retirado = ?, imagen = ? WHERE id_director = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, director.getNombre());
        statement.setString(2, director.getNacionalidad());
        statement.setDate(3, director.getFechaNacimiento());
        statement.setInt(4, director.getNPeliculas());
        statement.setBoolean(5, director.isRetirado());
        statement.setString(6, director.getImagen());
        statement.setInt(7, director.getIdDirector());

        int affectedRows = statement.executeUpdate();
        statement.close();

        return affectedRows != 0;
    }

    public boolean delete(int idDirector) throws SQLException {
        String sql = "DELETE FROM directores WHERE id_director = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDirector);
        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }

}
