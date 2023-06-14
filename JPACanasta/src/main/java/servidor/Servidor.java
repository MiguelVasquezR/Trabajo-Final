package servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

   public void initServer(){
       try{
           ServerSocket server = new ServerSocket(8000, 50);
           List<HiloSkeleton> listaHilos = new ArrayList<>();
           boolean salir = false;
           while(!salir){
               Socket cliente = server.accept();
               System.out.println("Cliente: "+cliente);
               HiloSkeleton hiloSkeleton = new HiloSkeleton(cliente);
               listaHilos.add(hiloSkeleton);
               hiloSkeleton.start();
           }
           server.close();
       }catch (Exception e){
           System.out.println("Fallo la comunicación");
       }
   }

}
