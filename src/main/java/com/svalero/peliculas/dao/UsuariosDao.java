package com.svalero.peliculas.dao;

import com.svalero.peliculas.model.Usuarios;
import com.svalero.peliculas.exception.UsuarioNoEncontradoExcepcion;
import com.svalero.peliculas.util.HashUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDao {

    private Connection connection;

    public UsuariosDao(Connection connection) {
        this.connection = connection;
    }

    public Usuarios getUsuarioByEmailAndPassword(String email, String password) throws SQLException, UsuarioNoEncontradoExcepcion {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contraseña = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();

        if (!result.next()){
            throw new UsuarioNoEncontradoExcepcion();
        }

        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(result.getInt("id_usuario"));
        usuario.setNombre(result.getString("nombre"));
        usuario.setEmail(result.getString("email"));
        usuario.setRol(result.getString("rol"));
        usuario.setFechaRegistro(result.getDate("fecha_registro"));
        usuario.setActivo(result.getBoolean("activo"));

        return usuario;
    }

    public void update(Usuarios usuario) throws SQLException {
        String sql;
        PreparedStatement statement;

        if (usuario.getContraseña() != null && !usuario.getContraseña().isBlank()) {
            sql = "UPDATE usuarios SET nombre = ?, email = ?, contraseña = ? WHERE id_usuario = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, HashUtil.sha1(usuario.getContraseña()));
            statement.setInt(4, usuario.getIdUsuario());
        } else {
            sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id_usuario = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setInt(3, usuario.getIdUsuario());
        }

        statement.executeUpdate();
    }

    public boolean add(Usuarios usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nombre, email, contraseña, rol, fecha_registro, activo) " +
                "VALUES (?, ?, ?, 'usuario', CURRENT_DATE, TRUE)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNombre());
        statement.setString(2, usuario.getEmail());
        statement.setString(3, HashUtil.sha1(usuario.getContraseña()));

        int affectedRows = statement.executeUpdate();
        statement.close();

        return affectedRows != 0;
    }

    public boolean delete(int idUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idUsuario);
        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }
}