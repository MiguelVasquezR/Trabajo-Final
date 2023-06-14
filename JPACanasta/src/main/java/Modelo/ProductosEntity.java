package Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "productos", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Productos.all", query = "select u from ProductosEntity u order by u.id"),
        @NamedQuery(name = "Productos.byID", query = "select u from ProductosEntity u where u.id = :id"),
})
public class ProductosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private ProductoEntity productoByIdProducto;
    @ManyToOne
    @JoinColumn(name = "id_compra", referencedColumnName = "id")
    private CompraVentaEntity compraVentaByIdCompra;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductoEntity getProductoByIdProducto() {
        return productoByIdProducto;
    }

    public void setProductoByIdProducto(ProductoEntity productoByIdProducto) {
        this.productoByIdProducto = productoByIdProducto;
    }

    public CompraVentaEntity getCompraVentaByIdCompra() {
        return compraVentaByIdCompra;
    }

    public void setCompraVentaByIdCompra(CompraVentaEntity compraVentaByIdCompra) {
        this.compraVentaByIdCompra = compraVentaByIdCompra;
    }
}
