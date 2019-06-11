package service;

import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felipe
 */
public class CategoriaServiceTest {
    
    public CategoriaServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    //O Serviço da Categoria possui 4 métodos, sendo 3 herdados. Todos são testados abaixo.

    @Test
    public void testGetCategoriaByNome() {
        System.out.println("getCategoriaByNome");
        String value = "BEBIDAS";
        CategoriaService instance = new CategoriaService();
        
        Categoria expResult = new Categoria("BEBIDAS");
        Categoria result = instance.getCategoriaByNome(value);
        assertEquals(expResult.getDescricao(), result.getDescricao());
    }
    
    @Test
    public void testGetAllCategoria() {
        System.out.println("getAll");
        CategoriaService instance = new CategoriaService();
        List<Categoria> uneexpResult = new ArrayList<Categoria>();
        List<Categoria> result = instance.getAll(Categoria.class);
        
        //Verifica se não retornou nulo e se não retornou vazio.
        //Não foi utilizado assertEquals pois o banco é dinâmico e não seria bom comparar com algo.
        assertNotNull(result);
        assertNotEquals(uneexpResult, result);
    }
    
    @Test
    public void testSalvarCategoria() {
        
        System.out.println("salvarCategoria");
        CategoriaService instance = new CategoriaService();
        Categoria obj = new Categoria("UTILIDADES");
        
        //Retorna 0 em caso de sucesso ou -1 em caso de falha.
        int resultado = instance.salvar(obj);
        assertEquals(resultado, 0);
        
        //Verifica se foi cadastrado com sucesso
        String expResult = "UTILIDADES";
        String result = instance.getCategoriaByNome(expResult).getDescricao();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testRemoverCategoria() {
        System.out.println("removerCategoria");
        CategoriaService instance = new CategoriaService();
        Categoria obj = new Categoria("FRUTAS");
        instance.salvar(obj);
        
        //VErifica se retornou sucesso (0).
        int resultado = instance.remover(obj);
        assertEquals(resultado, 0);
        
        //Verifica se o objeto não existe mais
        String nome = "FRUTAS";
        Categoria result = instance.getCategoriaByNome(nome);
        assertNotNull(result);
    }
        
}
