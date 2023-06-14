package servidor;

import java.io.*;
import java.net.Socket;

public class HiloSkeleton {

    private BufferedReader lector;
    private BufferedWriter escrito;
    private Socket cliente;

    MetodosServidor metodosServidor;

    public HiloSkeleton(Socket cliente) {
        try {
            this.cliente = cliente;
            lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escrito = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void start() {
        run();
    }

    public void enviarMenasaje(String mensaje) throws IOException {
        escrito.write(mensaje + "\n");
        escrito.flush();
    }

    private void run() {
        boolean salir = false;
        metodosServidor = new MetodosServidor(cliente, lector);
        try {
            while (!salir) {
                try {
                    String peticion = lector.readLine();
                    String tarea = "";
                    if (peticion == null) {
                        salir = true;
                        continue;
                    }
                    switch (peticion) {
                        case "USUARIO":
                            tarea = lector.readLine();
                            System.out.println(tarea);
                            metodosServidor.servidorUsuario(tarea);
                            salir = true;
                            break;
                        case "PROVEEDOR":
                            tarea = lector.readLine();
                            metodosServidor.servidorProveedor(tarea);
                            salir = true;
                            break;
                        case "CLIENTE":
                            tarea = lector.readLine();
                            metodosServidor.servidorCliente(tarea);
                            salir = true;
                            break;
                        case "INFORME":

                            salir = true;
                            break;
                        case "CATEGORIA":
                            tarea = lector.readLine();
                            metodosServidor.servidorCategoria(tarea);
                            salir = true;
                            break;
                        case "PRODUCTO":
                            tarea = lector.readLine();
                            metodosServidor.servidorProductos(tarea);
                            salir = true;
                            break;
                        case "VENTA-COMPRA":

                            salir = true;
                            break;
                        default:
                            salir = true;
                            break;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                lector.close();
                escrito.close();
                cliente.close();
            } catch (Exception e) {

            }
        }
    }

}

