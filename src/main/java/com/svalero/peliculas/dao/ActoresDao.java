package com.svalero.peliculas.dao;

import com.svalero.peliculas.exception.ActorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Actores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.svalero.peliculas.util.Constant.PAGE_SIZE;

public class ActoresDao {

    private final Connection connection;

    public ActoresDao(Connection connection) {
        this.connection = connection;
    }

    public boolean add(Actores actor) throws SQLException {
        String sql = "INSERT INTO actores (nombre, nacionalidad, fecha_nacimiento, numero_peliculas, retirado, imagen) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, actor.getNombre());
        statement.setString(2, actor.getNacionalidad());
        statement.setDate(3, actor.getFechaNacimiento());
        statement.setInt(4, actor.getNumeroPeliculas());
        statement.setBoolean(5, actor.isRetirado());
        statement.setString(6, actor.getImagen());

        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }

    public List<Actores> getAll(int page) throws SQLException {
        List<Actores> actores = new ArrayList<>();
        String sql = "SELECT * FROM actores ORDER BY nombre LIMIT ?, ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, page * PAGE_SIZE);
        statement.setInt(2, PAGE_SIZE);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Actores actor = new Actores();
            actor.setIdActor(resultSet.getInt("id_actor"));
            actor.setNombre(resultSet.getString("nombre"));
            actor.setNacionalidad(resultSet.getString("nacionalidad"));
            actor.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            actor.setNumeroPeliculas(resultSet.getInt("numero_peliculas"));
            actor.setRetirado(resultSet.getBoolean("retirado"));
            actor.setImagen(resultSet.getString("imagen"));
            actores.add(actor);
        }

        resultSet.close();
        statement.close();
        return actores;
    }

    public Actores get(int id) throws SQLException, ActorNoEncontradoExcepcion {
        String sql = "SELECT * FROM actores WHERE id_actor = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            throw new ActorNoEncontradoExcepcion();
        }

        Actores actor = new Actores();
        actor.setIdActor(result.getInt("id_actor"));
        actor.setNombre(result.getString("nombre"));
        actor.setNacionalidad(result.getString("nacionalidad"));
        actor.setFechaNacimiento(result.getDate("fecha_nacimiento"));
        actor.setNumeroPeliculas(result.getInt("numero_peliculas"));
        actor.setRetirado(result.getBoolean("retirado"));
        actor.setImagen(result.getString("imagen"));

        result.close();
        statement.close();
        return actor;
    }

    public boolean modify(Actores actor) throws SQLException {
        String sql = "UPDATE actores SET nombre = ?, nacionalidad = ?, fecha_nacimiento = ?, numero_peliculas = ?, retirado = ?, imagen = ? WHERE id_actor = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, actor.getNombre());
        statement.setString(2, actor.getNacionalidad());
        statement.setDate(3, actor.getFechaNacimiento());
        statement.setInt(4, actor.getNumeroPeliculas());
        statement.setBoolean(5, actor.isRetirado());
        statement.setString(6, actor.getImagen());
        statement.setInt(7, actor.getIdActor());

        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }

    public boolean delete(int idActor) throws SQLException {
        String sql = "DELETE FROM actores WHERE id_actor = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idActor);
        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }
}