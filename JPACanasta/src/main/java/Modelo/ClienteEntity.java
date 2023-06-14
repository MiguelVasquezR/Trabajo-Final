package Modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "cliente", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Cliente.all", query = "select u from ClienteEntity u order by u.id"),
        @NamedQuery(name = "Cliente.byID", query = "select u from ClienteEntity u where u.id = :id"),
        @NamedQuery(name = "Cliente.byIDF", query = "select u from ClienteEntity u where u.personaByIdPersona.id = :id"),
})
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nom_empresa")
    private String nomEmpresa;
    @Basic
    @Column(name = "tamaño_empresa")
    private String tamañoEmpresa;
    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private PersonaEntity personaByIdPersona;
    @OneToMany(mappedBy = "clienteByIdCliente")
    private Collection<CompraVentaEntity> compraVentasById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEmpresa() {
        return nomEmpresa;
    }

    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    public String getTamañoEmpresa() {
        return tamañoEmpresa;
    }

    public void setTamañoEmpresa(String tamañoEmpresa) {
        this.tamañoEmpresa = tamañoEmpresa;
    }

    public PersonaEntity getPersonaByIdPersona() {
        return personaByIdPersona;
    }

    public void setPersonaByIdPersona(PersonaEntity personaByIdPersona) {
        this.personaByIdPersona = personaByIdPersona;
    }

    public Collection<CompraVentaEntity> getCompraVentasById() {
        return compraVentasById;
    }

    public void setCompraVentasById(Collection<CompraVentaEntity> compraVentasById) {
        this.compraVentasById = compraVentasById;
    }
}
