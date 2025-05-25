package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.ActoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.ActorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Actores;

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

@WebServlet("/editar-actor")
@MultipartConfig
public class EditarActorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            ActoresDao actoresDao = new ActoresDao(database.getConnection());
            Actores actor = actoresDao.get(id);

            request.setAttribute("actor", actor);
            request.setAttribute("action", "Modificar");
            request.getRequestDispatcher("formulario-actor.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("ID inválido");
        } catch (ActorNoEncontradoExcepcion e) {
            response.getWriter().println("Actor no encontrado");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            int idActor = Integer.parseInt(request.getParameter("idActor"));
            String nombre = request.getParameter("nombre");
            String nacionalidad = request.getParameter("nacionalidad");
            Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
            int numeroPeliculas = Integer.parseInt(request.getParameter("numeroPeliculas"));
            boolean retirado = request.getParameter("retirado") != null;

            String nombreArchivo = request.getParameter("imagenActual");
            Part imagen = request.getPart("imagen");
            if (imagen != null && imagen.getSize() > 0) {
                nombreArchivo = UUID.randomUUID() + ".jpg";
                String ruta = getServletContext().getInitParameter("imagePath");
                InputStream inputStream = imagen.getInputStream();
                Files.copy(inputStream, Path.of(ruta + File.separator + nombreArchivo));
            }

            Actores actor = new Actores();
            actor.setIdActor(idActor);
            actor.setNombre(nombre);
            actor.setNacionalidad(nacionalidad);
            actor.setFechaNacimiento(fechaNacimiento);
            actor.setNumeroPeliculas(numeroPeliculas);
            actor.setRetirado(retirado);
            actor.setImagen(nombreArchivo);

            Database database = new Database();
            database.connect();
            ActoresDao actoresDao = new ActoresDao(database.getConnection());

            actoresDao.modify(actor);

            response.sendRedirect("listar-actores");

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error al editar el actor: " + e.getMessage());
        }
    }
}