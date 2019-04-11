/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author 171711
 */
public class PopularBD{
    public static void main(String[] args) {
        //Criando Categorias
        Categoria c1 = new Categoria();
        Categoria c2 = new Categoria();
        Categoria c3 = new Categoria();
        //Descricoes
        c1.setDescricao("Bebidas");
        c2.setDescricao("Alimentos");
        c3.setDescricao("Limpeza");
        //Criando Produtos
        ProdutoExportacao p1 = new ProdutoExportacao();
        ProdutoMercadoInterno p2 = new ProdutoMercadoInterno();
        ProdutoExportacao p3 = new ProdutoExportacao();
        //Categorias
        p1.setCategoria(c1);
        p2.setCategoria(c2);
        p3.setCategoria(c3);
        //Nomes
        p1.setNome("Coca-Cola");
        p2.setNome("Bolacha");
        p3.setNome("Desinfetante");
        //Imposto
        p1.setImposto(10);
        p2.setImposto(20);
        p3.setImposto(30);
        //Preco
        p1.setPreco(5);
        p2.setPreco(10);
        p3.setPreco(15);
        //Especificos
   
        p1.setDestino("Brasil");
        p2.setIncentivo(true);
        p3.setDestino("China");
        //Moedas
        p1.setMoeda(1);
        p2.setMoeda(2);
        p3.setMoeda(3);
        
                //Clientes
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        Cliente cliente3 = new Cliente();
        //Nomes
        cliente1.setNome("Jose");
        cliente2.setNome("Maria");
        cliente3.setNome("Joao");
        //Endereco
        cliente1.setEndereco("Rua 1");
        cliente2.setEndereco("Rua 2");
        cliente3.setEndereco("Rua 3");
        //Limites
        cliente1.setLimite(1000);
        cliente2.setLimite(2000);
        cliente3.setLimite(3000);
        //Status
        cliente1.setStatus(1);
        cliente2.setStatus(0);
        cliente3.setStatus(1);
        //Telefone
        cliente1.setTelefone("48756875");
        cliente2.setTelefone("42814697");
        cliente3.setTelefone("41238649");
        //Pedidos
        Pedido ped1 = new Pedido();
        Pedido ped2 = new Pedido();
        Pedido ped3 = new Pedido();
        //Clientes
        ped1.setCliente(cliente1);
        ped2.setCliente(cliente2);
        ped3.setCliente(cliente3);
        //Data
        Date d = new Date();
        d.getTime();
        ped1.setData(d);
        ped2.setData(d);
        ped3.setData(d);
        //Itens pedidos
        ItemPedido ip1 = new ItemPedido();
        ip1.setProduto(p1);
        ip1.setQuantidade(3);
        ip1.setPedido_IP(ped1);
        ped1.addItens(ip1);
        
        ItemPedido ip2 = new ItemPedido();
        ip2.setProduto(p2);
        ip2.setQuantidade(5);
        ip2.setPedido_IP(ped2);
        ped1.addItens(ip2);
        
        ItemPedido ip3 = new ItemPedido();
        ip3.setProduto(p2);
        ip3.setQuantidade(7);
        ip3.setPedido_IP(ped3);
        ped1.addItens(ip3);
        
        
        cliente1.addPedido(ped1);
        cliente2.addPedido(ped2);
        cliente3.addPedido(ped3);
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjFornPU");
        EntityManager manager = factory.createEntityManager();
        System.out.println(p1.getCategoria().getDescricao()+" "+p1.getNome()+" "+p1.getDestino()+ " " + p1.getCodigo() + " " + p1.getImposto() + " " + p1.getMoeda() + " " + p1.getPreco());
        manager.getTransaction().begin();        
        manager.persist(c1);
        manager.persist(c2);
        manager.persist(c3);
        manager.persist(p1);
        manager.persist(p2);
        manager.persist(p3);
        manager.persist(cliente1);
        manager.persist(cliente2);
        manager.persist(cliente3);
        manager.persist(ped1);
        manager.persist(ped2);
        manager.persist(ped3);
        manager.persist(ip1);
        manager.persist(ip2);
        manager.persist(ip3);
        manager.getTransaction().commit(); 
        manager.close();
    }
}
