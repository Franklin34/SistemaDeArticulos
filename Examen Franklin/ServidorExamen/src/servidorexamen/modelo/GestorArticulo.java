/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorexamen.modelo;

import servidorexamen.modelo.GestorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import servidorexamen.modelo.Articulo;

/**
 *
 * @author Usuario
 */
public class GestorArticulo {

    public Articulo recuperar() {
        Articulo resultado = null;
        return resultado;
    }

    public GestorArticulo() {
    }

    public void guardar(Articulo nuevoarticulo) throws Exception {
        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        PreparedStatement stm
                = cnx.prepareStatement(COMANDO_INSERTAR);
        stm.clearParameters();
        stm.setInt(1, nuevoarticulo.getId());
        stm.setString(2, nuevoarticulo.getNombre());
        stm.setString(3, nuevoarticulo.getDescripcion());
        stm.setInt(4, nuevoarticulo.getPrecio());
        stm.setInt(5, nuevoarticulo.getImpuesto());
        stm.setInt(6, nuevoarticulo.getInventario());

        stm.setInt(7, nuevoarticulo.getCategoriaINT());
        System.out.println("aqui: " + nuevoarticulo.getCategoriaINT());
        stm.setInt(8, nuevoarticulo.getEstadoINT());

        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        GestorBD.obtenerInstancia().cerrarConexion();
    }

    public void consultar(JTable tabla) throws Exception {

        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        Statement stm = cnx.createStatement();
        // Contiene los datos recuperados.
        ResultSet rs = stm.executeQuery(COMANDO_CONSULTAR);
        tabla.removeAll();
        while (rs.next()) {
            int id = rs.getInt("idarticulo");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            int precio = rs.getInt("precio");
            int impuesto = rs.getInt("impuesto");
            int inventario = rs.getInt("inventario");
            int idCate = rs.getInt("idCate");
            int idEstado = rs.getInt("idEstado");

            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

            // DefaultTableModel modelo1 = new DefaultTableModel();
            String[] vector = new String[8];

            String idS = String.valueOf(id);
            String precioS = String.valueOf(precio);
            String impuestoS = String.valueOf(impuesto);
            String inventarioS = String.valueOf(inventario);

            vector[0] = idS;
            vector[1] = nombre;
            vector[2] = descripcion;
            vector[3] = precioS;
            vector[4] = impuestoS;

            if (idCate == 1) {
                vector[5] = "salud";
            }
            if (idCate == 2) {
                vector[5] = "educacion";
            }
            if (idCate == 3) {
                vector[5] = "musica";
            }
            if (idCate == 4) {
                vector[5] = "antiguedades";
            }
            if (idCate == 5) {
                vector[5] = "ferreteria";
            }
            if (idCate == 6) {
                vector[5] = "ropa";
            }
            if (idCate == 7) {
                vector[5] = "tecnologia";
            }
            if (idCate == 8) {
                vector[5] = "limpieza";
            }
            if (idCate == 9) {
                vector[5] = "navidad";
            }

            if (idEstado == 1) {
                vector[6] = "agotado";
            }

            if (idEstado == 2) {
                vector[6] = "disponible";
            }
            if (idEstado == 3) {
                vector[6] = "Bajo Inventario";
            }

            vector[7] = inventarioS;

            modelo.addRow(vector);

            tabla.setModel(modelo);

            System.out.println(String.format(
                    "%d\t%s",
                    id, nombre, descripcion, precio, impuesto, inventario, idCate, idEstado));

        }

        GestorBD.obtenerInstancia().cerrarConexion();

    }

    public void consultarCate(JComboBox caja) throws Exception {

        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        Statement stm = cnx.createStatement();

        // Contiene los datos recuperados.
        ResultSet rs = stm.executeQuery(COMANDO_CONSULTAR1);
        while (rs.next()) {
            int id = rs.getInt("idcategoria");
            String descripcion = rs.getString("descripcion");
            
            caja.addItem(descripcion);
            

            System.out.println(String.format(
                    "%d\t%s",
                    id, descripcion));
        }

        GestorBD.obtenerInstancia().cerrarConexion();

    }

    public void actualizar(Articulo actual, Articulo nueva) throws Exception {

        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        PreparedStatement stm
                = cnx.prepareStatement(COMANDO_ACTUALIZAR);

        stm.setString(1, nueva.getNombre());
        stm.setString(2, nueva.getDescripcion());
        stm.setInt(3, nueva.getPrecio());
        stm.setInt(4, nueva.getImpuesto());
        stm.setInt(5, nueva.getInventario());
        stm.setInt(6, nueva.getCategoriaINT());
        stm.setInt(7, nueva.getEstadoINT());
        stm.setInt(8, actual.getId());

        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        GestorBD.obtenerInstancia().cerrarConexion();
    }

    public void eliminar(int idarticulo) throws Exception {
        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        PreparedStatement stm
                = cnx.prepareStatement(COMANDO_ELIMINAR);
        stm.clearParameters();
        stm.setInt(1, idarticulo);
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        GestorBD.obtenerInstancia().cerrarConexion();
    }

    public void eliminarCate(int idcategoria) throws Exception {
        Connection cnx
                = GestorBD.obtenerInstancia().obtenerConexion();

        PreparedStatement stm
                = cnx.prepareStatement(COMANDO_ELIMINAR1);
        stm.clearParameters();
        stm.setInt(1, idcategoria);
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        GestorBD.obtenerInstancia().cerrarConexion();
    }

    private static final String COMANDO_INSERTAR
            = "INSERT INTO articulo "
            + "(idarticulo, nombre, descripcion, precio,impuesto,inventario,idCate,idEstado) "
            + "VALUES(?, ?, ?,?,?, ?, ?,?); ";

    private static final String COMANDO_CONSULTAR
            = "SELECT * FROM articulo; ";

    private static final String COMANDO_CONSULTAR1
            = "SELECT * FROM categoria; ";

    private static final String COMANDO_ACTUALIZAR
            = "UPDATE articulo SET nombre=?, descripcion=?,precio=?,impuesto=?,inventario=?,idCate=?,idEstado=?  WHERE idarticulo=?; ";

    private static final String COMANDO_ELIMINAR
            = "DELETE FROM articulo "
            + "WHERE idarticulo=?; ";

    private static final String COMANDO_ELIMINAR1
            = "DELETE FROM categoria "
            + "WHERE idcategoria=?; ";
}
