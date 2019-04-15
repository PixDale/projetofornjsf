
package modelo;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PopularBD{
    public static void main(String[] args) {
        //Criando Categorias
        Categoria c1 = new Categoria("Bebidas");
        Categoria c2 = new Categoria("Alimentos");
        Categoria c3 = new Categoria("Limpeza");
        //Criando Produtos
        ProdutoExportacao p1 = new ProdutoExportacao("Brasil", "Coca-Cola", c1, 5, 1, 10);
        ProdutoMercadoInterno p2 = new ProdutoMercadoInterno(true, "Bolacha", c2, 10, 2, 20);
        ProdutoExportacao p3 = new ProdutoExportacao("China", "Desinfetante", c3, 15, 3, 30);
        //Clientes
        Cliente cliente1 = new Cliente("Jose", "Rua 1", "48756875", 1, 1000);
        Cliente cliente2 = new Cliente("Maria", "Rua 2","42814697", 0, 2000);
        Cliente cliente3 = new Cliente("Joao", "Rua 3", "41238649", 1, 3000);
        //Data
        Date d = new Date();
        d.getTime();
        //Pedidos
        Pedido ped1 = new Pedido(cliente1, d);
        Pedido ped2 = new Pedido(cliente2, d);
        Pedido ped3 = new Pedido(cliente3, d);
        //Itens pedidos
        ItemPedido ip1 = new ItemPedido(ped1, 3, p1);
        ped1.addItens(ip1);
        
        ItemPedido ip2 = new ItemPedido(ped2, 5, p2);
        ped1.addItens(ip2);
        
        ItemPedido ip3 = new ItemPedido(ped3, 7, p2);
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
