<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 25/05/2025
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Detalle del Actor</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="page-wrapper">
<div class="container mt-5">
  <h1 class="text-center mb-5">Detalle del Actor</h1>

  <div class="card shadow p-4">
    <div class="row">
      <div class="col-md-4 text-center">
        <img src="images/<%= ((com.svalero.peliculas.model.Actores)request.getAttribute("actor")).getImagen() %>"
             class="img-fluid rounded" style="max-height: 400px;" alt="Imagen del actor">
      </div>

      <div class="col-md-8">
        <h2 class="mb-3"><%= ((com.svalero.peliculas.model.Actores)request.getAttribute("actor")).getNombre() %></h2>
        <p><strong>Nacionalidad:</strong> <%= ((com.svalero.peliculas.model.Actores)request.getAttribute("actor")).getNacionalidad() %></p>
        <p><strong>Fecha de nacimiento:</strong> <%= ((com.svalero.peliculas.model.Actores)request.getAttribute("actor")).getFechaNacimiento() %></p>
        <p><strong>Películas en las que ha participado:</strong> <%= ((com.svalero.peliculas.model.Actores)request.getAttribute("actor")).getNumeroPeliculas() %></p>
        <p><strong>¿Está retirado?</strong> <%= ((com.svalero.peliculas.model.Actores)request.getAttribute("actor")).isRetirado() ? "Sí" : "No" %></p>
      </div>
    </div>
  </div>

  <div class="text-center mt-4">
    <a href="listar-actores" class="btn btn-secondary">Volver al Listado</a>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="includes/footer.jsp" %>
</div>
</body>
</html>
