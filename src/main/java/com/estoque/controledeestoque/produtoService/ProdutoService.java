package com.estoque.controledeestoque.produtoService;

import com.estoque.controledeestoque.model.Produto;
import com.estoque.controledeestoque.produtoDto.ProdutoDto;
import com.estoque.controledeestoque.produtoRepository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    public Produto addProduto (ProdutoDto produtoDto){
        Produto produto = new Produto();
        produto.setName(produtoDto.getName());;
        produto.setPreco(produtoDto.getPreco());

        return produtoRepository.save(produto);
    }

    public List<Produto> getAllProduto(){
        return produtoRepository.findAll();
    }
    public Optional<Produto> getProdutoById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto updateProduto(Long id, ProdutoDto produtoDto) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setName(produtoDto.getName());
                    produto.setPreco(produtoDto.getPreco());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    public void deleteProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
