package ModeloNormal;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

public class Usuario {

    private int ID;
    private String usuario;
    private String contrasena;
    public Usuario() {
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setID(int id) {
        this.ID = id;
    }

    public int getID() {
        return ID;
    }
}
