package Controlador;

import Modelo.CategoriaEntity;
import ModeloNormal.Categoria;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorCategoria {


    EntityManager em;
    Gson gson;

    public ControladorCategoria(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
         gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    }

    public List<CategoriaEntity> listaCategoria() throws Exception{
        List<CategoriaEntity> lista = em.createNamedQuery("Categoria.all").getResultList();
        return lista;
    }

    public boolean agregarCategoria(CategoriaEntity categoria){
        try{
            if (isValid(categoria)){
                EntityTransaction et = em.getTransaction();
                et.begin();
                em.persist(categoria);
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


    private boolean isValid(CategoriaEntity categoria) {
        boolean correcto = true;
        if (categoria == null)
            correcto = false;
        else if (categoria.getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("Categoria.byClave").setParameter("id", categoria.getClave()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(c.id) from CategoriaEntity c");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public CategoriaEntity getLastCategoria(){
        try{
            CategoriaEntity categoria = (CategoriaEntity) em.createNamedQuery("Categoria.byID").setParameter("id", getLastID()).getSingleResult();
            return categoria;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String categoriasJSON(){
        try {
            List<CategoriaEntity> listaCategoriaEntity = listaCategoria();
            List<Categoria> listaCategoria = new ArrayList<>();
            for (CategoriaEntity categoriaEntity : listaCategoriaEntity){
                Categoria categoria = new Categoria();
                categoria.setClave(categoriaEntity.getClave());
                categoria.setNombre(categoriaEntity.getNombre());
                listaCategoria.add(categoria);
            }
            String categoriasJSON = gson.toJson(listaCategoria);
            return categoriasJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public CategoriaEntity buscarCategoria(int clave){
        try{
            CategoriaEntity categoria = (CategoriaEntity) em.createNamedQuery("Categoria.byClave").setParameter("clave", clave).getSingleResult();
            if (categoria!=null)
                return categoria;
            else
                return null;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String categoriaToJson(int clave){
        Categoria categoria = null;
        try{
            CategoriaEntity categoriaEntity = buscarCategoria(clave);
            if (categoriaEntity!=null){
                categoria = new Categoria();
                categoria.setNombre(categoriaEntity.getNombre());
                categoria.setClave(categoriaEntity.getClave());
            }
            String categoriaJSON = gson.toJson(categoria);
            return categoriaJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public CategoriaEntity jsonToCategoria(String categoriaJSON){
        try{
            Categoria categoria = gson.fromJson(categoriaJSON, Categoria.class);
            CategoriaEntity categoriaEntity = new CategoriaEntity();
            categoriaEntity.setNombre(categoria.getNombre());
            categoriaEntity.setClave(categoria.getClave());
            return categoriaEntity;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public CategoriaEntity categoriabyClave(Integer clave) {
        try{
            CategoriaEntity categoria = (CategoriaEntity) em.createNamedQuery("Categoria.byClave").setParameter("clave", clave).getSingleResult();
            return categoria;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
