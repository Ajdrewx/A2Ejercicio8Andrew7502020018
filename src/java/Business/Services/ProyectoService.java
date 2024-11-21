package Business.Services;

import Infraestructure.Persistence.ProyectoCrud;
import Domain.Model.Proyecto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProyectoService {

    private ProyectoCrud proyectoCrud;

    // Constructor para inicializar el CRUD
    public ProyectoService(ProyectoCrud proyectoCrud) {
        this.proyectoCrud = proyectoCrud;
    }

    // Método para crear un nuevo proyecto
    public void crearProyecto(String nombre, float presupuesto, int duracion, java.sql.Date fecha_inicio, int usuario_id) throws SQLException, IllegalArgumentException {
        // Validaciones
        if (presupuesto <= 0) {
            throw new IllegalArgumentException("El presupuesto debe ser un valor positivo.");
        }
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser un valor positivo.");
        }

        Proyecto proyecto = new Proyecto(0, nombre, presupuesto, duracion, fecha_inicio, usuario_id); // ID autoincremental
        proyectoCrud.agregarProyecto(proyecto);
    }

    // Método para obtener todos los proyectos
    public List<Proyecto> obtenerProyectos() throws SQLException {
        return proyectoCrud.obtenerTodosProyectos();
    }

    // Método para actualizar un proyecto
    public void actualizarProyecto(int id, String nombre, float presupuesto, int duracion, java.sql.Date fecha_inicio, int usuario_id) throws SQLException, IllegalArgumentException {
        // Validaciones
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del proyecto no puede ser menor o igual a cero.");
        }
        if (presupuesto <= 0) {
            throw new IllegalArgumentException("El presupuesto debe ser un valor positivo.");
        }
        if (duracion <= 0) {
            throw new IllegalArgumentException("La duración debe ser un valor positivo.");
        }

        Proyecto proyecto = new Proyecto(id, nombre, presupuesto, duracion, fecha_inicio, usuario_id);
        proyectoCrud.actualizarProyecto(proyecto);
    }

    // Método para eliminar un proyecto por ID
    public void eliminarProyecto(int id) throws SQLException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del proyecto no puede ser menor o igual a cero.");
        }
        proyectoCrud.eliminarProyecto(id);
    }

    // Método para obtener un proyecto por su ID
    public Proyecto obtenerProyectoPorId(int id) throws SQLException, IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del proyecto no puede ser menor o igual a cero.");
        }
        return proyectoCrud.obtenerProyectoPorId(id);
    }
}
