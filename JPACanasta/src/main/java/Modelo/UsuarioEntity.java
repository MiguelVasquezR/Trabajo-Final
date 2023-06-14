package Modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "usuario", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Usuario.all", query = "select u from UsuarioEntity u order by u.id"),
        @NamedQuery(name = "Usuario.byID", query = "select u from UsuarioEntity u where u.id = :id"),
        @NamedQuery(name = "Usuario.Verificar", query = "select u from UsuarioEntity u where u.usuario = :usuario"),
        @NamedQuery(name = "Usuario.idPersona", query = "select u from UsuarioEntity  u where u.personaByIdPersona.id = :id")
})
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "usuario")
    private String usuario;
    @Basic
    @Column(name = "contrasena")
    private String contrasena;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<CompraVentaEntity> compraVentasById;
    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id")
    private PersonaEntity personaByIdPersona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public Collection<CompraVentaEntity> getCompraVentasById() {
        return compraVentasById;
    }

    public void setCompraVentasById(Collection<CompraVentaEntity> compraVentasById) {
        this.compraVentasById = compraVentasById;
    }

    public PersonaEntity getPersonaByIdPersona() {
        return personaByIdPersona;
    }

    public void setPersonaByIdPersona(PersonaEntity personaByIdPersona) {
        this.personaByIdPersona = personaByIdPersona;
    }
}
