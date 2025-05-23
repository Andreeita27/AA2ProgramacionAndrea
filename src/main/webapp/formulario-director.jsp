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
    if (!"admin".equals(session.getAttribute("role"))) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registrar Director</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Registrar Director</h1>

    <% if (request.getAttribute("errores") != null) {
        java.util.List<String> errores = (java.util.List<String>) request.getAttribute("errores");
        for (String error : errores) { %>
    <div class="alert alert-danger"><%= error %></div>
    <%  } } %>

    <form action="registrar-director" method="post" enctype="multipart/form-data" class="row g-3">
        <div class="col-md-6">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>

        <div class="col-md-6">
            <label for="nacionalidad" class="form-label">Nacionalidad</label>
            <input type="text" class="form-control" id="nacionalidad" name="nacionalidad" required>
        </div>

        <div class="col-md-6">
            <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento">
        </div>

        <div class="col-md-6">
            <label for="imagen" class="form-label">Imagen</label>
            <input class="form-control" type="file" id="imagen" name="imagen" accept="image/*">
        </div>

        <div class="col-md-6">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" id="retirado" name="retirado" value="true">
                <label class="form-check-label" for="retirado">¿Está retirado?</label>
            </div>
        </div>

        <div class="col-12 d-grid gap-2">
            <button type="submit" class="btn btn-primary">Registrar</button>
            <a href="index.jsp" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
