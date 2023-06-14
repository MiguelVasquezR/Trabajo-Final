package Controlador;

import Modelo.CompraVentaEntity;
import Modelo.DireccionEntity;
import jakarta.persistence.*;

import java.util.List;

public class ControladorCompra_Venta {

    EntityManager em;

    public ControladorCompra_Venta(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List<CompraVentaEntity> listCompraVenta() throws Exception{
        List<CompraVentaEntity> lista = em.createNamedQuery("CompraVenta.all").getResultList();
        return lista;
    }

    public boolean agregarVenta(CompraVentaEntity compraVenta){
        try{
            if (isValid(compraVenta)){
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(compraVenta);
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

    private boolean isValid(CompraVentaEntity compraVenta){
        boolean correcto = true;
        if (compraVenta == null)
            correcto = false;
        else if (compraVenta.getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("CompraVenta.byID").setParameter("id", compraVenta.getId()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(d.id) from CompraVentaEntity d");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public CompraVentaEntity getLastCompraVenta() {
        try{
            CompraVentaEntity direccion = (CompraVentaEntity) em.createNamedQuery("CompraVenta.byID").setParameter("id", getLastID()).getSingleResult();
            return direccion;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }
}
