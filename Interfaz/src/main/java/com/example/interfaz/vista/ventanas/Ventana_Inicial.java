package com.example.interfaz.vista.ventanas;

import com.example.interfaz.vista.menus.Menu_Horizontal;
import com.example.interfaz.vista.menus.Menu_Izquierda;
import com.example.interfaz.vista.paneles.Panel_Inicio;
import com.example.interfaz.vista.paneles.Panel_Principal;
import com.example.interfaz.vista.paneles.paneles_clientes.PC_Agregar;
import com.example.interfaz.vista.paneles.paneles_clientes.PC_Clientes;
import com.example.interfaz.vista.paneles.paneles_clientes.PC_Editar;
import com.example.interfaz.vista.paneles.paneles_estadisticas.PE_Clientes;
import com.example.interfaz.vista.paneles.paneles_estadisticas.PE_CompraVenta;
import com.example.interfaz.vista.paneles.paneles_estadisticas.PE_Productos;
import com.example.interfaz.vista.paneles.paneles_informes.P_Informes_V;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Agregar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Editar;
import com.example.interfaz.vista.paneles.paneles_inventario.PP_Inventario;
import com.example.interfaz.vista.paneles.paneles_proveedores.PPR_Agregar;
import com.example.interfaz.vista.paneles.paneles_proveedores.PPR_Editar;
import com.example.interfaz.vista.paneles.paneles_proveedores.PPR_Proveedor;
import com.example.interfaz.vista.paneles.paneles_usuarios.PU_Agregar;
import com.example.interfaz.vista.paneles.paneles_usuarios.PU_Editar;
import com.example.interfaz.vista.paneles.paneles_usuarios.PU_Usuario;
import com.example.interfaz.vista.paneles.paneles_ventas.P_Compra;
import com.example.interfaz.vista.paneles.paneles_ventas.P_Venta;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Ventana_Inicial extends BorderPane {

    Menu_Izquierda menu_izq;
    Menu_Horizontal menu_hor;
    Button btnProductos, btnVentas, btnClientes, btnProveedores, btnInformes, btnUsuarios, btnEstadisticas, btnUno, btnDos, btnTres;
    String[] toltips;
    ScrollPane sp;
    Panel_Principal pp;

    //Paneles Inventario
    PP_Inventario p_inventario;
    PP_Agregar p_agregar;
    PP_Editar p_editar;

    //Paneles Clientes
    PC_Clientes pc_clientes;
    PC_Agregar pc_agregar;
    PC_Editar pc_editar;

    //Paneles Usuarios
    PU_Usuario pu_usuario;
    PU_Agregar pu_agregar;
    PU_Editar puEditar;

    //Paneles Proveedores
    PPR_Proveedor ppr_proveedor;
    PPR_Agregar ppr_agregar;
    PPR_Editar ppr_editar;

    //Paneles Ventas
    P_Venta p_venta;
    P_Compra p_compra;

    //Paneles Informes
    P_Informes_V pInformesV;

    //Paneles Estadísticas
    PE_CompraVenta pEstadisticasCv;
    PE_Clientes peClientes;
    PE_Productos peProductos;



    public Ventana_Inicial(){
        localizacion();
        botones();
    }

    private void localizacion(){
        menu_izq = new Menu_Izquierda();
        setLeft(menu_izq);
        menu_hor = new Menu_Horizontal();
        setTop(menu_hor);
        var URL = getClass().getResource("/img/Logo.png");
        var imagen = new Image(URL.toString(), 150, 150, true, true);
        pp = new Panel_Principal(imagen, "La Canasta");
        setCenter(pp);
    }

    private void botones(){
        btnProductos = menu_izq.getButtons()[0];
        btnProductos.setOnAction(evt->{
            cambiarPalenInicial(1);
        });

        btnVentas = menu_izq.getButtons()[1];
        btnVentas.setOnAction(evt->{
            cambiarPalenInicial(2);
        });

        btnClientes = menu_izq.getButtons()[2];
        btnClientes.setOnAction(evt->{
            cambiarPalenInicial(3);
        });

        btnProveedores = menu_izq.getButtons()[3];
        btnProveedores.setOnAction(evt->{
            cambiarPalenInicial(4);
        });

        btnInformes = menu_izq.getButtons()[4];
        btnInformes.setOnAction(evt->{
            cambiarPalenInicial(5);
        });

        btnUsuarios = menu_izq.getButtons()[5];
        btnUsuarios.setOnAction(evt->{
            cambiarPalenInicial(6);
        });

        btnEstadisticas = menu_izq.getButtons()[6];
        btnEstadisticas.setOnAction(evt->{
            cambiarPalenInicial(7);
        });

    }

    private void cambiarPalenInicial(int op){
        Panel_Inicio pi;
        java.net.URL urlImg = null;
        Image img = null;
        setCenter(null);
        System.out.println(1);

        switch (op){
            case 1:
                urlImg = getClass().getResource("/img/Logos/productos.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Gestión de Productos");
                eleccionsBotonesSuperiores(1);
                setCenter(pi);
                break;
            case 2:
                urlImg = getClass().getResource("/img/Logos/ventas.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Gestión de Compras & Ventas");
                eleccionsBotonesSuperiores(2);
                setCenter(pi);
                break;
            case 3:
                urlImg = getClass().getResource("/img/Logos/usuarios.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Gestión de Clientes");
                eleccionsBotonesSuperiores(3);
                setCenter(pi);
                break;
            case 4:
                urlImg = getClass().getResource("/img/Logos/proveedor.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Gestión de Proveedores");
                eleccionsBotonesSuperiores(4);
                setCenter(pi);
                break;
            case 5:
                urlImg = getClass().getResource("/img/Logos/informes.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Generador de Informes");
                eleccionsBotonesSuperiores(5);
                setCenter(pi);
                break;
            case 6:
                urlImg = getClass().getResource("/img/Logos/empleado.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Gestión de Empleados");
                eleccionsBotonesSuperiores(6);
                setCenter(pi);
                break;
            case 7:
                urlImg = getClass().getResource("/img/Logos/estadística.png");
                img = new Image(urlImg.toString(), 150, 150, true, true);
                pi = new Panel_Inicio(img, "Generador de Estadística");
                eleccionsBotonesSuperiores(7);
                setCenter(pi);
                break;
        }
    }

    private void eleccionsBotonesSuperiores(int op) {
        switch (op) {
            case 1:
                toltips = new String[]{"Ver Productos", "Agregar un Producto", "Editar un Producto"};
                menu_hor.agregar3("Productos", "Agregar", "Editar", toltips);
                btnMenuSuperior();
                asignarBTN(1);
                break;
            case 2:
                toltips = new String[]{"Hacer una Venta", "Hacer una Compra"};
                menu_hor.agregar2("Venta", "Compra", toltips);
                btnMenuSuperior();
                asignarBTN(2);
                break;
            case 3:
                toltips = new String[]{"Ver Clientes", "Agregar un Cliente", "Editar un Cliente"};
                menu_hor.agregar3("Clientes", "Agregar", "Editar", toltips);
                btnMenuSuperior();
                asignarBTN(3);
                break;
            case 4:
                toltips = new String[]{"Ver Proveedores", "Agregar un Proveedor", "Editar un Proveedor"};
                menu_hor.agregar3("Proveedores", "Agregar", "Editar", toltips);
                btnMenuSuperior();
                asignarBTN(4);
                break;
            case 5:
                toltips = new String[]{"Informe de Compra & Venta"};
                menu_hor.agregar1("Compra & Venta", toltips);
                btnMenuSuperior();
                asignarBTN(5);
                break;
            case 6:
                toltips = new String[]{"Ver Usuario", "Agregar un Usuario", "Editar un Usuario"};
                menu_hor.agregar3("Usuarios", "Agregar", "Editar", toltips);
                btnMenuSuperior();
                asignarBTN(6);
                break;
            case 7:
                toltips = new String[]{"Estadística de Productos", "Estadística de Compra & Venta", "Estadística de Clientes"};
                menu_hor.agregar3("Productos", "Compra & Venta", "Clientes", toltips);
                btnMenuSuperior();
                asignarBTN(7);
                break;
        }
    }

    private void btnMenuSuperior () {
        if (menu_hor.getBotones().length == 2) {
            btnUno = menu_hor.getBotones()[0];
            btnDos = menu_hor.getBotones()[1];
        } else if(menu_hor.getBotones().length == 3){
            btnUno = menu_hor.getBotones()[0];
            btnDos = menu_hor.getBotones()[1];
            btnTres = menu_hor.getBotones()[2];
        }else{
            btnUno = menu_hor.getBotones()[0];
        }
    }

    private void asignarBTN(int op){
        switch(op){
            case 1:
                btnUno.setOnAction(evt->{
                    p_inventario = new PP_Inventario();
                    setCenter(p_inventario);
                });
                btnDos.setOnAction(evt->{
                    p_agregar = new PP_Agregar();
                    setCenter(p_agregar);
                });
                btnTres.setOnAction(evt->{
                    p_editar = new PP_Editar();
                    setCenter(p_editar);
                });
                break;
            case 2:
                btnUno.setOnAction(evt->{
                    p_venta = new P_Venta();
                    setCenter(p_venta);
                });
                btnDos.setOnAction(evt->{
                    p_compra = new P_Compra();
                    setCenter(p_compra);
                });
                break;
            case 3:
                btnUno.setOnAction(evt->{
                    pc_clientes = new PC_Clientes();
                    setCenter(pc_clientes);
                });
                btnDos.setOnAction(evt->{
                    pc_agregar = new PC_Agregar();
                    sp = new ScrollPane(pc_agregar);
                    sp.getStyleClass().clear();
                    setCenter(sp);
                });
                btnTres.setOnAction(evt->{
                    pc_editar = new PC_Editar();
                    sp = new ScrollPane(pc_editar);
                    sp.getStyleClass().clear();
                    setCenter(sp);
                });
                break;
            case 4:
                btnUno.setOnAction(evt->{
                    ppr_proveedor = new PPR_Proveedor();
                    setCenter(ppr_proveedor);
                });
                btnDos.setOnAction(evt->{
                    ppr_agregar = new PPR_Agregar();
                    sp = new ScrollPane(ppr_agregar);
                    sp.getStyleClass().clear();
                    setCenter(sp);
                });
                btnTres.setOnAction(evt->{
                    ppr_editar = new PPR_Editar();
                    sp = new ScrollPane(ppr_editar);
                    sp.getStyleClass().clear();
                    setCenter(sp);
                });
                break;
            case 5:
                btnUno.setOnAction(evt->{
                    pInformesV = new P_Informes_V();
                    setCenter(pInformesV);
                });
                break;
            case 6:
                btnUno.setOnAction(evt->{
                    pu_usuario = new PU_Usuario();
                    setCenter(pu_usuario);
                });
                btnDos.setOnAction(evt->{
                    pu_agregar = new PU_Agregar();
                    sp = new ScrollPane(pu_agregar);
                    sp.getStyleClass().clear();
                    setCenter(sp);
                });
                btnTres.setOnAction(evt->{
                    puEditar = new PU_Editar();
                    sp = new ScrollPane(puEditar);
                    sp.getStyleClass().clear();
                    setCenter(sp);
                });
                break;
            case 7:
                btnUno.setOnAction(evt->{
                    peProductos = new PE_Productos();
                    setCenter(peProductos);
                });
                btnDos.setOnAction(evt->{
                    pEstadisticasCv = new PE_CompraVenta();
                    setCenter(pEstadisticasCv);
                });
                btnTres.setOnAction(evt->{
                    peClientes = new PE_Clientes();
                    setCenter(peClientes);
                });
                break;
        }
    }

}
