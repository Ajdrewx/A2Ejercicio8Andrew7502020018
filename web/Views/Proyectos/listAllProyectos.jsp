<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Domain.Model.Proyecto" %>

<html>
<head>
    <title>Listar Proyectos</title>
</head>
<body>
    <h1>Lista de Proyectos</h1>

    <table border="1">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Presupuesto</th>
                <th>Duración</th>
                <th>Fecha de Inicio</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Proyecto> proyectos = (List<Proyecto>) request.getAttribute("proyectos");
                if (proyectos != null && !proyectos.isEmpty()) {
                    for (Proyecto proyecto : proyectos) { 
            %>
            <tr>
                <td><%= proyecto.getNombre() %></td>
                <td><%= proyecto.getPresupuesto() %></td>
                <td><%= proyecto.getDuracion() %></td>
                <td><%= proyecto.getFecha_inicio() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/Controllers/ProyectoController.jsp?action=edit&id=<%= proyecto.getId() %>">Editar</a> |
                    <a href="<%= request.getContextPath() %>/Controllers/ProyectoController.jsp?action=delete&id=<%= proyecto.getId() %>" onclick="return confirm('¿Seguro que deseas eliminar este proyecto?');">Eliminar</a>
                </td>
            </tr>
            <% 
                    }
                } else { 
            %>
            <tr>
                <td colspan="5">No hay proyectos disponibles</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <br>
    <a href="<%= request.getContextPath() %>/Controllers/ProyectoController.jsp?action=create">Agregar Nuevo Proyecto</a>
    <br>
    <a href="<%= request.getContextPath() %>/index.jsp">Atrás</a> <!-- Botón para regresar -->
</body>
</html>
