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
import javax.servlet.http.HttpServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Database database = new Database();
            database.connect();
            UsuariosDao usuariosDao = new UsuariosDao(database.getConnection());

            String hashedPassword = HashUtil.sha1(password);
            Usuarios usuario = usuariosDao.getUsuarioByEmailAndPassword(email, hashedPassword);

            if (usuario != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario);
                session.setAttribute("role", usuario.getRol());
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("error", "Credenciales incorrectas");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error en la base de datos: " + e.getMessage());
        }
    }
}
