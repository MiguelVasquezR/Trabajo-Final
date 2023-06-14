package com.example.interfaz.ModeloClases;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {

    private String usuario;
    private String contrasena;

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void encriptar(String contrasena){
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        boolean validar = BCrypt.checkpw(contrasena, hashedPassword);
        if (validar)
            this.contrasena = hashedPassword;
        else
            this.contrasena = contrasena;
    }

    public boolean verificar(String password, String hashed){
        if (BCrypt.checkpw(password, hashed)) {
            return true;
        } else {
            return false;
        }
    }


}
