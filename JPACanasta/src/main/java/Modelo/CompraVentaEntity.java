package Modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "compra_venta", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "CompraVenta.all", query = "select u from CompraVentaEntity u order by u.id"),
        @NamedQuery(name = "CompraVenta.byID", query = "select u from CompraVentaEntity u where u.id = :id")
})
public class CompraVentaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "clave")
    private Integer clave;
    @Basic
    @Column(name = "tipo")
    private String tipo;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private UsuarioEntity usuarioByIdUsuario;
    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private ClienteEntity clienteByIdCliente;
    @OneToMany(mappedBy = "compraVentaByIdCompra")
    private Collection<ProductosEntity> productosById;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioEntity getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(UsuarioEntity usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public ClienteEntity getClienteByIdCliente() {
        return clienteByIdCliente;
    }

    public void setClienteByIdCliente(ClienteEntity clienteByIdCliente) {
        this.clienteByIdCliente = clienteByIdCliente;
    }

    public Collection<ProductosEntity> getProductosById() {
        return productosById;
    }

    public void setProductosById(Collection<ProductosEntity> productosById) {
        this.productosById = productosById;
    }
}
