<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 23/05/2025
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle del Director</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="page-wrapper">
<div class="container mt-5">
    <h1 class="text-center mb-5">Detalle del Director</h1>

    <div class="card shadow p-4">
        <div class="row">
            <div class="col-md-4 text-center">
                <img src="images/<%= ((com.svalero.peliculas.model.Directores)request.getAttribute("director")).getImagen() %>"
                     class="img-fluid rounded" style="max-height: 400px;" alt="Imagen del director">
            </div>

            <div class="col-md-8">
                <h2 class="mb-3"><%= ((com.svalero.peliculas.model.Directores)request.getAttribute("director")).getNombre() %></h2>
                <p><strong>Nacionalidad:</strong> <%= ((com.svalero.peliculas.model.Directores)request.getAttribute("director")).getNacionalidad() %></p>
                <p><strong>Fecha de nacimiento:</strong> <%= ((com.svalero.peliculas.model.Directores)request.getAttribute("director")).getFechaNacimiento() %></p>
                <p><strong>Número de películas dirigidas:</strong> <%= ((com.svalero.peliculas.model.Directores)request.getAttribute("director")).getNPeliculas() %></p>
                <p><strong>Retirado:</strong> <%= ((com.svalero.peliculas.model.Directores)request.getAttribute("director")).isRetirado() ? "Sí" : "No" %></p>
            </div>
        </div>
    </div>

    <div class="text-center mt-4">
        <a href="listar-directores" class="btn btn-secondary">Volver al Listado</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="includes/footer.jsp" %>
</div>
</body>
</html>

