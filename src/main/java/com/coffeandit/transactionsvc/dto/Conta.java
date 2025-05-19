package com.coffeandit.transactionsvc.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(of = {"codigoAgencia", "codigoConta"})
public class Conta implements Serializable {

    private static final long serialVersionUIDLONG = 2806412403585360625L;


    @NotNull(message = "Informar o código da Agência.")
    private Long codigoAgencia;
    @NotNull(message = "Informar o código da Conta.")
    private Long codigoConta;
}



