package modelo;
import java.util.ArrayList;

public class Cliente {
    private int codigo;
    private String nome;
    private String endereco;
    private String telefone;
    private int status;
    private double limite;
    private ArrayList<Integer> pedidos = new ArrayList();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public ArrayList<Integer> getPedidos() {
        return pedidos;
    }

    public void addPedido(int numPedido) {
        pedidos.add(numPedido);
    }
    
    
    
}
