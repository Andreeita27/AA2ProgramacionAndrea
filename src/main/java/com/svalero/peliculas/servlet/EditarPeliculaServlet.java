package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.PeliculasDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.PeliculaNoEncontradaExcepcion;
import com.svalero.peliculas.model.Peliculas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editar-pelicula")
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
}

