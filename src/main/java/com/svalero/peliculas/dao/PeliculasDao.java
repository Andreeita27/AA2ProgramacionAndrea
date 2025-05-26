package com.svalero.peliculas.dao;

import com.svalero.peliculas.exception.PeliculaNoEncontradaExcepcion;
import com.svalero.peliculas.model.Peliculas;

import java.sql.*;
import java.util.ArrayList;

import static com.svalero.peliculas.util.Constant.PAGE_SIZE;

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
    public ArrayList<Peliculas> getAll(int page, String titulo, String streaming) throws SQLException {
        String sql = "SELECT * FROM peliculas WHERE 1=1";

        if (titulo != null && !titulo.isEmpty()) {
            sql += " AND titulo LIKE ?";
        }
        if (streaming != null && !streaming.isEmpty()) {
            sql += " AND disponible_streaming = ?";
        }

        sql += " ORDER BY titulo LIMIT ?, ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        int paramIndex = 1;
        if (titulo != null && !titulo.isEmpty()) {
            statement.setString(paramIndex++, "%" + titulo + "%");
        }
        if (streaming != null && !streaming.isEmpty()) {
            statement.setBoolean(paramIndex++, Boolean.parseBoolean(streaming));
        }

        statement.setInt(paramIndex++, page * PAGE_SIZE);
        statement.setInt(paramIndex, PAGE_SIZE);

        ResultSet result = statement.executeQuery();
        ArrayList<Peliculas> peliculaList = new ArrayList<>();
        while (result.next()) {
            Peliculas pelicula = new Peliculas();
            pelicula.setIdPelicula(result.getInt("id_pelicula"));
            pelicula.setTitulo(result.getString("titulo"));
            pelicula.setSinopsis(result.getString("sinopsis"));
            pelicula.setDuracion(result.getInt("duracion"));
            pelicula.setFechaEstreno(result.getDate("fecha_estreno"));
            pelicula.setDisponibleStreaming(result.getBoolean("disponible_streaming"));
            pelicula.setImagen(result.getString("imagen"));
            pelicula.setIdDirector(result.getInt("id_director"));
            peliculaList.add(pelicula);
        }

        statement.close();
        return peliculaList;
    }

    public Peliculas get(int id) throws SQLException, PeliculaNoEncontradaExcepcion {
        String sql = "SELECT * FROM peliculas WHERE id_pelicula = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        if (!result.next()) {
            throw new PeliculaNoEncontradaExcepcion();
        }

        Peliculas pelicula = new Peliculas();
        pelicula.setIdPelicula(result.getInt("id_pelicula"));
        pelicula.setTitulo(result.getString("titulo"));
        pelicula.setSinopsis(result.getString("sinopsis"));
        pelicula.setDuracion(result.getInt("duracion"));
        pelicula.setFechaEstreno(result.getDate("fecha_estreno"));
        pelicula.setDisponibleStreaming(result.getBoolean("disponible_streaming"));
        pelicula.setImagen(result.getString("imagen"));
        pelicula.setIdDirector(result.getInt("id_director"));

        statement.close();
        return pelicula;
    }

    public boolean modify(Peliculas pelicula) throws SQLException {
        String sql = "UPDATE peliculas SET titulo = ?, sinopsis = ?, duracion = ?, fecha_estreno = ?, disponible_streaming = ?, imagen = ?, id_director = ? WHERE id_pelicula = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, pelicula.getTitulo());
        statement.setString(2, pelicula.getSinopsis());
        statement.setInt(3, pelicula.getDuracion());
        statement.setDate(4, pelicula.getFechaEstreno());
        statement.setBoolean(5, pelicula.isDisponibleStreaming());
        statement.setString(6, pelicula.getImagen());
        statement.setInt(7, pelicula.getIdDirector());
        statement.setInt(8, pelicula.getIdPelicula());

        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }

    public boolean delete(int idPelicula) throws SQLException {
        String sql = "DELETE FROM peliculas WHERE id_pelicula = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idPelicula);
        int affectedRows = statement.executeUpdate();
        statement.close();
        return affectedRows != 0;
    }
}
