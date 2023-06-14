package Modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "producto", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Producto.all", query = "select u from ProductoEntity u order by u.id"),
        @NamedQuery(name = "Producto.byClave", query = "select u from ProductoEntity u where u.clave = :clave"),
        @NamedQuery(name = "Producto.byID", query = "select u from ProductoEntity u where u.id = :id"),
})
public class ProductoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "clave")
    private Integer clave;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "precio")
    private Double precio;
    @Basic
    @Column(name = "cantidad")
    private Double cantidad;
    @Basic
    @Column(name = "tipo")
    private String tipo;
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    private ProveedorEntity proveedorByIdProveedor;
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    private CategoriaEntity categoriaByIdCategoria;
    @OneToMany(mappedBy = "productoByIdProducto")
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ProveedorEntity getProveedorByIdProveedor() {
        return proveedorByIdProveedor;
    }

    public void setProveedorByIdProveedor(ProveedorEntity proveedorByIdProveedor) {
        this.proveedorByIdProveedor = proveedorByIdProveedor;
    }

    public CategoriaEntity getCategoriaByIdCategoria() {
        return categoriaByIdCategoria;
    }

    public void setCategoriaByIdCategoria(CategoriaEntity categoriaByIdCategoria) {
        this.categoriaByIdCategoria = categoriaByIdCategoria;
    }

    public Collection<ProductosEntity> getProductosById() {
        return productosById;
    }

    public void setProductosById(Collection<ProductosEntity> productosById) {
        this.productosById = productosById;
    }
}
