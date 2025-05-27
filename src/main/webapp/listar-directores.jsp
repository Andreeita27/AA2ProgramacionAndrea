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

    <form method="get" action="listar-directores" class="row g-3 mb-4">
        <div class="col-md-5">
            <input type="text" name="nombre" class="form-control" placeholder="Buscar por nombre"
                   value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>">
        </div>

        <div class="col-md-3">
            <select name="retirado" class="form-select">
                <option value="">Todos</option>
                <option value="true" <%= "true".equals(request.getAttribute("retirado")) ? "selected" : "" %>>Sí</option>
                <option value="false" <%= "false".equals(request.getAttribute("retirado")) ? "selected" : "" %>>No</option>
            </select>
        </div>

        <div class="col-md-2">
            <button type="submit" class="btn btn-primary w-100">Buscar</button>
        </div>

        <div class="col-md-2">
            <a href="listar-directores" class="btn btn-secondary w-100">Limpiar</a>
        </div>
    </form>

    <% if ("admin".equals(session.getAttribute("role"))) { %>
    <div class="text-end mb-3">
        <a href="formulario-director.jsp" class="btn btn-success">Añadir Director</a>
    </div>
    <% } %>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>Imagen</th>
            <th>Nombre</th>
            <th>Nacionalidad</th>
            <th>Fecha Nacimiento</th>
            <th>Numero de peliculas</th>
            <th>Retirado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <% for (int i = 0; i < ((java.util.List)request.getAttribute("directores")).size(); i++) {
            com.svalero.peliculas.model.Directores director =
                    (com.svalero.peliculas.model.Directores) ((java.util.List)request.getAttribute("directores")).get(i); %>
        <tr>
            <td class="align-middle">
                <img src="../images/<%= director.getImagen() %>" width="80" height="100" alt="imagen director">
            </td>
            <td class="align-middle"><%= director.getNombre() %></td>
            <td class="align-middle"><%= director.getNacionalidad() %></td>
            <td class="align-middle"><%= director.getFechaNacimiento() %></td>
            <td class="align-middle"><%= director.getNPeliculas()%></td>
            <td class="align-middle"><%= director.isRetirado() ? "Sí" : "No" %></td>
            <td class="align-middle">
                <a href="ver-director?id=<%= director.getIdDirector() %>" class="btn btn-info btn-sm">Ver</a>
                <% if ("admin".equals(session.getAttribute("role"))) { %>
                <a href="editar-director?id=<%= director.getIdDirector() %>" class="btn btn-warning btn-sm">Editar</a>
                <a href="eliminar-director?id=<%= director.getIdDirector() %>" class="btn btn-danger btn-sm"
                   onclick="return confirm('¿Estás segura de que quieres eliminar este director?')">Eliminar</a>
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
        <a href="listar-directores?page=<%= currentPage - 1 %>" class="btn btn-outline-primary me-2"
                <%= (currentPage <= 0) ? "style='pointer-events:none;opacity:0.5;'" : "" %>>
            Anterior
        </a>

        <a href="listar-directores?page=<%= currentPage + 1 %>" class="btn btn-outline-primary">
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

