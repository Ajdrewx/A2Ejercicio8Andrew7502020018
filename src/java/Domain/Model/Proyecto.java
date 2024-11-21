package Domain.Model;

import java.sql.Date;

public class Proyecto {
    private int id;
    private String nombre;
    private float presupuesto;
    private int duracion;
    private Date fecha_inicio;
    private int usuario_id;

    // Constructor
    public Proyecto(int id, String nombre, float presupuesto, int duracion, Date fecha_inicio, int usuario_id) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.duracion = duracion;
        this.fecha_inicio = fecha_inicio;
        this.usuario_id = usuario_id;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
