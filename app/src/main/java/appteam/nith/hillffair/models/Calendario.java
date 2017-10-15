package appteam.nith.hillffair.models;

import java.util.ArrayList;
import java.util.List;


public class Calendario {

    private String ID;
    private String fecha;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private String nombre;
    private String tipo;
    private String estado;


    public static final List<Calendario> EVENTOS = new ArrayList<>();
}
