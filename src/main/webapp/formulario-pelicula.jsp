<%--
  Created by IntelliJ IDEA.
  User: fdezd
  Date: 22/05/2025
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="includes/header.jsp" %>
<%
  if (!"admin".equals(role)) {
    response.sendRedirect("index.jsp");
    return;
  }

  com.svalero.peliculas.model.Peliculas pelicula = (com.svalero.peliculas.model.Peliculas) request.getAttribute("pelicula");
  String action = (String) request.getAttribute("action");
  if (action == null) {
    action = "Registrar";
  }
  String formAction = action.equals("Modificar") ? "editar-pelicula" : "registrar-pelicula";
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title><%= action %> Película</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
  <h1 class="text-center mb-4"><%= action %> Película</h1>

  <form id="pelicula-form" action="<%= formAction %>" method="post" class="row g-3" enctype="multipart/form-data">
    <div class="col-md-6">
      <label for="titulo" class="form-label">Título</label>
      <input type="text" class="form-control" id="titulo" name="titulo"
             value="<%= pelicula != null ? pelicula.getTitulo() : "" %>" required>
    </div>

    <div class="col-md-6">
      <label for="duracion" class="form-label">Duración (minutos)</label>
      <input type="number" class="form-control" id="duracion" name="duracion"
             value="<%= pelicula != null ? pelicula.getDuracion() : "" %>" required>
    </div>

    <div class="col-md-12">
      <label for="sinopsis" class="form-label">Sinopsis</label>
      <textarea class="form-control" id="sinopsis" name="sinopsis" rows="3"><%= pelicula != null ? pelicula.getSinopsis() : "" %></textarea>
    </div>

    <div class="col-md-6">
      <label for="fechaEstreno" class="form-label">Fecha de estreno</label>
      <input type="date" class="form-control" id="fechaEstreno" name="fechaEstreno"
             value="<%= pelicula != null ? pelicula.getFechaEstreno() : "" %>" required>
    </div>

    <div class="col-md-6">
      <label for="idDirector" class="form-label">ID del Director</label>
      <input type="number" class="form-control" id="idDirector" name="idDirector"
             value="<%= pelicula != null ? pelicula.getIdDirector() : "" %>" required>
    </div>

    <div class="col-md-6">
      <label for="imagen" class="form-label">Imagen</label>
      <input class="form-control" type="file" id="imagen" name="imagen" accept="image/*">
      <% if (pelicula != null) { %>
      <input type="hidden" name="imagenActual" value="<%= pelicula.getImagen() %>">
      <% } %>
    </div>

    <div class="col-md-6">
      <label class="form-label">¿Disponible en streaming?</label>
      <div class="form-check">
        <input class="form-check-input" type="checkbox" id="disponibleStreaming" name="disponibleStreaming" value="true"
          <%= (pelicula != null && pelicula.isDisponibleStreaming()) ? "checked" : "" %>>
        <label class="form-check-label" for="disponibleStreaming">Sí</label>
      </div>
    </div>

    <% if (pelicula != null) { %>
    <input type="hidden" name="idPelicula" value="<%= pelicula.getIdPelicula() %>">
    <% } %>

    <div class="col-12 d-grid gap-2">
      <button type="submit" class="btn btn-primary"><%= action %></button>
      <a href="listar-peliculas" class="btn btn-secondary">Volver al listado</a>
    </div>

    <div id="result" class="mt-3"></div>
  </form>
</div>

<script>
  const action = "<%= action %>";
  $(document).ready(function () {
    if (action === "Registrar") {
      $("#pelicula-form").on("submit", function (event) {
        event.preventDefault();
        const form = $("#pelicula-form")[0];
        const formData = new FormData(form);

        $.ajax({
          url: "registrar-pelicula",
          type: "POST",
          enctype: "multipart/form-data",
          data: formData,
          processData: false,
          contentType: false,
          cache: false,
          timeout: 10000,
          statusCode: {
            200: function (response) {
              if (response === "ok") {
                $("#result").html("<div class='alert alert-success'>Película registrada correctamente</div>");
                form.reset();
              } else {
                $("#result").html("<div class='alert alert-danger'>" + response + "</div>");
              }
            },
            500: function () {
              $("#result").html("<div class='alert alert-danger'>Error en el servidor</div>");
            }
          }
        });
      });
    }
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


