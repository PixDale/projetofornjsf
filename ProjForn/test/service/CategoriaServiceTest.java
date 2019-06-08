/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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

    /**
     * Test of getCategoriaByNome method, of class CategoriaService.
     */
    @Test
    public void testGetCategoriaByNome() {
        System.out.println("getCategoriaByNome");
        String value = "BEBIDAS";
        CategoriaService instance = new CategoriaService();
        Categoria expResult = new Categoria("BEBIDAS");
        Categoria result = instance.getCategoriaByNome(value);
        assertEquals(expResult.getDescricao(), result.getDescricao());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
}
