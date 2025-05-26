package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.PeliculasDao;
import com.svalero.peliculas.model.Peliculas;
import com.svalero.peliculas.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/listar-peliculas")
public class ListarPeliculasServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        int page = 0;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException nfe) {
                page = 0;
            }
        }

        String titulo = request.getParameter("titulo");
        String streaming = request.getParameter("streaming");

        try {
            Database database = new Database();
            database.connect();

            PeliculasDao peliculaDao = new PeliculasDao(database.getConnection());
            ArrayList<Peliculas> peliculas = peliculaDao.getAll(page, titulo, streaming);

            request.setAttribute("peliculas", peliculas);
            request.setAttribute("currentPage", page);
            request.setAttribute("titulo", titulo);
            request.setAttribute("streaming", streaming);

            request.getRequestDispatcher("listar-peliculas.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al acceder a la base de datos: " + e.getMessage());
        }
    }
}
