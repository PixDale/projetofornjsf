package service;

import DAO.ProdutoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Produto;
import modelo.ProdutoExportacao;
import modelo.ProdutoMercadoInterno;

public class ProdutoService extends BaseService <Produto>{
    
    public ProdutoService(){
        dao = new ProdutoDAO();
    }
    
    //Retorna a lista de produtos do tipo t (1-Exportação; 2-MercadoInterno).
    public List getProdutos(int t){
        List<Produto> listaProduto = getAll(Produto.class);
        if (t == 1) {
            List<ProdutoExportacao> listaux = new ArrayList();
            listaProduto.stream()
                        .filter((p) -> (p instanceof ProdutoExportacao))
                        .forEachOrdered((p) -> {
                listaux.add((ProdutoExportacao) p);
            });
            return listaux;
        } else if (t == 2) {
            ArrayList<ProdutoMercadoInterno> listaux = new ArrayList();
            listaProduto.stream()
                        .filter((p) -> (p instanceof ProdutoMercadoInterno))
                        .forEachOrdered((p) -> listaux.add((ProdutoMercadoInterno) p));
            return listaux;
        }
        return listaProduto;
    }
    
    public Produto getProdutoByNome(String str) {
        List<Produto> listaProduto = getAll(Produto.class);
        for (Produto c : listaProduto) {
            if(c.getNome().equals(str)) {
                return c;
            }
        }
        return null;
    }
    //Verifica se existe algum produto cadastrado com a categoria c.
    public boolean checkCategoria(Categoria c){
        List<Produto> listaProduto = getAll(Produto.class);
        return !listaProduto.stream()
                            .noneMatch((p) -> (c.equals(p.getCategoria())));
    }
}