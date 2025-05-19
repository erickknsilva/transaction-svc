package com.coffeandit.transactionsvc.api;

import com.coffeandit.transactionsvc.domain.TransactionBusiness;
import com.coffeandit.transactionsvc.dto.TransactionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/transaction")
@RestController
public class TransactionController {


    private final TransactionBusiness transactionBusiness;

    public TransactionController(TransactionBusiness transactionBusiness) {
        this.transactionBusiness = transactionBusiness;
    }

    @GetMapping("/{agencia}/{conta}")
    public List<TransactionDto> buscarLimiteDiario(@PathVariable("agencia") final Long agencia,
                                                   @PathVariable("conta") final Long conta) {

        return transactionBusiness.findByConta(agencia, conta);
    }

}
