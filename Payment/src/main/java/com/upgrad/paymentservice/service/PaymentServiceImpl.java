package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.entity.TransactionDetailsEntity;
import com.upgrad.paymentservice.exception.TransactionNotFoundException;
import com.upgrad.paymentservice.repository.TransactionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    public int getTransactionId(TransactionDetailsEntity transactionDetailsEntity){
        transactionDetailsRepository.save(transactionDetailsEntity);
        return transactionDetailsEntity.getTransactionId();
    }

    @Override
    public TransactionDetailsEntity getTransactionDetails(int id){
        Optional<TransactionDetailsEntity> transactionDetailsEntity = transactionDetailsRepository.findById(id);

        if(transactionDetailsEntity.isPresent()){
            return transactionDetailsEntity.get();
        }
        else{
            throw new TransactionNotFoundException("Transaction Id Not Found");
        }
    }
}
