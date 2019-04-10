package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.Categoria;

public class CategoriaService implements Serializable {
    EntityManagerFactory ef = Persistence.createEntityManagerFactory("ProjFornPU");
        
    public void salvarCategoria(Categoria c){
        EntityManager em = ef.createEntityManager();
        //Implementar validações se ja existe no banco
        em.getTransaction().begin();        
        em.persist(c);
        em.getTransaction().commit();    
        System.out.println("ID da tarefa: " + c.getId());
        em.close();
    }

    public List<Categoria> getCategorias() {
        EntityManager em = ef.createEntityManager();
        
        Query q = em.createQuery("Select cat From Categoria cat");
	List<Categoria> resultListCategorias = q.getResultList();
        em.close();
        return resultListCategorias;
    }
    
    public void removerCategoria(Categoria c){
        EntityManager em = ef.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Categoria c WHERE c.id = :param");
        query.setParameter("param", c.getId());
        query.executeUpdate();
        em.getTransaction().commit();
        
        em.close();
    }

    public Categoria getCategoriaByNome(String value) {
        List<Categoria> listaCategorias = getCategorias();
        for(Categoria e: listaCategorias){
           if(e.getDescricao().equals(value))
               return e;
        }
        return null;
    }
    
    
    
    
}
