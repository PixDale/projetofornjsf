package service;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import modelo.Categoria;

public class CategoriaService {
    
    List<Categoria> listaCategorias;
        
    public void salvarCategoria(Categoria c){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjFornPU");
        EntityManager em = emf.createEntityManager();
        //Implementar validações se ja existe no banco
        em.getTransaction().begin();        
        em.merge(c);
        em.getTransaction().commit();    
        System.out.println("ID da tarefa: " + c.getId() + " | Desc: "+ c.getDescricao());
        em.close();
        emf.close();
    }

    public List<Categoria> getCategorias() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjFornPU");
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createQuery("Select cat From Categoria cat");
	List<Categoria> categs = q.getResultList();
        em.close();
        emf.close();
        if(listaCategorias == null || listaCategorias.size() != categs.size()) {
            System.out.println("ATUALIZOU A LISTA OFFLINE");
            listaCategorias = categs;
        }
        return listaCategorias;
    }
    
    public void removerCategoria(Categoria c){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjFornPU");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Categoria c WHERE c.id = :param");
        query.setParameter("param", c.getId());
        query.executeUpdate();
        em.getTransaction().commit();
        
        em.close();
        emf.close();
    }

    public Categoria getCategoriaByNome(String value) {
        getCategorias();
        for(Categoria e: listaCategorias){
           if(e.getDescricao().equals(value))
               return e;
        }
        return null;
    }
    
    
    
    
}
