package Modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "direccion", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Direccion.all", query = "select u from DireccionEntity u order by u.id"),
        @NamedQuery(name = "Direccion.byClave", query = "select u from DireccionEntity u where u.calle = :calle"),
        @NamedQuery(name = "Direccion.byID", query = "select u from DireccionEntity u where u.id = :id"),
})
public class DireccionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "calle")
    private String calle;
    @Basic
    @Column(name = "numero")
    private Integer numero;
    @Basic
    @Column(name = "colonia")
    private String colonia;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @OneToMany(mappedBy = "direccionByIdDireccion")
    private Collection<PersonaEntity> personasById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Collection<PersonaEntity> getPersonasById() {
        return personasById;
    }

    public void setPersonasById(Collection<PersonaEntity> personasById) {
        this.personasById = personasById;
    }
}
