package com.estoque.controledeestoque.produtoController;


import com.estoque.controledeestoque.model.Produto;
import com.estoque.controledeestoque.produtoDto.ProdutoDto;
import com.estoque.controledeestoque.produtoService.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> addProduto(@RequestBody ProdutoDto produtoDto){
        Produto addProduto = produtoService.addProduto(produtoDto);
        return new ResponseEntity<>(addProduto, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProduto(){
        return ResponseEntity.ok(produtoService.getAllProduto());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        return produtoService.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        Produto updatedProduto = produtoService.updateProduto(id, produtoDto);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
