package com.coffeandit.transaction_svc.repositort;

import com.coffeandit.transaction_svc.dto.TransactionDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository
        extends MongoRepository<TransactionDto, UUID> {


}
