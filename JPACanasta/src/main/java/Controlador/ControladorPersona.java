package Controlador;

import Modelo.PersonaEntity;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.List;

public class ControladorPersona {

    EntityManager em;

    public ControladorPersona(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }
    public List<PersonaEntity> getPersonas() throws IOException {
        List<PersonaEntity> listaDir = em.createNamedQuery("Persona.all").getResultList();
        return listaDir;
    }
    public boolean agregarPersona(PersonaEntity persona){
        if (isValid(persona)){
            try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(persona);
                tx.commit();
                return true;
            } catch (Exception e) {
                return false;
            }
        }else{
            return false;
        }
    }
    private boolean isValid(PersonaEntity persona){
        boolean correcto = true;
        if (persona == null)
            correcto = false;
        else if (persona.getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("Persona.byClave").setParameter("id", persona.getClave()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }
    public boolean editarPersona(PersonaEntity persona){
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(persona);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean eliminarPersona(int id){
        try {
            PersonaEntity direccion = (PersonaEntity) em.createNamedQuery("Persona.byClave").setParameter("clave", id).getSingleResult();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(direccion);
            tx.commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public PersonaEntity buscarClave(int clave){
        try{
            PersonaEntity persona = (PersonaEntity) em.createNamedQuery("Persona.byClave").setParameter("clave", clave).getSingleResult();
            return persona;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(p.id) from PersonaEntity p");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }
    public PersonaEntity getLastPersona(){
        try{
            PersonaEntity direccion = (PersonaEntity) em.createNamedQuery("Persona.byID").setParameter("id", getLastID()).getSingleResult();
            return direccion;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}
