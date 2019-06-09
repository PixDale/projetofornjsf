package managedbean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Cliente;
import org.primefaces.event.RowEditEvent;
import service.ClienteService;

@ManagedBean
@SessionScoped

public class ClienteMB {
    private Cliente cliente = new Cliente();
    private final ClienteService servico = new ClienteService();
    private List<Cliente> listaCli;

    @PostConstruct
    public void init() {
        listaCli = servico.getAll(Cliente.class);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public void salvarCliente(){
        cliente.setNome(cliente.getNome().toUpperCase());
        cliente.setEndereco(cliente.getEndereco().toUpperCase());
        servico.salvar(cliente);
        cliente = new Cliente();
        init();
    }
    
    public void removerCliente(Cliente cliente){
        if(cliente.getPedidos().isEmpty()){
            servico.remover(cliente);
            init();
        }
    }
    
    public List<Cliente> getClientes(){
        return listaCli;
    }
    
    public void onRowEdit(RowEditEvent event) {
        try {
            Cliente c = (Cliente) event.getObject();
            //System.out.println("ID= " + c.getCodigo() + " = Desc= " + c.getNome());
            if (!c.getNome().equals("")) {

                c.setNome(c.getNome().toUpperCase());
                servico.salvar(c);
                init();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            init();
        }
    }
    
  

}
