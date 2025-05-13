package com.coffeandit.transaction_svc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeneficiatioDto implements Serializable {

    private static final long serialVersionUIDLONG = 2806421543985360625L;

    @NotNull(message = "Informar o CPF.")
    private Long CPF;

    @NotNull(message = "Informar o código do banco de destino.")
    private Long codigoBanco;

    @NotNull(message = "Informar a agência de destino.")
    private String agencia;

    @NotNull(message = "Informar a conta de destino.")
    private String conta;

    @NotNull(message = "Informar o nome do Favorecido.")
    private String nomeFavorecido;


}
