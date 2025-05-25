package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.ActoresDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.exception.ActorNoEncontradoExcepcion;
import com.svalero.peliculas.model.Actores;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ver-actor")
public class VerActorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Database database = new Database();
            database.connect();
            ActoresDao actoresDao = new ActoresDao(database.getConnection());
            Actores actor = actoresDao.get(id);

            request.setAttribute("actor", actor);
            request.getRequestDispatcher("ver-actor.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("ID inválido");
        } catch (ActorNoEncontradoExcepcion e) {
            response.getWriter().println("Actor no encontrado");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }
}