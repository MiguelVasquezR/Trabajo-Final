package com.example.interfaz.servidor;

import com.example.interfaz.ModeloClases.Direccion;
import com.example.interfaz.ModeloClases.Persona;
import com.example.interfaz.ModeloClases.Proveedor;
import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.controladores.*;
import com.example.interfaz.vista.emergentes.Alerta;
import com.example.interfaz.vista.paneles.panel_categoria.PanelCategoria;
import com.example.interfaz.vista.paneles.paneles_clientes.PC_Clientes;
import com.example.interfaz.vista.paneles.paneles_clientes.PC_Editar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Agregar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Editar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Inventario;
import com.example.interfaz.vista.paneles.paneles_proveedores.PPR_Editar;
import com.example.interfaz.vista.paneles.paneles_proveedores.PPR_Proveedor;
import com.example.interfaz.vista.paneles.paneles_usuarios.PU_Editar;
import com.example.interfaz.vista.paneles.paneles_usuarios.PU_Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;

public class Cliente {

    private String mensaje;

    private int puerto = 8000;

    public Cliente(String mensaje) {
        this.mensaje = mensaje;
    }

    public void usuario(String usuario, ControladorLogin controladorLoginE){
        try{
            ControladorLogin controladorLogin = controladorLoginE;
            Socket cliente = new Socket("localhost", puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            escritor.write("USUARIO\n");
            escritor.write("BUSCAR\n");
            escritor.write(usuario+"\n");
            escritor.flush();
            String respuesta = lector.readLine();
            Gson gson = new Gson();
            Usuario local = gson.fromJson(usuario, Usuario.class);
            Usuario usuario1 = gson.fromJson(respuesta, Usuario.class);
            if (local.getContrasena().equals(usuario1.getContrasena())){
                controladorLogin.entrar();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            Alerta alerta = new Alerta(Alert.AlertType.ERROR, "Sin Conexión al Servidor");
            alerta.show();
        }
    }

    public void Usuario(String tipo, String usuario, PU_Editar editar, PU_Usuario usuarios){
        try{
            Socket cliente = new Socket("localhost", puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            if (tipo.equals("AGREGAR")){
                escritor.write("USUARIO\n");
                escritor.write("AGREGAR\n");
                escritor.write(usuario+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("BUSCAR")){
                String clave = usuario;
                escritor.write("USUARIO\n");
                escritor.write("BUSCARRETORNO\n");
                escritor.write(clave+"\n");
                escritor.flush();
                String objetos = lector.readLine();
                String[] JSON = objetos.split("},");
                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                Persona persona = gson.fromJson(JSON[0], Persona.class);
                Direccion direccion = gson.fromJson(JSON[1], Direccion.class);
                Usuario usuario1 = gson.fromJson(JSON[2], Usuario.class);
                editar.llenarCampos(persona, direccion, usuario1);
            }else if (tipo.equals("EDITAR")){
                escritor.write("USUARIO\n");
                escritor.write("EDITAR\n");
                escritor.write(usuario+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("LLENAR")){
                escritor.write("USUARIO\n");
                escritor.write("LISTATABLA\n");
                escritor.flush();
                String ListaJSON = lector.readLine();
                usuarios.listaProveedor(ListaJSON);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void Cliente(String tipo, String cliente, PC_Editar puEditar, PC_Clientes pcClientes){
        try{
            Socket clienteS = new Socket("localhost", puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(clienteS.getInputStream()));
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(clienteS.getOutputStream()));
            if (tipo.equals("AGREGAR")){
                escritor.write("CLIENTE\n");
                escritor.write("AGREGAR\n");
                escritor.write(cliente+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("BUSCAR")){
                escritor.write("CLIENTE\n");
                escritor.write("BUSCAR\n");
                escritor.write(cliente+"\n");
                escritor.flush();
                String datos = lector.readLine();
                ControladorCliete controladorCliete = new ControladorCliete();
                controladorCliete.llenarCampos(datos, puEditar);
            }else if (tipo.equals("NUEVOSDATOS")){
                escritor.write("CLIENTE\n");
                escritor.write("NUEVOSDATOS\n");
                escritor.write(cliente+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("LISTAP")){
                escritor.write("CLIENTE\n");
                escritor.write("LISTATABLA\n");
                escritor.flush();
                String objetos = lector.readLine();
                pcClientes.listaCliente(objetos);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void Categoria(String tipo, String categorira, ControladorVCategoria controladorVCategoria, PP_Agregar ppAgregar, PP_Editar ppEditar, PanelCategoria panelCategoria){
        try{
            Socket clienteS = new Socket("localhost", puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(clienteS.getInputStream()));
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(clienteS.getOutputStream()));
            if (tipo.equals("AGREGAR")){
                escritor.write("CATEGORIA\n");
                escritor.write("AGREGAR\n");
                escritor.write(categorira+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("LISTA")){
                escritor.write("CATEGORIA\n");
                escritor.write("LISTA\n");
                escritor.flush();
                String datos = lector.readLine();
                ControladorVCategoria controladorVCategoria1 = new ControladorVCategoria();
                controladorVCategoria1.listaProveedores(datos, ppAgregar);
            }else if (tipo.equals("LISTAEDITAR")){
                escritor.write("CATEGORIA\n");
                escritor.write("LISTA\n");
                escritor.flush();
                String datos = lector.readLine();
                ControladorVCategoria controladorVCategoria1 = new ControladorVCategoria();
                controladorVCategoria1.listaProveedores(datos, ppEditar);
            }else if (tipo.equals("LLENAR")){
                escritor.write("CATEGORIA\n");
                escritor.write("LISTATABLA\n");
                escritor.flush();
                String ListaJSON = lector.readLine();
                panelCategoria.listaProveedor(ListaJSON);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void proveedor(String tipo, String proveedor, PPR_Editar editar, PP_Agregar ppAgregar, PP_Editar ppEditar, PPR_Proveedor pprProveedor){
        try{
            Socket cliente = new Socket("localhost", puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
            if (tipo.equals("AGREGAR")){
                escritor.write("PROVEEDOR\n");
                escritor.write("AGREGAR\n");
                escritor.write(proveedor+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("BUSCAR")){
                    String clave = proveedor;
                    escritor.write("PROVEEDOR\n");
                    escritor.write("BUSCARRETORNO\n");
                    escritor.write(clave+"\n");
                    escritor.flush();
                    String objetos = lector.readLine();
                    String[] JSON = objetos.split("},");
                    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                    Persona persona = gson.fromJson(JSON[0], Persona.class);
                    Direccion direccion = gson.fromJson(JSON[1], Direccion.class);
                    Proveedor proveedor1 = gson.fromJson(JSON[2], Proveedor.class);
                    editar.llenarCampos(persona, direccion, proveedor1);
            }else if (tipo.equals("EDITAR")){
                escritor.write("PROVEEDOR\n");
                escritor.write("EDITAROB\n");
                escritor.write(proveedor+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("LISTA")){
                escritor.write("PROVEEDOR\n");
                escritor.write("LISTA\n");
                escritor.flush();
                String datos = lector.readLine();
                ControladorProveedor controladorProveedor = new ControladorProveedor();
                controladorProveedor.listaProveedores(datos, ppAgregar);
            }else if (tipo.equals("LISTAEDITAR")){
                escritor.write("PROVEEDOR\n");
                escritor.write("LISTA\n");
                escritor.flush();
                String datos = lector.readLine();
                ControladorProveedor controladorProveedor = new ControladorProveedor();
                controladorProveedor.listaProveedores(datos, ppEditar);
            }else if (tipo.equals("LISTAP")){
                escritor.write("PROVEEDOR\n");
                escritor.write("LISTATABLA\n");
                escritor.flush();
                String objetos = lector.readLine();
                pprProveedor.listaProveedor(objetos);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void producto(String tipo, String objeto, PP_Editar ppEditar, PP_Inventario ppInventario){
        try{
            Socket clienteS = new Socket("localhost", puerto);
            BufferedReader lector = new BufferedReader(new InputStreamReader(clienteS.getInputStream()));
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(clienteS.getOutputStream()));
            if (tipo.equals("AGREGAR")){
                escritor.write("PRODUCTO\n");
                escritor.write("AGREGAR\n");
                escritor.write(objeto+"\n");
                escritor.flush();
                String respuesta = lector.readLine();
                if (respuesta.equals("AGREGADO")){
                    System.out.println("GUARDADO");
                }
            }else if (tipo.equals("BUSCAREDITAR")){
                escritor.write("PRODUCTO\n");
                escritor.write("EDITAR\n");
                escritor.write(objeto+"\n");
                escritor.flush();
                String datos = lector.readLine();
                ppEditar.lenarCampos(datos);
            }else if (tipo.equals("NUEVOSDATOS")){
                escritor.write("PRODUCTO\n");
                escritor.write("NUEVOSDATOS\n");
                escritor.write(objeto+"\n");
                escritor.flush();
                String datos = lector.readLine();
                System.out.println(datos);
            }else if (tipo.equals("LISTAP")){
                escritor.write("PRODUCTO\n");
                escritor.write("LISTA\n");
                escritor.write(objeto+"\n");
                escritor.flush();
                String objetos = lector.readLine();
                ppInventario.listaProductos(objetos);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
