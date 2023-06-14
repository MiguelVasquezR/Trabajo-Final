package Controlador;

import Modelo.*;
import ModeloNormal.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ControladorCliente {

    EntityManager em;
    Gson gson;
    public ControladorCliente(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    }

    public boolean agregarCliente(ClienteEntity cliente){
        try {
            if(isValid(cliente)){
                EntityTransaction tx = em.getTransaction();
                tx.begin();
                em.persist(cliente);
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

    private boolean isValid(ClienteEntity cliente) {
        boolean correcto = true;
        if (cliente == null)
            correcto = false;
        else if (cliente.getPersonaByIdPersona().getClave().toString().length() == 0)
            correcto = false;
        else {
            try {
                em.createNamedQuery("Cliente.byClave").setParameter("id", cliente.getPersonaByIdPersona().getClave()).getSingleResult();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
        return  correcto;
    }

    public boolean editarCliente(ClienteEntity cliente){
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(cliente);
            tx.commit();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public boolean eliminarCliente(ClienteEntity cliente){
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.remove(cliente);
            tx.commit();
            return true;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public List<ClienteEntity> listaClientes(){
        try{
            List<ClienteEntity> list = em.createNamedQuery("Cliente.all").getResultList();
            return list;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int getLastID(){
        try{
            Query query = em.createQuery("select  max(p.id) from ClienteEntity p");
            Integer lastId = (Integer) query.getSingleResult();
            return lastId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public ClienteEntity getLastPersona(){
        try{
            ClienteEntity direccion = (ClienteEntity) em.createNamedQuery("Cliente.byID").setParameter("id", getLastID()).getSingleResult();
            return direccion;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }


    public String clientesJSON(){
        try{
            List<ClienteEntity> listaClientesEntity = listaClientes();
            List<Cliente> listaCliente = new ArrayList<>();
            for (ClienteEntity clienteEntity : listaClientesEntity){
                Cliente cliente = new Cliente();
                cliente.setNomEmpresa(clienteEntity.getNomEmpresa());
                cliente.setTamaño(clienteEntity.getTamañoEmpresa());
                listaCliente.add(cliente);
            }
            String clienteJSON = gson.toJson(listaCliente);
            return clienteJSON;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private ClienteEntity buscarCliente(int id){
        try{
            List<ClienteEntity> listaCliente = listaClientes();
            for (ClienteEntity cliente : listaCliente){
                if (cliente.getPersonaByIdPersona().getId() == id){
                    return cliente;
                }
            }

            ClienteEntity cliente = (ClienteEntity) em.createNamedQuery("Cliente.byIDF").setParameter("personaByIdPersona.id", id).getSingleResult();
            if (cliente!= null){
                return cliente;
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String enviarCliente(int id){
        Categoria categoria = null;
        Persona persona=null;
        Direccion direccion=null;
        Cliente cliente=null;

        ControladorPersona controladorPersona = new ControladorPersona();
        try{
            PersonaEntity personaEntity = controladorPersona.buscarClave(id);
            if (personaEntity != null){
                persona = new Persona();
                persona.setClave(personaEntity.getClave());
                persona.setNombre(personaEntity.getNombre());
                persona.setApellido(personaEntity.getApellido());
                persona.setFechaNac(personaEntity.getFechaNac());
                persona.setTelefono(personaEntity.getTelefono());
                persona.setCorreo(personaEntity.getCorreo());
            }
            DireccionEntity direccionEntity = personaEntity.getDireccionByIdDireccion();
            if (direccionEntity!=null){
                direccion = new Direccion();
                direccion.setCalle(direccionEntity.getCalle());
                direccion.setNumero(direccionEntity.getNumero());
                direccion.setColonia(direccionEntity.getColonia());
                direccion.setCiudad(direccionEntity.getCiudad());
            }
            ClienteEntity clienteEntity = buscarCliente(personaEntity.getId());
            if (clienteEntity!=null){
                cliente = new Cliente();
                cliente.setNomEmpresa(clienteEntity.getNomEmpresa());
                cliente.setTamaño(clienteEntity.getTamañoEmpresa());
            }else{
                return null;
            }
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String clienteJSON = gson.toJson(cliente);
            return personaJSON + "}," + direccionJSON + "}," + clienteJSON;
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
            ClienteEntity usuarioEntity = null;
            List<ClienteEntity> listaUsuario = listaClientes();
            for (ClienteEntity usuarioEntity1 : listaUsuario){
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
            Cliente cliente = new Cliente();
            cliente.setTamaño(usuarioEntity.getTamañoEmpresa());
            cliente.setNomEmpresa(usuarioEntity.getNomEmpresa());
            String OBJSON = entityToNormal(persona, direccion, cliente);
            return OBJSON;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    private String entityToNormal(Persona persona, Direccion direccion, Cliente cliente){
        try{
            String personaJSON = gson.toJson(persona);
            String direccionJSON = gson.toJson(direccion);
            String usuarioJSON = gson.toJson(cliente);
            String retorno = personaJSON + "}," + direccionJSON + "}," + usuarioJSON;
            return retorno;
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
            Cliente cliente = gson.fromJson(ND[2], Cliente.class);


            //Datos actuales
            ControladorPersona controladorPersona = new ControladorPersona();
            ControladorDireccion controladorDireccion = new ControladorDireccion();

            PersonaEntity personaEntity = controladorPersona.buscarClave(persona.getClave());
            DireccionEntity direccionEntity = controladorDireccion.buscarDireccion(personaEntity.getDireccionByIdDireccion().getId());
            ClienteEntity clienteEntityE = null;
            List<PersonaEntity> listaPersona = controladorPersona.getPersonas();
            List<ClienteEntity> listaCliente = listaClientes();
            int i=0;
            for (ClienteEntity clienteEntity : listaCliente ){
                if (personaEntity.getId() == clienteEntity.getPersonaByIdPersona().getId()){
                    clienteEntityE = clienteEntity;
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

            clienteEntityE.setNomEmpresa(cliente.getNomEmpresa());
            clienteEntityE.setTamañoEmpresa(cliente.getTamaño());
            editarCliente(clienteEntityE);

            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    public String tabla() {
        try{
            ControladorPersona controladorPersona = new ControladorPersona();
            List<ClienteEntity> listaCliente = listaClientes();
            List<ClienteR> listaClienteRetorno = new ArrayList<>();
            List<PersonaEntity> listaPersona = controladorPersona.getPersonas();
            int i=0;
            for (PersonaEntity persona : listaPersona){
                for (ClienteEntity cliente : listaCliente){
                    if (cliente.getPersonaByIdPersona().getId() == persona.getId()){
                        ClienteR clienteR = new ClienteR();
                        clienteR.setClave(persona.getClave());
                        clienteR.setNombre(persona.getNombre());
                        clienteR.setTelefono(persona.getTelefono());
                        clienteR.setTamano(cliente.getTamañoEmpresa());
                        clienteR.setEmpresa(cliente.getNomEmpresa());
                        listaClienteRetorno.add(clienteR);
                    }
                }
            }
            String datosR = gson.toJson(listaClienteRetorno);
            return datosR;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
