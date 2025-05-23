package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.PeliculasDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.PeliculaNoEncontradaExcepcion;
import com.svalero.peliculas.model.Peliculas;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/editar-pelicula")
@MultipartConfig
public class EditarPeliculaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            PeliculasDao peliculasDao = new PeliculasDao(database.getConnection());
            Peliculas pelicula = peliculasDao.get(id);

            request.setAttribute("pelicula", pelicula);
            request.setAttribute("action", "Modificar");
            request.getRequestDispatcher("formulario-pelicula.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("ID inválido");
        } catch (PeliculaNoEncontradaExcepcion e) {
            response.getWriter().println("Película no encontrada");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
            String titulo = request.getParameter("titulo");
            String sinopsis = request.getParameter("sinopsis");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            Date fechaEstreno = Date.valueOf(request.getParameter("fechaEstreno"));
            int idDirector = Integer.parseInt(request.getParameter("idDirector"));
            boolean disponibleStreaming = request.getParameter("disponibleStreaming") != null;

            String nombreArchivo = request.getParameter("imagenActual");
            Part imagen = request.getPart("imagen");
            if (imagen != null && imagen.getSize() > 0) {
                nombreArchivo = UUID.randomUUID() + ".jpg";
                String ruta = getServletContext().getInitParameter("imagePath");
                InputStream inputStream = imagen.getInputStream();
                Files.copy(inputStream, Path.of(ruta + File.separator + nombreArchivo));
            }

            Peliculas pelicula = new Peliculas();
            pelicula.setIdPelicula(idPelicula);
            pelicula.setTitulo(titulo);
            pelicula.setSinopsis(sinopsis);
            pelicula.setDuracion(duracion);
            pelicula.setFechaEstreno(fechaEstreno);
            pelicula.setIdDirector(idDirector);
            pelicula.setDisponibleStreaming(disponibleStreaming);
            pelicula.setImagen(nombreArchivo);

            Database database = new Database();
            database.connect();
            PeliculasDao peliculasDao = new PeliculasDao(database.getConnection());

            peliculasDao.modify(pelicula);

            response.sendRedirect("listar-peliculas");

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error al editar la película: " + e.getMessage());
        }
    }
}


