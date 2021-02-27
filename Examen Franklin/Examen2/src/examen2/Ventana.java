/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import servidorexamen.modelo.Articulo;
import servidorexamen.modelo.CategoriaArticulo;
import servidorexamen.modelo.EstadoArticulo;
import servidorexamen.modelo.GestorArticulo;

/**
 *
 * @author Usuario
 */
public class Ventana extends JFrame {

    FondoPanel fondo = new FondoPanel();
    private JTable tabla;

    public Ventana() {
        super("");
        try {
            cliente = new Cliente(this);
            gP1 = new GestorArticulo();
            articuloUniversal = new Articulo();
            fila = 0;
            tabla = new JTable();
            conectarse();

            setSize(1450, 900);
            setResizable(false);
            setLocationRelativeTo(null);
            this.setContentPane(fondo);

            setDefaultCloseOperation(EXIT_ON_CLOSE);

            setLayout(new BorderLayout());

            ajustarComponentes();
        } catch (Exception ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrar() {
        setVisible(true);
    }
    private JPanel panelPrincipal;
    private JPanel panelDerecha;
    private JButton botonGuardar;

    public void ajustarComponentes() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout());
        JLabel titulo = new JLabel("MANTENIMIENTO DE ARTICULOS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("times new roman", Font.PLAIN, 40));

        panel.add(titulo);
        add(panel, BorderLayout.NORTH);

        panel.add(Box.createVerticalStrut(230));

        ////////////
        panelPrincipal = new JPanel();
        panelPrincipal.setOpaque(false);
        panelPrincipal.setLayout(new FlowLayout());
        ////////////

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));

        JPanel panelNombre = new JPanel();
        panelNombre.setOpaque(false);
        panelNombre.setLayout(new FlowLayout());

        JLabel etiquetaNombre = new JLabel("Nombre: ");
        etiquetaNombre.setForeground(Color.WHITE);
        etiquetaNombre.setFont(new Font("times new roman", Font.PLAIN, 25));

        JTextField cajaNombre = new JTextField(15);

        panelNombre.add(etiquetaNombre);
        panelNombre.add(cajaNombre);

        panel1.add(panelNombre);
        panel1.add(Box.createVerticalStrut(50));

        JPanel panelDescripcion = new JPanel();
        panelDescripcion.setOpaque(false);
        panelDescripcion.setLayout(new FlowLayout());

        JLabel etiquetaDescrip = new JLabel("Descripcion: ");
        etiquetaDescrip.setForeground(Color.WHITE);
        etiquetaDescrip.setFont(new Font("times new roman", Font.PLAIN, 25));

        JTextField cajaDescripcion = new JTextField(15);
        cajaDescripcion.setSize(15, 50);

        cajaDescripcion.setMinimumSize(new Dimension(600, 100));
        cajaDescripcion.setMaximumSize(new Dimension(600, 100));
        cajaDescripcion.setPreferredSize(new Dimension(600, 100));

        panelDescripcion.add(etiquetaDescrip);
        panelDescripcion.add(cajaDescripcion);

        panel1.add(panelDescripcion);
        panel1.add(Box.createVerticalStrut(50));

        JPanel panelPrecio = new JPanel();
        panelPrecio.setOpaque(false);
        panelPrecio.setLayout(new FlowLayout());

        JLabel etiPrecio = new JLabel("Precio: ");
        etiPrecio.setForeground(Color.WHITE);
        etiPrecio.setFont(new Font("times new roman", Font.PLAIN, 25));

        panelPrecio.add(etiPrecio);

        JTextField cajaPrecio = new JTextField(15);

        panelPrecio.add(cajaPrecio);

        panel1.add(panelPrecio);
        panel1.add(Box.createVerticalStrut(50));

        JPanel panelCategoria = new JPanel();
        panelCategoria.setOpaque(false);
        panelCategoria.setLayout(new FlowLayout());

        JLabel etiquetaCategoria = new JLabel("Categoria: ");
        etiquetaCategoria.setForeground(Color.WHITE);
        etiquetaCategoria.setFont(new Font("times new roman", Font.PLAIN, 25));

        String[] categorias = {"salud", "educacion", "musica", "antiguedades", "ferreteria", "ropa", "tecnologia", "limpieza", "navidad"};

        JComboBox cajaCategorias = new JComboBox(categorias);

        cajaCategorias.setMinimumSize(new Dimension(200, 20));
        cajaCategorias.setMaximumSize(new Dimension(200, 20));
        cajaCategorias.setPreferredSize(new Dimension(200, 20));

        panelCategoria.add(etiquetaCategoria);
        panelCategoria.add(cajaCategorias);

        panel1.add(panelCategoria);
        panel1.add(Box.createVerticalStrut(50));

        JPanel panelEstado = new JPanel();
        panelEstado.setOpaque(false);
        panelEstado.setLayout(new FlowLayout());

        JLabel etiEstado = new JLabel("Estado: ");
        etiEstado.setForeground(Color.WHITE);
        etiEstado.setFont(new Font("times new roman", Font.PLAIN, 25));

        panelEstado.add(etiEstado);

        String[] estados = {"disponible", "agotado", "Bajo Inventario"};

        JComboBox cajaEstados = new JComboBox(estados);

        panelEstado.add(cajaEstados);

        panel1.add(panelEstado);
        panel1.add(Box.createVerticalStrut(50));

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new FlowLayout());

        botonGuardar = new JButton("GUARDAR");
        botonGuardar.setEnabled(false);
        botonGuardar.setFont(new Font("times new roman", Font.PLAIN, 25));

        panelBotones.add(botonGuardar);

        JButton botonelimcate = new JButton("ELIMINAR CATEGORIA");
        botonelimcate.setFont(new Font("times new roman", Font.PLAIN, 20));

        panelBotones.add(botonelimcate);

        botonelimcate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                EliminarCategoria eliminar = new EliminarCategoria();
                eliminar.setVisible(true);
            }
        });

        panel1.add(panelBotones);
        panel1.add(Box.createVerticalStrut(50));

        panelPrincipal.add(panel1);
        panelPrincipal.add(Box.createHorizontalStrut(300));

        ////////////////////////////////////////////////////////////////////////////
        panelDerecha = new JPanel();
        panelDerecha.setOpaque(false);
        panelDerecha.setLayout(new BoxLayout(panelDerecha, BoxLayout.PAGE_AXIS));

        JPanel panelBotones2 = new JPanel();
        panelBotones2.setOpaque(false);
        panelBotones2.setLayout(new FlowLayout());

        JButton botonConsultar = new JButton("CONSULTAR");
        botonConsultar.setFont(new Font("times new roman", Font.PLAIN, 25));

        panelBotones2.add(botonConsultar);

        cajaNombre.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {

                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }
        });

        cajaDescripcion.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }
        });

        cajaPrecio.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                if (cajaNombre.getText().equals("") || cajaDescripcion.getText().equals("") || cajaPrecio.getText().equals("")) {
                    botonGuardar.setEnabled(false);
                } else {
                    botonGuardar.setEnabled(true);
                }
            }
        });

        botonConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    gP1.consultar(tabla);
                } catch (Exception ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton botonActualizar = new JButton("ACTUALIZAR");
        botonActualizar.setFont(new Font("times new roman", Font.PLAIN, 25));

        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ActualizarArticulos actualizar = new ActualizarArticulos();
                actualizar.setVisible(true);
            }
        });

        panelBotones2.add(botonActualizar);

        JButton botonElim = new JButton("ELIMINAR");
        botonElim.setFont(new Font("times new roman", Font.PLAIN, 25));

        botonElim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fila = tabla.getSelectedRow();
                DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
                if (fila >= 0) {

                    try {
                        gP1.eliminar(articuloUniversal.getId());
                        articuloUniversal.habilitarBool();
                        cliente.escribirMensajeServidor(articuloUniversal);
                        modelo.removeRow(fila);

                    } catch (Exception ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(fondo, "Seleccione un Articulo");
                }
            }
        });

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int seleccion = tabla.getSelectedRow();
                int id = Integer.parseInt(tabla.getValueAt(seleccion, 0).toString());
                int precio = Integer.parseInt(tabla.getValueAt(seleccion, 3).toString());
                int impuesto = Integer.parseInt(tabla.getValueAt(seleccion, 4).toString());
                int inventario = Integer.parseInt(tabla.getValueAt(seleccion, 7).toString());

                articuloUniversal.setId(id);
                articuloUniversal.setNombre(tabla.getValueAt(seleccion, 1).toString());
                articuloUniversal.setDescripcion(tabla.getValueAt(seleccion, 2).toString());
                articuloUniversal.setPrecio(precio);
                articuloUniversal.setImpuesto(impuesto);

                int idcategoria = 0;
                int idEstado = 0;

                if ("salud".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 1;
                }
                if ("educacion".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 2;
                }
                if ("musica".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 3;
                }
                if ("antiguedades".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 4;
                }
                if ("ferreteria".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 5;
                }
                if ("ropa".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 6;
                }
                if ("tecnologia".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 7;
                }
                if ("limpieza".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 8;
                }
                if ("navidad".equals(tabla.getValueAt(seleccion, 5).toString())) {
                    idcategoria = 9;
                }
                if ("disponible".equals(tabla.getValueAt(seleccion, 6).toString())) {
                    idEstado = 1;
                }
                if ("agotado".equals(tabla.getValueAt(seleccion, 6).toString())) {
                    idEstado = 2;
                }
                if ("Bajo Inventario".equals(tabla.getValueAt(seleccion, 6).toString())) {
                    idEstado = 3;
                }

                articuloUniversal.setCategoria(new CategoriaArticulo(idcategoria, tabla.getValueAt(seleccion, 5).toString()));
                articuloUniversal.setEstado(new EstadoArticulo(idEstado, tabla.getValueAt(seleccion, 6).toString()));
                articuloUniversal.setInventario(inventario);

                fila = seleccion;

            }
        });

        panelBotones2.add(botonElim);

        panelDerecha.add(panelBotones2);
        panelDerecha.add(Box.createVerticalStrut(100));

        DefaultTableModel tabladefault = new DefaultTableModel();
        tabladefault.addColumn("Identificador");
        tabladefault.addColumn("Nombre");
        tabladefault.addColumn("Descripcion");
        tabladefault.addColumn("Precio c/u");
        tabladefault.addColumn("Impuesto");
        tabladefault.addColumn("Categoria");
        tabladefault.addColumn("Estado");
        tabladefault.addColumn("Inventario");

        tabla.setModel(tabladefault);

        JScrollPane scrollpane = new JScrollPane(tabla);
        scrollpane.setMinimumSize(new Dimension(650, 400));
        scrollpane.setMaximumSize(new Dimension(650, 400));
        scrollpane.setPreferredSize(new Dimension(650, 400));

        panelDerecha.add(scrollpane);
        panelPrincipal.add(panelDerecha);

        cajaPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char validar = ke.getKeyChar();

                if (Character.isLetter(validar)) {
                    getToolkit().beep();
                    ke.consume();

                    JOptionPane.showMessageDialog(fondo, "Ingresar solo numeros");
                }
            }
        });

        add(panelPrincipal, BorderLayout.CENTER);

        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    Articulo articulo = new Articulo();

                    int precio = Integer.parseInt(cajaPrecio.getText());

                    articulo.setId(getContador());
                    articulo.setNombre(cajaNombre.getText());
                    articulo.setDescripcion(cajaDescripcion.getText());
                    CategoriaArticulo categoria = new CategoriaArticulo(cajaCategorias.getSelectedIndex() + 1, cajaCategorias.getSelectedItem().toString());
                    articulo.setCategoria(categoria);
                    articulo.setPrecio(precio);
                    articulo.setImpuesto(100);
                    EstadoArticulo estado = new EstadoArticulo(cajaEstados.getSelectedIndex() + 1, cajaEstados.getSelectedItem().toString());
                    articulo.setEstado(estado);
                    articulo.setInventario(10);

                    cliente.escribirMensajeServidor(articulo);

                    gP1.guardar(articulo);

                    cajaNombre.setText("");
                    cajaDescripcion.setText("");
                    cajaPrecio.setText("");

                } catch (Exception ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private GestorArticulo gP1;

    private void conectarse() {
        cliente.iniciar();
    }

    public int getContador() {
        return tabla.getRowCount() + 1;
    }

    public void actualizarTabla(Articulo articulo) {

        if (articulo.getEliminar() == false) {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

            String[] vector = new String[8];

            String idS = String.valueOf(articulo.getId());
            String precioS = String.valueOf(articulo.getPrecio());
            String impuestoS = String.valueOf(articulo.getImpuesto());
            String inventarioS = String.valueOf(articulo.getInventario());

            vector[0] = idS;
            vector[1] = articulo.getNombre();
            vector[2] = articulo.getDescripcion();
            vector[3] = precioS;
            vector[4] = impuestoS;
            vector[5] = articulo.getCategoria().getDescripcion();
            vector[6] = articulo.getEstado().getDescripcion();
            vector[7] = inventarioS;

            modelo.addRow(vector);

            tabla.setModel(modelo);
        } else {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            for (int i = 0; i < tabla.getRowCount(); i++) {
                if (tabla.getValueAt(i, 1).toString().equals(articulo.getNombre())) {
                    modelo.removeRow(i);
                    break;
                }
            }
        }

    }

    private Cliente cliente;
    private Thread hiloCliente;
    private JLabel etiparaver;
    private Articulo articuloUniversal;
    int fila;

    class EliminarCategoria extends JFrame {

        public EliminarCategoria() {
            setSize(400, 100);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new FlowLayout());
            ajustarComponentes1();
        }

        public void ajustarComponentes1() {
            JComboBox caja = new JComboBox();
            try {
                gP1.consultarCate(caja);
            } catch (Exception e) {

            }

            add(caja);

            JButton botonajus = new JButton("Eliminar");
            add(botonajus);
            
            botonajus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    String categoria = caja.getSelectedItem().toString();
                    int idcate =0;
                    if("salud".equals(categoria)){
                        idcate = 1;
                    }
                    if("educacion".equals(categoria)){
                        idcate = 2;
                    }
                    if("musica".equals(categoria)){
                        idcate = 3;
                    }
                    if("antiguedades".equals(categoria)){
                        idcate = 4;
                    }
                    if("ferreteria".equals(categoria)){
                        idcate = 5;
                    }
                    if("ropa".equals(categoria)){
                        idcate = 6;
                    }
                    if("tecnologia".equals(categoria)){
                        idcate = 7;
                    }
                    if("limpieza".equals(categoria)){
                        idcate = 8;
                    }
                    if("navidad".equals(categoria)){
                        idcate = 9;
                    }
                   
                    if (sepuesde(categoria) == true) {
                        caja.removeItem(categoria);
                        try {
                            System.out.println("este es:" + idcate);
                            gP1.eliminarCate(idcate);
                        } catch (Exception e) {
                        }
                    } else {
                        JOptionPane.showMessageDialog(fondo, "No se pudo elminar la categoria...");
                    }

                }
            });
        }

        public boolean sepuesde(String categoria1) {

            for (int i = 0; i < tabla.getRowCount(); i++) {
                String categoria = tabla.getValueAt(i, 5).toString();
                System.out.println(categoria + categoria1);
                if (categoria1.equals(categoria)) {
                    return false;
                }
            }
            return true;
        }

    }

    class ActualizarArticulos extends JFrame {

        public ActualizarArticulos() {
            setSize(400, 100);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLayout(new FlowLayout());
            ajustarComponentes();
        }

        public void ajustarComponentes() {
            ArrayList<String> lista = new ArrayList<>();
            for (int i = 0; i < tabla.getRowCount(); i++) {
                lista.add((String) tabla.getValueAt(i, 1));
            }

            JComboBox caja1 = new JComboBox();
            for (int i = 0; i < lista.size(); i++) {
                caja1.addItem(lista.get(i));
            }

            JComboBox caja2 = new JComboBox();
            for (int i = 0; i < lista.size(); i++) {
                caja2.addItem(lista.get(i));
            }

            add(caja1);
            add(caja2);

            JButton botonActualizar = new JButton("Actualizar");
            add(botonActualizar);

            botonActualizar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        String articulo1 = caja1.getSelectedItem().toString();
                        String articulo2 = caja2.getSelectedItem().toString();
                        Articulo art1 = null;
                        Articulo art2 = null;

                        for (int i = 0; i < tabla.getRowCount(); i++) {
                            if (articulo1 == tabla.getValueAt(i, 1)) {
                                System.out.println("entramos mae!!!");
                                System.out.println(tabla.getValueAt(i, 5).toString());
                                int idcategoria = 0;
                                int idEstado = 0;

                                if ("salud".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 1;
                                }
                                if ("educacion".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 2;
                                }
                                if ("musica".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 3;
                                }
                                if ("antiguedades".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 4;
                                }
                                if ("ferreteria".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 5;
                                }
                                if ("ropa".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 6;
                                }
                                if ("tecnologia".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 7;
                                }
                                if ("limpieza".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 8;
                                }
                                if ("navidad".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 9;
                                }
                                if ("disponible".equals(tabla.getValueAt(i, 6).toString())) {
                                    idEstado = 1;
                                }
                                if ("agotado".equals(tabla.getValueAt(i, 6).toString())) {
                                    idEstado = 2;
                                }
                                if ("Bajo Inventario".equals(tabla.getValueAt(i, 6).toString())) {
                                    idEstado = 3;
                                }

                                int id = Integer.parseInt(tabla.getValueAt(i, 0).toString());
                                int precio = Integer.parseInt(tabla.getValueAt(i, 3).toString());
                                int impuesto = Integer.parseInt(tabla.getValueAt(i, 4).toString());
                                int inventario = Integer.parseInt(tabla.getValueAt(i, 7).toString());

                                art1 = new Articulo(id, articulo1, tabla.getValueAt(i, 2).toString(), precio,
                                        impuesto, new CategoriaArticulo(idcategoria, tabla.getValueAt(i, 5).toString()), new EstadoArticulo(idEstado, tabla.getValueAt(i, 6).toString()),
                                        inventario);
                                System.out.println(art1.toString());
                            }
                        }
                        for (int i = 0; i < tabla.getRowCount(); i++) {
                            if (articulo2 == tabla.getValueAt(i, 1)) {
                                System.out.println("entramos mae!!x2");
                                System.out.println(tabla.getValueAt(i, 5).toString());
                                int idcategoria = 0;
                                int idEstado = 0;

                                if ("salud".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 1;
                                }
                                if ("educacion".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 2;
                                }
                                if ("musica".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 3;
                                }
                                if ("antiguedades".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 4;
                                }
                                if ("ferreteria".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 5;
                                }
                                if ("ropa".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 6;
                                }
                                if ("tecnologia".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 7;
                                }
                                if ("limpieza".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 8;
                                }
                                if ("navidad".equals(tabla.getValueAt(i, 5).toString())) {
                                    idcategoria = 9;
                                }
                                if ("disponible".equals(tabla.getValueAt(i, 6).toString())) {
                                    idEstado = 1;
                                }
                                if ("agotado".equals(tabla.getValueAt(i, 6).toString())) {
                                    idEstado = 2;
                                }
                                if ("Bajo Inventario".equals(tabla.getValueAt(i, 6).toString())) {
                                    idEstado = 3;
                                }

                                int id = Integer.parseInt(tabla.getValueAt(i, 0).toString());
                                int precio = Integer.parseInt(tabla.getValueAt(i, 3).toString());
                                int impuesto = Integer.parseInt(tabla.getValueAt(i, 4).toString());
                                int inventario = Integer.parseInt(tabla.getValueAt(i, 7).toString());

                                art2 = new Articulo(id, articulo2, tabla.getValueAt(i, 2).toString(), precio,
                                        impuesto, new CategoriaArticulo(idcategoria, tabla.getValueAt(i, 5).toString()), new EstadoArticulo(idEstado, tabla.getValueAt(i, 6).toString()),
                                        inventario);
                                System.out.println(art2.toString());
                            }
                        }

                        gP1.actualizar(art1, art2);
                        gP1.actualizar(art2, art1);

                        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

                        String[] info = new String[8];

                        String id = String.valueOf(art1.getId());
                        String precio = String.valueOf(art1.getPrecio());
                        String impuesto = String.valueOf(art1.getImpuesto());
                        String inventario = String.valueOf(art1.getInventario());

                        info[0] = id;
                        info[1] = art1.getNombre();
                        info[2] = art1.getDescripcion();
                        info[3] = precio;
                        info[4] = impuesto;
                        info[5] = art1.getCategoria().getDescripcion();
                        info[6] = art1.getEstado().getDescripcion();
                        info[7] = inventario;

                        for (int i = 0; i < tabla.getRowCount(); i++) {
                            if (tabla.getValueAt(i, 1) == art2.getNombre()) {
                                for (int j = 0; j < tabla.getColumnCount(); j++) {
                                    modelo.setValueAt(info[j], i, j);
                                }
                                break;
                            }
                        }

                        String[] info1 = new String[8];

                        String id1 = String.valueOf(art2.getId());
                        String precio1 = String.valueOf(art2.getPrecio());
                        String impuesto1 = String.valueOf(art2.getImpuesto());
                        String inventario1 = String.valueOf(art2.getInventario());

                        info1[0] = id1;
                        info1[1] = art2.getNombre();
                        info1[2] = art2.getDescripcion();
                        info1[3] = precio1;
                        info1[4] = impuesto1;
                        info1[5] = art2.getCategoria().getDescripcion();
                        info1[6] = art2.getEstado().getDescripcion();
                        info1[7] = inventario1;

                        for (int i = 0; i < tabla.getRowCount(); i++) {
                            if (tabla.getValueAt(i, 1) == art1.getNombre()) {
                                for (int j = 0; j < tabla.getColumnCount(); j++) {
                                    modelo.setValueAt(info1[j], i, j);
                                }
                                break;
                            }
                        }

                        dispose();
                    } catch (Exception ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });
        }

    }

}

class FondoPanel extends JPanel {

    private Image imagen;

    @Override
    public void paint(Graphics g) {
        imagen = new ImageIcon(getClass().getResource("/imagenes/fondo1.png")).getImage();

        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

        setOpaque(false);
        super.paint(g);

    }
}
