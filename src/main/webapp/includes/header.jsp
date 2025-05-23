<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 21/05/2025
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String nombreUsuario = (session.getAttribute("usuario") != null)
          ? ((com.svalero.peliculas.model.Usuarios) session.getAttribute("usuario")).getNombre()
          : null;
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">🎬 Películas</a>
    <div class="collapse navbar-collapse justify-content-end">
      <ul class="navbar-nav">

        <% if (role == null) { %>
        <li class="nav-item">
          <a class="nav-link" href="login.jsp">Iniciar sesión</a>
        </li>
        <% } else { %>
        <li class="nav-item">
          <a class="nav-link" href="mi-cuenta.jsp">Mi cuenta</a>
        </li>
        <li class="nav-item">
          <span class="navbar-text text-white me-2">Hola, <%= nombreUsuario %></span>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout">Cerrar sesión</a>
        </li>
        <% } %>

      </ul>
    </div>
  </div>
</nav>

