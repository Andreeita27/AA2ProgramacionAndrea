package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.DirectoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.model.Directores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar-directores")
public class ListarDirectoresServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        response.setCharacterEncoding("UTF-8");

        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String nombre = request.getParameter("nombre");
        String retirado = request.getParameter("retirado");

        try {
            Database database = new Database();
            database.connect();
            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());

            List<Directores> directores = directoresDao.getAll(page, nombre, retirado);
            request.setAttribute("directores", directores);
            request.setAttribute("currentPage", page);
            request.setAttribute("nombre", nombre);
            request.setAttribute("retirado", retirado);

            request.getRequestDispatcher("listar-directores.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al acceder a la base de datos: " + e.getMessage());
        }
    }
}