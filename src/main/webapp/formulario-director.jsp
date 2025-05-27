<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 23/05/2025
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%
    if (!"admin".equals(role)) {
        response.sendRedirect("index.jsp");
        return;
    }

    com.svalero.peliculas.model.Directores director = (com.svalero.peliculas.model.Directores) request.getAttribute("director");
    String action = (String) request.getAttribute("action");
    if (action == null) {
        action = "Registrar";
    }
    String formAction = action.equals("Modificar") ? "editar-director" : "registrar-director";
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= action %> Director</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="page-wrapper">
<div class="container mt-5">
    <h1 class="text-center mb-4"><%= action %> Director</h1>

    <form action="<%= formAction %>" method="post" class="row g-3" enctype="multipart/form-data">
        <div class="col-md-6">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre"
                   value="<%= director != null ? director.getNombre() : "" %>" required>
        </div>

        <div class="col-md-6">
            <label for="nacionalidad" class="form-label">Nacionalidad</label>
            <input type="text" class="form-control" id="nacionalidad" name="nacionalidad"
                   value="<%= director != null ? director.getNacionalidad() : "" %>">
        </div>

        <div class="col-md-6">
            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento"
                   value="<%= director != null ? director.getFechaNacimiento() : "" %>" required>
        </div>

        <div class="col-md-6">
            <label class="form-label">¿Está retirado?</label>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="retirado" name="retirado" value="true"
                    <%= (director != null && director.isRetirado()) ? "checked" : "" %>>
                <label class="form-check-label" for="retirado">Sí</label>
            </div>
        </div>

        <div class="col-md-6">
            <label for="imagen" class="form-label">Imagen</label>
            <input class="form-control" type="file" id="imagen" name="imagen" accept="image/*">
            <% if (director != null) { %>
            <input type="hidden" name="imagenActual" value="<%= director.getImagen() %>">
            <% } %>
        </div>
        <div class="col-md-6">
            <label for="nPeliculas" class="form-label">Número de películas dirigidas</label>
            <input type="number" class="form-control" id="nPeliculas" name="nPeliculas" min="0"
                   value="<%= director != null ? director.getNPeliculas() : "" %>" required>
        </div>

        <% if (director != null) { %>
        <input type="hidden" name="idDirector" value="<%= director.getIdDirector() %>">
        <% } %>

        <div class="col-12 d-grid gap-2">
            <button type="submit" class="btn btn-primary"><%= action %></button>
            <a href="listar-directores" class="btn btn-secondary">Volver al listado</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="includes/footer.jsp" %>
</div>
</body>
</html>
