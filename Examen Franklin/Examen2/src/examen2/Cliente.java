/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorexamen.modelo.Articulo;


/**
 *
 * @author Usuario
 */
public class Cliente implements Runnable {

    public Cliente(Ventana ventana) {
        jugador = ventana;
    }

    @Override
    public void run() {
        int puerto = 7878;
        String servidor = "localhost";
        try {
            skt = new Socket(servidor, puerto);

            entrada = new ObjectInputStream(skt.getInputStream());
            salida = new ObjectOutputStream(skt.getOutputStream());

            
            while (true) {                
                // Espera la respuesta..
                //String c = entrada.readObject().toString();
                Articulo art = (Articulo)entrada.readObject();
                //System.out.println(art.getNombre());
                System.out.println("Confirmación: " + art.getNombre());
                jugador.actualizarTabla(art);
            }                        

        } catch (IOException e) {
            System.err.println("Ocurrio un error con la entrada de datos ... ");
        }catch (Exception e) {
            System.err.println(" Ocurrio un error con la respuesta del servidor ...");
        }
        finally{
            try {
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                entrada.close();
                skt.close();
            } catch (IOException ex) {
               System.err.println("Ocurrio un error con la entrada de datos ... "); 
            }
            System.out.println("Cliente - Conexión cerrada ...");
            
        }
    }
    
     public void iniciar(){
        hiloCliente = new Thread(this);
        if(hiloCliente!=null){
            hiloCliente.start();
        }
    }

    public void escribirMensajeServidor(Object obj) {
        try {
            salida.writeObject(obj);
        } catch (Exception ex) {
            System.out.println("aqui es");
        }
    }


    //Atributos
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private Socket skt;
    private Thread hiloCliente;
    private Ventana jugador;
}
