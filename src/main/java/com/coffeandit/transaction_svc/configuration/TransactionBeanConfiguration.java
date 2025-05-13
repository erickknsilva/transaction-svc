package com.coffeandit.transaction_svc.configuration;


import com.coffeandit.transaction_svc.validation.EmptyTransactionValidationBean;
import com.coffeandit.transaction_svc.validation.TransactionValidation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnJava.Range.OLDER_THAN;

@Configuration
public class TransactionBeanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSevenTeen.class)
    public TransactionValidation emptyTransactionNewerThanJavaSevenTeenValidation() {
        return new EmptyTransactionValidationBean();
    }

    @ConditionalOnJava(range = OLDER_THAN, value = JavaVersion.SEVENTEEN)
    @Bean
    @ConditionalOnMissingBean
    @Conditional(TransactionEnableNewerThanJavaSevenTeen.class)
    public TransactionValidation emptyTransactionJavaOlderTheanSevenTeenValidation() {
        return new EmptyTransactionValidationBean();
    }

}

