package ModeloNormal;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

public class Categoria {
    private Integer clave;
    private String nombre;
    public Categoria() {
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
