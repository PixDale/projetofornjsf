/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import modelo.Categoria;

/**
 *
 * @author Matheus
 */
public class CategoriaService {
    private static ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
    
    public void salvarCategoria(Categoria c){
        listaCategorias.add(c);
    }

    public ArrayList<Categoria> getCategorias() {
        return listaCategorias;
    }
    
    public void removerCategoria(Categoria c){
        listaCategorias.remove(c);
    }
    
}
