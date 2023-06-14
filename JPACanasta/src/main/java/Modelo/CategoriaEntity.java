package Modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "categoria", schema = "canastabd", catalog = "")
@NamedQueries({
        @NamedQuery(name = "Categoria.all", query = "select u from CategoriaEntity u order by u.id"),
        @NamedQuery(name = "Categoria.byClave", query = "select u from CategoriaEntity u where u.clave = :clave"),
        @NamedQuery(name = "Categoria.byID", query = "select u from CategoriaEntity u where u.id = :id"),
})
public class CategoriaEntity {
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
    @OneToMany(mappedBy = "categoriaByIdCategoria")
    private Collection<ProductoEntity> productosById;

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

    public Collection<ProductoEntity> getProductosById() {
        return productosById;
    }

    public void setProductosById(Collection<ProductoEntity> productosById) {
        this.productosById = productosById;
    }
}
