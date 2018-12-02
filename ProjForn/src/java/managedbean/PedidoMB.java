/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package managedbean;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import org.primefaces.context.RequestContext;
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

public class PedidoMB implements Serializable{
    private Pedido pedido = new Pedido();
    private PedidoService servicopedido = new PedidoService();
    private ClienteService servicocliente = new ClienteService();
    private Pedido selectedPedido;
    private ItemPedido itempedido = new ItemPedido();
    private ProdutoService servicoproduto = new ProdutoService();
    private static int codigogeral = 0;
    
    public void setSelectedPedido(Pedido p){
        selectedPedido = p;
    }
    
    public Pedido getSelectedPedido(){
        return selectedPedido;
    }
    
    public void removeSelectedPedido(){
        servicopedido.removerPedido(selectedPedido);
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
        
    }
    
      public List<Cliente> completeCliente(String query) {
        List<Cliente> allClientes = servicocliente.getClientes();
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
        if(servicocliente.checkClientes(pedido.getCliente())){
            pedido.setNumero(++codigogeral);
            servicopedido.salvarPedido(pedido);
            servicocliente.addPedidoToCliente(pedido.getNumero(), pedido.getCliente().getCodigo());
            pedido = new Pedido();
        }
        
        }
        catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error! Cliente necessario", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void removerPedido(Pedido pedido){
        servicopedido.removerPedido(pedido);
    }
    
    public List<Pedido> getPedidos(){
        return servicopedido.getPedidos();
    }

    public void inserirProduto(){
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println(itempedido.getNumeropedido()+" - "+itempedido.getQuantidade()+" - "+itempedido.getProduto().getNome());
        if(itempedido.getQuantidade()>0){
        if(!servicopedido.inserirProduto(itempedido)){
            //mostrar mensagem de erro
        }
        }
        itempedido = new ItemPedido();
        System.out.println(servicopedido.getPedidos().get(0));
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
        List<Produto> allProdutos = servicoproduto.getProdutos(0);
        List<Produto> filteredProdutos = new ArrayList<>();

        for (int i = 0; i < allProdutos.size(); i++) {
            Produto skin = allProdutos.get(i);
            if(skin.getNome().toLowerCase().contains(query)) {
                filteredProdutos.add(skin);
            }
        }
        return filteredProdutos;
    }
    public List<ItemPedido> getItemPedidoByNumPedido (int num){
        List<Pedido> listapedido = getPedidos();
        for (Pedido p : listapedido) {
            if (p.getNumero() == num) {
                return p.getItensPedido();
            }
        }
        return null;
    }
    public List<Pedido> getPedidosByArrayNumPedido (List<Integer> num){
        List<Pedido> listapedido = getPedidos();
        List<Pedido> pedidosFiltrados = new ArrayList();
        
        for (Pedido p : listapedido) {
            for (Integer i : num) {
                if (i == p.getNumero()) {
                    pedidosFiltrados.add(p);                    
                }
            }
        }
        return pedidosFiltrados;
    }
    public void viewProdutos() {
        Map<String,Object> options = new HashMap<>();
        options.put("modal", true);
        Map<String, List<String>> params = new HashMap<>();
        //params.put("selectedDepartments", Arrays.asList(selectedDeptsAsParam));
        RequestContext.getCurrentInstance().openDialog("dfitens", options, null);
    }
    
    public float valorTotalUnitario(ItemPedido ped){
        return (float) (ped.getQuantidade()*(ped.getProduto().getPreco()+ped.getProduto().getImposto()));
    }
    
    public float impostoTotal(ItemPedido ped){
        return (float) (ped.getQuantidade()*(ped.getProduto().getImposto()));
    }
    
    public float valorTotal(Pedido ped){
        float aux=0;
        for(ItemPedido p : ped.getItensPedido()){
            aux += (p.getQuantidade()*(p.getProduto().getPreco()+p.getProduto().getImposto()));
        }
        System.out.println(aux);
        return aux;
    }
    

}
