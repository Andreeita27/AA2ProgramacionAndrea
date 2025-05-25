package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.ActoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.model.Actores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar-actores")
public class ListarActoresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        int currentPage = 0;
        if (request.getParameter("page") != null) {
            try {
                currentPage = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                currentPage = 0;
            }
        }

        try {
            Database database = new Database();
            database.connect();

            ActoresDao actoresDao = new ActoresDao(database.getConnection());
            List<Actores> actores = actoresDao.getAll(currentPage);

            request.setAttribute("actores", actores);
            request.setAttribute("currentPage", currentPage);
            request.getRequestDispatcher("listar-actores.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al acceder a la base de datos: " + e.getMessage());
        }
    }
}