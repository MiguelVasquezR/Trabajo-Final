package ModeloNormal;

import java.sql.Date;

public class ProveedorR {

    private int clave;
    private String nombre;
    private String telefono;
    private String compania;
    private Date fechaRecoge;
    private Date fechaEntrega;

    public ProveedorR() {
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Date getFechaRecoge() {
        return fechaRecoge;
    }

    public void setFechaRecoge(Date fechaRecoge) {
        this.fechaRecoge = fechaRecoge;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
}
