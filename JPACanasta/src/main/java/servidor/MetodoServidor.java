package servidor;

import Controlador.*;
import Modelo.*;
import ModeloNormal.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.net.Socket;
import java.util.Date;

class MetodosServidor{

    HiloSkeleton hiloSkeleton;
    BufferedReader lector;
    Gson gson;

    public MetodosServidor(Socket socket, BufferedReader lector) {hiloSkeleton = new HiloSkeleton(socket); this.lector = lector; gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();}

    public void servidorProductos(String peticion){
        try{
            ControladorProducto producto = new ControladorProducto();
            ControladorProveedor controladorProveedor = new ControladorProveedor();
            ControladorCategoria controladorCategoria = new ControladorCategoria();
            if (peticion.equals("LISTA")){
                hiloSkeleton.enviarMenasaje(producto.productostoJSON());
            }else if (peticion.equals("AGREGAR")){
                String productoJSON = lector.readLine();
                String[] objetos = productoJSON.split("},");
                ProductoEntity productoEntity =  producto.jsonToProducto(objetos[0]);
                ProveedorEntity proveedorEntity = controladorProveedor.jsonToProveedor(objetos[1]);
                CategoriaEntity categoriaEntity = controladorCategoria.jsonToCategoria(objetos[2]);

                ProveedorEntity proveedorAsignar = controladorProveedor.buscarProveedorbyCompania(proveedorEntity.getCompania());
                CategoriaEntity categoriaAsignar = controladorCategoria.buscarCategoria(categoriaEntity.getClave());

                productoEntity.setProveedorByIdProveedor(proveedorAsignar);
                productoEntity.setCategoriaByIdCategoria(categoriaAsignar);
                producto.agregarProducto(productoEntity);
                hiloSkeleton.enviarMenasaje("AGREGADO");
            }else if (peticion.equals("EDITAR")){
                int clave = Integer.parseInt(lector.readLine());
                String productoJSON = producto.productoToJSON(clave);
                hiloSkeleton.enviarMenasaje(productoJSON);
            }else if (peticion.equals("NUEVOSDATOS")){
                String datos = lector.readLine();
                if (producto.editarDatos(datos)){
                    hiloSkeleton.enviarMenasaje("AGREGADO");
                }else{
                    hiloSkeleton.enviarMenasaje("No AGREGADO");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void servidorUsuario(String peticion){
        try {
            ControladorUsuario controladorUsuario = new ControladorUsuario();
            if (peticion.equals("LISTA")){
                hiloSkeleton.enviarMenasaje(controladorUsuario.usuariosJSON());
            }else if (peticion.equals("BUSCAR")){
                Usuario usuario = gson.fromJson(lector.readLine(), Usuario.class);
                UsuarioEntity usuarioEntity = controladorUsuario.consultarUsuario(usuario.getUsuario());
                Usuario usuario1 = new Usuario();
                usuario1.setUsuario(usuarioEntity.getUsuario());
                usuario1.setContrasena(usuarioEntity.getContrasena());
                String usuarioJSON = gson.toJson(usuario1);
                hiloSkeleton.enviarMenasaje(usuarioJSON);
            }else if (peticion.equals("AGREGAR")){
                String json = lector.readLine();
                String[] datosJSON = json.split("},");
                Persona persona = gson.fromJson(datosJSON[0], Persona.class);
                Direccion direccion = gson.fromJson(datosJSON[1], Direccion.class);
                Usuario usuario = gson.fromJson(datosJSON[2], Usuario.class);
                DireccionEntity direccionEntity = new DireccionEntity();
                direccionEntity.setCalle(direccion.getCalle());
                direccionEntity.setNumero(direccion.getNumero());
                direccionEntity.setColonia(direccion.getColonia());
                direccionEntity.setCiudad(direccion.getCiudad());
                ControladorDireccion controladorDireccion = new ControladorDireccion();
                controladorDireccion.agregarDireccion(direccionEntity);
                PersonaEntity personaEntity = new PersonaEntity();
                personaEntity.setClave(persona.getClave());
                personaEntity.setFechaNac(persona.getFechaNac());
                personaEntity.setNombre(persona.getNombre());
                personaEntity.setApellido(persona.getApellido());
                personaEntity.setCorreo(persona.getCorreo());
                personaEntity.setTelefono(persona.getTelefono());
                personaEntity.setDireccionByIdDireccion(controladorDireccion.getLastDireccion());
                ControladorPersona controladorPersona = new ControladorPersona();
                controladorPersona.agregarPersona(personaEntity);
                UsuarioEntity usuarioEntity = new UsuarioEntity();
                usuarioEntity.setUsuario(usuario.getUsuario());
                usuarioEntity.setContrasena(usuario.getContrasena());
                usuarioEntity.setPersonaByIdPersona(controladorPersona.getLastPersona());
                controladorUsuario.agregarUsuario(usuarioEntity);
                hiloSkeleton.enviarMenasaje("AGREGADO");
            }else if (peticion.equals("BUSCARRETORNO")){
                String clave = lector.readLine();
                int claveO = Integer.parseInt(clave);
                String objeto = controladorUsuario.buscarByClave(claveO);
                hiloSkeleton.enviarMenasaje(objeto);
            }else if (peticion.equals("EDITAR")){
                //Datos Nuevos
                String json = lector.readLine();
                String[] datosJSON = json.split("},");
                Persona personaN = gson.fromJson(datosJSON[0], Persona.class);
                Direccion direccionN = gson.fromJson(datosJSON[1], Direccion.class);
                Usuario usuarioN = gson.fromJson(datosJSON[2], Usuario.class);
                System.out.println(json);

                //Datos actuales
                PersonaEntity personaEntity = controladorUsuario.personaByClave(personaN.getClave());
                DireccionEntity direccionEntity = controladorUsuario.direccionByID(personaEntity.getDireccionByIdDireccion().getId());
                UsuarioEntity usuarioEntity = controladorUsuario.usuarioByID(personaEntity.getId());


                //Datos sustituir
                direccionEntity.setCalle(direccionN.getCalle());
                direccionEntity.setNumero(direccionN.getNumero());
                direccionEntity.setColonia(direccionN.getColonia());
                direccionEntity.setCiudad(direccionN.getCiudad());

                personaEntity.setClave(personaN.getClave());
                personaEntity.setFechaNac(personaN.getFechaNac());
                personaEntity.setNombre(personaN.getNombre());
                personaEntity.setApellido(personaN.getApellido());
                personaEntity.setCorreo(personaN.getCorreo());
                personaEntity.setTelefono(personaN.getTelefono());
                personaEntity.setDireccionByIdDireccion(direccionEntity);

                usuarioEntity.setPersonaByIdPersona(personaEntity);
                usuarioEntity.setUsuario(usuarioN.getUsuario());
                usuarioEntity.setContrasena(usuarioN.getContrasena());

                ControladorPersona controladorPersona = new ControladorPersona();
                ControladorDireccion controladorDireccion = new ControladorDireccion();
                controladorDireccion.editarDireccion(direccionEntity);
                controladorPersona.editarPersona(personaEntity);
                controladorUsuario.editarUsuario(usuarioEntity);

                hiloSkeleton.enviarMenasaje("AGREGADO");
            }else if (peticion.equals("LISTATABLA")){
                String datos = controladorUsuario.tabla();
                hiloSkeleton.enviarMenasaje(datos);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void servidorCliente(String peticion){
        ControladorCliente controladorCliente = new ControladorCliente();
        try{
            if (peticion.equals("LISTA")){
                hiloSkeleton.enviarMenasaje(controladorCliente.clientesJSON());
            }else if (peticion.equals("EDITAR")){
                int clave = Integer.parseInt(lector.readLine());
                hiloSkeleton.enviarMenasaje(controladorCliente.enviarCliente(clave));
            }else if (peticion.equals("AGREGAR")){
                String json = lector.readLine();
                String[] datosJSON = json.split("},");
                System.out.println(datosJSON[2]);
                Persona persona = gson.fromJson(datosJSON[0], Persona.class);
                Direccion direccion = gson.fromJson(datosJSON[1], Direccion.class);
                Cliente cliente = gson.fromJson(datosJSON[2], Cliente.class);
                DireccionEntity direccionEntity = new DireccionEntity();
                direccionEntity.setCalle(direccion.getCalle());
                direccionEntity.setNumero(direccion.getNumero());
                direccionEntity.setColonia(direccion.getColonia());
                direccionEntity.setCiudad(direccion.getCiudad());
                ControladorDireccion controladorDireccion = new ControladorDireccion();
                controladorDireccion.agregarDireccion(direccionEntity);
                PersonaEntity personaEntity = new PersonaEntity();
                personaEntity.setClave(persona.getClave());
                personaEntity.setFechaNac(persona.getFechaNac());
                personaEntity.setNombre(persona.getNombre());
                personaEntity.setApellido(persona.getApellido());
                personaEntity.setCorreo(persona.getCorreo());
                personaEntity.setTelefono(persona.getTelefono());
                personaEntity.setDireccionByIdDireccion(controladorDireccion.getLastDireccion());
                ControladorPersona controladorPersona = new ControladorPersona();
                controladorPersona.agregarPersona(personaEntity);
                ClienteEntity clienteEntity = new ClienteEntity();
                clienteEntity.setNomEmpresa(cliente.getNomEmpresa());
                clienteEntity.setTamañoEmpresa(cliente.getTamaño());
                clienteEntity.setPersonaByIdPersona(controladorPersona.getLastPersona());
                controladorCliente.agregarCliente(clienteEntity);
                hiloSkeleton.enviarMenasaje("AGREGADO");
            }else if (peticion.equals("BUSCAR")){
                String clave = lector.readLine();
                int claveO = Integer.parseInt(clave);
                String objeto = controladorCliente.buscarByClave(claveO);
                hiloSkeleton.enviarMenasaje(objeto);
            }else if(peticion.equals("NUEVOSDATOS")){
                String datos = lector.readLine();
                if (controladorCliente.preEditar(datos)){
                    hiloSkeleton.enviarMenasaje("EDITADO");
                }else{
                    hiloSkeleton.enviarMenasaje("NO EDITADO");
                }
            }else if (peticion.equals("LISTATABLA")){
                String datos = controladorCliente.tabla();
                hiloSkeleton.enviarMenasaje(datos);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void servidorProveedor(String peticion){
        ControladorProveedor controladorProveedor = new ControladorProveedor();
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        try{
            if (peticion.equals("LISTA")){
                hiloSkeleton.enviarMenasaje(controladorProveedor.proveedoresJSON());
            }else if (peticion.equals("EDITAR")){
                int id = Integer.parseInt(lector.readLine());
                hiloSkeleton.enviarMenasaje(controladorProveedor.proveedorToJSON(id));
            }else if (peticion.equals("AGREGAR")){
                String datos = lector.readLine();
                String[] datosJSON = datos.split("},");
                try{
                    Persona persona = gson.fromJson(datosJSON[0], Persona.class);
                    DireccionEntity direccion = gson.fromJson(datosJSON[1], DireccionEntity.class);
                    Proveedor proveedor = gson.fromJson(datosJSON[2], Proveedor.class);
                    ControladorDireccion controladorDireccion = new ControladorDireccion();
                    ControladorCliente controladorCliente = new ControladorCliente();
                    ControladorPersona controladorPersona = new ControladorPersona();
                    controladorDireccion.agregarDireccion(direccion);
                    PersonaEntity personaEntity = new PersonaEntity();
                    personaEntity.setClave(persona.getClave());
                    personaEntity.setFechaNac(persona.getFechaNac());
                    personaEntity.setNombre(persona.getNombre());
                    personaEntity.setApellido(persona.getApellido());
                    personaEntity.setCorreo(persona.getCorreo());
                    personaEntity.setTelefono(persona.getTelefono());
                    personaEntity.setDireccionByIdDireccion(controladorDireccion.getLastDireccion());
                    controladorPersona.agregarPersona(personaEntity);
                    ProveedorEntity proveedorEntity = new ProveedorEntity();
                    proveedorEntity.setCompania(proveedor.getEmpresa());
                    proveedorEntity.setFechaEntrega(proveedor.getFechaEntrega());
                    proveedorEntity.setFechaRecPro(proveedor.getFechaRecoge());
                    proveedorEntity.setPersonaByIdPersona(controladorPersona.getLastPersona());
                    controladorProveedor.agregarProveedor(proveedorEntity);
                    hiloSkeleton.enviarMenasaje("AGREGADO");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }else if (peticion.equals("BUSCARRETORNO")){
                String clave = lector.readLine();
                int claveO = Integer.parseInt(clave);
                String objeto = controladorProveedor.buscarByClave(claveO);
                hiloSkeleton.enviarMenasaje(objeto);
            }else if (peticion.equals("EDITAROB")){
                String datos = lector.readLine();
                if (controladorProveedor.preEditar(datos)){
                    hiloSkeleton.enviarMenasaje("AGREGADO");
                }else{
                    hiloSkeleton.enviarMenasaje("No AGREGADO");
                }

            }else if (peticion.equals("LISTATABLA")){
                String datos = controladorProveedor.tabla();
                hiloSkeleton.enviarMenasaje(datos);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void servidorVentaCompra(String peticion){
        try{

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void servidorInforme(Date fecha1, Date fecha2){
        try{

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void servidorCategoria(String peticion){
        ControladorCategoria controladorCategoria = new ControladorCategoria();
        try{
            if (peticion.equals("LISTA")){
                hiloSkeleton.enviarMenasaje(controladorCategoria.categoriasJSON());
            }else if (peticion.equals("AGREGAR")){
                Categoria categoria = gson.fromJson(lector.readLine(), Categoria.class);
                CategoriaEntity categoriaEntity = new CategoriaEntity();
                categoriaEntity.setClave(categoria.getClave());
                categoriaEntity.setNombre(categoria.getNombre());
                controladorCategoria.agregarCategoria(categoriaEntity);
                hiloSkeleton.enviarMenasaje("AGREGADO");
            }else if (peticion.equals("EDITAR")){
                int clave = Integer.parseInt(lector.readLine());
            }else if (peticion.equals("LISTATABLA")){
                hiloSkeleton.enviarMenasaje(controladorCategoria.categoriasJSON());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
