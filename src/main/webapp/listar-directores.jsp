<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 23/05/2025
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Directores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Listado de Directores</h1>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>Imagen</th>
            <th>Nombre</th>
            <th>Nacionalidad</th>
            <th>Fecha de Nacimiento</th>
            <th>Retirado</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < ((java.util.List)request.getAttribute("directores")).size(); i++) {
            com.svalero.peliculas.model.Directores director = (com.svalero.peliculas.model.Directores) ((java.util.List)request.getAttribute("directores")).get(i); %>
        <tr>
            <td class="align-middle">
                <img src="images/<%= director.getImagen() %>" width="80" height="100" alt="imagen director">
            </td>
            <td class="align-middle"><%= director.getNombre() %></td>
            <td class="align-middle"><%= director.getNacionalidad() %></td>
            <td class="align-middle"><%= director.getFechaNacimiento() %></td>
            <td class="align-middle"><%= director.isRetirado() ? "Sí" : "No" %></td>
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
