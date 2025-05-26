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

@WebServlet("/registrar-usuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Database database = new Database();
            database.connect();
            UsuariosDao usuariosDao = new UsuariosDao(database.getConnection());

            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setContraseña(HashUtil.sha1(password));

            boolean registrado = usuariosDao.add(nuevoUsuario);

            if (registrado) {
                response.sendRedirect("login.jsp");
            } else {
                request.setAttribute("error", "No se pudo registrar el usuario");
                request.getRequestDispatcher("registro.jsp").forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en la base de datos: " + e.getMessage());
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }
}
