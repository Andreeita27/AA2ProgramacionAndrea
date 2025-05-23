package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.DirectoresDao;
import com.svalero.peliculas.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eliminar-director")
public class EliminarDirectorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());
            directoresDao.delete(id);

            response.sendRedirect("listar-directores");

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error al eliminar el director: " + e.getMessage());
        }
    }
}