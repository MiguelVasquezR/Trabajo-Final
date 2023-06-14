package Controlador;

import Modelo.DireccionEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.IOException;
import java.util.List;

public class ControladorDireccion {

    EntityManager em;

    public ControladorDireccion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List<DireccionEntity> listaDirecciones(){
        List<DireccionEntity> lista = em.createNamedQuery("Direccion.all").getResultList();
        return lista;
    }

    public boolean agregarDireccion(DireccionEntity direccion){
        if (isValid(direccion)){
            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(direccion);
                tx.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean isValid(DireccionEntity direccion){
        boolean correcto = true;
        if (direccion == null)
            correcto = false;
        else if (direccion.getCalle().length() == 0)
            correcto = false;
        else {
            try {
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return  correcto;
    }

    public boolean editarDireccion(DireccionEntity direccion){
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(direccion);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eliminarDireccion(int id){
        try {
            DireccionEntity direccion = (DireccionEntity) em.createNamedQuery("Direccion.byID").setParameter("id", id).getSingleResult();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(direccion);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(d.id) from DireccionEntity d");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public DireccionEntity getLastDireccion(){
        try{
            DireccionEntity direccion = (DireccionEntity) em.createNamedQuery("Direccion.byID").setParameter("id", getLastID()).getSingleResult();
            return direccion;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public DireccionEntity buscarDireccion(int id) {
        try{
            DireccionEntity direccion = (DireccionEntity) em.createNamedQuery("Direccion.byID").setParameter("id", id).getSingleResult();
            return direccion;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
