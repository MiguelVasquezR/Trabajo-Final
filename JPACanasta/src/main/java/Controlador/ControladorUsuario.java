package Controlador;

import Modelo.DireccionEntity;
import Modelo.PersonaEntity;
import Modelo.UsuarioEntity;
import ModeloNormal.Direccion;
import ModeloNormal.Persona;
import ModeloNormal.Usuario;
import ModeloNormal.UsuarioR;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ControladorUsuario {

    EntityManager em;
    Gson gson;

    public ControladorUsuario(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    }

    public boolean agregarUsuario(UsuarioEntity usuario){
        try {
            if(isValid(usuario)){
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(usuario);
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

    private boolean isValid(UsuarioEntity usuario) {
        boolean correcto = true;
        if (usuario == null)
            correcto = false;
        else if (usuario.getPersonaByIdPersona().getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("Persona.byClave").setParameter("id", usuario.getPersonaByIdPersona().getClave()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }

    public boolean editarUsuario(UsuarioEntity usuario){
        try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(usuario);
                tx.commit();
                return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarUsuario(UsuarioEntity usuario){
        try {
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.remove(usuario);
                tx.commit();
                return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public UsuarioEntity consultarUsuario(String usuarioE){
        try{
            UsuarioEntity usuario = (UsuarioEntity) em.createNamedQuery("Usuario.Verificar").setParameter("usuario", usuarioE).getSingleResult();
            if(usuario!=null){
                return usuario;
            }else{
                return null;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
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
            UsuarioEntity usuarioEntity = null;
            List<UsuarioEntity> listaUsuario = controladorUsuario.listaUsuario();
            for (UsuarioEntity usuarioEntity1 : listaUsuario){
                if (usuarioEntity1.getPersonaByIdPersona().getId() == personaEntity.getId()){
                    usuarioEntity = usuarioEntity1;
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
            Usuario usuario = new Usuario();
            usuario.setID(usuarioEntity.getId());
            usuario.setUsuario(usuarioEntity.getUsuario());
            usuario.setContrasena(usuarioEntity.getContrasena());
            String OBJSON = entityToNormal(persona, direccion, usuario);
            return OBJSON;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public PersonaEntity personaByClave(int clave){
        try{
            PersonaEntity personaEntity = (PersonaEntity) em.createNamedQuery("Persona.byClave").setParameter("clave", clave).getSingleResult();
            return personaEntity;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public DireccionEntity direccionByID(int id){
        try{
            DireccionEntity direccionEntity = (DireccionEntity) em.createNamedQuery("Direccion.byID").setParameter("id", id).getSingleResult();
            return direccionEntity;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    private String entityToNormal(Persona persona, Direccion direccion, Usuario usuario){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String usuarioJSON = gson.toJson(usuario);
            String retorno = personaJSON + "}," + direccionJSON + "}," + usuarioJSON;
            return retorno;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<UsuarioEntity> listaUsuario(){
        try{
            List<UsuarioEntity> list = em.createNamedQuery("Usuario.all").getResultList();
            return list;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(p.id) from UsuarioEntity p");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public UsuarioEntity getLastPersona(){
        try{
            UsuarioEntity direccion = (UsuarioEntity) em.createNamedQuery("Usuario.byID").setParameter("id", getLastID()).getSingleResult();
            return direccion;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }


    public String usuariosJSON(){
        try{
            List<UsuarioEntity> listaUsarioEntity = listaUsuario();
            List<Usuario> listaUsuario = new ArrayList<>();
            for (UsuarioEntity usuarioEntity : listaUsarioEntity) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(usuarioEntity.getUsuario());
                usuario.setContrasena(usuarioEntity.getContrasena());
                listaUsuario.add(usuario);
            }
            String usuarios = gson.toJson(listaUsuario);
            return usuarios;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public UsuarioEntity usuarioByID(int id_persona) {
        try{
            UsuarioEntity usuarioEntity = (UsuarioEntity) em.createNamedQuery("Usuario.idPersona").setParameter("id", id_persona).getSingleResult();
            return usuarioEntity;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String tabla() {
        try{
            ControladorPersona controladorPersona = new ControladorPersona();
            List<UsuarioEntity> listaUsuarios = listaUsuario();
            List<UsuarioR> listaUsuariosRetorno = new ArrayList<>();
            List<PersonaEntity> listaPersona = controladorPersona.getPersonas();
            int i=0;
            for (PersonaEntity persona : listaPersona){
                for (UsuarioEntity usuario : listaUsuarios){
                    if (usuario.getPersonaByIdPersona().getId() == persona.getId()){
                        UsuarioR usuarioR = new UsuarioR();
                        usuarioR.setClave(persona.getClave());
                        usuarioR.setNombre(persona.getNombre());
                        usuarioR.setTelefono(persona.getTelefono());
                        usuarioR.setUsuario(usuario.getUsuario());
                        listaUsuariosRetorno.add(usuarioR);
                    }
                }
            }
            String datosR = gson.toJson(listaUsuariosRetorno);
            return datosR;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
