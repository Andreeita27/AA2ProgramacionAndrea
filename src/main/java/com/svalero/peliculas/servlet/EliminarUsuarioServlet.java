package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.UsuariosDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.model.Usuarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/eliminar-usuario")
public class EliminarUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

        try {
            Database database = new Database();
            database.connect();
            UsuariosDao usuariosDao = new UsuariosDao(database.getConnection());

            usuariosDao.delete(usuario.getIdUsuario());

            session.invalidate();
            response.sendRedirect("index.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al eliminar el usuario: " + e.getMessage());
        }
    }
}