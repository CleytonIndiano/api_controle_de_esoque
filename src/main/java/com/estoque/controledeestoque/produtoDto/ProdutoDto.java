package com.estoque.controledeestoque.produtoDto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ProdutoDto {
    @Id
    private long id;
    private String name;
    private double preco;

}
