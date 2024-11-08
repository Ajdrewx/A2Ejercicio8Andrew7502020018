<%--
    Document    : index
    Created on  : 20 oct 2024
    Author      : johnarrieta
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Domain.Model.User" %>

<html>
    <head>
        <title>Página de Inicio</title>
    </head>
    <body>
        <h1>Bienvenido a la Gestión de Usuarios</h1>
        
        <%-- Verificamos si el usuario ha iniciado sesión --%>
        <% User loggedInUser = (User) session.getAttribute("loggedInUser"); %>
        
        <% if (loggedInUser == null) { %>
            <%-- Si no ha iniciado sesión, mostramos la opción de login --%>
            <h3>No has iniciado sesión</h3>
            <a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=login">Iniciar Sesión</a>
        <% } else { %>
            <%-- Si ha iniciado sesión, mostramos el menú de gestión de usuarios --%>
            <h3>Hola, <%= loggedInUser.getName() %> (Has iniciado sesión)</h3>
            <ul>
                <li><a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=showCreateForm">Agregar Usuario</a></li>
                <li><a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=showFindForm">Buscar Usuario</a></li>
                <li><a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=listAll">Listar Todos los Usuarios</a></li>
            </ul>
            <br>
            <a href="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=logout">Cerrar Sesión</a>
        <% } %>
    </body>
</html>
