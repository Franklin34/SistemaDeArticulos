/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorexamen.modelo;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Articulo implements Serializable{
    
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int impuesto;
    private int inventario;
    private CategoriaArticulo categoria;
    private EstadoArticulo estado;
    private boolean eliminar = false;

    public Articulo(int id, String nombre, String descripcion, int precio, int impuesto, CategoriaArticulo categoria, EstadoArticulo estado,int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.impuesto = impuesto;
        this.categoria = categoria;
        this.estado = estado;
        this.inventario = inventario;
        eliminar = false;
    }

    public Articulo() {
       id =0;
       nombre ="";
       descripcion ="";
       precio =0;
       impuesto =0;
       categoria =null;
       estado=null;
       inventario=0;
       eliminar =false;
    }
    
    public void habilitarBool(){
        eliminar = true;
    }

    public boolean getEliminar() {
        return eliminar;
    }
    

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(int impuesto) {
        this.impuesto = impuesto;
    }

    public int getCategoriaINT() {
        return categoria.getId();
    }

    public void setCategoria(CategoriaArticulo categoria) {
        this.categoria = categoria;
    }

    public int getEstadoINT() {
        return estado.getId();
    }

    public void setEstado(EstadoArticulo estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", impuesto=" + impuesto + ", inventario=" + inventario + ", categoria=" + categoria + ", estado=" + estado + '}';
    }

    

    public CategoriaArticulo getCategoria() {
        return categoria;
    }

    public EstadoArticulo getEstado() {
        return estado;
    }


    
    
    
}
