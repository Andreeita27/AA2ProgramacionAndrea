package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.ActoresDao;
import com.svalero.peliculas.database.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eliminar-actor")
public class EliminarActorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("role") == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            ActoresDao actoresDao = new ActoresDao(database.getConnection());
            actoresDao.delete(id);

            response.sendRedirect("listar-actores");

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().println("Error al eliminar el actor: " + e.getMessage());
        }
    }
}