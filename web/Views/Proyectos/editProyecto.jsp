<%@ page import="java.sql.*, Business.Services.ProyectoService, Infraestructure.Persistence.ProyectoCrud, Domain.Model.Proyecto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    int proyectoId = Integer.parseInt(request.getParameter("id"));
    Connection conexion = null;
    ProyectoService proyectoService = null;
    Proyecto proyecto = null;

    try {
        conexion = ConnectionDbMySql.getConnection();
        ProyectoCrud proyectoCrud = new ProyectoCrud(conexion);
        proyectoService = new ProyectoService(proyectoCrud);
        proyecto = proyectoService.obtenerProyectoPorId(proyectoId);
        
        if (proyecto == null) {
            request.setAttribute("error", "Proyecto no encontrado.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        request.setAttribute("error", "Error al obtener el proyecto.");
    } finally {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Proyecto</title>
</head>
<body>
    <% if (request.getAttribute("error") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <h2>Editar Proyecto</h2>
    
    <form action="ProyectoController.jsp?action=editar" method="post">
        <input type="hidden" name="id" value="<%= proyecto.getId() %>">
        
        <label>Nombre:</label>
        <input type="text" name="nombre" value="<%= proyecto.getNombre() %>" required><br>
        
        <label>Presupuesto:</label>
        <input type="number" name="presupuesto" step="0.01" value="<%= proyecto.getPresupuesto() %>" required><br>
        
        <label>Duración (días):</label>
        <input type="number" name="duracion" value="<%= proyecto.getDuracion() %>" required><br>
        
        <label>Fecha de Inicio:</label>
        <input type="date" name="fecha_inicio" value="<%= proyecto.getFecha_inicio() %>" required><br>
        
        <label>Usuario ID:</label>
        <input type="number" name="usuario_id" value="<%= proyecto.getUsuario_id() %>" required><br>
        
        <button type="submit">Actualizar Proyecto</button>
    </form>

    <br>
    <a href="ProyectoController.jsp?action=listar">Volver al listado de proyectos</a>
</body>
</html>
