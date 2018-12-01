/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;

/**
 *
 * @author Matheus
 */
public class CategoriaService {
    private static ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
    
    public void salvarCategoria(Categoria c){
       if(listaCategorias.size() > 0){ 
        for (Categoria cat: listaCategorias){
            if (cat.getDescricao().equals(c.getDescricao())){
                System.out.println("ENTROU NO IF");
                return;
            }
        }
           System.out.println("SAIU DO FOR");
        listaCategorias.add(c);
    } else
           listaCategorias.add(c);
           
    }

    public List<Categoria> getCategorias() {
        return listaCategorias;
    }
    
    public void removerCategoria(Categoria c){
        listaCategorias.remove(c);
    }

    public Categoria getCategoriaByNome(String value) {
        for(Categoria e: listaCategorias){
           if(e.getDescricao().equals(value))
               return e;
        }
        return null;
    }
    
}
