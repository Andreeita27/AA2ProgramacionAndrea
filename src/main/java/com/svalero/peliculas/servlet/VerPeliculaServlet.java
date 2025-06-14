package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.PeliculasDao;
import com.svalero.peliculas.dao.DirectoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.PeliculaNoEncontradaExcepcion;
import com.svalero.peliculas.exception.DirectorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Peliculas;
import com.svalero.peliculas.model.Directores;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ver-pelicula")
@MultipartConfig
 class VerPeliculaServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Database database = new Database();
            database.connect();

            PeliculasDao peliculasDao = new PeliculasDao(database.getConnection());
            Peliculas pelicula = peliculasDao.get(id);
            request.setAttribute("pelicula", pelicula);

            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());
            String nombreDirector;
            try {
                Directores director = directoresDao.get(pelicula.getIdDirector());
                nombreDirector = director.getNombre();
            } catch (DirectorNoEncontradoExcepcion e) {
                nombreDirector = "Desconocido";
            }

            request.setAttribute("nombreDirector", nombreDirector);

            request.getRequestDispatcher("ver-pelicula.jsp").forward(request, response);

        } catch (PeliculaNoEncontradaExcepcion pnfe) {
            response.getWriter().println("Película no encontrada");
            pnfe.printStackTrace();
        } catch (SQLException sqle) {
            response.getWriter().println("Error al acceder a la base de datos");
            sqle.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            response.getWriter().println("No se ha podido cargar el driver de la base de datos");
            cnfe.printStackTrace();
        } catch (Exception e) {
            response.getWriter().println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}