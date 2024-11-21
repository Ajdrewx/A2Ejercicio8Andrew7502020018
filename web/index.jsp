<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Domain.Model.User" %>
<%@ page import="Domain.Model.Proyecto" %>
<%@ page import="Business.Services.ProyectoService" %>
<%@ page import="Infraestructure.Persistence.ProyectoCrud" %>
<%@ page import="Infraestructure.Database.ConnectionDbMySql" %>

<html>
    <head>
        <title>Página de Inicio</title>
    </head>
    <body>
        <center>
            <h1>Bienvenido a la Gestión de Usuarios y Proyectos</h1>

            <%-- Verificamos si el usuario ha iniciado sesión --%>
            <% User loggedInUser = (User) session.getAttribute("loggedInUser"); %>
            
            <% if (loggedInUser == null) { %>
                <%-- Si no ha iniciado sesión, mostramos la opción de login --%>
                <h3>No has iniciado sesión</h3>
                <a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=login">Iniciar Sesión</a>
            <% } else { %>
                <%-- Si ha iniciado sesión, mostramos el menú de gestión de usuarios --%>
                <h3>Hola, <%= loggedInUser.getName() %> (Has iniciado sesión)</h3>
                <p><a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=showCreateForm">Agregar Usuario</a></p>
                <p><a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=showFindForm">Buscar Usuario</a></p>
                <p><a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=listAll">Listar Todos los Usuarios</a></p>
                <br>
                <%-- Menú de proyectos (CRUD de proyectos) --%>
                    <h3>Gestión de Proyectos</h3>
                    <p><a href="<%= request.getContextPath() %>/Controllers/ProyectoController.jsp?action=crear">Crear Proyecto</a></p>
                    <p><a href="<%= request.getContextPath() %>/Controllers/ProyectoController.jsp?action=listar">Listar Proyectos</a></p>
                    

                <a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=logout">Cerrar Sesión</a>
                
                

            <% } %>
        </center>
    </body>
</html>
