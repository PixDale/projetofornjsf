package managedbean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Categoria;
import modelo.Cliente;
import modelo.ItemPedido;
import modelo.Pedido;
import modelo.Produto;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;

@ManagedBean
@SessionScoped
public class PedidoMB {

    //Services
    private final PedidoService servicopedido = new PedidoService();
    private final ClienteService servicocliente = new ClienteService();
    private final ProdutoService servicoproduto = new ProdutoService();

    private List<Pedido> listaPed;
    private ItemPedido itempedido = new ItemPedido();
    private Pedido pedido = new Pedido();
    private int numPedidoIP;

    @PostConstruct
    public void init() {
        listaPed = servicopedido.getAll(Pedido.class); // Consulta o banco aqui.
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

    //GrowlMB.success("Categoria removida com sucesso");
    public List<Cliente> autocompleteCliente(String query) {
        List<Cliente> allClientes = servicocliente.dao.getAll(Cliente.class);
        return allClientes
                .stream()
                .filter((p) -> p.getNome()
                .toUpperCase()
                .contains(query.toUpperCase()))
                .collect(Collectors.toList());
    }

    public void salvarPedido() {
        try {
            if (servicocliente.checkClientes(pedido.getCliente())) {
                servicopedido.salvar(pedido);
                servicocliente.addPedidoToCliente(pedido);
                pedido = new Pedido();
                GrowlMB.success("Pedido salvo com sucesso");
            }
        } catch (NullPointerException e) {
            GrowlMB.error("Não foi possível salvar o pedido");
            System.out.println(e.getMessage());
        } finally {
            init();
        }
    }

    public void removerPedido(Pedido pedido) {
        try {
            if (servicopedido.remover(pedido) == 1) {
                if (servicocliente.removePedidoOfCliente(pedido)) {
                    GrowlMB.success("Pedido removido com sucesso");
                }
            }
        } catch (Exception e) {
            GrowlMB.error("Não foi possivel remover o pedido");
            System.out.println(e.getMessage());
        } finally {
            init();
        }

    }

    public List<Pedido> getPedidos() {
        return listaPed;
    }

    public Pedido getPedidoByNum(int num) {
        init();
        for (Pedido pe : listaPed) {
            if (pe.getNumero() == num) {
                return pe;
            }
        }
        return null;
    }

    public void inserirProduto() {
        if (itempedido.getQuantidade() > 0) {
            pedido = getPedidoByNum(this.numPedidoIP);
            if (pedido != null) {
                itempedido.setPedido_IP(pedido);
                if (!servicopedido.inserirProduto(itempedido)) {
                    //mostrar mensagem de erro
                }
            }
        }
        System.out.println("----------------------RENOVOU CAMPOS");
        numPedidoIP = 0;
        itempedido = new ItemPedido();
        pedido = new Pedido();
    }

    public ItemPedido getItempedido() {
        return itempedido;
    }

    public void setItempedido(ItemPedido itempedido) {
        this.itempedido = itempedido;
    }

    public List<Produto> autocompleteProduto(String query) {
        List<Produto> allProdutos = servicoproduto.getAll(Produto.class);
        return allProdutos
                .stream()
                .filter((p) -> p.getNome()
                .toUpperCase()
                .contains(query.toUpperCase()))
                .collect(Collectors.toList());
    }

    public List<ItemPedido> getItemPedidoByNumPedido(int num) {
        List<Pedido> listapedido = getPedidos();
        for (Pedido p : listapedido) {
            if (p.getNumero() == num) {
                return p.getItenspedido();
            }
        }
        return null;
    }

    public float getValorTotalItemPedido(ItemPedido ip) {
        return (float) (ip.getQuantidade() * (ip.getProduto().getPreco() + ip.getProduto().getImposto()) * (ip.getProduto().getMoeda()));
    }

    public float getImpostoTotal(ItemPedido ip) {
        return (float) (ip.getQuantidade() * (ip.getProduto().getImposto()));
    }

    public float getValorTotal(Pedido ped) {
        try {
            float aux = 0;
            List<ItemPedido> lista = ped.getItenspedido();
            System.out.println("LISTA DO VALOR TOTAL-------------------------" + lista.toString());
            for (ItemPedido p : lista) {
                aux += getValorTotalItemPedido(p);
            }
            System.out.println(aux);
            return aux;
        } catch (Exception e) {
            System.out.println("----------------ERROR-LOG-----" + e);
            return 0;
        }

    }

    public int getNumPedidoIP() {
        return numPedidoIP;
    }

    public void setNumPedidoIP(int numPedidoIP) {
        this.numPedidoIP = numPedidoIP;
    }

    public void onRowEdit(RowEditEvent event) {
        try {
            Pedido p = (Pedido) event.getObject();
            System.out.println("ID= " + p.getNumero());
            if (!(p.getNumero() == 0)) {
                servicopedido.salvar(p);
                init();
                GrowlMB.success("Pedido alterado com sucesso.");
            }
        } catch (Exception e) {
            GrowlMB.error("Erro ao alterar o pedido.");
            System.out.println(e.getMessage());
        } finally {
            init();
        }
    }
}
