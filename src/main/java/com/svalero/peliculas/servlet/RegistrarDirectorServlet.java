package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.DirectoresDao;
import com.svalero.peliculas.database.Database;
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
import java.util.ArrayList;
import java.util.UUID;

@WebServlet("/registrar-director")
@MultipartConfig
public class RegistrarDirectorServlet extends HttpServlet {

    private ArrayList<String> errores;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        if (!validar(request)) {
            request.setAttribute("errores", errores);
            request.getRequestDispatcher("formulario-director.jsp").forward(request, response);
            return;
        }

        try {
            String nombre = request.getParameter("nombre");
            String nacionalidad = request.getParameter("nacionalidad");
            Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));
            int nPeliculas = Integer.parseInt(request.getParameter("nPeliculas"));
            boolean retirado = request.getParameter("retirado") != null;
            Part imagen = request.getPart("imagen");

            Directores director = new Directores();
            director.setNombre(nombre);
            director.setNacionalidad(nacionalidad);
            director.setFechaNacimiento(fechaNacimiento);
            director.setNPeliculas(nPeliculas);
            director.setRetirado(retirado);

            String filename = "default2.jpg";
            if (imagen.getSize() != 0) {
                filename =  UUID.randomUUID() + ".jpg";
                String imagePath = "C:/apache-tomcat-9.0.105/apache-tomcat-9.0.105/webapps/images";
                InputStream inputStream = imagen.getInputStream();
                Files.copy(inputStream, Path.of(imagePath + File.separator + filename));
            }
            director.setImagen(filename);

            Database database = new Database();
            database.connect();
            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());
            directoresDao.add(director);

            response.sendRedirect("listar-directores");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }

    private boolean validar(HttpServletRequest request) {
        errores = new ArrayList<>();

        if (request.getParameter("nombre") == null || request.getParameter("nombre").isEmpty()) {
            errores.add("El nombre es obligatorio");
        }
        if (request.getParameter("nacionalidad") == null || request.getParameter("nacionalidad").isEmpty()) {
            errores.add("La nacionalidad es obligatoria");
        }
        return errores.isEmpty();
    }
}