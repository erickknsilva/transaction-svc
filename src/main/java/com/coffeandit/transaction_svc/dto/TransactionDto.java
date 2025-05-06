package com.coffeandit.transaction_svc.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class TransactionDto {

    @Id
    private UUID uuid;
    @NotNull(message = "Informar o valor da transação")
    private BigDecimal valor;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;

    @NotNull(message = "Informar a conta de origem da transação")
    @Valid
    private Conta conta;

    @Valid
    private BeneficiatioDto beneficiario;

    @NotNull(message = "Informar o tipo da transação")

    private TipoTransacao tipoTransacao;

    private SituacaoEnum situacao;

}
