/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorexamen.modelo;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Usuario
 */
public class Modelo extends Observable{

    public Modelo() {
        articulos = new ArrayList<>();
        contador = 0;
    }
    
     public void agregarArticulo(Articulo nuevoArticulo){
        articulos.add(nuevoArticulo);
        this.setChanged();
        this.notifyObservers(nuevoArticulo);
    }
    
     public int getContador(){
        return contador++; 
    }
    
    public boolean verificarCategoria(String cate){
        
        for(int i=0;i<articulos.size();i++){
            if(articulos.get(i).getCategoria().getDescripcion().equals(cate)){
                return false;
            }
        }
        return true;
    }
    
    private ArrayList<Articulo>articulos;
    private int contador;
}
