package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Categoria;

public class CategoriaService {
    //EntityManagerFactory ef = Persistence.createEntityManagerFactory("ProjFornPU");
    //EntityManager em = ef.createEntityManager();
    private static ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
    
    /*public void salvarCategoria(Categoria c){
        //Implementar validações
        if(listaCategorias.size() > 0){ 
        for (Categoria cat: listaCategorias){
            if (cat.getDescricao().equals(c.getDescricao())){
                return;
            }
        }
        em.getTransaction().begin();        
        em.persist(c);
        em.getTransaction().commit();    
        System.out.println("ID da tarefa: " + c.getId());
        em.close();
    }
    */
    
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
