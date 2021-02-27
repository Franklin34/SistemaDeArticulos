/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorexamen.control;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorexamen.modelo.Articulo;
import servidorexamen.Servidor;

/**
 *
 * @author Usuario
 */
public class GestorUsuario implements Runnable, Observer {

    public GestorUsuario(Servidor nuevogestor, Socket skt, int num) {
        gestorPrincipal = nuevogestor;
        if (skt != null) {
            socket = skt;
        }
        direccionCliente = socket.getInetAddress();
        nCliente = num;
    }

    @Override
    public void run() {
        try {
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Articulo articulo = (Articulo) entrada.readObject();
                String s = articulo.toString();
                System.out.println("Línea recibida del Cliente#" + nCliente + ": " + s);
                gestorPrincipal.insertarArticulo(articulo);

            }

        } catch (IOException e) {
            System.err.println("Ocurrio un error con la entrada de datos ... ");
        } catch (Exception e) {
            System.err.println(" Se perdió la conexión con el cliente ...");
        } finally {
            try {
                salida.close();
            } catch (IOException ex) {
                Logger.getLogger(GestorUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                entrada.close();
            } catch (IOException ex) {
                System.err.println("Ocurrio un error con la entrada de datos ... ");
            }
            System.out.println("Servidor - Conexión cerrada ...");
        }
    }
    
        @Override
    public String toString() {
        return String.format(
                "Cliente@%s", direccionCliente.getHostName(),nCliente);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try {
            salida.writeObject(arg);
        } catch (IOException ex) {
            Logger.getLogger(GestorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private Servidor gestorPrincipal;
    private InetAddress direccionCliente;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private Socket socket;
    private int nCliente;
    private int nEvento;

}
