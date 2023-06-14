package com.example.interfaz.servidor;

import com.example.interfaz.ModeloClases.Usuario;
import com.example.interfaz.controladores.ControladorLogin;
import com.example.interfaz.vista.emergentes.Alerta;
import com.google.gson.Gson;
import javafx.scene.control.Alert;

import java.io.*;
import java.net.Socket;

public class HiloAntencion extends Thread{


    private BufferedReader lector;
    private BufferedWriter escrito;
    private Socket cliente;
    public HiloAntencion(Socket cliente){
        try {
            this.cliente = cliente;
            lector = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            escrito = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void start(){
        run();
    }

    public void enviarMenasaje(String mensaje) throws IOException {
        escrito.write(mensaje+"\n");
        escrito.flush();
    }

    public void run(){
        boolean salir = false;
        try {
            while(!salir){
                try {
                    String peticion = lector.readLine();
                    if (peticion.equals("INICIARSESION")){

                    }
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
                escrito.close();
                lector.close();
            }
            cliente.close();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void usuario(String usuario, ControladorLogin controladorLoginE){
        try{
            ControladorLogin controladorLogin = controladorLoginE;
            Socket cliente = new Socket("localhost", 8000);
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
            if (usuario1.verificar(local.getContrasena(), usuario1.getContrasena())){
                controladorLogin.entrar();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            Alerta alerta = new Alerta(Alert.AlertType.ERROR, "Sin Conexión al Servidor");
            alerta.show();
        }
    }


}
