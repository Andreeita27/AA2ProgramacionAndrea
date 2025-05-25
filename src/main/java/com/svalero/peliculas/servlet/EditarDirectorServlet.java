package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.DirectoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.DirectorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Directores;

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

@WebServlet("/editar-director")
@MultipartConfig
public class EditarDirectorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());
            Directores director = directoresDao.get(id);

            request.setAttribute("director", director);
            request.setAttribute("action", "Modificar");
            request.getRequestDispatcher("formulario-director.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("ID inválido");
        } catch (DirectorNoEncontradoExcepcion e) {
            response.getWriter().println("Director no encontrado");
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
            int idDirector = Integer.parseInt(request.getParameter("idDirector"));
            String nombre = request.getParameter("nombre");
            String nacionalidad = request.getParameter("nacionalidad");
            Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
            int nPeliculas = Integer.parseInt(request.getParameter("nPeliculas"));
            boolean retirado = request.getParameter("retirado") != null;

            String nombreArchivo = request.getParameter("imagenActual");
            Part imagen = request.getPart("imagen");
            if (imagen != null && imagen.getSize() > 0) {
                nombreArchivo = UUID.randomUUID() + ".jpg";
                String ruta = getServletContext().getInitParameter("imagePath");
                InputStream inputStream = imagen.getInputStream();
                Files.copy(inputStream, Path.of(ruta + File.separator + nombreArchivo));
            }

            Directores director = new Directores();
            director.setIdDirector(idDirector);
            director.setNombre(nombre);
            director.setNacionalidad(nacionalidad);
            director.setFechaNacimiento(fechaNacimiento);
            director.setNPeliculas(nPeliculas);
            director.setRetirado(retirado);
            director.setImagen(nombreArchivo);

            Database database = new Database();
            database.connect();
            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());

            directoresDao.modify(director);

            response.sendRedirect("listar-directores");

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error al editar el director: " + e.getMessage());
        }
    }
}