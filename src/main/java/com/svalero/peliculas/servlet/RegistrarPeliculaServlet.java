package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.PeliculasDao;
import com.svalero.peliculas.database.Database;
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

@WebServlet("/registrar-pelicula")
@MultipartConfig
public class RegistrarPeliculaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            Database database = new Database();
            database.connect();
            PeliculasDao peliculasDao = new PeliculasDao(database.getConnection());

            String titulo = request.getParameter("titulo");
            String sinopsis = request.getParameter("sinopsis");
            int duracion = Integer.parseInt(request.getParameter("duracion"));
            Date fechaEstreno = Date.valueOf(request.getParameter("fechaEstreno"));
            int idDirector = Integer.parseInt(request.getParameter("idDirector"));
            boolean disponibleStreaming = request.getParameter("disponibleStreaming") != null;

            Part imagen = request.getPart("imagen");
            String nombreArchivo = "default.jpg";
            if (imagen != null && imagen.getSize() > 0) {
                nombreArchivo = UUID.randomUUID() + ".jpg";
                String ruta = "C:/apache-tomcat-9.0.105/apache-tomcat-9.0.105/webapps/peliculas/images";
                InputStream inputStream = imagen.getInputStream();
                Files.copy(inputStream, Path.of(ruta + File.separator + nombreArchivo));
            }

            Peliculas pelicula = new Peliculas();
            pelicula.setTitulo(titulo);
            pelicula.setSinopsis(sinopsis);
            pelicula.setDuracion(duracion);
            pelicula.setFechaEstreno(fechaEstreno);
            pelicula.setDisponibleStreaming(disponibleStreaming);
            pelicula.setImagen(nombreArchivo);
            pelicula.setIdDirector(idDirector);

            peliculasDao.add(pelicula);

            response.sendRedirect("listar-peliculas.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar la película: " + e.getMessage());
        }
    }
}
