package com.estoque.controledeestoque.produtoController;

import com.estoque.controledeestoque.model.Produto;
import com.estoque.controledeestoque.produtoDto.ProdutoDto;
import com.estoque.controledeestoque.produtoService.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Cadastrar novo produto")
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso")
    @PostMapping
    public ResponseEntity<Produto> addProduto(@RequestBody ProdutoDto produtoDto){
        Produto addProduto = produtoService.addProduto(produtoDto);
        return new ResponseEntity<>(addProduto, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<List<Produto>> getAllProduto(){
        return ResponseEntity.ok(produtoService.getAllProduto());
    }

    @Operation(summary = "Buscar produto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        return produtoService.getProdutoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar produto existente")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody ProdutoDto produtoDto) {
        Produto updatedProduto = produtoService.updateProduto(id, produtoDto);
        return ResponseEntity.ok(updatedProduto);
    }

    @Operation(summary = "Excluir produto por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}
