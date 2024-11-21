<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Proyecto</title>
</head>
<body>
    <h2>Crear Proyecto</h2>

    <form action="ProyectoController.jsp?action=crear" method="post">
        <label>Nombre:</label>
        <input type="text" name="nombre" required><br>
        
        <label>Presupuesto:</label>
        <input type="number" name="presupuesto" step="0.01" required><br>
        
        <label>Duración (días):</label>
        <input type="number" name="duracion" required><br>
        
        <label>Fecha de Inicio:</label>
        <input type="date" name="fecha_inicio" required><br>
        
        <label>Usuario ID:</label>
        <input type="number" name="usuario_id" required><br>
        
        <button type="submit">Crear Proyecto</button>
    </form>

    <br>
    <a href="ProyectoController.jsp?action=listar">Volver al listado de proyectos</a>
</body>
</html>
