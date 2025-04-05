package com.estoque.controledeestoque.produtoDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para transferir dados de produto entre cliente e servidor")
public class ProdutoDto {

    @Schema(description = "Identificador único do produto", example = "1")
    private long id;

    @Schema(description = "Nome do produto", example = "Teclado Mecânico RGB")
    private String name;

    @Schema(description = "Preço do produto", example = "249.90")
    private double preco;
}
