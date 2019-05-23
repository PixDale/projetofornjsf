package managedbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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

@ManagedBean
@SessionScoped

public class PedidoMB {
    private Pedido pedido = new Pedido();
    private final PedidoService servicopedido = new PedidoService();
    private final ClienteService servicocliente = new ClienteService();
    private Pedido selectedPedido;
    private ItemPedido itempedido = new ItemPedido();
    private final ProdutoService servicoproduto = new ProdutoService();
    private static int codigogeral = 0;
    private int numPedidoIP;
    private List<Pedido> listaPed;
    
    @PostConstruct
    public void init() {
        listaPed = servicopedido.getAll(Pedido.class); // Call the DB here.
    }
    
    public void setSelectedPedido(Pedido p){
        selectedPedido = p;
    }
    
    public Pedido getSelectedPedido(){
        return selectedPedido;
    }
    
    public void removeSelectedPedido(){
        servicopedido.remover(selectedPedido);
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
        List<Cliente> allClientes = servicocliente.dao.getAll(Cliente.class);
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
            servicopedido.salvar(pedido);
            servicocliente.addPedidoToCliente(pedido);
            pedido = new Pedido();
        }
        
        }
        catch(NullPointerException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error! Cliente necessario", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void removerPedido(Pedido pedido){
        if(servicopedido.remover(pedido)==1)
            if(servicocliente.removePedidoOfCliente(pedido))
                return;
        //TODO Exibir algum erro
    }
    
    public List<Pedido> getPedidos(){
        return listaPed;
    }
    public Pedido getPedidoByNum(int num) {
        List<Pedido> peds = getPedidos();
        for (Pedido pe : peds) {
            if(pe.getNumero() == num) {
                return pe;
            }
        }
        return null;
    }

    public void inserirProduto(){
        if(itempedido.getQuantidade()>0){
            pedido = getPedidoByNum(this.numPedidoIP);
            if(pedido != null) {
                itempedido.setPedido_IP(pedido);
                if(!servicopedido.inserirProduto(itempedido)){
                //mostrar mensagem de erro
                }
            }
        }
        itempedido = new ItemPedido();
        pedido = new Pedido();
    }

    public ItemPedido getItempedido() {
        return itempedido;
    }

    public void setItempedido(ItemPedido itempedido) {
        this.itempedido = itempedido;
    }
    public void setItempedidoNumero(int numero) {
        //itempedido.setPedido_IP();
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

    public int getNumPedidoIP() {
        return numPedidoIP;
    }

    public void setNumPedidoIP(int numPedidoIP) {
        this.numPedidoIP = numPedidoIP;
    }
    
}
