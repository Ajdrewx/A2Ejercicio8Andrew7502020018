<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <center>
        <h1>Iniciar Sesión</h1>

        <%-- Mensaje de error en caso de credenciales incorrectas --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
        <% } %>

        <%-- Formulario de Login --%>
        <form action="<%= request.getContextPath() %>/Controllers/UserController.jsp?action=authenticate" method="post">
            <fieldset>
                <legend>Datos de Inicio de Sesión</legend>
                <table border="0">
                    <tr>
                        <th><label for="email">Email:</label></th>
                        <td><input type="email" id="email" name="email" required></td>
                    </tr>
                    <tr>
                        <th><label for="password">Contraseña:</label></th>
                        <td><input type="password" id="password" name="password" required></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="submit" value="Iniciar Sesión">
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>

        <br>
        <a href="<%= request.getContextPath() %>/index.jsp">Volver a la página de inicio</a>
    </center>
</body>
</html>
