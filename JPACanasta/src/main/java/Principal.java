import Controlador.*;
import Modelo.*;
import ModeloNormal.Direccion;
import com.google.gson.Gson;
import servidor.Servidor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Principal {

    ControladorDireccion cd = new ControladorDireccion();
    ControladorPersona controladorPersona = new ControladorPersona();
    public static void main(String[] args) {
        try{
            Servidor servidor = new Servidor();
            servidor.initServer();

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }


    private void agregarCompraVenta(){
        ControladorProducto controladorProducto = new ControladorProducto();
        ControladorCompra_Venta compraVenta = new ControladorCompra_Venta();
        ControladorCliente controladorCliente = new ControladorCliente();
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        ControladorProductos controladorProductos = new ControladorProductos();
        try{
            CompraVentaEntity compra_venta = new CompraVentaEntity();
            compra_venta.setClave(11111);
            compra_venta.setTipo("Venta");
            compra_venta.setFecha(getfecha("2023-06-02"));
            compra_venta.setClienteByIdCliente(controladorCliente.getLastPersona());
            compra_venta.setUsuarioByIdUsuario(controladorUsuario.getLastPersona());
            compraVenta.agregarVenta(compra_venta);
            List<ProductoEntity> lista = controladorProducto.listaProductos();
            for (ProductoEntity producto : lista){
                ProductosEntity productosEntity = new ProductosEntity();
                if (controladorProductos.getLastID() == 1){
                    productosEntity.setId(controladorProductos.getLastID());
                }else{
                    productosEntity.setId(controladorProductos.getLastID());
                }
                productosEntity.setCompraVentaByIdCompra(compraVenta.getLastCompraVenta());
                productosEntity.setProductoByIdProducto(producto);
                controladorProductos.agregarVenta(productosEntity);
            }
        }catch (Exception e){
            System.out.println("\n\n");
            System.err.println(e.getMessage());
        }
    }

    public void agregarCategoria(){
        try{
            ControladorCategoria controladorCategoria = new ControladorCategoria();
            CategoriaEntity categoria = new CategoriaEntity();
            categoria.setClave(12312);
            categoria.setNombre("Higiene Personal");
            controladorCategoria.agregarCategoria(categoria);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void agregarProducto(){
        try{
            ControladorProducto cppp = new ControladorProducto();
            ControladorProveedor ctrlP = new ControladorProveedor();
            ControladorCategoria controladorCategoria = new ControladorCategoria();
            ProductoEntity producto = new ProductoEntity();
            producto.setClave(Integer.parseInt("12345"));
            producto.setCantidad(50.00);
            producto.setNombre("Senso");
            producto.setTipo("Galletas");
            producto.setPrecio(22.00);
            producto.setProveedorByIdProveedor(ctrlP.getLasrProveedor());
            producto.setCategoriaByIdCategoria(controladorCategoria.getLastCategoria());
            System.out.println("Producto: " + cppp.agregarProducto(producto));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void agregarPersona(){
        try{
            String fechaString = "2002-09-18";
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaUtil = formatter.parse(fechaString);
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());

            ControladorDireccion controladorDireccion = new ControladorDireccion();
            DireccionEntity direccion = new DireccionEntity();
            direccion.setCalle("Del Obrero");
            direccion.setNumero(9);
            direccion.setColonia("Progreso");
            direccion.setCiudad("Xalapa");
            System.out.println("Direccion: " + controladorDireccion.agregarDireccion(direccion));

            PersonaEntity persona = new PersonaEntity();
            persona.setClave(18092002);
            persona.setNombre("Xanery Magally");
            persona.setApellido("Landeros Mariano");
            persona.setTelefono("2251282345");
            persona.setCorreo("magallylanderos@gmail.com");
            persona.setFechaNac(fechaSQL);
            persona.setDireccionByIdDireccion(controladorDireccion.getLastDireccion());
            System.out.println("Persona: " + controladorPersona.agregarPersona(persona));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void agregarCliente(){
        try{
            agregarPersona();
            ControladorCliente controladorCliente = new ControladorCliente();
            ClienteEntity cliente = new ClienteEntity();
            cliente.setNomEmpresa("Chetos");
            cliente.setTamañoEmpresa("Mediana");
            cliente.setPersonaByIdPersona(controladorPersona.getLastPersona());
            System.out.println("Cliente: " + controladorCliente.agregarCliente(cliente));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void agregarUsuario(){
        try{
            ControladorUsuario cu = new ControladorUsuario();
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setUsuario("Xanery");
            usuario.setContrasena("08122022");
            usuario.setPersonaByIdPersona(controladorPersona.getLastPersona());
            System.out.println("Usuario: " + cu.agregarUsuario(usuario));

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void agregarProveedor(){

        System.out.println("Entra");
        try{
            ControladorProveedor cpp = new ControladorProveedor();
            ProveedorEntity proveedor = new ProveedorEntity();
            proveedor.setCompania("La Costeña");
            String fechaPro = "2002-09-18";
            proveedor.setFechaRecPro(getfecha(fechaPro));
            String fechaP = "2002-03-18";
            proveedor.setFechaEntrega(getfecha(fechaP));
            proveedor.setPersonaByIdPersona(controladorPersona.getLastPersona());
            System.out.println("Proveedor: " + cpp.agregarProveedor(proveedor));

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    private java.sql.Date getfecha(String fecha) throws Exception{
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        Date fu = f1.parse(fecha);
        java.sql.Date fk = new java.sql.Date(fu.getTime());
        return fk;
    }

    public void toJson(){
        try{
            Gson gson = new Gson();
            List<DireccionEntity> listaDireccion = cd.listaDirecciones();
            List<Direccion> listaDirecciones = new ArrayList<Direccion>();
            System.out.println(listaDireccion.size());
            for (DireccionEntity direccion : listaDireccion){
                Direccion direccionD = new Direccion();
                direccionD.setId(direccion.getId());
                direccionD.setCalle(direccion.getCalle());
                direccionD.setNumero(direccion.getNumero());
                direccionD.setColonia(direccion.getColonia());
                direccionD.setCiudad(direccion.getCiudad());
                listaDirecciones.add(direccionD);
            }
            String cadenaGson =gson.toJson(listaDirecciones);
            System.out.println(cadenaGson);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private void initServer() {

    }


}
