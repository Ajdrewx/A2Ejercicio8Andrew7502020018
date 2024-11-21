<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Agregar Usuario</title>
</head>
<body>
    <h1>Agregar Usuario</h1>

    <%-- Mensajes de error o éxito --%>
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>

    <% if (request.getAttribute("successMessage") != null) { %>
        <p style="color:green;"><%= request.getAttribute("successMessage") %></p>
    <% } %>

    <%-- Formulario para agregar usuario --%>
    <form action="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=create" method="post">
        <label for="code">Código:</label><br>
        <input type="text" id="code" name="code" required><br><br>

        <label for="name">Nombre:</label><br>
        <input type="text" id="name" name="name" required><br><br>

        <label for="apellidos">Apellidos:</label><br>
        <input type="text" id="apellidos" name="apellidos" required><br><br>

        <label for="rol">Rol:</label><br>
        <input type="text" id="rol" name="rol" required><br><br>

        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>

        <label for="telefono">Teléfono:</label><br>
        <input type="text" id="telefono" name="telefono"><br><br>

        <label for="estado">Estado:</label><br>
        <select id="estado" name="estado">
            <option value="activo">Activo</option>
            <option value="inactivo">Inactivo</option>
        </select><br><br>

        <label for="fecha_registro">Fecha de Registro:</label><br>
        <input type="date" id="fecha_registro" name="fecha_registro" required><br><br>

        <label for="password">Contraseña:</label><br>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Agregar Usuario">
    </form>

    <br>
    <a href="<%= request.getContextPath() %>/index.jsp">Menu Principal</a>
</body>
</html>
