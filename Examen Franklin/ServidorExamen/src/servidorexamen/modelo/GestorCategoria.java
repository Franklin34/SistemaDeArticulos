/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorexamen.modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import servidorexamen.modelo.CategoriaArticulo;

/**
 *
 * @author Usuario
 */
public class GestorCategoria {
       
    public void guardar(CategoriaArticulo tipo) throws Exception {
        Connection cnx =
                GestorBD.obtenerInstancia().obtenerConexion();

        PreparedStatement stm =
                cnx.prepareStatement(COMANDO_INSERTAR);
        stm.clearParameters();
        stm.setInt(1, tipo.getId());
        stm.setString(2, tipo.getDescripcion());
        if (stm.executeUpdate() != 1) {
            throw new Exception();
        }

        GestorBD.obtenerInstancia().cerrarConexion();
    }
    
        public void consultar() throws Exception{                  
       
            Connection cnx =
                GestorBD.obtenerInstancia().obtenerConexion();
            
            Statement stm = cnx.createStatement();
            
            // Contiene los datos recuperados.
            ResultSet rs = stm.executeQuery(COMANDO_CONSULTAR);
        while(rs.next()) {
                int id = rs.getInt("idcategoria");
                String descripcion = rs.getString("descripcion");
                
                System.out.println(String.format(
                        "%d\t%s",
                        id,descripcion));
            }
            

        GestorBD.obtenerInstancia().cerrarConexion();
     
     }
    
        private static final String COMANDO_INSERTAR =
            "INSERT INTO categoria "
            + "(idcategoria, descripcion) "
            + "VALUES(?, ?); ";
    
    private static final String COMANDO_CONSULTAR =
            "SELECT * FROM categoria; ";
}
