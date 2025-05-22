package com.svalero.peliculas.servlet;

import com.svalero.peliculas.dao.UsuariosDao;
import com.svalero.peliculas.database.Database;
import com.svalero.peliculas.model.Usuarios;
import com.svalero.peliculas.util.HashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/actualizar-usuario")
public class ActualizarUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuarios usuarioSesion = (Usuarios) session.getAttribute("usuario");

        String nuevoNombre = request.getParameter("nombre");
        String nuevoEmail = request.getParameter("email");
        String nuevaPassword = request.getParameter("password");

        try {
            Database database = new Database();
            database.connect();
            UsuariosDao usuariosDao = new UsuariosDao(database.getConnection());

            usuarioSesion.setNombre(nuevoNombre);
            usuarioSesion.setEmail(nuevoEmail);

            if (nuevaPassword != null && !nuevaPassword.isBlank()) {
                usuarioSesion.setContraseña(HashUtil.sha1(nuevaPassword));
            }

            usuariosDao.update(usuarioSesion);
            session.setAttribute("usuario", usuarioSesion);

            response.sendRedirect("mi-cuenta.jsp");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error al actualizar el usuario: " + e.getMessage());
        }
    }
}
