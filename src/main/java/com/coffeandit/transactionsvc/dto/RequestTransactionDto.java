package com.coffeandit.transactionsvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestTransactionDto extends TransactionDto {

    @JsonIgnore
    private SituacaoEnum situacao;

    private LocalDate data;

}

