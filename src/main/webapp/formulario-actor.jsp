<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 25/05/2025
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%
  if (!"admin".equals(role)) {
    response.sendRedirect("index.jsp");
    return;
  }

  com.svalero.peliculas.model.Actores actor = (com.svalero.peliculas.model.Actores) request.getAttribute("actor");
  String action = (String) request.getAttribute("action");
  if (action == null) {
    action = "Registrar";
  }
  String formAction = action.equals("Modificar") ? "editar-actor" : "registrar-actor";
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title><%= action %> Actor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h1 class="text-center mb-4"><%= action %> Actor</h1>

  <form action="<%= formAction %>" method="post" class="row g-3" enctype="multipart/form-data">
    <div class="col-md-6">
      <label for="nombre" class="form-label">Nombre</label>
      <input type="text" class="form-control" id="nombre" name="nombre"
             value="<%= actor != null ? actor.getNombre() : "" %>" required>
    </div>

    <div class="col-md-6">
      <label for="nacionalidad" class="form-label">Nacionalidad</label>
      <input type="text" class="form-control" id="nacionalidad" name="nacionalidad"
             value="<%= actor != null ? actor.getNacionalidad() : "" %>">
    </div>

    <div class="col-md-6">
      <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
      <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento"
             value="<%= actor != null ? actor.getFechaNacimiento() : "" %>" required>
    </div>

    <div class="col-md-6">
      <label for="numeroPeliculas" class="form-label">Nº de Películas</label>
      <input type="number" class="form-control" id="numeroPeliculas" name="numeroPeliculas"
             value="<%= actor != null ? actor.getNumeroPeliculas() : "" %>" required>
    </div>

    <div class="col-md-6">
      <label class="form-label">Retirado</label>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="retirado" name="retirado" value="true"
          <%= (actor != null && actor.isRetirado()) ? "checked" : "" %>>
        <label class="form-check-label" for="retirado">Sí</label>
      </div>
    </div>

    <div class="col-md-6">
      <label for="imagen" class="form-label">Imagen</label>
      <input class="form-control" type="file" id="imagen" name="imagen" accept="image/*">
      <% if (actor != null) { %>
      <input type="hidden" name="imagenActual" value="<%= actor.getImagen() %>">
      <% } %>
    </div>

    <% if (actor != null) { %>
    <input type="hidden" name="idActor" value="<%= actor.getIdActor() %>">
    <% } %>

    <div class="col-12 d-grid gap-2">
      <button type="submit" class="btn btn-primary"><%= action %></button>
      <a href="listar-actores" class="btn btn-secondary">Volver al listado</a>
    </div>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
