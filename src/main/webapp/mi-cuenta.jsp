<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 22/05/2025
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    com.svalero.peliculas.model.Usuarios usuario = (com.svalero.peliculas.model.Usuarios) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi cuenta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">Zona privada - Mi cuenta</h1>

    <form action="actualizar-usuario" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= usuario.getNombre() %>" required>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= usuario.getEmail() %>" required>
        </div>

        <div class="mb-3">
            <label for="fechaRegistro" class="form-label">Fecha de registro</label>
            <input type="text" class="form-control" id="fechaRegistro" name="fechaRegistro" value="<%= usuario.getFechaRegistro() %>" readonly>
        </div>

        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <input type="text" class="form-control" id="rol" name="rol" value="<%= usuario.getRol() %>" readonly>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Nueva contraseña (opcional)</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>

        <div class="d-grid gap-2">
            <button type="submit" class="btn btn-primary">Guardar cambios</button>
            <a href="index.jsp" class="btn btn-secondary">Volver al inicio</a>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
