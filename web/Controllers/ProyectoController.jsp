<%@ page import="java.sql.Connection, java.sql.SQLException, java.util.List" %>
<%@ page import="Infraestructure.Database.ConnectionDbMySql, Infraestructure.Persistence.ProyectoCrud" %>
<%@ page import="Business.Services.ProyectoService, Domain.Model.Proyecto" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Proyectos</title>
</head>
<body>
    <h3>Gestión de Proyectos</h3>

    <%
        String action = request.getParameter("action");
        ProyectoService proyectoService = null;
        Connection conexion = null;
        ProyectoCrud proyectoCrud = null;

        try {
            conexion = ConnectionDbMySql.getConnection(); // Conexión a la base de datos
            proyectoCrud = new ProyectoCrud(conexion);
            proyectoService = new ProyectoService(proyectoCrud);

            if (action != null) {
                if (action.equals("listar")) {
                    // Listar todos los proyectos
                    List<Proyecto> proyectos = proyectoService.obtenerProyectos();
                    request.setAttribute("proyectos", proyectos);
                    request.getRequestDispatcher("/Views/Proyectos/listAllProyectos.jsp").forward(request, response);
                } else if (action.equals("crear")) {
                    // Redirigir a la página para crear un proyecto
                    request.getRequestDispatcher("/Views/Proyectos/createProyecto.jsp").forward(request, response);
                } else if (action.equals("editar")) {
                    // Redirigir a la página de edición de proyectos (buscar)
                    request.getRequestDispatcher("/Views/Proyectos/editProyecto.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("Error al conectar con la base de datos: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close(); // Cerrar la conexión
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    %>
</body>
</html>
