<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 20/05/2025
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Peliculas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Listado de Películas</h1>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>Título</th>
            <th>Duración (min)</th>
            <th>Fecha de Estreno</th>
            <th>Streaming</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < ((java.util.List)request.getAttribute("peliculas")).size(); i++) {
            com.svalero.peliculas.model.Peliculas pelicula = (com.svalero.peliculas.model.Peliculas) ((java.util.List)request.getAttribute("peliculas")).get(i); %>
        <tr>
            <td><%= pelicula.getIdPelicula() %></td>
            <td><%= pelicula.getTitulo() %></td>
            <td><%= pelicula.getDuracion() %></td>
            <td><%= pelicula.getFechaEstreno() %></td>
            <td><%= pelicula.isDisponibleStreaming() ? "Sí" : "No" %></td>
            <td>
                <!-- TODO: Agregar enlaces para ver detalle, editar o borrar -->
                <a href="#" class="btn btn-info btn-sm">Ver</a>
                <a href="#" class="btn btn-warning btn-sm">Editar</a>
                <a href="#" class="btn btn-danger btn-sm">Eliminar</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a href="index.jsp" class="btn btn-secondary">Volver al Inicio</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
