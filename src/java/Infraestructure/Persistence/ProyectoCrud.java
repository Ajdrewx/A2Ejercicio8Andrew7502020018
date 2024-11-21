package Infraestructure.Persistence;

import Domain.Model.Proyecto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyectoCrud {
    private Connection conexion;

    // Constructor que recibe la conexión a la base de datos
    public ProyectoCrud(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para agregar un proyecto
    public void agregarProyecto(Proyecto proyecto) throws SQLException {
        String sql = "INSERT INTO Proyecto (nombre, presupuesto, duracion, fecha_inicio, usuario_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, proyecto.getNombre());
            stmt.setFloat(2, proyecto.getPresupuesto());
            stmt.setInt(3, proyecto.getDuracion());
            stmt.setDate(4, proyecto.getFecha_inicio());
            stmt.setInt(5, proyecto.getUsuario_id());
            stmt.executeUpdate();
        }
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerTodosProyectos() throws SQLException {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT * FROM Proyecto";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Proyecto proyecto = new Proyecto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getFloat("presupuesto"),
                    rs.getInt("duracion"),
                    rs.getDate("fecha_inicio"),
                    rs.getInt("usuario_id")
                );
                proyectos.add(proyecto);
            }
        }
        return proyectos;
    }

    // Método para obtener un proyecto por ID
    public Proyecto obtenerProyectoPorId(int id) throws SQLException {
        Proyecto proyecto = null;
        String sql = "SELECT * FROM Proyecto WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proyecto = new Proyecto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getFloat("presupuesto"),
                        rs.getInt("duracion"),
                        rs.getDate("fecha_inicio"),
                        rs.getInt("usuario_id")
                    );
                }
            }
        }
        return proyecto;
    }

    // Método para actualizar un proyecto
    public void actualizarProyecto(Proyecto proyecto) throws SQLException {
        String sql = "UPDATE Proyecto SET nombre = ?, presupuesto = ?, duracion = ?, fecha_inicio = ?, usuario_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, proyecto.getNombre());
            stmt.setFloat(2, proyecto.getPresupuesto());
            stmt.setInt(3, proyecto.getDuracion());
            stmt.setDate(4, proyecto.getFecha_inicio());
            stmt.setInt(5, proyecto.getUsuario_id());
            stmt.setInt(6, proyecto.getId());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un proyecto
    public void eliminarProyecto(int id) throws SQLException {
        String sql = "DELETE FROM Proyecto WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
