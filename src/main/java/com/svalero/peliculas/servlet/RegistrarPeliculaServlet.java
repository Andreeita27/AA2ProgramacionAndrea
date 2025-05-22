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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet("/registrar-pelicula")
@MultipartConfig
public class RegistrarPeliculaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String sinopsis = request.getParameter("sinopsis");
        String duracionStr = request.getParameter("duracion");
        String fechaEstrenoStr = request.getParameter("fechaEstreno");
        String idDirectorStr = request.getParameter("idDirector");
        boolean disponibleStreaming = request.getParameter("disponibleStreaming") != null;

        List<String> errores = new ArrayList<>();

        if (titulo == null || titulo.trim().isEmpty()) {
            errores.add("El título no puede estar vacío");
        }

        int duracion = 0;
        try {
            duracion = Integer.parseInt(duracionStr);
            if (duracion <= 0) {
                errores.add("La duración debe ser mayor que 0");
            }
        } catch (NumberFormatException e) {
            errores.add("La duración debe ser un número válido");
        }

        if (!errores.isEmpty()) {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("formulario-pelicula.jsp").forward(request, response);
            return;
        }

        try {
            Database database = new Database();
            database.connect();
            PeliculasDao peliculasDao = new PeliculasDao(database.getConnection());

            Date fechaEstreno = Date.valueOf(fechaEstrenoStr);
            int idDirector = Integer.parseInt(idDirectorStr);

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

            response.sendRedirect("listar-peliculas");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar la película: " + e.getMessage());
        }
    }
}

