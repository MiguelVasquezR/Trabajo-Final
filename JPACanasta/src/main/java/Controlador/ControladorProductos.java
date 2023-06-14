package Controlador;

import Modelo.CompraVentaEntity;
import Modelo.ProductosEntity;
import jakarta.persistence.*;

import java.util.List;

public class ControladorProductos {

    EntityManager em;

    public ControladorProductos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List<ProductosEntity> listCompraVenta() throws Exception{
        List<ProductosEntity> lista = em.createNamedQuery("Productos.all").getResultList();
        return lista;
    }

    public boolean agregarVenta(ProductosEntity productos){
        try{
            if (isValid(productos)){
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(productos);
                tx.commit();
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    private boolean isValid(ProductosEntity productos){
        boolean correcto = true;
        if (productos == null)
            correcto = false;
        else
            correcto=true;
        return correcto;
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(d.id) from ProductosEntity d");
            if (query.getSingleResult() == null){
                return 1;
            }else{
                Integer lastId = (Integer) query.getSingleResult();
                return lastId+1;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public ProductosEntity getLastProductos() {
        try{
            ProductosEntity productos = (ProductosEntity) em.createNamedQuery("Productos.byID").setParameter("id", getLastID()).getSingleResult();
            return productos;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}
