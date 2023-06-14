package ModeloNormal;

import jakarta.persistence.*;

import java.sql.Date;

public class Persona {


    private int id;
    private Integer clave;
    private Date fechaNac;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;

    public Persona() {

    }

    public Persona(int id, Integer clave, Date fechaNac, String nombre, String apellido, String correo, String telefono) {
        this.id = id;
        this.clave = clave;
        this.fechaNac = fechaNac;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
