/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public void salvarCategoria(){
        servico.salvarCategoria(categoria);
        categoria = new Categoria();
    }
    
    public void removerCategoria(Categoria categoria){
        servico.removerCategoria(categoria);
    }
    
    public ArrayList<Categoria> getCategorias(){
        return servico.getCategorias();
    }
}
