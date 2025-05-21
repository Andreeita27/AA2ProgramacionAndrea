<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 21/05/2025
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Detalle de Película</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
  <h1 class="text-center mb-4">Detalle de Película</h1>

  <div class="card">
    <div class="card-body">
      <h3 class="card-title"><%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getTitulo() %></h3>
      <p class="card-text"><strong>ID:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getIdPelicula() %></p>
      <p class="card-text"><strong>Sinopsis:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getSinopsis() %></p>
      <p class="card-text"><strong>Duración:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getDuracion() %> minutos</p>
      <p class="card-text"><strong>Fecha de estreno:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getFechaEstreno() %></p>
      <p class="card-text"><strong>Disponible en streaming:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).isDisponibleStreaming() ? "Sí" : "No" %></p>
      <p class="card-text"><strong>Imagen:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getImagen() %></p>
      <p class="card-text"><strong>ID Director:</strong> <%= ((com.svalero.peliculas.model.Peliculas)request.getAttribute("pelicula")).getIdDirector() %></p>

      <a href="listar-peliculas" class="btn btn-secondary mt-3">Volver al Listado</a>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
