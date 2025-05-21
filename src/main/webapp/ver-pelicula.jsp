<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 21/05/2025
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Detalle de Película</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h1 class="text-center mb-5">Detalle de Película</h1>

  <div class="card shadow p-4">
    <div class="row">
      <div class="col-md-4 text-center">
        <img src="images/<%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getImagen() %>"
             class="img-fluid rounded" style="max-height: 400px;" alt="Imagen de la película">
      </div>

      <div class="col-md-8">
        <h2 class="mb-3"><%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getTitulo() %></h2>
        <p><strong>Sinopsis:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getSinopsis() %></p>
        <p><strong>Duración:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getDuracion() %> minutos</p>
        <p><strong>Fecha de estreno:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getFechaEstreno() %></p>
        <p><strong>Disponible en streaming:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).isDisponibleStreaming() ? "Sí" : "No" %></p>
        <p><strong>Director:</strong> <%= request.getAttribute("nombreDirector") %></p>
      </div>
    </div>
  </div>

  <div class="text-center mt-4">
    <a href="listar-peliculas" class="btn btn-secondary">Volver al Listado</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
