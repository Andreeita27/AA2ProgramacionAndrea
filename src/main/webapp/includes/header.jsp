<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 21/05/2025
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String role = (String) session.getAttribute("role");
  String nombreUsuario = (session.getAttribute("usuario") != null)
          ? ((com.svalero.peliculas.model.Usuarios) session.getAttribute("usuario")).getNombre()
          : null;
%>

<style>
  html, body {
    height: 100%;
    margin: 0;
  }

  .page-wrapper {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
  }

  main {
    flex: 1;
  }
</style>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">FlixNet</a>
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
        <li class="nav-item d-flex align-items-center ms-2">
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


