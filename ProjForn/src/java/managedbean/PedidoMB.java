/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package managedbean;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import org.primefaces.event.SelectEvent;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;

/**
 *
 * @author 171711
 */
@ManagedBean
@SessionScoped

public class PedidoMB {
    private Pedido pedido = new Pedido();
    private PedidoService servico = new PedidoService();
    private ClienteService servicocli = new ClienteService();
    private Pedido selectedPedido;
    private ItemPedido itempedido = new ItemPedido();
    private ProdutoService servicopro = new ProdutoService();
    
    public void setSelectedPedido(Pedido p){
        selectedPedido = p;
    }
    
    public Pedido getSelectedPedido(){
        return selectedPedido;
    }
    
    public void removeSelectedPedido(){
        servico.removerPedido(selectedPedido);
        selectedPedido = null;
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
    
      public List<Cliente> completeCliente(String query) {
        List<Cliente> allClientes = servicocli.getClientes();
        List<Cliente> filteredClientes = new ArrayList<Cliente>();
         
        for (int i = 0; i < allClientes.size(); i++) {
            Cliente skin = allClientes.get(i);
            if(skin.getNome().toLowerCase().contains(query)) {
                filteredClientes.add(skin);
            }
        }
         
        return filteredClientes;
    }
    
    public void salvarPedido(){
        try{
        if(servicocli.checkClientes(pedido.getCliente())){
            servico.salvarPedido(pedido);
            pedido = new Pedido();
        }
        
        }
        catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error! Cliente necessario", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void removerPedido(Pedido pedido){
        servico.removerPedido(pedido);
    }
    
    public ArrayList<Pedido> getPedidos(){
        return servico.getPedidos();
    }
    public void inserirProduto(){
        if(!servico.inserirProduto(itempedido)){
            //mostrar mensagem de erro
        }
        itempedido = new ItemPedido();
        System.out.println(servico.getPedidos());
    }

    public ItemPedido getItempedido() {
        return itempedido;
    }

    public void setItempedido(ItemPedido itempedido) {
        this.itempedido = itempedido;
    }
    public void setItempedidoNumero(int numero) {
        itempedido.setNumeropedido(numero);
    }
    public List<Produto> completeProduto(String query) {
        List<Produto> allProdutos = servicopro.getProdutos(0);
        List<Produto> filteredProdutos = new ArrayList<Produto>();

        for (int i = 0; i < allProdutos.size(); i++) {
            Produto skin = allProdutos.get(i);
            if(skin.getNome().toLowerCase().contains(query)) {
                filteredProdutos.add(skin);
            }
        }

        return filteredProdutos;
    }
    

}
