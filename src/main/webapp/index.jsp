<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 20/05/2025
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Películas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center mb-4">FlixNet</h1>

    <div class="row justify-content-center">

        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body">
                    <h5 class="card-title">🎬 Películas</h5>
                    <p class="card-text">Ver y gestionar todas las películas registradas</p>
                    <a href="listar-peliculas" class="btn btn-primary">Ir a Películas</a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body">
                    <h5 class="card-title">🎭 Actores</h5>
                    <p class="card-text">Listado de actores con su información personal</p>
                    <a href="listar-actores" class="btn btn-success">Ir a Actores</a>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-4">
            <div class="card h-100 text-center">
                <div class="card-body">
                    <h5 class="card-title">🎬 Directores</h5>
                    <p class="card-text">Directores disponibles y sus películas asociadas</p>
                    <a href="listar-directores" class="btn btn-warning">Ir a Directores</a>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
