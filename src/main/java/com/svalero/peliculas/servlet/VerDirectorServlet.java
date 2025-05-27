package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.DirectoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.DirectorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Directores;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ver-director")
@MultipartConfig
public class VerDirectorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            DirectoresDao directoresDao = new DirectoresDao(database.getConnection());
            Directores director = directoresDao.get(id);

            request.setAttribute("director", director);
            request.getRequestDispatcher("ver-director.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("ID inválido");
        } catch (DirectorNoEncontradoExcepcion e) {
            response.getWriter().println("Director no encontrado");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }
}
