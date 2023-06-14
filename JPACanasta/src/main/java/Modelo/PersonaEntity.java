package Modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "persona", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Persona.all", query = "select u from PersonaEntity u order by u.id"),
        @NamedQuery(name = "Persona.byClave", query = "select u from PersonaEntity u where u.clave = :clave"),
        @NamedQuery(name = "Persona.byID", query = "select u from PersonaEntity u where u.id = :id"),
})
public class PersonaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "clave")
    private Integer clave;
    @Basic
    @Column(name = "fecha_nac")
    private Date fechaNac;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellido")
    private String apellido;
    @Basic
    @Column(name = "correo")
    private String correo;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(mappedBy = "personaByIdPersona")
    private Collection<ClienteEntity> clientesById;
    @ManyToOne
    @JoinColumn(name = "id_direccion", referencedColumnName = "id")
    private DireccionEntity direccionByIdDireccion;
    @OneToMany(mappedBy = "personaByIdPersona")
    private Collection<ProveedorEntity> proveedorsById;
    @OneToMany(mappedBy = "personaByIdPersona")
    private Collection<UsuarioEntity> usuariosById;

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

    public Collection<ClienteEntity> getClientesById() {
        return clientesById;
    }

    public void setClientesById(Collection<ClienteEntity> clientesById) {
        this.clientesById = clientesById;
    }

    public DireccionEntity getDireccionByIdDireccion() {
        return direccionByIdDireccion;
    }

    public void setDireccionByIdDireccion(DireccionEntity direccionByIdDireccion) {
        this.direccionByIdDireccion = direccionByIdDireccion;
    }

    public Collection<ProveedorEntity> getProveedorsById() {
        return proveedorsById;
    }

    public void setProveedorsById(Collection<ProveedorEntity> proveedorsById) {
        this.proveedorsById = proveedorsById;
    }

    public Collection<UsuarioEntity> getUsuariosById() {
        return usuariosById;
    }

    public void setUsuariosById(Collection<UsuarioEntity> usuariosById) {
        this.usuariosById = usuariosById;
    }
}
