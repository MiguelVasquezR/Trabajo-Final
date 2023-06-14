package Controlador;

import Modelo.*;
import ModeloNormal.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class ControladorProducto {


    EntityManager em;
    Gson gson;

    public ControladorProducto(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    }

    public List<ProductoEntity> listaProductos(){
        try{
            List<ProductoEntity> lista = em.createNamedQuery("Producto.all").getResultList();
            return lista;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean agregarProducto(ProductoEntity producto){
        try{
            if (isValid(producto)){
                EntityTransaction et = em.getTransaction();
                et.begin();
                em.persist(producto);
                et.commit();
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    private boolean isValid(ProductoEntity producto) {
        boolean correcto = true;
        if (producto == null)
            correcto = false;
        else if (producto.getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("Proveedor.byClave").setParameter("id", producto.getClave()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }

    public ProductoEntity productoByClave(int clave){
        try{
            ProductoEntity productoEntity = (ProductoEntity) em.createNamedQuery("Producto.byClave").setParameter("clave", clave).getSingleResult();
            return productoEntity;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }




    public String productostoJSON(){
        try{
            List<ProductoEntity> lista = listaProductos();
            List<Producto> listaProductos = new ArrayList<>();
            for (ProductoEntity productoEntity : lista){
                Producto producto = new Producto();
                producto.setClave(productoEntity.getClave());
                producto.setNombre(productoEntity.getNombre());
                producto.setCantidad(productoEntity.getCantidad());
                producto.setPrecio(productoEntity.getPrecio());
                producto.setTipo(producto.getTipo());
                listaProductos.add(producto);
            }
            String productosJSON = gson.toJson(listaProductos);
            return productosJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public String productoToJSON(int clave){
        try{
            ProductoEntity productoEntity = productoByClave(clave);
            Producto producto = new Producto();
            producto.setClave(productoEntity.getClave());
            producto.setNombre(productoEntity.getNombre());
            producto.setTipo(productoEntity.getTipo());
            producto.setPrecio(productoEntity.getPrecio());
            producto.setCantidad(productoEntity.getCantidad());
            String productoJSON = gson.toJson(producto);

            ProveedorEntity proveedorEntity = new ProveedorEntity();
            proveedorEntity = productoEntity.getProveedorByIdProveedor();
            Proveedor proveedor = new Proveedor();
            proveedor.setEmpresa(proveedorEntity.getCompania());
            proveedor.setFechaEntrega(proveedorEntity.getFechaEntrega());
            proveedor.setFechaRecoge(proveedorEntity.getFechaRecPro());
            String proveedorJSON = gson.toJson(proveedor);

            CategoriaEntity categoriaEntity = new CategoriaEntity();
            categoriaEntity = productoEntity.getCategoriaByIdCategoria();
            Categoria categoria = new Categoria();
            categoria.setClave(categoriaEntity.getClave());
            categoria.setNombre(categoriaEntity.getNombre());
            String categoriaJSON = gson.toJson(categoria);
            return productoJSON + "}," + proveedorJSON + "}," + categoriaJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ProductoEntity jsonToProducto(String productoJSON){
        try{
            Producto producto = gson.fromJson(productoJSON, Producto.class);
            ProductoEntity productoEntity = new ProductoEntity();
            productoEntity.setClave(producto.getClave());
            productoEntity.setTipo(producto.getTipo());
            productoEntity.setPrecio(producto.getPrecio());
            productoEntity.setCantidad(producto.getCantidad());
            productoEntity.setNombre(producto.getNombre());
            return productoEntity;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public boolean editarDatos(String datos) {
        try{
            String[] datoN = datos.split("},");
            Producto productoNuevo = gson.fromJson(datoN[0], Producto.class);
            Proveedor proveedorNuevo = gson.fromJson(datoN[1], Proveedor.class);
            Categoria categoriaNuevo = gson.fromJson(datoN[2], Categoria.class);

            //Datos actuales
            ControladorProducto controladorProducto = new ControladorProducto();
            ControladorProveedor controladorProveedor = new ControladorProveedor();
            ControladorCategoria controladorCategoria = new ControladorCategoria();
            ProductoEntity productoEntity = controladorProducto.productoByClave(productoNuevo.getClave());
            ProveedorEntity proveedorEntity = controladorProveedor.proveedorbyEmpresa(proveedorNuevo.getEmpresa());
            CategoriaEntity categoriaEntity = controladorCategoria.categoriabyClave(categoriaNuevo.getClave());

            //Datos sustituir
            categoriaEntity.setNombre(categoriaNuevo.getNombre());
            categoriaEntity.setClave(categoriaNuevo.getClave());
            controladorCategoria.agregarCategoria(categoriaEntity);

            proveedorEntity.setCompania(proveedorNuevo.getEmpresa());
            proveedorEntity.setFechaRecPro(proveedorNuevo.getFechaRecoge());
            proveedorEntity.setFechaEntrega(proveedorNuevo.getFechaEntrega());
            controladorProveedor.editarProveedor(proveedorEntity);

            productoEntity.setClave(productoNuevo.getClave());
            productoEntity.setTipo(productoNuevo.getTipo());
            productoEntity.setPrecio(productoNuevo.getPrecio());
            productoEntity.setCantidad(productoNuevo.getCantidad());
            productoEntity.setNombre(productoNuevo.getNombre());
            productoEntity.setCategoriaByIdCategoria(categoriaEntity);
            productoEntity.setProveedorByIdProveedor(proveedorEntity);
            controladorProducto.agregarProducto(productoEntity);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
