/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorexamen;

import servidorexamen.control.GestorUsuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import servidorexamen.modelo.Articulo;
import servidorexamen.modelo.Modelo;

/**
 *
 * @author Usuario
 *
 */
public class Servidor {

    public Servidor() {
        datos = new Modelo();
        clientes = new ArrayList<>();
        numCliente = 0;
    }
    
     public void insertarArticulo(Articulo nuevoArticulo){
        datos.agregarArticulo(nuevoArticulo);
    }

    public void iniciar() {
        System.out.println("Iniciando aplicación..");
        hiloAtencion = new Thread(new Runnable() {
            @Override
            public void run() {
                atenderClientes();
            }
        });
        hiloAtencion.start();
    }

    public void registrar(Observer nuevoObservador) {
        datos.addObserver(nuevoObservador);
    }

    public void atenderClientes() {
        System.out.println("Atendiendo clientes..");
        try {
            srv = new ServerSocket(7878);

            while (hiloAtencion == Thread.currentThread()) {
                try {
                    skt = srv.accept();
                    numCliente++;
                    System.out.println("Conexión iniciada por el Cliente#" + numCliente);  
                    GestorUsuario nuevoCliente = new GestorUsuario(this, skt, numCliente);
                    clientes.add(nuevoCliente);
                    System.out.println("Agregando: " + nuevoCliente + "Cliente" + numCliente);
                    registrar(nuevoCliente);
                    iniciarCanalComunicacion(nuevoCliente);
                    System.out.println("Canal de comunicación abierto con: " + nuevoCliente + " Cliente#" + numCliente);
                } catch (SocketTimeoutException e) {
                    // No se ha conectado ningún cliente.
                    // Se mantiene esperando conexiones..
                    System.out.println("El servidor se mantiene esperando conexiones ...");
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                skt.close();
                srv.close();
            } catch (IOException ex) {
                System.err.println("Ocurrio un error con la entrada de datos ... ");
            }
            System.out.println("Servidor - Conexión cerrada ...");
        }
    }


    public void iniciarCanalComunicacion(GestorUsuario nuevoCliente) {
        hiloCliente = new Thread(nuevoCliente);
        if (hiloCliente != null) {
            hiloCliente.start();
        }

    }

    public Modelo getDatos() {
        return datos;
    }

    private Modelo datos;
    private Thread hiloAtencion;
    private Thread hiloCliente;
    private ServerSocket srv;
    private Socket skt;
    private ArrayList<GestorUsuario> clientes;
    private int numCliente;
}
