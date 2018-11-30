/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Categoria;
import service.CategoriaService;

/**
 *
 * @author Matheus
 */
@ManagedBean
@SessionScoped
public class CategoriaMB {
    private Categoria categoria = new Categoria();
    private CategoriaService servico = new CategoriaService();
    private Categoria selectedCategoria;
   
  

        
    public Categoria getCategoria() {
        return categoria;
    }
    public Categoria getSelectedCategoria(){
        return selectedCategoria;
    }
    
    public void setSelectedCategoria(Categoria selected){
        selectedCategoria = selected;
    }
    
    public void removeSelectedCategoria(){
        servico.removerCategoria(selectedCategoria);
        selectedCategoria = null;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void salvarCategoria(){
        if(!categoria.getDescricao().equals("")){
            servico.salvarCategoria(categoria);
            categoria = new Categoria();
        }
    }
    
    public void removerCategoria(Categoria categoria){
        servico.removerCategoria(categoria);
    }
    
    public List<Categoria> getCategorias(){
        return servico.getCategorias();
    }
}
