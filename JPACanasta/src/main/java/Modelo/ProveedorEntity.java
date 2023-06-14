package Modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "proveedor", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Proveedor.all", query = "select u from ProveedorEntity u order by u.id"),
        @NamedQuery(name = "Proveedor.byID", query = "select u from ProveedorEntity u where u.id = :id"),
        @NamedQuery(name = "Proveedor.byClave", query = "select u from ProveedorEntity u where u.personaByIdPersona.clave = :id"),
        @NamedQuery(name = "Proveedor.idPersona", query = "select u from ProveedorEntity u where u.personaByIdPersona.id = :id"),
        @NamedQuery(name = "Proveedor.byCompania", query = "select u from ProveedorEntity u where u.compania = :compania"),

})

public class ProveedorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "compania")
    private String compania;
    @Basic
    @Column(name = "fecha_Entrega")
    private Date fechaEntrega;
    @Basic
    @Column(name = "fecha_rec_pro")
    private Date fechaRecPro;
    @OneToMany(mappedBy = "proveedorByIdProveedor")
    private Collection<ProductoEntity> productosById;
    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private PersonaEntity personaByIdPersona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaRecPro() {
        return fechaRecPro;
    }

    public void setFechaRecPro(Date fechaRecPro) {
        this.fechaRecPro = fechaRecPro;
    }

    public Collection<ProductoEntity> getProductosById() {
        return productosById;
    }

    public void setProductosById(Collection<ProductoEntity> productosById) {
        this.productosById = productosById;
    }

    public PersonaEntity getPersonaByIdPersona() {
        return personaByIdPersona;
    }

    public void setPersonaByIdPersona(PersonaEntity personaByIdPersona) {
        this.personaByIdPersona = personaByIdPersona;
    }
}
