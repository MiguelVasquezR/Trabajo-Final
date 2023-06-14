package ModeloNormal;

import java.sql.Date;

public class Proveedor {

    private String empresa;
    private Date fechaEntrega;
    private Date fechaRecoge;

    public Proveedor() {
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaRecoge() {
        return fechaRecoge;
    }

    public void setFechaRecoge(Date fechaRecoge) {
        this.fechaRecoge = fechaRecoge;
    }
}
