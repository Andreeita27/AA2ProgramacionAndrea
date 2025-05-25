<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 25/05/2025
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Listado de Actores</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h1 class="text-center mb-4">Listado de Actores</h1>

  <% if ("admin".equals(session.getAttribute("role"))) { %>
  <div class="text-end mb-3">
    <a href="formulario-actor.jsp" class="btn btn-success">Añadir Actor</a>
  </div>
  <% } %>

  <table class="table table-bordered table-hover">
    <thead class="table-dark">
    <tr>
      <th>Imagen</th>
      <th>Nombre</th>
      <th>Nacionalidad</th>
      <th>Fecha de Nacimiento</th>
      <th>Películas</th>
      <th>Retirado</th>
      <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <% for (int i = 0; i < ((java.util.List)request.getAttribute("actores")).size(); i++) {
      com.svalero.peliculas.model.Actores actor = (com.svalero.peliculas.model.Actores) ((java.util.List)request.getAttribute("actores")).get(i); %>
    <tr>
      <td class="align-middle">
        <img src="images/<%= actor.getImagen() %>" width="80" height="100" alt="imagen actor">
      </td>
      <td class="align-middle"><%= actor.getNombre() %></td>
      <td class="align-middle"><%= actor.getNacionalidad() %></td>
      <td class="align-middle"><%= actor.getFechaNacimiento() %></td>
      <td class="align-middle"><%= actor.getNumeroPeliculas() %></td>
      <td class="align-middle"><%= actor.isRetirado() ? "Sí" : "No" %></td>
      <td class="align-middle">
        <a href="ver-actor?id=<%= actor.getIdActor() %>" class="btn btn-info btn-sm">Ver</a>
        <% if ("admin".equals(session.getAttribute("role"))) { %>
        <a href="editar-actor?id=<%= actor.getIdActor() %>" class="btn btn-warning btn-sm">Editar</a>
        <a href="eliminar-actor?id=<%= actor.getIdActor() %>" class="btn btn-danger btn-sm"
           onclick="return confirm('¿Estás segura de que quieres eliminar este actor?')">Eliminar</a>
        <% } %>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <%
    int currentPage = (request.getAttribute("currentPage") != null) ?
            (Integer) request.getAttribute("currentPage") : 0;
  %>

  <div class="text-center mt-4">
    <a href="listar-actores?page=<%= currentPage - 1 %>" class="btn btn-outline-primary me-2"
            <%= (currentPage <= 0) ? "style='pointer-events:none;opacity:0.5;'" : "" %>>
      Anterior
    </a>

    <a href="listar-actores?page=<%= currentPage + 1 %>" class="btn btn-outline-primary">
      Siguiente
    </a>
  </div>

  <div class="text-center mt-4">
    <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>