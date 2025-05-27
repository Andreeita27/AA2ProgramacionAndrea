package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.ActoresDao;
import com.svalero.peliculas.database.Database;
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
import java.util.ArrayList;
import java.util.UUID;

@WebServlet("/registrar-actor")
@MultipartConfig
public class RegistrarActorServlet extends HttpServlet {

    private ArrayList<String> errores;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        if (!validar(request)) {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("formulario-actor.jsp").forward(request, response);
            return;
        }

        try {
            String nombre = request.getParameter("nombre");
            String nacionalidad = request.getParameter("nacionalidad");
            Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
            int numeroPeliculas = Integer.parseInt(request.getParameter("numeroPeliculas"));
            boolean retirado = request.getParameter("retirado") != null;
            Part imagen = request.getPart("imagen");



            Actores actor = new Actores();
            actor.setNombre(nombre);
            actor.setNacionalidad(nacionalidad);
            actor.setFechaNacimiento(fechaNacimiento);
            actor.setNumeroPeliculas(numeroPeliculas);
            actor.setRetirado(retirado);

            String filename = "default2.jpg";
            if (imagen.getSize() != 0) {
                filename =  UUID.randomUUID() + ".jpg";
                String imagePath = "C:/apache-tomcat-9.0.105/apache-tomcat-9.0.105/webapps/images";
                InputStream inputStream = imagen.getInputStream();
                Files.copy(inputStream, Path.of(imagePath + File.separator + filename));
            }
            actor.setImagen(filename);

            Database database = new Database();
            database.connect();
            ActoresDao actoresDao = new ActoresDao(database.getConnection());
            actoresDao.add(actor);

            response.sendRedirect("listar-actores");

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error al registrar el actor: " + e.getMessage());
        }
    }

    private boolean validar(HttpServletRequest request) {
        errores = new ArrayList<>();

        if (request.getParameter("nombre") == null || request.getParameter("nombre").isEmpty()) {
            errores.add("El nombre es obligatorio");
        }
        if (request.getParameter("fechaNacimiento") == null || request.getParameter("fechaNacimiento").isEmpty()) {
            errores.add("La fecha de nacimiento es obligatoria");
        }
        if (request.getParameter("numeroPeliculas") == null ||
                !request.getParameter("numeroPeliculas").matches("\\d+")) {
            errores.add("El número de películas debe ser un número entero");
        }

        return errores.isEmpty();
    }
}
