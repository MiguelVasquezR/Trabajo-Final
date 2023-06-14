package Controlador;

import Modelo.ClienteEntity;
import Modelo.DireccionEntity;
import Modelo.PersonaEntity;
import Modelo.ProveedorEntity;
import ModeloNormal.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorProveedor {

    EntityManager em;
    Gson gson;
    public ControladorProveedor(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    }

    public boolean agregarProveedor(ProveedorEntity proveedor){
        try {
            if(isValid(proveedor)){
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(proveedor);
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

    private boolean isValid(ProveedorEntity proveedor) {
        boolean correcto = true;
        if (proveedor == null)
            correcto = false;
        else if (proveedor.getPersonaByIdPersona().getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("Proveedor.byClave").setParameter("id", proveedor.getPersonaByIdPersona().getClave()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }

    public boolean editarProveedor(ProveedorEntity proveedor){
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(proveedor);
            tx.commit();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarProveedor(ProveedorEntity proveedor){
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(proveedor);
            tx.commit();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<ProveedorEntity> listaProveedor(){
        try{
            List<ProveedorEntity> list = em.createNamedQuery("Proveedor.all").getResultList();
            return list;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(p.id) from ProveedorEntity p");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public ProveedorEntity getLasrProveedor(){
        try{
            ProveedorEntity proveedor = (ProveedorEntity) em.createNamedQuery("Proveedor.byID").setParameter("id", getLastID()).getSingleResult();
            return proveedor;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public String proveedoresJSON(){
        try{
            List<ProveedorEntity> listaProveedorEntity = listaProveedor();
            List<Proveedor> listaProveedor = new ArrayList<>();
            for (ProveedorEntity proveedorEntity : listaProveedorEntity){
                Proveedor proveedor = new Proveedor();
                proveedor.setEmpresa(proveedorEntity.getCompania());
                proveedor.setFechaEntrega(proveedorEntity.getFechaEntrega());
                proveedor.setFechaRecoge(proveedorEntity.getFechaRecPro());
                listaProveedor.add(proveedor);
            }
            String proveedorJSON = gson.toJson(listaProveedor);
            return proveedorJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private ProveedorEntity buscarProveedor(int clave){
        try{
            ProveedorEntity proveedor = (ProveedorEntity) em.createNamedQuery("Proveedor.byID").setParameter("id", clave).getSingleResult();
            if (proveedor!=null){
                return proveedor;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ProveedorEntity buscarProveedorbyCompania(String compania){
        try{
            ProveedorEntity proveedor = (ProveedorEntity) em.createNamedQuery("Proveedor.byCompania").setParameter("compania", compania).getSingleResult();
            if (proveedor!=null){
                return proveedor;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String proveedorToJSON(int id){
        Proveedor proveedor = null;
        try {
                ProveedorEntity proveedorEntity = buscarProveedor(id);
            if (proveedorEntity!= null){
                proveedor = new Proveedor();
                proveedor.setEmpresa(proveedorEntity.getCompania());
                proveedor.setFechaEntrega(proveedorEntity.getFechaEntrega());
                proveedor.setFechaRecoge(proveedorEntity.getFechaRecPro());
            }
            String proveedorJSON = gson.toJson(proveedor);
            return proveedorJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ProveedorEntity jsonToProveedor(String proveedorJSON){
        try{
            Proveedor proveedor = gson.fromJson(proveedorJSON, Proveedor.class);
            ProveedorEntity proveedorEntity;
            if (proveedor!=null){
                proveedorEntity = new ProveedorEntity();
                proveedorEntity.setCompania(proveedor.getEmpresa());
                proveedorEntity.setFechaEntrega(proveedor.getFechaEntrega());
                proveedorEntity.setFechaRecPro(proveedor.getFechaRecoge());
                return proveedorEntity;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ProveedorEntity proveedorByID(int id) {
        try{
            ProveedorEntity proveedorEntity = (ProveedorEntity) em.createNamedQuery("Proveedor.idPersona").setParameter("id", id).getSingleResult();
            return proveedorEntity;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String buscarByClave(int clave){
        try{
            ControladorDireccion controladorDireccion = new ControladorDireccion();
            ControladorUsuario controladorUsuario = new ControladorUsuario();
            PersonaEntity personaEntity = (PersonaEntity) em.createNamedQuery("Persona.byClave").setParameter("clave", clave).getSingleResult();
            DireccionEntity direccionEntity=null;
            List<DireccionEntity> lista = controladorDireccion.listaDirecciones();
            for (DireccionEntity direccionEntity1 : lista){
                if (direccionEntity1.getId() == personaEntity.getDireccionByIdDireccion().getId()){
                    direccionEntity = direccionEntity1;
                    break;
                }
            }
            ProveedorEntity proveedorEntity = null;
            List<ProveedorEntity> listaProveedor = listaProveedor();
            for (ProveedorEntity proveedorEntity1 : listaProveedor){
                if (proveedorEntity1.getPersonaByIdPersona().getId() == personaEntity.getId()){
                    proveedorEntity = proveedorEntity1;
                    break;
                }
            }
            Persona persona = new Persona();
            persona.setClave(personaEntity.getClave());
            persona.setFechaNac(personaEntity.getFechaNac());
            persona.setNombre(personaEntity.getNombre());
            persona.setApellido(personaEntity.getApellido());
            persona.setTelefono(personaEntity.getTelefono());
            persona.setCorreo(personaEntity.getCorreo());
            Direccion direccion = new Direccion();
            direccion.setId(direccionEntity.getId());
            direccion.setCalle(direccionEntity.getCalle());
            direccion.setNumero(direccionEntity.getNumero());
            direccion.setColonia(direccionEntity.getColonia());
            direccion.setCiudad(direccionEntity.getCiudad());
            Proveedor proveedor = new Proveedor();
            proveedor.setEmpresa(proveedorEntity.getCompania());
            proveedor.setFechaEntrega(proveedorEntity.getFechaEntrega());
            proveedor.setFechaRecoge(proveedorEntity.getFechaRecPro());
            String OBJSON = entityToNormal(persona, direccion, proveedor);
            return OBJSON;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    private String entityToNormal(Persona persona, Direccion direccion, Proveedor proveedor){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String proveedorJSON = gson.toJson(proveedor);
            String retorno = personaJSON + "}," + direccionJSON + "}," + proveedorJSON;
            return retorno;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public ProveedorEntity proveedorbyEmpresa(String empresa) {
        try{
            ProveedorEntity proveedor = (ProveedorEntity) em.createNamedQuery("Proveedor.byCompania").setParameter("compania", empresa).getSingleResult();
            return proveedor;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean preEditar(String datos) {
        try{
            String[] ND = datos.split("},");
            Persona persona = gson.fromJson(ND[0], Persona.class);
            Direccion direccion = gson.fromJson(ND[1], Direccion.class);
            Proveedor proveedorN = gson.fromJson(ND[2], Proveedor.class);


            //Datos actuales
            ControladorPersona controladorPersona = new ControladorPersona();
            ControladorDireccion controladorDireccion = new ControladorDireccion();


            PersonaEntity personaEntity = controladorPersona.buscarClave(persona.getClave());
            DireccionEntity direccionEntity = controladorDireccion.buscarDireccion(personaEntity.getDireccionByIdDireccion().getId());
            ProveedorEntity proveedor=null;
            List<ProveedorEntity> listaProveedor = listaProveedor();
            for (ProveedorEntity proveedorE : listaProveedor){
                if (personaEntity.getId() == proveedorE.getPersonaByIdPersona().getId()){
                    proveedor = proveedorE;
                }
            }

            //Datos sustituir
            direccionEntity.setCalle(direccion.getCalle());
            direccionEntity.setNumero(direccion.getNumero());
            direccionEntity.setColonia(direccion.getColonia());
            direccionEntity.setCiudad(direccion.getCiudad());
            controladorDireccion.editarDireccion(direccionEntity);

            personaEntity.setClave(persona.getClave());
            personaEntity.setNombre(persona.getNombre());
            personaEntity.setApellido(persona.getApellido());
            personaEntity.setFechaNac(persona.getFechaNac());
            personaEntity.setTelefono(persona.getTelefono());
            personaEntity.setCorreo(persona.getCorreo());
            controladorPersona.editarPersona(personaEntity);

            proveedor.setCompania(proveedorN.getEmpresa());
            proveedor.setFechaRecPro(proveedorN.getFechaRecoge());
            proveedor.setFechaEntrega(proveedorN.getFechaEntrega());
            proveedor.setPersonaByIdPersona(personaEntity);
            editarProveedor(proveedor);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String tabla() {
        try{
            ControladorPersona controladorPersona = new ControladorPersona();
            List<ProveedorEntity> listaProveedor = listaProveedor();
            List<ProveedorR> listaRetorno = new ArrayList<>();
            List<PersonaEntity> listaPersona = controladorPersona.getPersonas();
            int i=0;
            for (PersonaEntity persona : listaPersona){
                for (ProveedorEntity proveedor : listaProveedor){
                    if (proveedor.getPersonaByIdPersona().getId() == persona.getId()){
                        ProveedorR proveedorR = new ProveedorR();
                        proveedorR.setClave(persona.getClave());
                        proveedorR.setNombre(persona.getNombre());
                        proveedorR.setTelefono(persona.getTelefono());
                        proveedorR.setCompania(proveedor.getCompania());
                        proveedorR.setFechaEntrega(proveedor.getFechaEntrega());
                        proveedorR.setFechaRecoge(proveedor.getFechaRecPro());
                        listaRetorno.add(proveedorR);
                    }
                }
            }
            String datosR = gson.toJson(listaRetorno);
            return datosR;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
